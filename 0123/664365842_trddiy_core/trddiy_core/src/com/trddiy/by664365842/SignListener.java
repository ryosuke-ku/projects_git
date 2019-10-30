package com.trddiy.by664365842;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener {
	// �������
	private Core plugin;
	private ItemForXP ifx;
	private static String name = ChatColor.BLUE + "[����һ�]";
	// ����
	public SignListener(Core plugin) {
		this.plugin = plugin;
		ifx = new ItemForXP(plugin);
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {// �������ʹ��
		Player player = event.getPlayer();
		// plugin.sendtoplayer(player,Integer.toString(event.getClickedBlock().getTypeId()));
		if (event.getClickedBlock() != null) {
			if (event.getClickedBlock().getTypeId() == 63) {
				Sign sign = (Sign) event.getClickedBlock().getState();
				if (sign.getLine(0).equals(name)) {
					if (Core.permission.has(player, "trd.sign.use")) {
						int amount = Integer.valueOf(sign.getLine(1));
						ifx.getItem(player, amount);
					} else {
						plugin.sendtoplayer(player, ChatColor.RED
								+ "����ʹ�ô����ӵ�Ȩ��!");
					}
				}
			}
		}
	}

	@EventHandler
	public void onplayerplaceblockevent(SignChangeEvent event) {// ������Ӵ���
		Player p = event.getPlayer();
		String[] lines = event.getLines();
		if(lines[0].compareTo("[����һ�]")!=0){
			return;
		}
		if (Core.permission.has(p, "trd.sign.create")) {
			if (lines[0].equals("[����һ�]")) {
				if (lines[1].matches("\\d+")) {
					event.setLine(0, name);
					plugin.sendtoplayer(p, "�������ӳɹ�!");
				} else {
					plugin.sendtoplayer(p, "��ʽ����!�ڶ���ӦΪ������!");
				}
			}
		} else {
			plugin.sendtoplayer(p, ChatColor.RED + "Ȩ�޲���!");
			event.setCancelled(true);
		}
	}
}