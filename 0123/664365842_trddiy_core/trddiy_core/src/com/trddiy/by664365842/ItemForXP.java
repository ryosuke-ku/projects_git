package com.trddiy.by664365842;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.herocraftonline.heroes.characters.Hero;

public class ItemForXP {
	private Core plugin;

	public ItemForXP(Core plugin) {
		this.plugin = plugin;
	}

	public void getItem(Player p, int a) {
		int id = Core.config.getInt("ItemForXP.Item");
		int xp = Core.config.getInt("ItemForXP.XP");
		ItemStack is = p.getItemInHand();
		if (is.getTypeId() != id) {
			plugin.sendtoplayer(p, "�����õ���Ʒ���ܶһ�����!");
			return;
		}
		if (is.getAmount() < a) {
			plugin.sendtoplayer(p, "���ϵĶ�������!");
			return;
		}
		if (a < 0) {
			plugin.sendtoplayer(p, "����һ���Ʒ������δ����!");
			return;
		}
		if (a == 0) {
			plugin.sendtoplayer(p, "�������������!");
			return;
		}
		Hero h = plugin.getheroesplugin().getCharacterManager().getHero(p);
		if (h.getLevel(h.getHeroClass()) == h.getHeroClass().getMaxLevel()) {
			plugin.sendtoplayer(p, "��ǰְҵ������!");
			return;
		}
		if (is.getAmount() - a == 0) {
			p.getInventory().removeItem(is);
		} else {
			is.setAmount(is.getAmount() - a);
		}
		h.addExp(xp * a, h.getHeroClass(),h.getPlayer().getLocation());
		h.syncExperience();
		plugin.sendtoplayer(p, "��ʹ�� " + ChatColor.GREEN + a + ChatColor.WHITE
				+ " �� " + ChatColor.GREEN
				+ is.getType().toString().toLowerCase() + ChatColor.WHITE
				+ " �һ��� " + ChatColor.GREEN + xp * a + ChatColor.WHITE
				+ " �㾭��.");
		if(Core.debug == true)
			plugin.sendtoserver(p.getDisplayName()+" "+xp*a+"EXP");
	}
}
