package coe528.project;

/**
 *
 * @author rajat
 */
public class PlatinumLevel extends Level {
    
    @Override
    public int levelFee() 
    {
        return 0;
    }
    
    @Override
    public String levelName() 
    {
        return "Platinum";
    }
}
