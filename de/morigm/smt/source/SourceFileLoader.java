package de.morigm.smt.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import de.morigm.smt.Main;

public class SourceFileLoader 
{
    private List<SourceFolder> folder = new ArrayList<>();
    
    public void load()
    {
    	folder.clear();
        try 
        {
            for(SourceData sc : Main.getInstance().getSourceListManager().getSources())
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(Main.getInstance().getDowloadHelper().getDownloadStream(sc.getConnection() + "list")));
                while(reader.ready())
                {
                    String s = reader.readLine();
                    folder.add(new SourceFolder(s.split(" ")[0], sc.getConnection(),sc.getVersion()));
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public List<SourceFolder> getFolder() 
    {
        return folder;
    }

   
    
    
}
