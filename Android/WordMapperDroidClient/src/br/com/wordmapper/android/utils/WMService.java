package br.com.wordmapper.android.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import br.com.wordmapper.service.container.RequestContainer;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class WMService {
	
	private Integer intIdTpOperation;
	
	private String responseJson;
	private RequestContainer responseObject;
	
	public final String urlWMService = "http://10.0.2.2:8080/WordMapperService/resources/WordMapper/";
	
	public static final int DEFINE_OPERATION = 0;
	public static final int SINGUP_OPERATION = 1;
	
	public String url;
	
	public WMService(){	}
		
	public void setTpOperation(Integer tpOperation){
		this.intIdTpOperation = tpOperation;
	}
	public Integer getTpOperation(){
		return this.intIdTpOperation;
	}
	
	public String getResponseJson(){
		return this.responseJson;
	}
	public RequestContainer getResponseJsonObject(){
		return responseObject;
	}
	
	public void requestServer(String json) throws Exception{
		
		String urlWS = this.urlWMService + this.intIdTpOperation.toString() + "/" + URLEncoder.encode(json);
		url = urlWS;
		try {
			
			HttpGet httpget = new HttpGet(urlWS);
			HttpResponse response = new DefaultHttpClient().execute(httpget);  
			 
			if (response.getEntity() != null) {
				InputStream instream = response.getEntity().getContent();
				this.responseJson = toString(instream);  
				
				//this.responseObject = this.parseJSON(responseJson);
				
				instream.close();
			}
		
		} catch (Exception e){
			throw e;
		}	
	
	}

	private String toString(InputStream is)	throws IOException{  
		  
		byte[] bytes = new byte[1024];  
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		int read;  
		while ((read = is.read(bytes)) > 0){  
			baos.write(bytes, 0, read);  
		}  
		return new String(baos.toByteArray());  
	}  
	
	private RequestContainer parseJSON(String json) throws Exception{
		try {
			return new Gson().fromJson(json, RequestContainer.class);
		} catch (Exception e) {
			throw e;
		}
	}
}
