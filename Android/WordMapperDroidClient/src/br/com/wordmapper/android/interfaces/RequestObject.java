package br.com.wordmapper.android.interfaces;

import java.util.List;

public class RequestObject {
    
    private Integer IdTpOperation;
    
    private List<DefinitionObject> Definitions;
    
    private String Word;
    private String IdMainDict;
    
    private String Error;
    
    public RequestObject(){}
    
    public void setIdMainDict(String IdMainDict){
        this.IdMainDict = IdMainDict;
    }  
    public String getIdMainDict() {
        return IdMainDict;
    }
    
    public void setError(String error){
        this.Error = error;
    }   
    public String getError(){
        return this.Error;
    }
  
    public void setWord(String strWord) {
        this.Word = strWord;
    }
    public String getWord() {
        return this.Word;
    }

    public void setDefinitions(List<DefinitionObject> definitions){
        this.Definitions = definitions;
    }    
    public List<DefinitionObject> getDefinitions(){
        return this.Definitions;
    }
    
    public Integer getTpOperationDefine(){
        return 1;
    }
    public Integer getTpOperationMapper(){
        return 2;
    }
     
    public Boolean setIdTpOperation(Integer operation) {
        if (this.getTpOperationDefine()!=operation && this.getTpOperationMapper()!=operation) return false;
        
        this.IdTpOperation = operation;
        
        return true;
    }
    public Integer getIdTpOperation() {
        return this.IdTpOperation;
    }
    
}