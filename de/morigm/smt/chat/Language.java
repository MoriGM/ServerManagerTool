package de.morigm.smt.chat;

import de.morigm.smt.Main;
import de.morigm.smt.data.Data;
import de.morigm.smt.file.FileHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

public class Language {
    
    private Properties prop;
    
    public void reconfiguration()
    {
        try 
        {
            if(!FileHelper.FileExists(new File(Data.system_folder + Main.getInstance().getPluginConfiguration().language)))
            {
                InputStream in = Main.getInstance().getResource("en-en.yml");
                FileOutputStream out;
                out = new FileOutputStream(new File(Data.system_folder + Main.getInstance().getPluginConfiguration().language));
                IOUtils.copy(in, out);
                out.close();
            }
            else
            {
                try
                {
                    String md5_one = FileHelper.getMD5(Main.getInstance().getResource("en-en.yml"));
                    String md5_two = FileHelper.getMD5(new File(Data.system_folder + Main.getInstance().getPluginConfiguration().language));
                    if(!md5_one.equals(md5_two))
                    {
                        InputStream in = Main.getInstance().getResource("en-en.yml");
                        FileOutputStream out;
                        out = new FileOutputStream(new File(Data.system_folder + Main.getInstance().getPluginConfiguration().language));
                        IOUtils.copy(in, out);
                        out.close();
                    }   
                    
                } 
                catch (Exception ex) 
                {
                    Logger.getLogger(Language.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        catch (IOException ex) 
        {
            Logger.getLogger(Language.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void load()
    {
        prop = new Properties();
        try {
            prop.load(new FileReader(new File(Data.system_folder + Main.getInstance().getPluginConfiguration().language)));
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Language.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String text(String text)
    {
        return prop.getProperty(text);
    }
    
}
