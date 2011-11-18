
package br.com.wordmapper.service.container;

import java.util.List;
/**
 *
 * @author Felipe
 */
public class RequestContainer {
    
    private Integer IdTpOperation;
    
    private List<DefinitionContainer> Definitions;
    
    private String Word;
    private String IdMainDict;
    
    private String Error;
    
    public RequestContainer(){}
    
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

    public void setDefinitions(List<DefinitionContainer> definitions){
        this.Definitions = definitions;
    }    
    public List<DefinitionContainer> getDefinitions(){
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
