package com.trddiy.by664365842;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class RestrictItem implements Listener {
	private Core plugin;

	public RestrictItem(Core plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	// 定义颜色
	ChatColor white = ChatColor.WHITE;
	ChatColor red = ChatColor.RED;
	ChatColor green = ChatColor.GREEN;
	ChatColor gold = ChatColor.GOLD;

	@EventHandler
	public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {// 玩家改变手中物品
		Player player = event.getPlayer();
		if (player.isOp() == false) {// 检查是否有admin权限
			int a = 0;
			for (int i = 0; i < 9; i++) {// 扫描玩家快捷栏
				PlayerInventory inv = player.getInventory();
				ItemStack[] content = inv.getContents();
				ItemStack itemstackn = content[i];
				if (itemstackn == null)// 如果快捷栏没有东西,跳过
					continue;
				// player.sendMessage(String.valueOf(itemstackn.getTypeId()));
				if (player.hasPermission("itemmanager.item."
						+ itemstackn.getTypeId())) {// 检查是否有持有此物品的权限
					a++;
					// 删除物品
					inv.setItem(i, null);

					boolean removed = false;

					for (int j = 9; j < 36; j++) {
						if (inv.getItem(j) == null) {
							inv.setItem(j, itemstackn);
							removed = true;
							break;
						}
					}

					if (removed == false) {
						Location pl = player.getLocation();
						World world = player.getWorld();
						world.dropItemNaturally(pl, itemstackn);
					}

					plugin.sendtoplayer(
							player,
							"你还没有受过训练使用 " + gold
									+ Getname.getname(itemstackn.getTypeId()));
				}

			}
			if (a >= 1) {
				plugin.sendtoplayer(player, "由于职业限制, " + a + "个mod武器已从物品栏移除");
				a = 0;
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {// 当玩家做出动作时 同上
		Player player = event.getPlayer();
		if (!player.hasPermission("itemmanager.admin")) {
			int a = 0;
			for (int i = 0; i < 9; i++) {
				PlayerInventory inv = player.getInventory();
				ItemStack[] content = inv.getContents();
				ItemStack itemstackn = content[i];
				if (itemstackn == null)
					continue;
				// player.sendMessage(String.valueOf(itemstackn.getTypeId()));
				if (player.hasPermission("itemmanager.item."
						+ itemstackn.getTypeId())) {
					a++;
					inv.setItem(i, null);

					boolean removed = false;

					for (int j = 9; j < 36; j++) {
						if (inv.getItem(j) == null) {
							inv.setItem(j, itemstackn);
							removed = true;
							break;
						}
					}

					if (removed == false) {
						Location pl = player.getLocation();
						World world = player.getWorld();
						world.dropItemNaturally(pl, itemstackn);
					}
					plugin.sendtoplayer(
							player,
							"你还没有受过训练使用 " + gold
									+ Getname.getname(itemstackn.getTypeId()));
					player.updateInventory();
				}
			}

			if (a >= 1) {
				plugin.sendtoplayer(player, "由于职业限制, " + a + "个mod武器已从物品栏移除");
				a = 0;
			}
		}
	}

	@EventHandler
	public void onplayerpickupitemevent(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		Item item = event.getItem();
		if (!player.hasPermission("itemmanager.admin")) {
			// player.sendMessage(String.valueOf(itemstackn.getTypeId()));
			if (player.hasPermission("itemmanager.item."
					+ item.getItemStack().getTypeId())) {
				Location pl = player.getLocation();
				World world = player.getWorld();
				world.dropItemNaturally(pl, item.getItemStack());
				item.remove();
				plugin.sendtoplayer(
						player,
						"你还没有受过训练使用 "
								+ gold
								+ Getname.getname(item.getItemStack()
										.getTypeId()));
				event.setCancelled(true);
			}
		}
	}
}