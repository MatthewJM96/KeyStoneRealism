package com.github.SamThePsychoticLeprechaun.KeyStone.Realism;

import java.util.logging.Logger;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.Listeners.WeightListener;
import com.github.SamThePsychoticLeprechaun.KeyStone.Realism.YAML.YamlLoader;

public class KeyStoneRealism extends JavaPlugin {

//---------------------------------------------------------------------------------------\\
	
	private KeyStoneRealism plugin;
	private Logger log;
	
	private static YamlConfiguration config;
	
	private static YamlConfiguration weights;
	
	public KeyStoneRealism() {
		return;
	}
	
//---------------------------------------------------------------------------------------\\
	
	@Override
	public void onEnable() {
		
		setPlugin(this);
		setLog(this.getLogger());
		
		final YamlLoader yl = new YamlLoader();	
		
		setConfig(new YamlConfiguration());
		setWeights(new YamlConfiguration());
		
		yl.yamlStartup();
		
		plugin.getServer().getScheduler().runTaskLater(this, new Runnable() {
			
			public void run() {
				yl.yamlShutdown();
			}
			
		}, 20);
		
		getServer().getPluginManager().registerEvents(new WeightListener(), this);
		
	}
	
//---------------------------------------------------------------------------------------\\
	
	@Override
	public void onDisable() {
		
		final YamlLoader yl = new YamlLoader();
		yl.yamlShutdown();
		
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

	public YamlConfiguration getWeights() {
		
		return weights;
		
	}

	public void setWeights(YamlConfiguration weights) {
		
		KeyStoneRealism.weights = weights;
		
	}
	
//---------------------------------------------------------------------------------------\\
	
}
