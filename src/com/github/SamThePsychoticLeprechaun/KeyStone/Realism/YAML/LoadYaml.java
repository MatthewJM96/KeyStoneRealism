package com.github.SamThePsychoticLeprechaun.KeyStone.Realism.YAML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.KeyStoneRealism;
import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.YAML.YamlType.YamlTypes;

public class LoadYaml {
	
//---------------------------------------------------------------------------------------\\

	KeyStoneRealism plugin = new KeyStoneRealism();
	
	public LoadYaml() {
		return;
	}
	
	Logger log = plugin.getLog();
	
	File file;
	YamlConfiguration fileConfig;
	
//---------------------------------------------------------------------------------------\\

	private HashMap<Integer, Double> itemWeights = new HashMap<Integer, Double>();
	private int itemcount;
	private HashMap<Integer, Double> armourWeights = new HashMap<Integer, Double>();
	private int armourcount;
	
	public void yamlStartup() {
		
		if(plugin.equals(null)) {
			Bukkit.broadcastMessage("Error 123");
		} else {
		
		overwriteYaml(YamlTypes.ALL);
		
		fileConfig = plugin.getWeights();
		
		int indMin = 1;
		int indMax = fileConfig.getInt("blockcount");
		itemcount = indMax;
		
		while(indMin <= indMax) {
			
			int blockID = fileConfig.getInt("id" + Integer.toString(indMin) + ".blockid");
			double weight = fileConfig.getDouble("id" + Integer.toString(indMin) + ".weight");
			
			itemWeights.put(blockID, weight);
			
			indMin += 1;
			
		}
		
		indMin = 1;
		indMax = fileConfig.getInt("armourcount");
		armourcount = indMax;
		
		while(indMin <= indMax) {
			
			int blockID = fileConfig.getInt("armourid" + Integer.toString(indMin) + ".blockid");
			double weight = fileConfig.getDouble("armourid" + Integer.toString(indMin) + ".weight");
			
			armourWeights.put(blockID, weight);
			
			indMin += 1;
			
		}
		
		}
		
	}
	
//---------------------------------------------------------------------------------------\\

	/**
	 * Overwrites specified yaml files, i.e. config.yml and itemweights.yml. 
	 * 
	 * @param yamlType - The yaml file(s) to be loaded. 
	 * </p>Possible YamlTypes are: 
	 * </p> ALL - This loads all yaml files. 
	 * </p> CONFIG - This loads the config file.
	 * </p> WEIGHTS - This loads the item weights file.
	 */
	public void loadYaml(YamlTypes yamlType) {
		
		if (yamlType.equals(YamlTypes.ALL)) {
			
			loadConf();
			loadWeights();
			
		} else if (yamlType.equals(YamlTypes.CONFIG)) {
			
			loadConf();
			
		} else {
			
			loadWeights();
			
		}
		
	}
	
//---------------------------------------------------------------------------------------\\

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
			
			overwriteConf();
			overwriteWeights();
			
		} else if (yamlType.equals(YamlTypes.CONFIG)) {
			
			overwriteConf();
			
		} else {
			
			overwriteWeights();
			
		}
		
	}
	
//---------------------------------------------------------------------------------------\\

	private void overwriteConf() {
		
		file = plugin.getConfigFile();
		fileConfig = plugin.getConfig();
		
		file.getParentFile().mkdirs();
        copy(plugin.getResource("config.yml"), file);
		plugin.setConfigFile(file);
		
		try {
			fileConfig.load(file);
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
		
		plugin.setConfig(fileConfig);
		
	}
	
	private void overwriteWeights() {
		
		file = plugin.getWeightsFile();
		fileConfig = plugin.getWeights();
		
		file.getParentFile().mkdirs();
        copy(plugin.getResource("itemweights.yml"), file);
		plugin.setWeightsFile(file);
		
		try {
			fileConfig.load(file);
		} catch (FileNotFoundException e) {
			log.info("Item Weights file was not found!");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("Error loading Item Weights file!");
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			log.info("Error loading Item Weights file!");
			e.printStackTrace();
		}
		
		plugin.setWeights(fileConfig);
		
	}
	
//---------------------------------------------------------------------------------------\\

	private void loadConf() {
		
		file = plugin.getConfigFile();
		fileConfig = plugin.getConfig();
		
		try {
			fileConfig.load(file);
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
		
		plugin.setConfig(fileConfig);
		
	}
	
	private void loadWeights() {
		
		file = plugin.getWeightsFile();
		fileConfig = plugin.getWeights();
		
		try {
			fileConfig.load(file);
		} catch (FileNotFoundException e) {
			log.info("Item Weights file was not found!");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("Error loading Item Weights file!");
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			log.info("Error loading Item Weights file!");
			e.printStackTrace();
		}
		
		plugin.setWeights(fileConfig);
		
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
