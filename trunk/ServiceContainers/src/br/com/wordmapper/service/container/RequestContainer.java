/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wordmapper.service.container;

import com.google.gson.Gson;

/**
 *
 * @author Felipe
 */
public class RequestContainer implements ContainerItf {
   
    private Integer intCodeError;
    private String strDescError;
    
    public RequestContainer(){}
   
    public String getJson(){
        return new Gson().toJson(this, this.getClass());
    }
   
}
