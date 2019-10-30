package com.trddiy.by664365842;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.herocraftonline.heroes.api.events.CharacterDamageEvent;
import com.herocraftonline.heroes.api.events.ClassChangeEvent;
import com.herocraftonline.heroes.api.events.HeroChangeLevelEvent;
import com.herocraftonline.heroes.api.events.HeroRegainHealthEvent;
import com.herocraftonline.heroes.api.events.HeroRegainManaEvent;
import com.herocraftonline.heroes.api.events.SkillDamageEvent;
import com.herocraftonline.heroes.api.events.SkillUseEvent;
import com.herocraftonline.heroes.api.events.WeaponDamageEvent;
import com.herocraftonline.heroes.characters.Hero;

public class HeroesListener implements Listener {
	private Core plugin;

	public HeroesListener(Core plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	//玩家登陆 发送cd hp mp
	@EventHandler
	public void onplayerlogin(PlayerLoginEvent event) {
		final Player p = event.getPlayer();
		plugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						plugin.getpcl().sendcd(p);
						plugin.getpcl().sendmana(plugin.getHero(p));
						plugin.getpcl().sendhp(plugin.getHero(p));
					}
				}, 100L);
	}
//玩家使用技能 发送cd
	@EventHandler
	public void onskilluse(SkillUseEvent event) {
		Player p = event.getPlayer();
		plugin.getpcl().sendcd(p);
	}
//玩家恢复魔法 发送mp
	@EventHandler
	public void onmanaregain(HeroRegainManaEvent event) {
		Hero h = event.getHero();
		plugin.getpcl().sendmana(h, event.getAmount());
	}
//武器伤害 发送hp mp
	@EventHandler
	public void onweapondamage(WeaponDamageEvent event) {
		Entity e = event.getEntity();
		if (e instanceof Player) {
			final Player player = (Player) event.getEntity();
			plugin.getServer().getScheduler()
					.scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							plugin.getpcl().sendmana(plugin.getHero(player));
							plugin.getpcl().sendhp(plugin.getHero(player));
						}
					}, 1L);
		}
	}
	//玩家死亡 发送cd hp mp
	@EventHandler
	public void ondeath(PlayerDeathEvent event){
		final Player p = event.getEntity();
		plugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						plugin.getpcl().sendcd(p);
						plugin.getpcl().sendmana(plugin.getHero(p));
						plugin.getpcl().sendhp(plugin.getHero(p));
					}
				}, 1L);
	}
	//玩家技能造成伤害 发送hp mp
	@EventHandler
	public void onskilldamage(SkillDamageEvent event) {
		Entity e = event.getEntity();
		if (e instanceof Player) {
			final Player player = (Player) event.getEntity();
			plugin.getServer().getScheduler()
					.scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							plugin.getpcl().sendmana(plugin.getHero(player));
							plugin.getpcl().sendhp(plugin.getHero(player));
						}
					}, 1L);
		}
	}
//自然伤害 发送hp mp
	@EventHandler
	public void oncharacterdamage(CharacterDamageEvent event) {
		Entity e = event.getEntity();
		if (e instanceof Player) {
			final Player player = (Player) event.getEntity();
			plugin.getServer().getScheduler()
					.scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							plugin.getpcl().sendmana(plugin.getHero(player));
							plugin.getpcl().sendhp(plugin.getHero(player));
						}
					}, 1L);
		}
	}
//重生 发送cd hp mp
	@EventHandler
	public void onreborn(PlayerRespawnEvent event) {
		final Player p = event.getPlayer();
		plugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						plugin.getpcl().sendcd(p);
						plugin.getpcl().sendmana(plugin.getHero(p));
						plugin.getpcl().sendhp(plugin.getHero(p));
					}
				}, 1L);
	}
//升级 发送hp mp
	@EventHandler
	public void onlevelup(HeroChangeLevelEvent event) {
		Hero h = event.getHero();
		final Player player = h.getPlayer();
		plugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						plugin.getpcl().sendmana(plugin.getHero(player));
						plugin.getpcl().sendhp(plugin.getHero(player));
					}
				}, 1L);
	}
//改变职业 发送hp mp
	@EventHandler
	public void onClassChange(ClassChangeEvent event) {
		Hero h = event.getHero();
		final Player p = h.getPlayer();
		plugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						plugin.getpcl().sendmana(plugin.getHero(p));
						plugin.getpcl().sendhp(plugin.getHero(p));
					}
				}, 1L);
	}
//玩家恢复生命 发送hp mp
	@EventHandler
	public void onHpRegain(HeroRegainHealthEvent event) {
		final Player p = event.getHero().getPlayer();
		plugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						plugin.getpcl().sendmana(plugin.getHero(p));
						plugin.getpcl().sendhp(plugin.getHero(p));
					}
				}, 1L);
	}
//玩家自然恢复生命(不知是否调用) 发送hp mp
	@EventHandler
	public void onHealthRegain(EntityRegainHealthEvent event) {
		if (event.getEntityType() != EntityType.PLAYER)
			return;
		final Player player = (Player) event.getEntity();
		plugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						plugin.getpcl().sendmana(plugin.getHero(player));
						plugin.getpcl().sendhp(plugin.getHero(player));
					}
				}, 1L);
	}
}