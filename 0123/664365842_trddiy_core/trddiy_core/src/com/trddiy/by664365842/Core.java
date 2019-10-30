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
		checkvault();// 检查是否有vault
		setupPermissions();// 启动vault 权限
		setupEconomy();// 启动vault 经济
		final Boolean a;
		final Boolean c;
		this.log = getLogger();
		config = getConfig();
		config.options().copyDefaults(true);// 拷贝设置文件
		saveConfig();
		a = config.getBoolean("Onlyoneweapon");// 获取设置文件
		c = config.getBoolean("RestrictItem");
		if (a == true) {
			new Onlyoneweapon(this);
		}
		if (c == true) {
			new RestrictItem(this);
		}
		new SignListener(this);
		pcl = new PluginChannel(this);// 加载频道
		sendtoserver("单武器限定: " + a);
		sendtoserver("武器限制: " + c);
		setupHeroes();// 加载heroes相关
		setupMobArenaListener();// 加载ma
		new HeroesListener(this);
		cmd = new CommandListener(this);
		getCommand("trd").setExecutor(cmd);
		sendtoserver(" v" + getDescription().getVersion() + "by:"
				+ getDescription().getAuthors() + "已启用!");
	}

	public void onDisable() {
		sendtoserver(" v" + getDescription().getVersion() + " by:"
				+ getDescription().getAuthors() + " 已禁用!");
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
			sendtoserver("错误:未找到Vault插件");
			sendtoserver("Trddiy核心插件禁用中.");
			setEnabled(false);
		} else {
			return;
		}
	}

	// 装载ma相关
	public void setupMobArenaListener() {
		MobArena maPlugin = (MobArena) getServer().getPluginManager()
				.getPlugin("MobArena");
		if (maPlugin != null) {
			am = maPlugin.getArenaMaster();
			new MobArenaListener(this);
			sendtoserver("Mobarena相关功能已启用");
		}
	}

	// 装载heroes相关
	public void setupHeroes() {
		Heroes hr = (Heroes) getServer().getPluginManager().getPlugin("Heroes");
		if (hr != null) {
			this.hr = hr;
			sendtoserver("Heroes相关功能已启用");
		}
	}

	public Heroes getheroesplugin() {
		return hr;
	}

	public PluginChannel getpcl() {
		return pcl;
	}

	/**
	 * 向玩家发送带前缀的信息
	 * 
	 * @param p
	 *            是玩家
	 * @param s
	 *            是要发送的字符串
	 */
	public void sendtoplayer(Player p, String s) {
		String title = ChatColor.GREEN + "[" + ChatColor.GOLD + "Trd核心插件"
				+ ChatColor.GREEN + "] " + ChatColor.WHITE;
		p.sendMessage(title + s);
	}

	/**
	 * 向控制台发送带前缀的信息
	 * 
	 * @param s
	 *            是要发送的字符串
	 */
	public void sendtoserver(String s) {
		String title = "[Trd核心插件]";
		this.log.info(title + s);
	}

	public Hero getHero(Player p) {
		return getheroesplugin().getCharacterManager().getHero(p);
	}
}
