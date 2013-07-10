/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ingimar
 */
public class ViewsTest {

    public ViewsTest() {
    }

    @Test
    public void how_many_views() {
        int count = View.values().length;
        assertEquals("Should be 5 views ( as of 2013-07-09 ), do not test all 40", 40, count);
    }

    @Test
    public void is_abdo_a_view() {
        String ecpected = "abdo";

        assertTrue(View.contains(ecpected));
    }

    @Test
    public void is_face_a_view() {
        String expected = "face";

        assertTrue(View.contains(expected));
    }

    @Test
    public void is_dors_a_view() {
        String expected = "dors";

        assertTrue(View.contains(expected));
    }

    @Test
    public void is_vent_a_view() {
        String expected = "vent";

        assertTrue(View.contains(expected));
    }

    @Test
    public void is_labe_a_view() {
        String expected = "labe";

        assertTrue(View.contains(expected));
    }

    @Test
    public void is_not_part_of_view() {
        String expected = "no-no-no-view";

        assertFalse(View.contains(expected));
    }
}