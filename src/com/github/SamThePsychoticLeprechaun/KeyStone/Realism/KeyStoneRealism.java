package com.github.SamThePsychoticLeprechaun.KeyStone.Realism;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.Listeners.WeightListener;
import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.YAML.LoadYaml;
import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.YAML.SaveYaml;

public class KeyStoneRealism extends JavaPlugin {

//---------------------------------------------------------------------------------------\\
	
	private KeyStoneRealism plugin;
	private Logger log;
	
	private static YamlConfiguration config;
	private static File configFile;
	
	private static YamlConfiguration weights;
	private static File weightsFile;
	
	public KeyStoneRealism() {
		return;
	}
	
//---------------------------------------------------------------------------------------\\
	
	@Override
	public void onEnable() {
		
		setPlugin(this);
		setLog(this.getLogger());
		
		final LoadYaml ly = new LoadYaml();		
		final SaveYaml sy = new SaveYaml();
		
		setConfig(new YamlConfiguration());
		setWeights(new YamlConfiguration());
		setConfigFile(new File(getDataFolder(), "config.yml"));
		setWeightsFile(new File(getDataFolder(), "itemweights.yml"));
		
		ly.yamlStartup();
		
		plugin.getServer().getScheduler().runTaskLater(this, new Runnable() {
			
			public void run() {
				sy.yamlShutdown();
			}
			
		}, 20);
		
		getServer().getPluginManager().registerEvents(new WeightListener(), this);
		
	}
	
//---------------------------------------------------------------------------------------\\
	
	@Override
	public void onDisable() {
		
		final SaveYaml sy = new SaveYaml();
		sy.yamlShutdown();
		
	}

//---------------------------------------------------------------------------------------\\
	
	public KeyStoneRealism getPlugin() {
		
		return plugin;
		
	}

	public void setPlugin(KeyStoneRealism plugin) {
		
		this.plugin = plugin;
		
	}

	public Logger getLog() {
		
		return log;
		
	}

	public void setLog(Logger log) {
		
		this.log = log;
		
	}

	public YamlConfiguration getConfig() {
		
		return config;
		
	}

	public void setConfig(YamlConfiguration config) {
		
		KeyStoneRealism.config = config;
		
	}

	public File getConfigFile() {
		
		return configFile;
		
	}

	public void setConfigFile(File configFile) {
		
		KeyStoneRealism.configFile = configFile;
		
	}

	public YamlConfiguration getWeights() {
		
		return weights;
		
	}

	public void setWeights(YamlConfiguration weights) {
		
		KeyStoneRealism.weights = weights;
		
	}

	public File getWeightsFile() {
		
		return weightsFile;
		
	}

	public void setWeightsFile(File weightsFile) {
		
		KeyStoneRealism.weightsFile = weightsFile;
		
	}
	
//---------------------------------------------------------------------------------------\\
	
}
