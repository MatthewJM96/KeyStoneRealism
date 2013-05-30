package com.github.SamThePsychoticLeprechaun.KeyStone.Realism.YAML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.KeyStoneRealism;
import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.YAML.YamlType.YamlTypes;

public class YamlLoader {

//---------------------------------------------------------------------------------------\\

	KeyStoneRealism plugin = new KeyStoneRealism();
	
	public YamlLoader() {
		return;
	}
	
	public YamlConfiguration yamlLoader(String file) {
		
		fileConfig = load(file);
		
		return fileConfig;		
		
	}
	
	Logger log = plugin.getLog();
	
	private HashMap<Integer, Double> itemWeights = new HashMap<Integer, Double>();
	private int itemcount;
	private HashMap<Integer, Double> armourWeights = new HashMap<Integer, Double>();
	private int armourcount;
	
	File file;
	YamlConfiguration fileConfig;
	
//---------------------------------------------------------------------------------------\\

	public void yamlStartup() {
				
		overwriteYaml(YamlTypes.ALL);
		
		fileConfig = plugin.getWeights();
		
		int indMin = 1;
		int indMax = fileConfig.getInt("blockcount");
		itemcount = indMax;
		
		int blockID;
		double weight;
		
		while(indMin <= indMax) {
			
			blockID = fileConfig.getInt("id" + Integer.toString(indMin) + ".blockid");
			weight = fileConfig.getDouble("id" + Integer.toString(indMin) + ".weight");
			
			itemWeights.put(blockID, weight);
			
			indMin++;
			
		}
		
		indMin = 1;
		indMax = fileConfig.getInt("armourcount");
		armourcount = indMax;
		
		while(indMin <= indMax) {
			
			blockID = fileConfig.getInt("armourid" + Integer.toString(indMin) + ".blockid");
			weight = fileConfig.getDouble("armourid" + Integer.toString(indMin) + ".weight");
			
			armourWeights.put(blockID, weight);
			
			indMin++;
			
		}
		
	}
	
//---------------------------------------------------------------------------------------\\

	public void yamlShutdown() {
		
		fileConfig = new YamlConfiguration();
		
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
	 * Loads specified yaml files, i.e. config.yml and itemweights.yml. 
	 * 
	 * @param yamlType - The yaml file(s) to be loaded. 
	 * </p>Possible YamlTypes are: 
	 * </p> ALL - This loads all yaml files. 
	 * </p> CONFIG - This loads the config file.
	 * </p> WEIGHTS - This loads the item weights file.
	 */
	public void loadYaml(YamlTypes yamlType) {
		
		if (yamlType.equals(YamlTypes.ALL)) {
			
			fileConfig = load("config.yml");
			plugin.setConfig(fileConfig);
			fileConfig = load("itemweights.yml");
			plugin.setWeights(fileConfig);
			
		} else if (yamlType.equals(YamlTypes.CONFIG)) {

			fileConfig = load("config.yml");
			plugin.setConfig(fileConfig);
			
		} else {

			fileConfig = load("itemweights.yml");
			plugin.setWeights(fileConfig);
			
		}
		
	}
	
	/**
	 * Overwrites specified yaml files, i.e. config.yml and itemweights.yml. 
	 * 
	 * @param yamlType - The yaml file(s) to be loaded. 
	 * </p>Possible YamlTypes are: 
	 * </p> ALL - This loads all yaml files. 
	 * </p> CONFIG - This loads the config file.
	 * </p> WEIGHTS - This loads the item weights file.
	 */
	public void overwriteYaml(YamlTypes yamlType) {
		
		if (yamlType.equals(YamlTypes.ALL)) {
			
			fileConfig = overwrite("config.yml");
			plugin.setConfig(fileConfig);
			fileConfig = overwrite("itemweights.yml");
			plugin.setWeights(fileConfig);
			
		} else if (yamlType.equals(YamlTypes.CONFIG)) {

			fileConfig = overwrite("config.yml");
			plugin.setConfig(fileConfig);
			
		} else {

			fileConfig = overwrite("itemweights.yml");
			plugin.setWeights(fileConfig);
			
		}
		
	}
	
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
				
			save("config.yml", plugin.getConfig());
			save("itemweights.yml", plugin.getWeights());
				
		} else if (yamlType.equals(YamlTypes.CONFIG)) {
				
			save("config.yml", plugin.getConfig());
				
		} else {
				
			save("itemweights.yml", plugin.getWeights());
				
		}
			
	}
	
//---------------------------------------------------------------------------------------\\
	
	private YamlConfiguration overwrite(String path) {
		
		File fl = new File(plugin.getDataFolder(), path);
		
		fl.getParentFile().mkdirs();
        copy(plugin.getResource(path), file);
		
		try {
			fileConfig.load(fl);
		} catch (FileNotFoundException e) {
			log.info("Config file was not found!");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("Error loading config file!");
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			log.info("Error loading config file!");
			e.printStackTrace();
		}

		return fileConfig;
		
	}
	
//---------------------------------------------------------------------------------------\\

	private YamlConfiguration load(String path) {
		
		File fl = new File(plugin.getDataFolder(), path);
		
		try {
			fileConfig.load(fl);
		} catch (FileNotFoundException e) {
			log.info("Config file was not found!");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("Error loading config file!");
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			log.info("Error loading config file!");
			e.printStackTrace();
		}
		
		return fileConfig;
		
	}	
	
//---------------------------------------------------------------------------------------\\

	private void save(String path, YamlConfiguration fileConfig) {
		
		File fl = new File(plugin.getDataFolder(), path);
		
		try{
			fileConfig.save(fl);			
		} catch (FileNotFoundException e) {
			log.info("Item Weights file was not found!");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("Error while saving Item Weights file!");
			e.printStackTrace();
		}
		
	}
	
//---------------------------------------------------------------------------------------\\

	private void copy(InputStream inputStream, File file) {
			 
		try {
	 
			OutputStream outputStream = new FileOutputStream(file);	
			byte[] buf = new byte[1024];
	        int len;
	 
	        while((len=inputStream.read(buf))>0){
	 
	        	outputStream.write(buf,0,len);
	 
	        }
	 
	        outputStream.close();
	        inputStream.close();
	 
		} catch (Exception e) {
	 
			e.printStackTrace();
	 
		}
	 
	}
	
//---------------------------------------------------------------------------------------\\

	public HashMap<Integer, Double> getItemWeights() {
		
		return itemWeights;
		
	}
	
	public void setItemWeights(HashMap<Integer, Double> itemWeights) {
		
		this.itemWeights = itemWeights;
		
	}
	
	public int getItemCount() {
		
		return itemcount;
		
	}
	
	public void setItemCount(int itemcount) {
		
		this.itemcount = itemcount;
		
	}

	public HashMap<Integer, Double> getArmourWeights() {
		
		return armourWeights;
		
	}
	
	public void setArmourWeights(HashMap<Integer, Double> armourWeights) {
		
		this.armourWeights = armourWeights;
		
	}
	
	public int getArmourCount() {
		
		return armourcount;
		
	}
	
	public void setArmourCount(int armourcount) {
		
		this.armourcount = armourcount;
		
	}
	
//---------------------------------------------------------------------------------------\\

}
