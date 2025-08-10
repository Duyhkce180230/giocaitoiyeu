/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author huakh
 */
public class Rarity {
    private int rarityID;
    private String StarName;

    public Rarity() {
    }

    public Rarity(int rarityID, String StarName) {
        this.rarityID = rarityID;
        this.StarName = StarName;
    }

    public int getRarityID() {
        return rarityID;
    }

    public void setRarityID(int rarityID) {
        this.rarityID = rarityID;
    }

    public String getStarName() {
        return StarName;
    }

    public void setStarName(String StarName) {
        this.StarName = StarName;
    }

    @Override
    public String toString() {
        return "Rarity{" + "rarityID=" + rarityID + ", StarName=" + StarName + '}';
    }
    
    
}
