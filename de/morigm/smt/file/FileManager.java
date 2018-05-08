package de.morigm.smt.file;

import de.morigm.smt.data.Data;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {
    
    public void checkDir()
    {
        File dir = new File(Data.system_folder);
        if(!dir.exists())
            dir.mkdirs();
    }
    
    public void checkFiles()
    {
        for(FileType f : FileType.values())
            if(!FileHelper.FileExists(f.getFile()))
                FileHelper.createFileType(f);
    }
    
    public void checkSourceList()
    {
        try 
        {
            List<String> list;
            list = new ArrayList<>();
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(FileType.SOURCE.getFile()));
            while(reader.ready())
            {
                String s = reader.readLine();
                if(!s.split(" ")[1].equals(Data.version_name))
                    list.add(s.split(" ")[0] + " " + Data.version_name);
                else
                    list.add(s);
            }
            
            if(list.size() >= 1)
                saveListToFile(list, FileType.SOURCE.getFile());
            reader.close();
        } 
        catch (IOException ex)
        {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void saveListToFile(List<String> list,File f)
    {
        saveArrayToFile(list.toArray(new String[list.size()]), f);
    }
    
    public static void saveArrayToFile(String[] s,File f)
    {
        try 
        {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(f));
            for(String g : s)
            {
                writer.write(g);
                writer.newLine();
            }
            writer.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
