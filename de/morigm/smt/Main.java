package de.morigm.smt;

import de.morigm.smt.chat.ChatText;
import de.morigm.smt.chat.Language;
import de.morigm.smt.config.PluginConfiguration;
import de.morigm.smt.file.FileManager;
import de.morigm.smt.file.PluginsFileManager;
import de.morigm.smt.helper.DowloadHelper;
import de.morigm.smt.source.SourceFileLoader;
import de.morigm.smt.source.SourceListManager;
import de.morigm.smt.source.SourceManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin 
{
    
    private static Main instance;
    private PluginConfiguration pluginConfiguration;
    private FileManager fileManager;
    private PluginsFileManager pluginsFileManager;
    private SourceListManager sourceListManager;
    private Language language;
    private SourceFileLoader sourceFileLoader;
    private SourceManager sourceManager;
    private DowloadHelper dowloadHelper;
    
    @Override
    public void onEnable() 
    {
        Main.instance = this;
        this.dowloadHelper = new DowloadHelper();
        this.dowloadHelper.registerDownloaders();
        this.pluginConfiguration = new PluginConfiguration();
        this.pluginConfiguration.reconfiguration();
        this.pluginConfiguration.load();
        this.fileManager = new FileManager();
        this.fileManager.checkDir();
        this.fileManager.checkFiles();
        this.fileManager.checkSourceList();
        this.pluginsFileManager = new PluginsFileManager();
        this.sourceListManager = new SourceListManager();
        this.language = new Language();
        if(getPluginConfiguration().language_reconfiguration)
            this.language.reconfiguration();
        this.language.load();
        this.sourceFileLoader = new SourceFileLoader();
        this.sourceFileLoader.load();
        this.sourceManager = new SourceManager();
        if(getPluginConfiguration().auto_update)
            this.sourceManager.update();
        Bukkit.getConsoleSender().sendMessage(ChatText.prefix + language.text("plugin.start"));
    }

    @Override
    public void onDisable() 
    {
        this.pluginsFileManager.save();
        Bukkit.getConsoleSender().sendMessage(ChatText.prefix + language.text("plugin.stop"));
    }
    
    public static Main getInstance()
    {
        return instance;
    }
    
    public PluginConfiguration getPluginConfiguration()
    {
        return pluginConfiguration;
    }

    public PluginsFileManager getPluginsFileManager() 
    {
        return pluginsFileManager;
    }

    public SourceListManager getSourceListManager() 
    {
        return sourceListManager;
    }

    public Language getLanguage() 
    {
        return language;
    }

    public SourceFileLoader getSourceFileLoader() 
    {
        return sourceFileLoader;
    }

    public SourceManager getSourceManager() 
    {
        return sourceManager;
    }

    public DowloadHelper getDowloadHelper() 
    {
        return dowloadHelper;
    }
    
}
