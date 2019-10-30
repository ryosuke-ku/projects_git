package add.dh.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.widget.Toast;


/**
 * Bearbeitet alle verbindungen mit dem Dualis system und liefert die HTML
 * Seiten zurück
 * 
 * @author 4ipalino
 */
public class Online
{
	private static final String	DUALIS_URL	                                   = "https://dualis.dhbw.de/";
	private static final String	DUALIS_LOGIN_URL	                           = DUALIS_URL + "scripts/mgrqcgi";
	private static final String	DUALIS_COOKIE	                               	= "cnsc=" + generateCookie();
	private static final String	DUALIS_ARGUMENTE_REGEXP	                       	= "ARGUMENTS=([^,]+),";
	private static final String	DUALIS_APP_URL	                               	= DUALIS_LOGIN_URL + "?APPNAME=CampusNet&PRGNAME=";
	private static final String	DUALIS_KALENDAR	                               	= "MONTH&ARGUMENTS=";
	private static final String	DUALIS_KALENDER_MONATSANSICHT_ARGUMENTS_SUFFIX	= ",-N000031,-A";	                                                                                                                                                                                                                                                                                                                                                                                                                                                                      // ",-A,-A,-N1";
	private static final String	DUALIS_RESULTS	                               	= "STUDENT_RESULT&ARGUMENTS=";
	private static final String	DUALIS_RESULTS_SUFFIX	                       	= ",-N000310,";
	private static final String	CHARSET	                                      	= "UTF-8";
	private static final String	DUALIS_KALENDER_REGEXP	                      	= "<a title=\"([^\"]+)\"";
	private static final String	DUALIS_RESULT_REGEXP	                       	= "<tr> <td class=\"tbdata\">([^\"]*)</td> <td class=\"tbdata\">([^\"]*)</td> <td class=\"tbdata\" style=\"text-align:right;\">([^\"]*)</td> <td class=\"tbdata\" style=\"text-align:right;\">([^\"]*)</td> <td class=\"tbdata\" style=\"text-align:right;\">([^\"]*)</td> <td class=\"tbdata\" style=\"text-align:center;\">([^\"]*)</td> <td class=\"tbdata\" style=\"text-align:center;\"><img src=\"/img/individual/([^\"]*).gif\" alt=\"([^\"]*)\" title=\"([^\"]*)\" /></td> </tr>";
	private static final int	ANZAHL_LOGIN_VERSUCHE	                       	= 5;
	private String	            _Args;

	
	/**
	 * Login-Funktion. Meldet sich per URLConnection am Dualis Server an. Dabei
	 * werden folgende Variablen verwendet: DUALIS_LOGIN_URL --> dies ist die
	 * URL zu der der POST Request geschickt wird DUALIS_COOKIE --> Unser
	 * Dauercookie. Es sieht so aus .... als wäre dualis scheiße!
	 * DUALIS_ARGUMENTE_REGEXP --> dieser Reguläre Ausdruck filter die Argumente
	 * für den Return Wert aus dem Header.
	 * 
	 * @param username
	 *            Benutzername
	 * @param passwort
	 *            Passwort
	 * @return die Argumente die nun immer im Get HEADER übergeben werden
	 *         müssen, um eingelogt zu bleiben. null, falls login fehlgeschlagen
	 *         hat (kann verschiedene Gründe haben).
	 */
	public String login(String username, String passwort)
	{

		String strCookie = null;
		String data = "usrname=" + username + "&pass=" + passwort + "&APPNAME=CampusNet&PRGNAME=LOGINCHECK&ARGUMENTS=clino%2Cusrname%2Cpass%2Cmenuno%2Cpersno%2Cbrowser%2Cplatform&clino=000000000000001&menuno=000000&persno=00000000&browser=&platform=";
		URLConnection connection;
		try
		{
			connection = connect(DUALIS_LOGIN_URL);
			connection.setDoOutput(true);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
			outputStreamWriter.write(data);
			outputStreamWriter.close();
			String header = getHeader(connection);
			Pattern pattern = Pattern.compile(DUALIS_ARGUMENTE_REGEXP, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(header);
			if (matcher.find())
			{
				strCookie = matcher.group(1);
			}
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return strCookie; // unser Argument String
	}

	
	/**
	 * Läd Termine aus Dualis in die Datenbank
	 * 
	 * @param username
	 *            Benutzername
	 * @param password
	 *            Passwort
	 * @param wieVieleMonate
	 *            Gibt an wieviele Monate heruntergeladen werden sollen
	 * @param context
	 *            Context der Activity auf der Fehler ausgegeben werden sollen
	 * @return
	 */
	public String getVorlesungen(String username, String password, int wieVieleMonate, Context context)
	{
		int i = 0;
		String vorlesungen = "";
		do
		{
			_Args = login(username, password);
			i++;
		} while (_Args == null && i < ANZAHL_LOGIN_VERSUCHE);
		if (_Args == null)
		{
			Toast.makeText(context, "Login fehlgeschlagen, überprüfen sie ihre Logindaten", Toast.LENGTH_SHORT).show();
		}
		else
		{
			vorlesungen = getCalenderMonth(wieVieleMonate);
		}
		return vorlesungen;
	}

	/**
	 * Läd die prüfungsergebnise aus dem Dualis in die Datenbank
	 * 
	 * @param username
	 *            Benutzername
	 * @param password
	 *            Password
	 * @param context
	 *            Context der Activity auf der Fehler ausgegeben werden sollen
	 */
	public String getResults(String username, String password, Context context)
	{
		int i = 0;
		String results = "";
		do
		{
			_Args = login(username, password);
			i++;
		} while (_Args == null && i < ANZAHL_LOGIN_VERSUCHE);
		if (_Args == null)
		{
			Toast.makeText(context, "Login fehlgeschlagen, überprüfen sie ihre Logindaten", Toast.LENGTH_SHORT).show();
		}
		else
		{
			results = getResults();

			results = results.replaceAll("\\s\\s+", " ");
		}
		return results;
	}

	/**
	 * Gibt die Argumente zurück
	 * 
	 * @return args
	 */
	public String getArgs()
	{
		if (_Args == null)
		{
			_Args = "";
		}
		return _Args;
	}

	/**
	 * Gibt den Header der URLConnection zurück
	 * 
	 * @param connection
	 *            URLConnection
	 * @return header
	 */
	private String getHeader(URLConnection connection)
	{
		String ret = "";
		for (String field : connection.getHeaderFields().keySet())
		{
			ret += field + " : " + connection.getHeaderFields().get(field);
		}
		return ret;
	}

	/**
	 * Methode um die Prüfungsergebnise herunterzuladen
	 * 
	 * @param args
	 *            Argumente der Verbindung
	 * @return Liefert die Ergebnisse in einem String zurück
	 */
	private String getResults()
	{
		String ret = null;
		StringBuilder stringBuilder = new StringBuilder();
		try
		{
			URLConnection conn = connect(DUALIS_APP_URL + DUALIS_RESULTS + _Args + DUALIS_RESULTS_SUFFIX);
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;

			while ((line = rd.readLine()) != null)
			{
				stringBuilder.append(line);
			}
			rd.close();
		}
		catch (Exception e)
		{
			ret = null;
		}
		ret = stringBuilder.toString();

		return ret;
	}

	/**
	 * Liefert den HTML-Code der Monatsansicht aus dem Dualis zurück
	 * 
	 * @param args
	 *            Argumente
	 * @param wieVieleMonate
	 *            Gibt an wieviele Monate heruntergeladen werden sollen
	 * @return Liefert die Vorlesungen in einem String
	 */
	private String getCalenderMonth(int wieVieleMonate)
	{
		String ret = null;
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i <= wieVieleMonate; i++)
		{
			try
			{
				String datum = getDatum(i - 1);
				URLConnection conn = connect(DUALIS_APP_URL + DUALIS_KALENDAR + _Args + ",-N000019" + datum + DUALIS_KALENDER_MONATSANSICHT_ARGUMENTS_SUFFIX);
				BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;

				while ((line = rd.readLine()) != null)
				{
					stringBuilder.append(line);
				}
				rd.close();

			}
			catch (Exception e)
			{
				ret = null;
			}
		}
		ret = stringBuilder.toString();
		return ret;
	}

	
	/**
	 * @param surl
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private URLConnection connect(String surl) throws MalformedURLException, IOException
	{
		URL url = new URL(surl);
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHARSET);
		conn.setRequestProperty("User-Agent", "DH Manager by Andreas H. and Michael V.");
		conn.setRequestProperty("Cookie", DUALIS_COOKIE);
		return conn;
	}

	/**
	 * Generiert einen zufälligen cookie
	 * 
	 * @return Cookie
	 */
	private static String generateCookie()
	{
		String cookie = "";
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 16; i++)
		{
			stringBuilder.append(new Double(Math.random() * 99).intValue());
		}
		cookie = stringBuilder.toString();
		return cookie;
	}

	/**
	 * Liefert das Datum für den entsprechenden Monat um die richtige Ansicht im
	 * Dualis zu bekommen
	 * 
	 * @param wieVielMonate
	 * @return Gibt das Datum in wieVielMonate zurück
	 */
	private String getDatum(int wieVielMonate)
	{
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		String curMonth = sdfMonth.format(cal.getTime());
		String curYear = sdfYear.format(cal.getTime());
		int curMonthInt = Integer.parseInt(curMonth);
		curMonthInt = curMonthInt + wieVielMonate;
		if (curMonthInt >= 13)
		{
			curMonthInt = curMonthInt - 12;
			int curYearInt = Integer.parseInt(curYear);
			curYearInt = curYearInt + 1;
			curYear = String.valueOf(curYearInt);
		}
		curMonth = String.valueOf(curMonthInt);
		if (curMonthInt <= 9)
		{
			curMonth = "0" + curMonthInt;
		}
		String ret = ",-A01." + curMonth + "." + curYear;
		return ret;
	}
}
