package com.github.SamThePsychoticLeprechaun.KeyStone.Realism.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.YAML.YamlLoader;

public class WeightCalculator {
	
	public WeightCalculator() {
		return;
	}

	private double weightCalc(Player player){
		
		double weight = 100.00;
		
		YamlLoader yl = new YamlLoader();
		//To Be Initialised
		HashMap<Integer, Double> blockWeights = yl.getItemWeights();
		HashMap<Integer, Double> armourWeights = yl.getArmourWeights();
		
		int boots = player.getEquipment().getBoots().getData().getItemTypeId();
		int chest = player.getEquipment().getChestplate().getData().getItemTypeId();
		int legs = player.getEquipment().getLeggings().getData().getItemTypeId();
		int helm = player.getEquipment().getHelmet().getData().getItemTypeId();
		
		weight += armourWeights.get(boots);
		weight += armourWeights.get(chest);
		weight += armourWeights.get(legs);
		weight += armourWeights.get(helm);

		ItemStack inv[] = player.getInventory().getContents();
		List<ItemStack> invList = Arrays.asList(inv);
		Iterator<ItemStack> i = invList.iterator();		
		
		int ind = 0;
		
		while(i.hasNext()) {
			
			int id = invList.get(ind).getData().getItemTypeId();
			weight += blockWeights.get(id);
			ind += 1;
			
		}
		
		return weight;
		
	}
	
	public double weightDecreaseMult(Player player) {
		
		double weight = weightCalc(player);
		double mult = 1.0;
		
		weight -= 100;
		weight /= 320;
		mult -= weight;		
		
		return mult;
		
	}
	
	public double weightIncreaseMult(Player player) {
		
		double weight = weightCalc(player);
		double mult = 1.0;
		
		weight -= 100;
		weight /= 320;
		mult += weight;		
		
		return mult;
		
	}
	
}
