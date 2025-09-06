/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author huakh
 */
public class CharacterHSR {

    private int characterID;
    private String characterName;
    private ElementHSR elementID;
    private PathHSR pathID;
    private Rarity rarityID;
    private String imageIcon;
    private String imageFull;
    private String description;
    private Timestamp lastUpdate;
    private int status;
    private float pointE0;
    private float pointE1;
    private float pointE2;
    private float pointE3;
    private float pointE4;
    private float pointE5;
    private float pointE6;

    public CharacterHSR() {
    }
    
     

    public CharacterHSR(int characterID, String characterName, ElementHSR elementID, PathHSR pathID, Rarity rarityID, String imageIcon, String imageFull, String description, Timestamp lastUpdate, int status, float pointE0, float pointE1, float pointE2, float pointE3, float pointE4, float pointE5, float pointE6) {
        this.characterID = characterID;
        this.characterName = characterName;
        this.elementID = elementID;
        this.pathID = pathID;
        this.rarityID = rarityID;
        this.imageIcon = imageIcon;
        this.imageFull = imageFull;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.status = status;
        this.pointE0 = pointE0;
        this.pointE1 = pointE1;
        this.pointE2 = pointE2;
        this.pointE3 = pointE3;
        this.pointE4 = pointE4;
        this.pointE5 = pointE5;
        this.pointE6 = pointE6;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }

    public String getImageFull() {
        return imageFull;
    }

    public void setImageFull(String imageFull) {
        this.imageFull = imageFull;
    }

    public CharacterHSR(int characterID, String characterName, int status) {
        this.characterID = characterID;
        this.characterName = characterName;
        this.status = status;
    }

    public CharacterHSR(int characterID, String characterName) {
        this.characterID = characterID;
        this.characterName = characterName;
    }

    
    public CharacterHSR(int characterID, String characterName, Rarity rarityID, String imageIcon, String imageFull, float pointE0, float pointE1, float pointE2, float pointE3, float pointE4, float pointE5, float pointE6) {
        this.characterID = characterID;
        this.characterName = characterName;
        this.rarityID = rarityID;
        this.imageIcon = imageIcon;
        this.imageFull = imageFull;
        this.pointE0 = pointE0;
        this.pointE1 = pointE1;
        this.pointE2 = pointE2;
        this.pointE3 = pointE3;
        this.pointE4 = pointE4;
        this.pointE5 = pointE5;
        this.pointE6 = pointE6;
    }

    public CharacterHSR(int characterID, String characterName, Rarity rarityID, String imageIcon, String imageFull) {
        this.characterID = characterID;
        this.characterName = characterName;
        this.rarityID = rarityID;
        this.imageIcon = imageIcon;
        this.imageFull = imageFull;
    }


    public CharacterHSR(String characterName, Rarity rarityID, String imageIcon) {
        this.characterName = characterName;
        this.rarityID = rarityID;
        this.imageIcon = imageIcon;
    }



    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public ElementHSR getElementID() {
        return elementID;
    }

    public void setElementID(ElementHSR elementID) {
        this.elementID = elementID;
    }

    public PathHSR getPathID() {
        return pathID;
    }

    public void setPathID(PathHSR pathID) {
        this.pathID = pathID;
    }

    public Rarity getRarityID() {
        return rarityID;
    }

    public void setRarityID(Rarity rarityID) {
        this.rarityID = rarityID;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getPointE0() {
        return pointE0;
    }

    public void setPointE0(float pointE0) {
        this.pointE0 = pointE0;
    }
    

    public float getPointE1() {
        return pointE1;
    }

    public void setPointE1(float pointE1) {
        this.pointE1 = pointE1;
    }

    public float getPointE2() {
        return pointE2;
    }

    public void setPointE2(float pointE2) {
        this.pointE2 = pointE2;
    }

    public float getPointE3() {
        return pointE3;
    }

    public void setPointE3(float pointE3) {
        this.pointE3 = pointE3;
    }

    public float getPointE4() {
        return pointE4;
    }

    public void setPointE4(float pointE4) {
        this.pointE4 = pointE4;
    }

    public float getPointE5() {
        return pointE5;
    }

    public void setPointE5(float pointE5) {
        this.pointE5 = pointE5;
    }

    public float getPointE6() {
        return pointE6;
    }

    public void setPointE6(float pointE6) {
        this.pointE6 = pointE6;
    }

    @Override
    public String toString() {
        return "CharacterHSR{" + "characterID=" + characterID + ", characterName=" + characterName + ", elementID=" + elementID + ", pathID=" + pathID + ", rarityID=" + rarityID + ", imageIcon=" + imageIcon + ", imageFull=" + imageFull + ", description=" + description + ", lastUpdate=" + lastUpdate + ", status=" + status + ", pointE0=" + pointE0 + ", pointE1=" + pointE1 + ", pointE2=" + pointE2 + ", pointE3=" + pointE3 + ", pointE4=" + pointE4 + ", pointE5=" + pointE5 + ", pointE6=" + pointE6 + '}';
    }

 

}
