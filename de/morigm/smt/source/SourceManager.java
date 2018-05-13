package de.morigm.smt.source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

import de.morigm.smt.Main;


public class SourceManager {
    
    /*
    	Install a Plugin and all Libraries
    	
    	
        0: Ok
        1: Is installed
        2: Connection Error
        3: No Version
        4: Lib Error
    */
    public int install(String s)
    {
        List<String> folders = new ArrayList<>();
        for(SourceFolder folder : Main.getInstance().getSourceFileLoader().getFolder())
        {
            if(folder.name.equalsIgnoreCase(s))
                if(Main.getInstance().getPluginsFileManager().conatinsPlugin(folder.name))
                    return 1;
                else
                {
                    try 
                    {
                        //URL url = new URL(folder.website + folder.name + "/data");
                        //HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(Main.getInstance().getDowloadHelper().getDownloadStream(folder.website + folder.name + "/data")));
                        String file = "";
                        while(reader.ready())
                        {
                            String text = reader.readLine();
                            if(text.split(" ")[0].equalsIgnoreCase(folder.version))
                                file = text;
                        }
                        if(file.isEmpty())
                           return 3;
                        folders.add(s);
                        URL url = new URL(folder.website + folder.name + "/libs");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
                        {
                        	reader = new BufferedReader(new InputStreamReader(Main.getInstance().getDowloadHelper().getDownloadStream(folder.website + folder.name + "/libs")));
                            while(reader.ready())
                            {
                                String text = reader.readLine();
                                boolean b = check(text,folders);
                                if(!b)
                                    return 4;
                            }
                        }
                        for(String in : folders)
                            download(in);
                        return 0;
                    } 
                    catch (IOException ex) {
                        Logger.getLogger(SourceManager.class.getName()).log(Level.SEVERE, null, ex);
                        return 2;
                    }
                }
        }
        return 0;
    }
    
    
    /*
     	Check for an Update
     */
    public boolean check(String s,List<String> install)
    {
        for(SourceFolder folder : Main.getInstance().getSourceFileLoader().getFolder())
        {
            if(folder.name.equalsIgnoreCase(s))
                if(Main.getInstance().getPluginsFileManager().conatinsPlugin(folder.name))
                    return true;
                else
                {
                    try 
                    {
                        //URL url = new URL(folder.website + folder.name + "/data");
                        //HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(Main.getInstance().getDowloadHelper().getDownloadStream(folder.website + folder.name + "/data")));
                        String file = "";
                        while(reader.ready())
                        {
                            String text = reader.readLine();
                            if(text.split(" ")[0].equalsIgnoreCase(folder.version))
                                file = text;
                        }
                        if(file.isEmpty())
                           return false;
                        install.add(s);
                        //url = new URL(folder.website + folder.name + "/libs");
                        //con = (HttpURLConnection) url.openConnection();
                        reader = new BufferedReader(new InputStreamReader(Main.getInstance().getDowloadHelper().getDownloadStream(folder.website + folder.name + "/libs")));
                        while(reader.ready())
                        {
                            String text = reader.readLine();
                            boolean b = check(text,install);
                            if(!b)
                                return false;
                        }
                        return true;
                    } 
                    catch (IOException ex) {
                        Logger.getLogger(SourceManager.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                }
        }
        return true;
    }
    
    /*
       	Only install plugin without the libraries
        
        
        0: Ok
        1: Is installed
        2: Connection Error
        3: No Version
        4: Not exsits
    */
    public int download(String s)
    {
        for(SourceFolder folder : Main.getInstance().getSourceFileLoader().getFolder())
        {
            if(folder.name.equalsIgnoreCase(s))
                if(Main.getInstance().getPluginsFileManager().conatinsPlugin(folder.name))
                    return 1;
                else
                {
                    try 
                    {
                        //URL url = new URL(folder.website + folder.name + "/data");
                        //HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(Main.getInstance().getDowloadHelper().getDownloadStream(folder.website + folder.name + "/data")));
                        String file = "";
                        while(reader.ready())
                        {
                            String text = reader.readLine();
                            if(text.split(" ")[0].equalsIgnoreCase(folder.version))
                                file = text;
                        }
                        if(file.isEmpty())
                           return 3; 
                        //url = new URL(folder.website + folder.name + "/" + file.split(" ")[1]);
                        //con = (HttpURLConnection) url.openConnection();
                        InputStream in = Main.getInstance().getDowloadHelper().getDownloadStream(folder.website + folder.name + "/" + file.split(" ")[1]);
                        OutputStream out = new FileOutputStream("./plugins/" + file.split(" ")[1]);
                        IOUtils.copy(in, out);
                        Main.getInstance().getPluginsFileManager().addPlugin(s, file.split(" ")[2],file.split(" ")[1]);
                        return 0;
                    }
                    catch (IOException e)
                    {
                        return 2;
                    }
                }
        }
        return 4;
    }
    /*
       	Remove the Plugin
        
        
        0: OK
        1: Not installed
    */
    public int remove(String s)
    {
        if(Main.getInstance().getPluginsFileManager().conatinsPlugin(s))
        {
            File f = new File(".plugins/" + Main.getInstance().getPluginsFileManager().getFullName(s));
            if(f.exists())
                f.delete();
            Main.getInstance().getPluginsFileManager().removePlugin(s);
            return 0;
        }
        else
        {
            return 1;
        }
    }
    
    /*
     	Update the SourceFils
     */
    public void update()
    {
    	Main.getInstance().getSourceFileLoader().load();
    }
    
    /*
       	Upgrade all Plugins
        
        
        0: Okay
        1: 
        2: 
    */
    public int upgrade()
    {
        //for(String s : Main.getInstance().getPluginsFileManager().getPlugins())
            for(SourceFolder folder : Main.getInstance().getSourceFileLoader().getFolder())
            {
                if(!Main.getInstance().getPluginsFileManager().conatinsPlugin(folder.name))
                    continue;
                try 
                    {
                        //URL url = new URL(folder.website + folder.name + "/data");
                        //HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(Main.getInstance().getDowloadHelper().getDownloadStream(folder.website + folder.name + "/data")));
                        String file = "";
                        while(reader.ready())
                        {
                            String text = reader.readLine();
                            if(text.split(" ")[0].equalsIgnoreCase(folder.version))
                                file = text;
                        }
                        if(Integer.valueOf(Main.getInstance().getPluginsFileManager().getPluginVersion(folder.name)) < Integer.valueOf(file.split(" ")[2]))
                        {
                            remove(folder.name);
                            install(folder.name);
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
            }
        return 0;
    }
    
}
