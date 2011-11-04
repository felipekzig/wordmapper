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
        
        if (!Definition.isEmpty()){
            Definition = Definition.trim().replaceAll("\\s+", " ");
            strDefFormated = Definition;
            /*
            int intColchetess = 0;
            int intIndexIni = 0;
            for(int i=0; i<=Definition.length();i++){
                if (Definition.charAt(i)=='['){
                    intColchetess++;
                    if (intColchetess==1) intIndexIni = i;
                }
                
                if (Definition.charAt(i)==']'){
                        intColchetess--;
                        if (intColchetess==0){
                            strDefFormated.replace(Definition.substring(intIndexIni, i), "");
                        }
                }
            }*/
        }
        
        return strDefFormated;
    }
    
}
