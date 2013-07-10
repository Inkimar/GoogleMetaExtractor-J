/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.util;

public enum NoView {

    NO_VIEW("no-view-no");

    private final String text;

    /**
     * @param text
     */
    private NoView(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    
}
