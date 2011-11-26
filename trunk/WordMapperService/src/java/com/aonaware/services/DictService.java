/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aonaware.services;

import javax.jws.WebService;
import javax.xml.ws.BindingType;

/**
 *
 * @author Felipe
 */
@WebService(serviceName = "DictService", portName = "DictServiceSoap12", endpointInterface = "com.aonaware.services.webservices.DictServiceSoap", targetNamespace = "http://services.aonaware.com/webservices/", wsdlLocation = "WEB-INF/wsdl/DictService/services.aonaware.com/DictService/DictService.asmx.wsdl")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class DictService {

    public java.lang.String serverInfo() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public com.aonaware.services.webservices.ArrayOfDictionary dictionaryList() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public com.aonaware.services.webservices.ArrayOfDictionary dictionaryListExtended() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public java.lang.String dictionaryInfo(java.lang.String dictId) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public com.aonaware.services.webservices.WordDefinition define(java.lang.String word) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public com.aonaware.services.webservices.WordDefinition defineInDict(java.lang.String dictId, java.lang.String word) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public com.aonaware.services.webservices.ArrayOfStrategy strategyList() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public com.aonaware.services.webservices.ArrayOfDictionaryWord match(java.lang.String word, java.lang.String strategy) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public com.aonaware.services.webservices.ArrayOfDictionaryWord matchInDict(java.lang.String dictId, java.lang.String word, java.lang.String strategy) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
