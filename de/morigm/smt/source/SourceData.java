package de.morigm.smt.source;

public class SourceData 
{
    
    private final String connection,version;

    public SourceData(String connection, String version) 
    {
        this.connection = connection;
        this.version = version;
    }
    
    public String getConnection()
    {
        return connection;
    }
    
    public String getVersion()
    {
        return version;
    }
    
}
