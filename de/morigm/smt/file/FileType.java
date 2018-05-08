package de.morigm.smt.file;

import de.morigm.smt.data.Data;
import java.io.File;

public enum FileType {
    
    SOURCE(new File(Data.system_folder + "source.list")),PLUGINS(new File(Data.system_folder + "plugins.list"))  ;
    
    private File file;
    
    private FileType(File f)
    {
        this.file = f;
    }
    
    public File getFile()
    {
        return file;
    }
    
}
