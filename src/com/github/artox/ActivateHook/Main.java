package com.github.artox.ActivateHook;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onLoad(){
		saveDefaultConfig();
	}
	
	@Override
	public void onEnable(){
		getLogger().info("onEnable has been invoked!");
		getCommand("activate_forum_account").setExecutor(new Commander(this));
	}

	@Override
	public void onDisable(){
		getLogger().info("onDisable has been invoked!");
	}
}
