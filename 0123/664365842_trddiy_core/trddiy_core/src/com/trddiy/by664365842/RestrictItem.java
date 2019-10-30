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

	// ������ɫ
	ChatColor white = ChatColor.WHITE;
	ChatColor red = ChatColor.RED;
	ChatColor green = ChatColor.GREEN;
	ChatColor gold = ChatColor.GOLD;

	@EventHandler
	public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {// ��Ҹı�������Ʒ
		Player player = event.getPlayer();
		if (player.isOp() == false) {// ����Ƿ���adminȨ��
			int a = 0;
			for (int i = 0; i < 9; i++) {// ɨ����ҿ����
				PlayerInventory inv = player.getInventory();
				ItemStack[] content = inv.getContents();
				ItemStack itemstackn = content[i];
				if (itemstackn == null)// ��������û�ж���,����
					continue;
				// player.sendMessage(String.valueOf(itemstackn.getTypeId()));
				if (player.hasPermission("itemmanager.item."
						+ itemstackn.getTypeId())) {// ����Ƿ��г��д���Ʒ��Ȩ��
					a++;
					// ɾ����Ʒ
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
							"�㻹û���ܹ�ѵ��ʹ�� " + gold
									+ Getname.getname(itemstackn.getTypeId()));
				}

			}
			if (a >= 1) {
				plugin.sendtoplayer(player, "����ְҵ����, " + a + "��mod�����Ѵ���Ʒ���Ƴ�");
				a = 0;
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {// �������������ʱ ͬ��
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
							"�㻹û���ܹ�ѵ��ʹ�� " + gold
									+ Getname.getname(itemstackn.getTypeId()));
					player.updateInventory();
				}
			}

			if (a >= 1) {
				plugin.sendtoplayer(player, "����ְҵ����, " + a + "��mod�����Ѵ���Ʒ���Ƴ�");
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
						"�㻹û���ܹ�ѵ��ʹ�� "
								+ gold
								+ Getname.getname(item.getItemStack()
										.getTypeId()));
				event.setCancelled(true);
			}
		}
	}
}