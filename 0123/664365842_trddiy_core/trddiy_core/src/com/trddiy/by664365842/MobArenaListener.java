package com.trddiy.by664365842;

import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.garbagemule.MobArena.ArenaClass;
import com.garbagemule.MobArena.events.ArenaPlayerJoinEvent;
import com.garbagemule.MobArena.events.NewWaveEvent;
import com.garbagemule.MobArena.framework.Arena;
import com.garbagemule.MobArena.waves.enums.WaveType;
import com.herocraftonline.heroes.characters.CharacterManager;
import com.herocraftonline.heroes.characters.Hero;

public class MobArenaListener implements Listener {
	private Core plugin;
	private CharacterManager chm;

	public MobArenaListener(Core plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onWave(NewWaveEvent event) {
		// TODO ��boss��֮���ٸ�����
		int wn = event.getWaveNumber();
		Arena arena = event.getArena();
		Set<Player> ap = arena.getPlayersInArena();
		if (event.getWave().getType() == WaveType.BOSS) {
			if(Core.debug == true)
				plugin.sendtoserver("BossWave "+event.getWaveNumber());
			for (Player p : ap) {
				if (p != null) {
					addexp(p, wn);
				}
			}
		}
	}

	@EventHandler
	public void onJoin(ArenaPlayerJoinEvent event) {
		Arena a = event.getArena();
		Player p = event.getPlayer();
		setclass(p, a);
	}

	public void addexp(Player p, int wn) {
		chm = plugin.getheroesplugin().getCharacterManager();
		int waven = plugin.getConfig().getInt("MobArena.multiply");
		Hero h = chm.getHero(p);
		h.addExp(wn * waven, h.getHeroClass(),h.getPlayer().getLocation());
		h.syncExperience();
		plugin.sendtoplayer(p, "�����˽���: " + wn * waven + " ����");
	}

	/**
	 * ������ҵ�pveְҵ,���޷���������ְҵ������Ϊ������
	 * 
	 * @param p
	 *            �����
	 * @param a
	 *            ��pve����
	 */
	public void setclass(final Player p, final Arena a) {
		chm = plugin.getheroesplugin().getCharacterManager();
		Hero h = chm.getHero(p);
		final String s = h.getHeroClass().getName();
		plugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						// plugin.sendtoplayer(p,
						// a.getArenaPlayer(p).toString());
						plugin.sendtoplayer(p, "��⵽ְҵ: " + ChatColor.GOLD + s);
						Map<String, ArenaClass> map = a.getClasses();
						if (map.containsKey(s)) {
							a.assignClass(p, s);
							plugin.sendtoplayer(p, "����ְҵ�ɹ�.");
							if(Core.debug == true)
							plugin.sendtoserver(p.getDisplayName()+" "+s);
						} else {
							a.assignClass(p, "������");
							plugin.sendtoplayer(p, "δ�ҵ���Ӧְҵ,����Ϊ������.");
							if(Core.debug == true)
							plugin.sendtoserver(p.getDisplayName()+" "+s);
						}
					}
				}, 1L);
	}
}
