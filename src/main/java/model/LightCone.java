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
public class LightCone {
    private int lightConeID;
    private String lightConeName;
    private PathHSR pathID;
    private Rarity rarityID;
    private CharacterHSR CharacterSignatureID;
    private String lightConeImage;
    private String description;
    private Timestamp lastUpdate;
    private int status;
    private float pointS1;
    private float pointS2;
    private float pointS3;
    private float pointS4;
    private float pointS5;
    
    public LightCone() {
    }

    public LightCone(int lightConeID, String lightConeImage) {
        this.lightConeID = lightConeID;
        this.lightConeImage = lightConeImage;
    }

    public LightCone(int lightConeID, String lightConeName, PathHSR pathID, 
            Rarity rarityID, CharacterHSR CharacterSignatureID, String lightConeImage, String description, Timestamp lastUpdate, int status, float pointS1, float pointS2, float pointS3, float pointS4, float pointS5) {
        this.lightConeID = lightConeID;
        this.lightConeName = lightConeName;
        this.pathID = pathID;
        this.rarityID = rarityID;
        this.CharacterSignatureID = CharacterSignatureID;
        this.lightConeImage = lightConeImage;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.status = status;
        this.pointS1 = pointS1;
        this.pointS2 = pointS2;
        this.pointS3 = pointS3;
        this.pointS4 = pointS4;
        this.pointS5 = pointS5;
    }

    public LightCone(int lightConeID, String lightConeName, PathHSR pathID, Rarity rarityID, CharacterHSR CharacterSignatureID, String lightConeImage, float pointS1, float pointS2, float pointS3, float pointS4, float pointS5) {
        this.lightConeID = lightConeID;
        this.lightConeName = lightConeName;
        this.pathID = pathID;
        this.rarityID = rarityID;
        this.CharacterSignatureID = CharacterSignatureID;
        this.lightConeImage = lightConeImage;
        this.pointS1 = pointS1;
        this.pointS2 = pointS2;
        this.pointS3 = pointS3;
        this.pointS4 = pointS4;
        this.pointS5 = pointS5;
    }


 

    public int getLightConeID() {
        return lightConeID;
    }

    public void setLightConeID(int lightConeID) {
        this.lightConeID = lightConeID;
    }

    public String getLightConeName() {
        return lightConeName;
    }

    public void setLightConeName(String lightConeName) {
        this.lightConeName = lightConeName;
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

    public CharacterHSR getCharacterSignatureID() {
        return CharacterSignatureID;
    }

    public void setCharacterSignatureID(CharacterHSR CharacterSignatureID) {
        this.CharacterSignatureID = CharacterSignatureID;
    }

    public String getLightConeImage() {
        return lightConeImage;
    }

    public void setLightConeImage(String lightConeImage) {
        this.lightConeImage = lightConeImage;
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

    public float getPointS1() {
        return pointS1;
    }

    public void setPointS1(float pointS1) {
        this.pointS1 = pointS1;
    }

    public float getPointS2() {
        return pointS2;
    }

    public void setPointS2(float pointS2) {
        this.pointS2 = pointS2;
    }

    public float getPointS3() {
        return pointS3;
    }

    public void setPointS3(float pointS3) {
        this.pointS3 = pointS3;
    }

    public float getPointS4() {
        return pointS4;
    }

    public void setPointS4(float pointS4) {
        this.pointS4 = pointS4;
    }

    public float getPointS5() {
        return pointS5;
    }

    public void setPointS5(float pointS5) {
        this.pointS5 = pointS5;
    }
    

    @Override
    public String toString() {
        return "LightCone{" + "lightConeID=" + lightConeID + ", lightConeName=" + lightConeName + ", pathID=" + pathID + ", rarityID=" + rarityID + ", CharacterSignatureID=" + CharacterSignatureID + ", lightConeImage=" + lightConeImage + ", description=" + description + ", lastUpdate=" + lastUpdate + ", status=" + status + '}';
    }

}
