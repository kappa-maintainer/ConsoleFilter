package com.chaosthedude.consolefilter;

import com.google.common.eventbus.Subscribe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.ModMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chaosthedude.consolefilter.config.ConfigHandler;
import com.chaosthedude.consolefilter.filter.SystemFilter;
import com.chaosthedude.consolefilter.filter.JavaFilter;
import com.chaosthedude.consolefilter.filter.Log4jFilter;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

//@Mod(modid = ConsoleFilter.MODID, name = ConsoleFilter.NAME, version = ConsoleFilter.VERSION, acceptedMinecraftVersions = "[1.12,1.12.2]", acceptableRemoteVersions = "*")

public class ConsoleFilter extends DummyModContainer {

	public static final String MODID = "consolefilter";
	public static final String NAME = "Console Filter";
	public static final String VERSION = "1.2";

	public static final Logger logger = LogManager.getLogger(MODID);

	public ConsoleFilter() {
		super(getMeta());
	}

	@Subscribe
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ConfigHandler.ChangeListener());
		/*
		ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
		logger.info(ConfigHandler.getMessagesToFilter().size() + " message(s) to be filtered.");

		JavaFilter.applyFilter();
		Log4jFilter.applyFilter();
		SystemFilter.applyFilter();

		 */
	}

	private static ModMetadata getMeta() {
		ModMetadata md = new ModMetadata();
		md.modId = MODID;
		md.name = NAME;
		md.version = VERSION;
		return md;
	}

}
