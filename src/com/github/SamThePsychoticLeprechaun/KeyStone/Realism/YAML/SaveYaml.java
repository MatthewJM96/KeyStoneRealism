package com.github.SamThePsychoticLeprechaun.KeyStone.Realism.YAML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.configuration.file.YamlConfiguration;

import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.KeyStoneRealism;
import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.YAML.YamlType.YamlTypes;

public class SaveYaml {

//---------------------------------------------------------------------------------------\\

	KeyStoneRealism plugin = new KeyStoneRealism();
		
	public SaveYaml() {
		return;
	}
		
	Logger log = plugin.getLog();
		
	File file;
	YamlConfiguration fileConfig;
		
//---------------------------------------------------------------------------------------\\

	final LoadYaml ly = new LoadYaml();
	
	private HashMap<Integer, Double> itemWeights = new HashMap<Integer, Double>();
	private HashMap<Integer, Double> armourWeights = new HashMap<Integer, Double>();
	private int itemcount;
	private int armourcount;
	
	public void yamlShutdown() {
		
		itemWeights = ly.getItemWeights();
		armourWeights = ly.getArmourWeights();
		itemcount = ly.getItemCount();
		armourcount = ly.getArmourCount();
		
		fileConfig = plugin.getWeights();
		
		List<Integer> blockID = new ArrayList<Integer>(itemWeights.keySet());
		List<Double> blockWeightList = new ArrayList<Double>(itemWeights.values());
		
		int indMin = 1;
		int indMax = itemcount;
		
		while(indMin <= indMax) {
			
			fileConfig.set("id" + Integer.toString(indMin) + ".blockid", blockID.get(indMin));
			fileConfig.set("id" + Integer.toString(indMin) + ".weight", blockWeightList.get(indMin));
			
			indMin += 1;
			
		}
		
		fileConfig.set("blockcount", itemcount);
		
		List<Integer> armourID = new ArrayList<Integer>(armourWeights.keySet());
		List<Double> armourWeightList = new ArrayList<Double>(armourWeights.values());
		
		indMin = 1;
		indMax = armourcount;
		
		while(indMin <= indMax) {
			
			fileConfig.set("armourid" + Integer.toString(indMin) + ".blockid", armourID.get(indMin));
			fileConfig.set("armourid" + Integer.toString(indMin) + ".weight", armourWeightList.get(indMin));
			
			indMin += 1;
			
		}
		
		fileConfig.set("armourcount", armourcount);
		
		plugin.setWeights(fileConfig);
		
		saveYaml(YamlTypes.ALL);
		
	}	
		
//---------------------------------------------------------------------------------------\\

	/**
	 * Saves specified yaml files, i.e. config.yml and factions.yml. 
	 * 
	 * @param yamlType - The yaml file(s) to be loaded. 
	 * </p>Possible YamlTypes are: 
	 * </p> ALL - This saves all yaml files. 
	 * </p> CONFIG - This saves the config file.
	 * </p> WEIGHTS - This saves the item weights file.
	 */
	public void saveYaml(YamlTypes yamlType) {
			
		if (yamlType.equals(YamlTypes.ALL)) {
				
			saveConf();
			saveWeights();
				
		} else if (yamlType.equals(YamlTypes.CONFIG)) {
				
			saveConf();
				
		} else {
				
			saveWeights();
				
		}
			
	}
		
//---------------------------------------------------------------------------------------\\

	private void saveConf() {
		
		file = plugin.getConfigFile();
		fileConfig = plugin.getConfig();
		
		try {
			fileConfig.save(file);			
		} catch (FileNotFoundException e) {
			log.info("Config file was not found!");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("Error while saving config file!");
			e.printStackTrace();
		}
		
	}
	
	private void saveWeights() {
		
		file = plugin.getWeightsFile();
		fileConfig = plugin.getWeights();
		
		try {
			fileConfig.save(file);			
		} catch (FileNotFoundException e) {
			log.info("Item Weights file was not found!");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("Error while saving Item Weights file!");
			e.printStackTrace();
		}
		
	}
		
//---------------------------------------------------------------------------------------\\

	
}
