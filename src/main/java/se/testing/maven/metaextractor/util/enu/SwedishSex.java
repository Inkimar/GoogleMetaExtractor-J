/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.util.enu;

import java.util.EnumSet;

/**
 *
 * @author ingimar
 */
public enum SwedishSex {
    hane,hona,blank;

    public static EnumSet getSubset(){
        return EnumSet.of(SwedishSex.hane, SwedishSex.hona);
    }
    
}
