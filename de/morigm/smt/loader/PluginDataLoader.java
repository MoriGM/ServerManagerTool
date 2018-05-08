package de.morigm.smt.loader;

import de.morigm.smt.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public class PluginDataLoader {
    
    public void loadCommands()
    {
        
    }
    
    public void loadEvents()
    {
        
    }
    
    private void loadCommand(String command,CommandExecutor exec)
    {
        Main.getInstance().getCommand(command).setExecutor(exec);
    }
    
    private void loadEvent(Listener l)
    {
        Bukkit.getPluginManager().registerEvents(l, Main.getInstance());
    }
}
