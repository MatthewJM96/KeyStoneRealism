package com.github.SamThePsychoticLeprechaun.KeyStone.Realism.Listeners;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.KeyStoneRealism;
import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.Utils.WeightCalculator;

public class WeightListener implements Listener {
	
	KeyStoneRealism plugin = new KeyStoneRealism();
	WeightCalculator wc = new WeightCalculator();
	Logger log = plugin.getLog();
	
	@EventHandler (priority = EventPriority.LOWEST)
	public void onMoveListener(PlayerVelocityEvent e) {
		
		Player player = e.getPlayer();
		
		double mult = wc.weightDecreaseMult(player);
		
		Vector v = e.getVelocity();
		double X = e.getVelocity().getX();
		double Y = e.getVelocity().getY();
		double Z = e.getVelocity().getZ();
		
		X *= mult;
		Y *= mult;
		Z *= mult;
		
		v.setX(X);
		v.setY(Y);
		v.setZ(Z);
		
		e.setVelocity(v);
		
	}
	
	

}
