/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ingimar
 */
public enum View {
    ABDO,FACE,DORS,VENT,LABE;
    
    // replace with some guava-tech later ?
    public static List<String> getStringValues(){
        List<String> listOfViewString = new ArrayList<>();
        for (View view : Arrays.asList(values())) {
           listOfViewString.add(view.toString().toLowerCase());
        }
        return listOfViewString;
    }
}
