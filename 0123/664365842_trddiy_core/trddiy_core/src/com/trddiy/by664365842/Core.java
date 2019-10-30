package com.trddiy.by664365842;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.garbagemule.MobArena.MobArena;
import com.garbagemule.MobArena.framework.ArenaMaster;
import com.herocraftonline.heroes.Heroes;
import com.herocraftonline.heroes.characters.Hero;

public class Core extends JavaPlugin {
	public static Boolean debug = false;
	private CommandListener cmd;
	public Heroes hr;
	public PluginChannel pcl;
	public ArenaMaster am;
	public static Configuration config;
	public Core plugin;
	private Logger log;
	public static Permission permission = null;
	public static Economy economy = null;

	public void onEnable() {
		checkvault();// ����Ƿ���vault
		setupPermissions();// ����vault Ȩ��
		setupEconomy();// ����vault ����
		final Boolean a;
		final Boolean c;
		this.log = getLogger();
		config = getConfig();
		config.options().copyDefaults(true);// ���������ļ�
		saveConfig();
		a = config.getBoolean("Onlyoneweapon");// ��ȡ�����ļ�
		c = config.getBoolean("RestrictItem");
		if (a == true) {
			new Onlyoneweapon(this);
		}
		if (c == true) {
			new RestrictItem(this);
		}
		new SignListener(this);
		pcl = new PluginChannel(this);// ����Ƶ��
		sendtoserver("�������޶�: " + a);
		sendtoserver("��������: " + c);
		setupHeroes();// ����heroes���
		setupMobArenaListener();// ����ma
		new HeroesListener(this);
		cmd = new CommandListener(this);
		getCommand("trd").setExecutor(cmd);
		sendtoserver(" v" + getDescription().getVersion() + "by:"
				+ getDescription().getAuthors() + "������!");
	}

	public void onDisable() {
		sendtoserver(" v" + getDescription().getVersion() + " by:"
				+ getDescription().getAuthors() + " �ѽ���!");
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = getServer()
				.getServicesManager().getRegistration(
						net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null) {
			permission = permissionProvider.getProvider();
		}
		return (permission != null);
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer()
				.getServicesManager().getRegistration(
						net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}
		return (economy != null);
	}

	private void checkvault() {
		Plugin p = getServer().getPluginManager().getPlugin("Vault");
		if (p == null) {
			sendtoserver("����:δ�ҵ�Vault���");
			sendtoserver("Trddiy���Ĳ��������.");
			setEnabled(false);
		} else {
			return;
		}
	}

	// װ��ma���
	public void setupMobArenaListener() {
		MobArena maPlugin = (MobArena) getServer().getPluginManager()
				.getPlugin("MobArena");
		if (maPlugin != null) {
			am = maPlugin.getArenaMaster();
			new MobArenaListener(this);
			sendtoserver("Mobarena��ع���������");
		}
	}

	// װ��heroes���
	public void setupHeroes() {
		Heroes hr = (Heroes) getServer().getPluginManager().getPlugin("Heroes");
		if (hr != null) {
			this.hr = hr;
			sendtoserver("Heroes��ع���������");
		}
	}

	public Heroes getheroesplugin() {
		return hr;
	}

	public PluginChannel getpcl() {
		return pcl;
	}

	/**
	 * ����ҷ��ʹ�ǰ׺����Ϣ
	 * 
	 * @param p
	 *            �����
	 * @param s
	 *            ��Ҫ���͵��ַ���
	 */
	public void sendtoplayer(Player p, String s) {
		String title = ChatColor.GREEN + "[" + ChatColor.GOLD + "Trd���Ĳ��"
				+ ChatColor.GREEN + "] " + ChatColor.WHITE;
		p.sendMessage(title + s);
	}

	/**
	 * �����̨���ʹ�ǰ׺����Ϣ
	 * 
	 * @param s
	 *            ��Ҫ���͵��ַ���
	 */
	public void sendtoserver(String s) {
		String title = "[Trd���Ĳ��]";
		this.log.info(title + s);
	}

	public Hero getHero(Player p) {
		return getheroesplugin().getCharacterManager().getHero(p);
	}
}
