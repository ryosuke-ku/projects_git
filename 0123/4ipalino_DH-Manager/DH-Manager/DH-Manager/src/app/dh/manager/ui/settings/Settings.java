package app.dh.manager.ui.settings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import app.dh.manager.R;


/**
 * Zeigt je nach Android Version die Einstellungen an.
 * 
 * @author Andreas
 * 
 */
public class Settings extends PreferenceActivity
{
	protected Method	_MethodLoadHeaders	= null;
	protected Method	_MethodHasHeaders	= null;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle aSavedState)
	{
		// onBuildHeaders() will be called during super.onCreate()
		try
		{
			_MethodLoadHeaders = getClass().getMethod("loadHeadersFromResource", int.class, List.class);
			_MethodHasHeaders = getClass().getMethod("hasHeaders");
		}
		catch (NoSuchMethodException e)
		{
		}
		super.onCreate(aSavedState);
		if (!checkAndroidVersion())
		{
			addPreferencesFromResource(R.xml.settingsbeispielseite1);
			addPreferencesFromResource(R.xml.settingsbeispielseite2);
			addPreferencesFromResource(R.xml.settingsbeispielseite3);
		}
	}

	@Override
	public void onBuildHeaders(List<Header> aTarget)
	{
		try
		{
			_MethodLoadHeaders.invoke(this, new Object[] { R.xml.settingsheader, aTarget });
		}
		catch (IllegalArgumentException e)
		{
		}
		catch (IllegalAccessException e)
		{
		}
		catch (InvocationTargetException e)
		{
		}
	}

	/**
	 * Kontrolliert ob Verion 11 oder neuer Verwendet wird.
	 * 
	 * @return Gibt "false" zurück wenn weine Version vor v11 verwendet wird.
	 *         Ansonsten wird überprüft ob Header benutzt werden.
	 */
	public boolean checkAndroidVersion()
	{
		if (_MethodHasHeaders != null && _MethodLoadHeaders != null)
		{
			try
			{
				return (Boolean) _MethodHasHeaders.invoke(this);
			}
			catch (IllegalArgumentException e)
			{
			}
			catch (IllegalAccessException e)
			{
			}
			catch (InvocationTargetException e)
			{
			}
		}
		return false;
	}

	@TargetApi(11)
	static public class SettingsFragment extends PreferenceFragment
	{
		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			Context context = getActivity().getApplicationContext();
			int intSettingsResource = context.getResources().getIdentifier(getArguments().getString("pref-resource"), "xml", context.getPackageName());
			addPreferencesFromResource(intSettingsResource);
		}
	}
}