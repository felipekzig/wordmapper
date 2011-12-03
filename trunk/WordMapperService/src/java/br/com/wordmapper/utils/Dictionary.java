package br.com.wordmapper.utils;

import br.com.wordmapper.service.container.DefineContainer;
import br.com.wordmapper.service.container.DefinitionContainer;

import com.aonaware.services.webservices.DictService;
import com.aonaware.services.webservices.DictServiceSoap;
import com.aonaware.services.webservices.Definition;
import com.aonaware.services.webservices.WordDefinition;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *
 *@author Felipe e Bruno
 *
 */
public class Dictionary {

    private DictServiceSoap service;
    private DefineContainer requestJson;
    private DefineContainer responseJSON;

    public Dictionary(String json) {
        this.service = new DictService().getDictServiceSoap12();
        this.requestJson = new Gson().fromJson(json, DefineContainer.class);
    }

    private String getWord() {
        return requestJson.getWord();
    }

    private String getIdMainDict() {
        if (requestJson.getIdMainDict() != null) {
            return requestJson.getIdMainDict();
        }
        return "";
    }

    private List<DefinitionContainer> getDefinitions() {
        List<DefinitionContainer> response = new ArrayList<DefinitionContainer>();

        WordDefinition definitions;

        if (this.getIdMainDict().isEmpty()) {
            definitions = this.service.define(this.getWord());
        } else {
            definitions = this.service.defineInDict(this.getIdMainDict(), this.getWord());
        }

        List<Definition> lstDefinition = definitions.getDefinitions().getDefinition();

        for (Definition def : lstDefinition) {
            response.add(new DefinitionContainer(def.getDictionary().getName(), formatDefinitions(def.getWordDefinition(), def.getDictionary().getId())));
        }

        return response;
    }

    public String getServerInfo() {
        if (this.service != null) {
            return this.service.serverInfo();
        }

        return "Service Object is Null";
    }

    public void execute() {
        this.responseJSON = new DefineContainer();
        this.responseJSON.setDefinitions(this.getDefinitions());
    }

    public String getResponse() {
        return new Gson().toJson(this.responseJSON, DefineContainer.class);
    }

    private ArrayList<String> formatDefinitions(String definition, String idDict) {
        String regex;
        regex = "\\d(:|\\.)\\s.*?;";
        if(idDict=="gcide") regex = "\\d(:|\\.)\\s.*?\\.";
        
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(definition);

        ArrayList<String> formatedDefinitionList = new ArrayList<String>();

        while (m.find()) {
            formatedDefinitionList.add(m.group().replace(";", "").replaceFirst(":", "."));
        }
        
        return formatedDefinitionList;

    }
}
