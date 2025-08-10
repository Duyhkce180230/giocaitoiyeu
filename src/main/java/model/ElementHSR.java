/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author huakh
 */
public class ElementHSR {
    private int elementID;
    private String elementName;

    public ElementHSR() {
    }

    public ElementHSR(int elementID, String elementName) {
        this.elementID = elementID;
        this.elementName = elementName;
    }

    public int getElementID() {
        return elementID;
    }

    public void setElementID(int elementID) {
        this.elementID = elementID;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public String toString() {
        return "ElementHSR{" + "elementID=" + elementID + ", elementName=" + elementName + '}';
    }

    
    
}
