/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawing.JavaFX.Demos.Configuration;

/**
 *
 * @author peter.nguyen
 */
public class ConfigurationUtility
{
    private static final String CONFIGURATION_FILE_PATH = "c:\\temp\\Configuration.xml";
    
    public static DataSource getDataSource()
    {
        ConfigurationFileParser configFileParser = new ConfigurationFileParser(ConfigurationUtility.CONFIGURATION_FILE_PATH);
        return configFileParser.getDataSource();         
    }    
}
