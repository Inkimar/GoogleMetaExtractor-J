/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.util.enu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ingimar
 */
public enum View {
    ABDO,FACE,FACE2,DORS,DORS2,DORS3,DORS4,VENT,VENT1,VENT2,
    VENTR,LABE,LABE2,LABE3,LLABE,LABEL,LATE,LATE2,GENI,
    WING,HEAD,LEG,LEGS,LOBE,PRON,VARI,VARI2,VARI3,VARI4,VARI5,VARI6,MESO,POST,
    PALPS,PALP1,TARS,DIGE,NOTU,LABR,CHELI;
    
    public static boolean contains(String view){
        List<String> views = getViewListWithStringValues();
        boolean contains = views.contains(view);
        return contains;
    }
    
    // replace with some guava-tech later ?
    private static List<String> getViewListWithStringValues(){
        List<String> listOfViewString = new ArrayList<>();
        for (View view : Arrays.asList(values())) {
           listOfViewString.add(view.toString().toLowerCase());
        }
        return listOfViewString;
    }
}
