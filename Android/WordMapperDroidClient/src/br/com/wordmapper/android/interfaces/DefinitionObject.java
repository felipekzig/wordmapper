package br.com.wordmapper.android.interfaces;

public class DefinitionObject {
	
    private String Definition;
    private String Dictionary;
    
    public DefinitionObject(String strDict, String strDef){
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
        return Definition;
    }
}
