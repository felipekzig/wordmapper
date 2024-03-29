
package com.aonaware.services.webservices;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * Word Dictionary Web Service
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.5-b04 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "DictService", targetNamespace = "http://services.aonaware.com/webservices/", wsdlLocation = "file:/C:/Users/Felipe/Java/WordMapper/WordMapperService/src/conf/xml-resources/web-services/DictService/wsdl/services.aonaware.com/DictService/DictService.asmx.wsdl")
public class DictService
    extends Service
{

    private final static URL DICTSERVICE_WSDL_LOCATION;
    private final static WebServiceException DICTSERVICE_EXCEPTION;
    private final static QName DICTSERVICE_QNAME = new QName("http://services.aonaware.com/webservices/", "DictService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/Felipe/Java/WordMapper/WordMapperService/src/conf/xml-resources/web-services/DictService/wsdl/services.aonaware.com/DictService/DictService.asmx.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DICTSERVICE_WSDL_LOCATION = url;
        DICTSERVICE_EXCEPTION = e;
    }

    public DictService() {
        super(__getWsdlLocation(), DICTSERVICE_QNAME);
    }

    public DictService(WebServiceFeature... features) {
        super(__getWsdlLocation(), DICTSERVICE_QNAME, features);
    }

    public DictService(URL wsdlLocation) {
        super(wsdlLocation, DICTSERVICE_QNAME);
    }

    public DictService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DICTSERVICE_QNAME, features);
    }

    public DictService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DictService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DictServiceSoap
     */
    @WebEndpoint(name = "DictServiceSoap")
    public DictServiceSoap getDictServiceSoap() {
        return super.getPort(new QName("http://services.aonaware.com/webservices/", "DictServiceSoap"), DictServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DictServiceSoap
     */
    @WebEndpoint(name = "DictServiceSoap")
    public DictServiceSoap getDictServiceSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://services.aonaware.com/webservices/", "DictServiceSoap"), DictServiceSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns DictServiceSoap
     */
    @WebEndpoint(name = "DictServiceSoap12")
    public DictServiceSoap getDictServiceSoap12() {
        return super.getPort(new QName("http://services.aonaware.com/webservices/", "DictServiceSoap12"), DictServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DictServiceSoap
     */
    @WebEndpoint(name = "DictServiceSoap12")
    public DictServiceSoap getDictServiceSoap12(WebServiceFeature... features) {
        return super.getPort(new QName("http://services.aonaware.com/webservices/", "DictServiceSoap12"), DictServiceSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns DictServiceHttpGet
     */
    @WebEndpoint(name = "DictServiceHttpGet")
    public DictServiceHttpGet getDictServiceHttpGet() {
        return super.getPort(new QName("http://services.aonaware.com/webservices/", "DictServiceHttpGet"), DictServiceHttpGet.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DictServiceHttpGet
     */
    @WebEndpoint(name = "DictServiceHttpGet")
    public DictServiceHttpGet getDictServiceHttpGet(WebServiceFeature... features) {
        return super.getPort(new QName("http://services.aonaware.com/webservices/", "DictServiceHttpGet"), DictServiceHttpGet.class, features);
    }

    /**
     * 
     * @return
     *     returns DictServiceHttpPost
     */
    @WebEndpoint(name = "DictServiceHttpPost")
    public DictServiceHttpPost getDictServiceHttpPost() {
        return super.getPort(new QName("http://services.aonaware.com/webservices/", "DictServiceHttpPost"), DictServiceHttpPost.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DictServiceHttpPost
     */
    @WebEndpoint(name = "DictServiceHttpPost")
    public DictServiceHttpPost getDictServiceHttpPost(WebServiceFeature... features) {
        return super.getPort(new QName("http://services.aonaware.com/webservices/", "DictServiceHttpPost"), DictServiceHttpPost.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DICTSERVICE_EXCEPTION!= null) {
            throw DICTSERVICE_EXCEPTION;
        }
        return DICTSERVICE_WSDL_LOCATION;
    }

}
