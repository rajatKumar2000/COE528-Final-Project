/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author rajat
 */
public class GoldLevel extends Level {
    
    @Override
    public int levelFee() 
    {
        return 10;
    }
    
    @Override
    public String levelName() 
    {
        return "Gold";
    }
}
