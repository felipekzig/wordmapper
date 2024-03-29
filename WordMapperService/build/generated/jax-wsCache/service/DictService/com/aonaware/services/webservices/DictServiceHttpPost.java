
package com.aonaware.services.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.5-b04 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "DictServiceHttpPost", targetNamespace = "http://services.aonaware.com/webservices/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DictServiceHttpPost {


    /**
     * Show remote server information
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "ServerInfo")
    @WebResult(name = "string", targetNamespace = "http://services.aonaware.com/webservices/", partName = "Body")
    public String serverInfo();

    /**
     * Returns a list of available dictionaries
     * 
     * @return
     *     returns com.aonaware.services.webservices.ArrayOfDictionary
     */
    @WebMethod(operationName = "DictionaryList")
    @WebResult(name = "ArrayOfDictionary", targetNamespace = "http://services.aonaware.com/webservices/", partName = "Body")
    public ArrayOfDictionary dictionaryList();

    /**
     * Returns a list of advanced dictionaries (e.g. translating dictionaries)
     * 
     * @return
     *     returns com.aonaware.services.webservices.ArrayOfDictionary
     */
    @WebMethod(operationName = "DictionaryListExtended")
    @WebResult(name = "ArrayOfDictionary", targetNamespace = "http://services.aonaware.com/webservices/", partName = "Body")
    public ArrayOfDictionary dictionaryListExtended();

    /**
     * Show information about the specified dictionary
     * 
     * @param dictId
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "DictionaryInfo")
    @WebResult(name = "string", targetNamespace = "http://services.aonaware.com/webservices/", partName = "Body")
    public String dictionaryInfo(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "dictId")
        String dictId);

    /**
     * Define given word, returning definitions from all dictionaries
     * 
     * @param word
     * @return
     *     returns com.aonaware.services.webservices.WordDefinition
     */
    @WebMethod(operationName = "Define")
    @WebResult(name = "WordDefinition", targetNamespace = "http://services.aonaware.com/webservices/", partName = "Body")
    public WordDefinition define(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "word")
        String word);

    /**
     * Define given word, returning definitions from specified dictionary
     * 
     * @param dictId
     * @param word
     * @return
     *     returns com.aonaware.services.webservices.WordDefinition
     */
    @WebMethod(operationName = "DefineInDict")
    @WebResult(name = "WordDefinition", targetNamespace = "http://services.aonaware.com/webservices/", partName = "Body")
    public WordDefinition defineInDict(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "dictId")
        String dictId,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "word")
        String word);

    /**
     * Return list of all available strategies on the server
     * 
     * @return
     *     returns com.aonaware.services.webservices.ArrayOfStrategy
     */
    @WebMethod(operationName = "StrategyList")
    @WebResult(name = "ArrayOfStrategy", targetNamespace = "http://services.aonaware.com/webservices/", partName = "Body")
    public ArrayOfStrategy strategyList();

    /**
     * Look for matching words in all dictionaries using the given strategy
     * 
     * @param strategy
     * @param word
     * @return
     *     returns com.aonaware.services.webservices.ArrayOfDictionaryWord
     */
    @WebMethod(operationName = "Match")
    @WebResult(name = "ArrayOfDictionaryWord", targetNamespace = "http://services.aonaware.com/webservices/", partName = "Body")
    public ArrayOfDictionaryWord match(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "word")
        String word,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "strategy")
        String strategy);

    /**
     * Look for matching words in the specified dictionary using the given strategy
     * 
     * @param dictId
     * @param strategy
     * @param word
     * @return
     *     returns com.aonaware.services.webservices.ArrayOfDictionaryWord
     */
    @WebMethod(operationName = "MatchInDict")
    @WebResult(name = "ArrayOfDictionaryWord", targetNamespace = "http://services.aonaware.com/webservices/", partName = "Body")
    public ArrayOfDictionaryWord matchInDict(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "dictId")
        String dictId,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "word")
        String word,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "strategy")
        String strategy);

}
