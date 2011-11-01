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

    public void setDefinition(String strDefinition) {
        this.Definition = this.formatDefinition(strDefinition);
    }
    public String getDefinition() {
        return Definition;
    }

    public void setDictionary(String strDictionary) {
        this.Dictionary = strDictionary;
    }
    public String getDictionary() {
        return Dictionary;
    }
    
    private String formatDefinition(String Definition){
        String strDefFormated = "";
        
        if (!Definition.isEmpty())  strDefFormated = Definition.trim().replaceAll("(\\[.+?\\])", "").replaceAll("\\s+", " ");
        
        return strDefFormated;
    }
    
}
