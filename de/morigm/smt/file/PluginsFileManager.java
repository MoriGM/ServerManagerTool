package de.morigm.smt.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PluginsFileManager {

    private List<String> plugins = new ArrayList<>();
    
    public PluginsFileManager()
    {
        try 
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileType.PLUGINS.getFile())));
            while(reader.ready())
                plugins.add(reader.readLine());
            reader.close();
        } 
        catch (IOException ex)
        {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void save()
    {
        try 
        {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(FileType.PLUGINS.getFile()));
            for(String s : plugins)
            {
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        } 
        catch (IOException ex)
        {
            Logger.getLogger(PluginsFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<String> getPlugins()
    {
        return this.plugins;
    }
    
    public void addPlugin(String name,String version,String full)
    {
        plugins.add(name + ";" + version + ";" + full);
    }
    
    public void removePlugin(String name)
    {
        for(String s : plugins.toArray(new String[plugins.size()]))
            if(s.replaceAll(";", " ").split(" ")[0].equals(name))
                this.plugins.remove(s);
    }
    
    public boolean conatinsPlugin(String name)
    {
        for(String s : plugins.toArray(new String[plugins.size()]))
            if(s.replaceAll(";", " ").split(" ")[0].equals(name))
                return true;
        return false;
    }
    
    public String getPluginVersion(String name)
    {
        for(String s : plugins.toArray(new String[plugins.size()]))
            if(s.replaceAll(";", " ").split(" ")[0].equals(name))
                return s.replaceAll(";", " ").split(" ")[1];
        return null;
    }
    
    public String getFullName(String name)
    {
        for(String s : plugins.toArray(new String[plugins.size()]))
            if(s.replaceAll(";", " ").split(" ")[0].equals(name))
                return s.replaceAll(";", " ").split(" ")[2];
        return null;
    }
    
}
