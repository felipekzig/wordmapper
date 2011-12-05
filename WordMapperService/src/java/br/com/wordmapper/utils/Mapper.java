package br.com.wordmapper.utils;

import br.com.wordmapper.service.container.DefinitionContainer;
import br.com.wordmapper.service.container.MapperContainer;
import com.aonaware.services.webservices.Definition;
import com.aonaware.services.webservices.DictService;
import com.aonaware.services.webservices.DictServiceSoap;
import com.aonaware.services.webservices.WordDefinition;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Felipe e Bruno
 */
public class Mapper {

    private DictServiceSoap service;
    private MapperContainer requestJson;
    private MapperContainer responseJSON;
    private String definition;

    public Mapper(String json) {
        this.service = new DictService().getDictServiceSoap12();
        this.requestJson = new Gson().fromJson(json, MapperContainer.class);
    }

    private String getWord() {
        return requestJson.getWord();
    }

    private void define() {
        WordDefinition definitions;

        definitions = service.defineInDict("wn", this.getWord());

        this.definition = definitions.getDefinitions().getDefinition().get(0).getWordDefinition();
    }

    public ArrayList<String> getAntonyms() {
        String regex;
        regex = "\\[ant:\\s.*?\\]";

        definition = definition.replaceAll("\\s+", " ");

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(definition);

        ArrayList<String> antonyms = new ArrayList<String>();

        while (m.find()) {
            String[] ants = m.group().replace("[ant:", "").replace("]", "").split(",");
            antonyms.addAll(Arrays.asList(ants));
        }
        return antonyms;
    }

    public ArrayList<String> getSynonymous() {
        String regex;
        regex = "\\[syn:\\s.*?\\]";

        definition = definition.replaceAll("\\s+", " ");

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(definition);

        ArrayList<String> synonymous = new ArrayList<String>();

        while (m.find()) {
            String[] syns = m.group().replace("[syn:", "").replace("]", "").split(",");
            synonymous.addAll(Arrays.asList(syns));
        }
        return synonymous;
    }

    public void execute() {
        this.define();
        this.responseJSON = new MapperContainer();
        this.responseJSON.setAntonyms(this.getAntonyms());
        this.responseJSON.setSynonymous(this.getSynonymous());
    }

    public String getResponse() {
        return new Gson().toJson(this.responseJSON, MapperContainer.class);
    }
}