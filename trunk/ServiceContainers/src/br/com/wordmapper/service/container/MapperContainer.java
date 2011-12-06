package br.com.wordmapper.service.container;

import com.google.gson.Gson;
import java.util.ArrayList;

/**
 *
 * @author Felipe e Bruno
 */
public class MapperContainer implements ContainerItf {

    private String Word;
    private ArrayList<String> antonyms;
    private ArrayList<String> synonymous;

    public String getWord() {
        return this.Word;
    }

    public void setWord(String word) {
        this.Word = word;
    }

    public ArrayList<String> getAntonyms() {
        return this.antonyms;
    }

    public void setAntonyms(ArrayList<String> ant) {
        this.antonyms = ant;
    }

    public ArrayList<String> getSynonymous() {
        return this.synonymous;
    }

    public void setSynonymous(ArrayList<String> sin) {
        this.synonymous = sin;
    }

    public String getJson() {
        return new Gson().toJson(this, this.getClass());
    }
}
