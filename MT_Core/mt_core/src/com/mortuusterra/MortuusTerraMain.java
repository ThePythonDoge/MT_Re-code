package com.mortuusterra;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.mortuusterra.commands.MTcommands;
import com.mortuusterra.listeners.MTCommunication;
import com.mortuusterra.managers.MTCommunicationChannels;

public class MortuusTerraMain extends JavaPlugin {
	
	private static boolean debugMode = true;

	//private FileManager fm;
	private MTcommands cmd;
	private MTCommunicationChannels communicationChannels;
	
	private MTCommunication communicationListener;

	public MortuusTerraMain() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onEnable() {
		//fm = new FileManager(this);
		cmd = new MTcommands(this);
		getCommand("channel").setExecutor(cmd);
		communicationChannels = new MTCommunicationChannels(this);
		
		communicationListener = new MTCommunication(this);
		getServer().getPluginManager().registerEvents(communicationListener, this);
	}

	@Override
	public void onDisable() {

	}
	
	public void notifyConsol(String text) {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + text);
	}
	
	public final MortuusTerraMain getCore() {
		return this;
	}
	
	public MTcommands getCommands() {
		return cmd;
	}
	/*
	public FileManager getFileManager() {
		return fm;
	}
	*/

	public MTCommunicationChannels getCommunicationChannels() {
		return communicationChannels;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		MortuusTerraMain.debugMode = debugMode;
	}
}