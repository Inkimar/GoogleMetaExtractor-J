/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.entities;

/**
 *
 * @author ingimar
 */
public class Image {
    
    private String naturalistUUID;
    private String fileName;
    private String latinName;
    private String sex;

    public Image() {
    }

    public Image(String fileName, String latinName, String sex) {
        this.fileName = fileName;
        this.latinName = latinName;
        this.sex = sex;
    }

    public String getNaturalistUUID() {
        return naturalistUUID;
    }

    public void setNaturalistUUID(String naturalistUUID) {
        this.naturalistUUID = naturalistUUID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return this.latinName+","+this.naturalistUUID;
    }
    
    
    
}
