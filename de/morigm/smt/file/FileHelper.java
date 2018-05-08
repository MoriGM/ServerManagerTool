package de.morigm.smt.file;

import de.morigm.smt.data.Data;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHelper {
    
    public static boolean FileExists(String f)
    {
        return new File(f).exists();
    }
    
    public static boolean FileExists(File f)
    {
        return f.exists();
    }
    
    public static void createFile(String f) throws IOException
    {
        new File(f).createNewFile();
    }
    
    public static void createFile(File f) throws IOException
    {
        f.createNewFile();
    }
    
    public static void createFileType(FileType type)
    {
        if(type.equals(FileType.SOURCE))
            createSourceFile();
        if(type.equals(FileType.PLUGINS))
            createPluginsFile();
    }
    
    public static String getMD5(String f)
    {
        return getMD5(new File(f));
    }
    
    public static String getMD5(File f)
    {
        try 
        {
            return getMD5(new FileInputStream(f));
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String getMD5(InputStream in)
    {
        try 
        {
            MessageDigest md;
            md = MessageDigest.getInstance("MD5");
            byte[] byt = new byte[1024];
            int len;
            while((len = in.read()) > 0)
                md.update(byt, 0, len);
            BigInteger intg = new BigInteger(md.digest());
            return intg.toString(16);
        }
        catch (IOException | NoSuchAlgorithmException ex) 
        {
            Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void createSourceFile() 
    {
        try 
        {
            createFile(FileType.SOURCE.getFile());
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(FileType.SOURCE.getFile()));
            writer.write(Data.website + Data.website_folder + " " + Data.version_name);
            writer.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createPluginsFile()
    {
        try 
        {
            if(!FileExists(FileType.PLUGINS.getFile()))
                createFile(FileType.PLUGINS.getFile());
        } 
            catch (IOException ex) 
        {
            Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
