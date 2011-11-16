
package br.com.wordmapper.utils;

import com.aonaware.services.webservices.Definition;
import com.aonaware.services.webservices.WordDefinition;
import com.aonaware.services.webservices.DictService;

import com.aonaware.services.webservices.DictServiceSoap;
import java.util.ArrayList;
import java.util.List;

/*
 * 
 *@author Felipe e Bruno
 *
*/
public class Dictionary {
    private String _strWord;
    private String _strIdMainDict;
    
    private DictServiceSoap service;
    
    public Dictionary(){
        this.service = new DictService().getDictServiceSoap12();
        
        this._strIdMainDict = "";
    }
    
    public String getWord(){
        return this._strWord;
    }
    public void setWord(String word){
        this._strWord = word;
    }
    
    public String getIdMainDict(){
        return this._strIdMainDict;
    }
    public void setIdMainDict(String idMainDict){
        this._strIdMainDict = idMainDict;
    }
    
    public List<DefinitionContainer> getDefinitions(){
        List<DefinitionContainer> response = new ArrayList<DefinitionContainer>();
        
        WordDefinition definitions;     
      
        if (this.getIdMainDict().isEmpty()){
            definitions = this.service.define(this.getWord());
        } else {
            definitions = this.service.defineInDict(this.getIdMainDict(), this.getWord());
        }
         
        List<Definition> lstDefinition = definitions.getDefinitions().getDefinition();
        
        for(Definition def : lstDefinition){        
            response.add(new DefinitionContainer(def.getDictionary().getName(), def.getWordDefinition()));
        }
       
        return response;
    }
   
    public String getServerInfo(){
        if (this.service != null) return this.service.serverInfo();
        
        return "Service Object is Null";
    }
}


