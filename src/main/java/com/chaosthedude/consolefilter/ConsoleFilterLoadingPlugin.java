package com.chaosthedude.consolefilter;

import com.chaosthedude.consolefilter.config.ConfigHandler;
import com.chaosthedude.consolefilter.filter.JavaFilter;
import com.chaosthedude.consolefilter.filter.Log4jFilter;
import com.chaosthedude.consolefilter.filter.SystemFilter;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Map;

@IFMLLoadingPlugin.DependsOn("forge")
public class ConsoleFilterLoadingPlugin implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return "com.chaosthedude.consolefilter.ConsoleFilter";
        //return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        ConfigHandler.loadConfig(new File("config/consolefilter.cfg"));
        ConsoleFilter.logger.info(ConfigHandler.getMessagesToFilter().size() + " message(s) to be filtered.");

        JavaFilter.applyFilter();
        Log4jFilter.applyFilter();
        SystemFilter.applyFilter();
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
