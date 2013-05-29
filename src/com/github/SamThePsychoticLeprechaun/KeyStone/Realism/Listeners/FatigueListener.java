package com.github.SamThePsychoticLeprechaun.KeyStone.Realism.Listeners;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;

import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.KeyStoneRealism;

public class FatigueListener implements Listener {
	
	KeyStoneRealism plugin = new KeyStoneRealism();
	Logger log = plugin.getLog();
	
	HashMap<String, Integer> runTime = new HashMap<String, Integer>();
	HashMap<String, Integer> startRun = new HashMap<String, Integer>();
	HashMap<String, Integer> endRun = new HashMap<String, Integer>();
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onSprintListener(PlayerToggleSprintEvent e) {
		
		Player player = e.getPlayer();
		
		if(e.isCancelled()) {
			return;
		}
		
		if(e.isSprinting()) {
			
			int start = (((int) System.currentTimeMillis()) / 1000);
			startRun.put(player.getName(), start);
			
		} else {
			
			int end = (((int) System.currentTimeMillis()) / 1000);
			int dur = end - startRun.get(player.getName());
			dur += runTime.get(player.getName());
			
			endRun.put(player.getName(), end);
			runTime.put(player.getName(), dur);
						
		}
		
	}
	
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onJoinListener(PlayerJoinEvent e) {
		
		Player player = e.getPlayer();
		
		runTime.put(player.getName(), 0);
		
	}
	
	HashMap<String, Integer> lastSleep = new HashMap<String, Integer>();
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onSleepListener(PlayerBedLeaveEvent e) {
		
		Player player = e.getPlayer();
		
		int sleep = (((int) System.currentTimeMillis()) / 1000);
		lastSleep.put(player.getName(), sleep);
		
	}
	
}
