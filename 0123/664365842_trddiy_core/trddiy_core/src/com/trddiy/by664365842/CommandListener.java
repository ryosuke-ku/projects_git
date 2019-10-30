package com.trddiy.by664365842;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor {
	private Core plugin;
	public ItemForXP ifx;

	public CommandListener(Core plugin) {
		this.plugin = plugin;
		this.ifx = new ItemForXP(plugin);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			String arg1 = args[0];
			// 重载命令
			if (arg1.equals("reload")) {
				plugin.reloadConfig();
				plugin.sendtoserver("设置已由控制台重载.");
				return true;
			}
			if (arg1.equals("debug")) {
				Boolean debug = Core.debug;
				if (debug == false) {
					Core.debug = true;
				} else {
					Core.debug = false;
				}
				plugin.sendtoserver("调试状态更改为: " + Core.debug);
				return true;
			}
			plugin.sendtoserver("错误!本命令不支持控制台使用!");
			return true;
		} else {
			Player p = (Player) sender;
			if (args.length != 0) {
				String arg1 = args[0];
				if (arg1.equals("reload")
						&& Core.permission.has(p, "trd.reload")) {
					plugin.reloadConfig();
					plugin.sendtoserver("设置已由玩家 " + p.getName() + " 重载");
					plugin.sendtoplayer(p, "设置已重载");
				}
				if (arg1.equals("debug") && Core.permission.has(p, "trd.debug")) {
					Boolean debug = Core.debug;
					if (debug == false) {
						Core.debug = true;
					} else {
						Core.debug = false;
					}
					plugin.sendtoserver("调试状态由 " + p.getName() + " 更改为: "
							+ Core.debug);
					plugin.sendtoplayer(p, "调试状态已改为: " + Core.debug);
				}
				if (arg1.equals("exp") && Core.permission.has(p, "trd.exp")) {
					// plugin.sendtoplayer(p,"抱歉,功能未开放");
					if (args.length >= 2 && args[1] != null) {
						ifx.getItem(p, Integer.valueOf(args[1]));
					} else {
						plugin.sendtoplayer(p, "你无此命令的权限/你没有输入要兑换的数额.");
					}
				}
				if (arg1.equals("help")) {
					plugin.sendtoplayer(p, "===== Trddiy核心插件帮助 =====");
					plugin.sendtoplayer(p, "/trd exp 进行经验兑换");
					plugin.sendtoplayer(p, "/trd help 打开帮助界面");
					plugin.sendtoplayer(p, "/trd xiaodai 获得小呆的节操");
				}
				if (arg1.equals("xiaodai")) {
					plugin.sendtoplayer(p, "抱歉,功能未开放.");
				}
				if (arg1.equals("bcast")) {
					if (args.length >= 2 && args[1] != null
							&& Core.permission.has(p, "trd.bcast")) {
						String s = args[1];
						for (Player p2 : plugin.getServer().getOnlinePlayers()) {
							plugin.getpcl().sendbroadcast(p2, s);
						}
					} else {
						plugin.sendtoplayer(p, "你无此命令的权限/发送信息不能为空");
					}
				}
			} else {
				plugin.sendtoplayer(p, "===== Trddiy核心插件帮助 =====");
				plugin.sendtoplayer(p, "/trd exp 进行经验兑换");
				plugin.sendtoplayer(p, "/trd help 打开帮助界面");
				plugin.sendtoplayer(p, "/trd xiaodai 获得小呆的节操");
			}
			return true;
		}
	}
}
