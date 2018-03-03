package com.mortuusterra;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.mortuusterra.commands.MTcommands;
import com.mortuusterra.listeners.MTCommunication;
import com.mortuusterra.listeners.MTGeckListener;
import com.mortuusterra.listeners.MTGeneratorListener;
import com.mortuusterra.listeners.MTPlayerListener;
import com.mortuusterra.listeners.MTPower;
import com.mortuusterra.managers.MTCommunicationChannels;
import com.mortuusterra.managers.MTGeneratorBuildProcess;
import com.mortuusterra.managers.MTRadiation;

public class MortuusTerraMain extends JavaPlugin {

	private static boolean debugMode = true;

	// private FileManager fm;
	private MTcommands cmd;
	private MTCommunicationChannels communicationChannels;
	private MTRadiation rad;
	private MTGeneratorBuildProcess genBuild;

	private MTPlayerListener pl;
	private MTCommunication communicationListener;
	private MTGeckListener geck;
	private MTGeneratorListener genListener;
	private MTPower power;

	public MortuusTerraMain() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onEnable() {
		// fm = new FileManager(this);
		cmd = new MTcommands(this);
		getCommand("channel").setExecutor(cmd);
		getCommand("mortuusterra").setExecutor(cmd);
		communicationChannels = new MTCommunicationChannels(this);
		rad = new MTRadiation(this);
		genBuild = new MTGeneratorBuildProcess(this);

		power = new MTPower(this);
		getServer().getPluginManager().registerEvents(power, this);

		pl = new MTPlayerListener(this);
		getServer().getPluginManager().registerEvents(pl, this);

		geck = new MTGeckListener(this);
		getServer().getPluginManager().registerEvents(geck, this);

		genListener = new MTGeneratorListener(this);
		getServer().getPluginManager().registerEvents(genListener, this);

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

	public MTGeneratorBuildProcess getGenBuild() {
		return genBuild;
	}

	public MTcommands getCommands() {
		return cmd;
	}
	/*
	 * public FileManager getFileManager() { return fm; }
	 */

	public MTCommunicationChannels getCommunicationChannels() {
		return communicationChannels;
	}

	public MTRadiation getRad() {
		return rad;
	}

	public MTPlayerListener getPl() {
		return pl;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		MortuusTerraMain.debugMode = debugMode;
	}
}
