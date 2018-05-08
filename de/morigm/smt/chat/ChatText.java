package de.morigm.smt.chat;

import de.morigm.smt.Main;
import org.bukkit.ChatColor;

public class ChatText {

    public static final String name = Main.getInstance().getName(); 
    public static final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + name + ChatColor.DARK_GRAY + "]" + ChatColor.WHITE + " ";
    public static final String version = Main.getInstance().getDescription().getVersion();
    
}
