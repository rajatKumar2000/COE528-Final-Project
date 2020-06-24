package coe528.project;

/**
 *
 * @author rajat
 */
public class SilverLevel extends Level {
    
    @Override
    public int levelFee() 
    {
        return 20;
    }
    
    @Override
    public String levelName() 
    {
        return "Silver";
    }
}
