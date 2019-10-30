package com.trddiy.by664365842;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Onlyoneweapon implements Listener {
	private Core plugin;

	public Onlyoneweapon(Core plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	ChatColor white = ChatColor.WHITE;
	ChatColor red = ChatColor.RED;
	ChatColor green = ChatColor.GREEN;
	ChatColor gold = ChatColor.GOLD;

	@EventHandler
	public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {
		Player p = event.getPlayer();
		if (p.isOp() == false) {
			PlayerInventory inv = p.getInventory();
			int num = 0;
			int s = 0;
			for (int i = 0; i < 9; i++) {
				ItemStack a = inv.getItem(i);
				if (a != null) {
					switch (a.getTypeId()) {
					case 476:
					case 477:
					case 499:
					case 506:
					case 513:
						if (!p.hasPermission("itemmanager.item."
								+ a.getTypeId()))
							num++;
						break;
					default:
						break;
					}
				}
				if (num >= 2) {
					inv.clear(i);
					num = 1;
					s = 1;
				}
			}
			if (s == 1) {
				plugin.sendtoplayer(p, "吹箭,火枪等武器不允许在物品栏放多个!已清除多余物品!");
				s = 0;
			}
		}
	}
	/*
	 * @EventHandler public void onPlayerInteractEvent(PlayerInteractEvent
	 * event){ Player p = event.getPlayer(); if(p.isOp() == false){
	 * PlayerInventory inv = p.getInventory(); int num = 0; int s = 0; for(int i
	 * = 0;i < 9; i++){ ItemStack a = inv.getItem(i); if(a != null){
	 * switch(a.getTypeId()){ case 476: case 477: case 499: case 506: case 513:
	 * num++; break; default: break; } } if(num >= 2){ inv.clear(i); num = 1; s
	 * = 1; } } if(s == 1){ p.sendMessage(title+"吹箭,火枪等武器不允许在物品栏放多个!已清除多余物品!");
	 * s = 0; } } }
	 */
}
