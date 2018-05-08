package de.morigm.smt.config;

import de.morigm.smt.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class PluginConfiguration {

    public String language;
    public boolean auto_update;
    public boolean language_reconfiguration;

    public void reconfiguration()
    {
        getConfig().options().copyDefaults(true);
        getConfig().addDefault("language", "en-en.yml");
        getConfig().addDefault("auto-update", false);
        getConfig().addDefault("language-reconfiguration", true);
        Main.getInstance().saveConfig();
    }
    
    public void load()
    {
        this.language = getConfig().getString("language");
        this.auto_update = getConfig().getBoolean("auto-update");
        this.language_reconfiguration = getConfig().getBoolean("language-reconfiguration");
    }
    
    public FileConfiguration getConfig()
    {
        return Main.getInstance().getConfig();
    }
                
}
