
package com.wordmapper.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.wordmapper.utils.Dictionary;


/**
 * REST Web Service
 *
 * @author Felipe
 * http://localhost:8080/WordMapperService/resources/WordMapper/%7B%22Word%22:%22need%22,%22IdTpOperation%22:1,%22IdMainDict%22:%22wn%22%7D
 */
@Path("WordMapper")
public class WordMapperResource {
    
    private RequestContainer requestJson;
    private RequestContainer responseJSON;
    
    private Dictionary Dict;

    @GET
    @Path( "{json}" )
    @Produces("application/json")
    public String getJson(@PathParam( "json" ) String json) {
                    
        this.responseJSON = new RequestContainer();
   
        try{
            this.requestJson = new Gson().fromJson(json, RequestContainer.class);

            this._defineOperation();           
            this._executeOperation();
            
        } catch(Exception e){
            this.responseJSON.setError(e.getCause().toString() + "\n Message: " + e.getMessage());
            return e.getMessage();
        }
        
        return this.getResponseJson();
    }
    
    private Boolean _defineOperation(){
        try{
            this.Dict = new Dictionary();

            this.Dict.setWord(this.requestJson.getWord());
            if (this.requestJson.getIdTpOperation()==this.requestJson.getTpOperationDefine() && this.requestJson.getIdMainDict()!=null) 
                this.Dict.setIdMainDict(this.requestJson.getIdMainDict());
            
            return true;
        } catch(Exception e) {
            this.responseJSON.setError(e.getCause().toString() + "\n Message: " + e.getMessage());
            return false;
        }
    }
    
    private Boolean _executeOperation(){
        try {
            if (this.requestJson.getIdTpOperation()==this.requestJson.getIdTpOperation()){
                this._defineWord();
            } else {
                this._mappingWord();
            }
        } catch(Exception e) {
            this.responseJSON.setError(e.getCause().toString() + "\n Message: " + e.getMessage());
            return false;
        }
        
        return true;
    }
    
    private void _defineWord(){
        this.responseJSON.setDefinitions(this.Dict.getDefinitions());
    }
     
    private void _mappingWord(){
        
    }           
    public String getResponseJson(){
        return new Gson().toJson(this.responseJSON, RequestContainer.class);
    }

}
