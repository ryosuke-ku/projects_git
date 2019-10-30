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
			plugin.sendtoplayer(p, "手中拿的物品不能兑换经验!");
			return;
		}
		if (is.getAmount() < a) {
			plugin.sendtoplayer(p, "手上的东西不够!");
			return;
		}
		if (a < 0) {
			plugin.sendtoplayer(p, "经验兑换物品功能暂未开放!");
			return;
		}
		if (a == 0) {
			plugin.sendtoplayer(p, "请输入非零数字!");
			return;
		}
		Hero h = plugin.getheroesplugin().getCharacterManager().getHero(p);
		if (h.getLevel(h.getHeroClass()) == h.getHeroClass().getMaxLevel()) {
			plugin.sendtoplayer(p, "当前职业已满级!");
			return;
		}
		if (is.getAmount() - a == 0) {
			p.getInventory().removeItem(is);
		} else {
			is.setAmount(is.getAmount() - a);
		}
		h.addExp(xp * a, h.getHeroClass(),h.getPlayer().getLocation());
		h.syncExperience();
		plugin.sendtoplayer(p, "你使用 " + ChatColor.GREEN + a + ChatColor.WHITE
				+ " 个 " + ChatColor.GREEN
				+ is.getType().toString().toLowerCase() + ChatColor.WHITE
				+ " 兑换了 " + ChatColor.GREEN + xp * a + ChatColor.WHITE
				+ " 点经验.");
		if(Core.debug == true)
			plugin.sendtoserver(p.getDisplayName()+" "+xp*a+"EXP");
	}
}
