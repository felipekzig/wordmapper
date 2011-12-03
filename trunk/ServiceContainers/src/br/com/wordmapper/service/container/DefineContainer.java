package br.com.wordmapper.service.container;

import com.google.gson.Gson;
import java.util.List;

/**
 *
 * @author Felipe e Bruno
 */
public class DefineContainer implements ContainerItf {

    private List<DefinitionContainer> Definitions;
    private String Word;
    private String IdMainDict;

    public DefineContainer() {
    }

    public void setIdMainDict(String IdMainDict) {
        this.IdMainDict = IdMainDict;
    }

    public String getIdMainDict() {
        return IdMainDict;
    }

    public void setWord(String strWord) {
        this.Word = strWord;
    }

    public String getWord() {
        return this.Word;
    }

    public void setDefinitions(List<DefinitionContainer> definitions) {
        this.Definitions = definitions;
    }

    public List<DefinitionContainer> getDefinitions() {
        return this.Definitions;
    }

    public String getJson() {
        return new Gson().toJson(this, this.getClass());
    }
}
