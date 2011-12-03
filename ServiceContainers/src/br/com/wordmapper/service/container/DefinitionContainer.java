package br.com.wordmapper.service.container;

import com.google.gson.Gson;
import java.util.ArrayList;

/**
 *
 * @author Felipe e Bruno
 */
public class DefinitionContainer implements ContainerItf {

    private ArrayList<String> Definition;
    private String Dictionary;

    public DefinitionContainer(String strDict, ArrayList<String> listDef) {
        this.setDefinition(listDef);
        this.setDictionary(strDict);
    }

    public void setDefinition(ArrayList<String> listDef) {
        this.Definition = listDef;
    }

    public ArrayList<String> getDefinition() {
        return Definition;
    }

    public void setDictionary(String strDictionary) {
        this.Dictionary = strDictionary;
    }

    public String getDictionary() {
        return Dictionary;
    }

    public String getJson() {
        return new Gson().toJson(this, this.getClass());
    }
}
