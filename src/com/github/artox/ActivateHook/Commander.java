package com.github.artox.ActivateHook;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class Commander implements CommandExecutor {
	private Main plugin;
	private String url, pname_secret, secret, pname_mcacc, pname_forumacc;
	
	public Commander(Main plugin) {
		this.plugin = plugin;

		// read settings
		// url must be like this: http://example.com
		//                        http://example.com
		url = plugin.getConfig().getString("URL");
		pname_secret = plugin.getConfig().getString("pname_secret");
		secret = plugin.getConfig().getString("secret");
		pname_mcacc = plugin.getConfig().getString("pname_mcacc");
		pname_forumacc = plugin.getConfig().getString("pname_forumacc");
		
	}
	
	boolean ActivateAccount(String mcacc, String forumacc){
		String command = "curl -s \"" + url + "\"" +
				" -d " + pname_secret + "=" + secret +
				" -d " + pname_mcacc + "=" + mcacc +
				" -d " + pname_forumacc + "=" + forumacc;
		
		try{
			Runtime.getRuntime().exec(command);
		}
		catch(Exception e){
			plugin.getLogger().info(e.getMessage());
			return false; 
		}

		return true;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender instanceof Player) {} else return false;
		if(args.length != 1) return false;
		
		String mcacc = sender.getName();
		String forumacc = args[0];

		if(ActivateAccount(mcacc, forumacc)){
			sender.sendMessage("Activated forum account " + forumacc + " for " + mcacc);
		}
		else
			return false;
		
		return true;
	}
}
