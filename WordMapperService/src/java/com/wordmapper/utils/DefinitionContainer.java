/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordmapper.utils;

/**
 *
 * @author Felipe
 */
public class DefinitionContainer {
    
    private String Definition;
    private String Dictionary;
    
    public DefinitionContainer(String strDict, String strDef){
        this.setDefinition(strDef);
        this.setDictionary(strDict);
    }

    public void setDefinition(String Definition) {
        this.Definition = Definition;
    }
    public String getDefinition() {
        return Definition;
    }

    public void setDictionary(String Dictionary) {
        this.Dictionary = Dictionary;
    }
    public String getDictionary() {
        return Dictionary;
    }
    
}
