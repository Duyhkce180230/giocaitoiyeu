/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author huakh
 */
public class Team {
    private int teamID;
    private CharacterHSR idChar1;
    private String eChar1;
    private LightCone idLCChar1;
    private String sLCChar1;
    private CharacterHSR idChar2;
    private String eChar2;
    private LightCone idLCChar2;
    private String sLCChar2;
    private CharacterHSR idChar3;
    private String eChar3;
    private LightCone idLCChar3;
    private String sLCChar3;
    private CharacterHSR idChar4;
    private String eChar4;
    private LightCone idLCChar4;
    private String sLCChar4;
    private int totalCycle;
    private String note;
    private String linkSetup;
    private String half;

    public Team() {
    }

    public Team(int teamID, CharacterHSR idChar1, String eChar1, LightCone idLCChar1, String sLCChar1, CharacterHSR idChar2, String eChar2, LightCone idLCChar2, String sLCChar2, CharacterHSR idChar3, String eChar3, LightCone idLCChar3, String sLCChar3, CharacterHSR idChar4, String eChar4, LightCone idLCChar4, String sLCChar4, int totalCycle, String note, String linkSetup, String half) {
        this.teamID = teamID;
        this.idChar1 = idChar1;
        this.eChar1 = eChar1;
        this.idLCChar1 = idLCChar1;
        this.sLCChar1 = sLCChar1;
        this.idChar2 = idChar2;
        this.eChar2 = eChar2;
        this.idLCChar2 = idLCChar2;
        this.sLCChar2 = sLCChar2;
        this.idChar3 = idChar3;
        this.eChar3 = eChar3;
        this.idLCChar3 = idLCChar3;
        this.sLCChar3 = sLCChar3;
        this.idChar4 = idChar4;
        this.eChar4 = eChar4;
        this.idLCChar4 = idLCChar4;
        this.sLCChar4 = sLCChar4;
        this.totalCycle = totalCycle;
        this.note = note;
        this.linkSetup = linkSetup;
        this.half = half;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public CharacterHSR getIdChar1() {
        return idChar1;
    }

    public void setIdChar1(CharacterHSR idChar1) {
        this.idChar1 = idChar1;
    }

    public String geteChar1() {
        return eChar1;
    }

    public void seteChar1(String eChar1) {
        this.eChar1 = eChar1;
    }

    public LightCone getIdLCChar1() {
        return idLCChar1;
    }

    public void setIdLCChar1(LightCone idLCChar1) {
        this.idLCChar1 = idLCChar1;
    }

    public String getsLCChar1() {
        return sLCChar1;
    }

    public void setsLCChar1(String sLCChar1) {
        this.sLCChar1 = sLCChar1;
    }

    public CharacterHSR getIdChar2() {
        return idChar2;
    }

    public void setIdChar2(CharacterHSR idChar2) {
        this.idChar2 = idChar2;
    }

    public String geteChar2() {
        return eChar2;
    }

    public void seteChar2(String eChar2) {
        this.eChar2 = eChar2;
    }

    public LightCone getIdLCChar2() {
        return idLCChar2;
    }

    public void setIdLCChar2(LightCone idLCChar2) {
        this.idLCChar2 = idLCChar2;
    }

    public String getsLCChar2() {
        return sLCChar2;
    }

    public void setsLCChar2(String sLCChar2) {
        this.sLCChar2 = sLCChar2;
    }

    public CharacterHSR getIdChar3() {
        return idChar3;
    }

    public void setIdChar3(CharacterHSR idChar3) {
        this.idChar3 = idChar3;
    }

    public String geteChar3() {
        return eChar3;
    }

    public void seteChar3(String eChar3) {
        this.eChar3 = eChar3;
    }

    public LightCone getIdLCChar3() {
        return idLCChar3;
    }

    public void setIdLCChar3(LightCone idLCChar3) {
        this.idLCChar3 = idLCChar3;
    }

    public String getsLCChar3() {
        return sLCChar3;
    }

    public void setsLCChar3(String sLCChar3) {
        this.sLCChar3 = sLCChar3;
    }

    public CharacterHSR getIdChar4() {
        return idChar4;
    }

    public void setIdChar4(CharacterHSR idChar4) {
        this.idChar4 = idChar4;
    }

    public String geteChar4() {
        return eChar4;
    }

    public void seteChar4(String eChar4) {
        this.eChar4 = eChar4;
    }

    public LightCone getIdLCChar4() {
        return idLCChar4;
    }

    public void setIdLCChar4(LightCone idLCChar4) {
        this.idLCChar4 = idLCChar4;
    }

    public String getsLCChar4() {
        return sLCChar4;
    }

    public void setsLCChar4(String sLCChar4) {
        this.sLCChar4 = sLCChar4;
    }

    public int getTotalCycle() {
        return totalCycle;
    }

    public void setTotalCycle(int totalCycle) {
        this.totalCycle = totalCycle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLinkSetup() {
        return linkSetup;
    }

    public void setLinkSetup(String linkSetup) {
        this.linkSetup = linkSetup;
    }

    public String getHalf() {
        return half;
    }

    public void setHalf(String half) {
        this.half = half;
    }

    @Override
    public String toString() {
        return "Team{" + "teamID=" + teamID + ", idChar1=" + idChar1 + ", eChar1=" + eChar1 + ", idLCChar1=" + idLCChar1 + ", sLCChar1=" + sLCChar1 + ", idChar2=" + idChar2 + ", eChar2=" + eChar2 + ", idLCChar2=" + idLCChar2 + ", sLCChar2=" + sLCChar2 + ", idChar3=" + idChar3 + ", eChar3=" + eChar3 + ", idLCChar3=" + idLCChar3 + ", sLCChar3=" + sLCChar3 + ", idChar4=" + idChar4 + ", eChar4=" + eChar4 + ", idLCChar4=" + idLCChar4 + ", sLCChar4=" + sLCChar4 + ", totalCycle=" + totalCycle + ", note=" + note + ", linkSetup=" + linkSetup + ", half=" + half + '}';
    }

    
    
    
}
