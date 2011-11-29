package br.com.wordmapper.android.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public abstract class WMService {
	
	private Integer intIdTpOperation;
	
	private String responseJson;
	
	private final String urlWMService = "http://192.168.2.44:8080/WordMapperService/resources/WordMapper/";
	
	protected static final int DEFINE_OPERATION = 0;
	protected static final int SINGUP_OPERATION = 1;
	
	public WMService(){	}
		
	protected void setTpOperation(Integer tpOperation){
		this.intIdTpOperation = tpOperation;
	}
	protected Integer getTpOperation(){
		return this.intIdTpOperation;
	}
	
	public String getResponseJson(){
		return this.responseJson;
	}
	
	protected void requestServer(String json) throws Exception{
		
		String urlWS = this.urlWMService + this.intIdTpOperation.toString() + "/" + URLEncoder.encode(json);
		
		try {
			
			HttpResponse response = new DefaultHttpClient().execute(new HttpGet(urlWS));  
			 
			if (response.getEntity() != null) {
				InputStream instream = response.getEntity().getContent();
				this.responseJson = toString(instream);  
				
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

}
