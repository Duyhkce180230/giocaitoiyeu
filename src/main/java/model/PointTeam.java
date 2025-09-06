/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author huakh
 */

public class PointTeam {
    private Team pointTeamID;
    private float pointChar1;
    private float pointLCChar1;
    private float pointChar2;
    private float pointLCChar2;
    private float pointChar3;
    private float pointLCChar3;
    private float pointChar4;
    private float pointLCChar4;

    public PointTeam() {
    }

    public PointTeam(Team pointTeamID, float pointChar1, float pointLCChar1, float pointChar2, float pointLCChar2, float pointChar3, float pointLCChar3, float pointChar4, float pointLCChar4) {
        this.pointTeamID = pointTeamID;
        this.pointChar1 = pointChar1;
        this.pointLCChar1 = pointLCChar1;
        this.pointChar2 = pointChar2;
        this.pointLCChar2 = pointLCChar2;
        this.pointChar3 = pointChar3;
        this.pointLCChar3 = pointLCChar3;
        this.pointChar4 = pointChar4;
        this.pointLCChar4 = pointLCChar4;
    }

    public Team getPointTeamID() {
        return pointTeamID;
    }

    public void setPointTeamID(Team pointTeamID) {
        this.pointTeamID = pointTeamID;
    }

    public float getPointChar1() {
        return pointChar1;
    }

    public void setPointChar1(float pointChar1) {
        this.pointChar1 = pointChar1;
    }

    public float getPointLCChar1() {
        return pointLCChar1;
    }

    public void setPointLCChar1(float pointLCChar1) {
        this.pointLCChar1 = pointLCChar1;
    }

    public float getPointChar2() {
        return pointChar2;
    }

    public void setPointChar2(float pointChar2) {
        this.pointChar2 = pointChar2;
    }

    public float getPointLCChar2() {
        return pointLCChar2;
    }

    public void setPointLCChar2(float pointLCChar2) {
        this.pointLCChar2 = pointLCChar2;
    }

    public float getPointChar3() {
        return pointChar3;
    }

    public void setPointChar3(float pointChar3) {
        this.pointChar3 = pointChar3;
    }

    public float getPointLCChar3() {
        return pointLCChar3;
    }

    public void setPointLCChar3(float pointLCChar3) {
        this.pointLCChar3 = pointLCChar3;
    }

    public float getPointChar4() {
        return pointChar4;
    }

    public void setPointChar4(float pointChar4) {
        this.pointChar4 = pointChar4;
    }

    public float getPointLCChar4() {
        return pointLCChar4;
    }

    public void setPointLCChar4(float pointLCChar4) {
        this.pointLCChar4 = pointLCChar4;
    }

    @Override
    public String toString() {
        return "PointTeam{" + "pointTeamID=" + pointTeamID + ", pointChar1=" + pointChar1 + ", pointLCChar1=" + pointLCChar1 + ", pointChar2=" + pointChar2 + ", pointLCChar2=" + pointLCChar2 + ", pointChar3=" + pointChar3 + ", pointLCChar3=" + pointLCChar3 + ", pointChar4=" + pointChar4 + ", pointLCChar4=" + pointLCChar4 + '}';
    }
    
    
    
}
