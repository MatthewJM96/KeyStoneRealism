package com.github.SamThePsychoticLeprechaun.KeyStone.Realism.Utils;

import org.bukkit.entity.Player;

public class FatigueCalculator {

	public FatigueCalculator() {
		return;
	}
	
	private double fatigueCalc(Player player) {
		
		double fatigue = 100.00;
		
		int foodChange = 20 - player.getFoodLevel();
		
		//To be done
		
		return fatigue;
		
	}
	
}
