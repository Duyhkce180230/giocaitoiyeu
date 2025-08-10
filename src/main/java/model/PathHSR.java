/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author huakh
 */
public class PathHSR {
    private int pathID;
    private String pathName;

    public PathHSR() {
    }

    public PathHSR(int pathID, String pathName) {
        this.pathID = pathID;
        this.pathName = pathName;
    }

    public int getPathID() {
        return pathID;
    }

    public void setPathID(int pathID) {
        this.pathID = pathID;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    @Override
    public String toString() {
        return "PathHSR{" + "pathID=" + pathID + ", pathName=" + pathName + '}';
    }

    
    
}
