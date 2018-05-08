package de.morigm.smt.source;

import de.morigm.smt.file.FileType;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SourceListManager 
{
    
    private List<SourceData> list = new ArrayList<>();

    public SourceListManager() 
    {
        try 
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileType.SOURCE.getFile())));
            while(reader.ready())
            {
                String[] s = reader.readLine().split(" ");
                SourceData sourceData = new SourceData(s[0], s[1]);
                list.add(sourceData);
            }
            reader.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(SourceListManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<SourceData> getSources()
    {
        return list;
    }
    
}
