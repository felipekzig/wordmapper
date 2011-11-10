package br.com.wordmapper.android.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class WMService {

	private String strWord;
	private String strIdMainDict;
	private Integer intIdTpOperation;
	
	private String responseJson;
	private Object responseObject;
	
	public final static String urlWMService = "http://10.0.2.2:8080/WordMapperService/resources/WordMapper/";
	
	public WMService(){	}
	
	public void setWord(String strWord){
		this.strWord = strWord;
	}
	public String getWord(){
		return this.strWord;
	}

	public void setMainDict(String mainDict){
		this.strIdMainDict = mainDict;
	}
	public String getMainDict(){
		return this.strIdMainDict;
	}

	public void setTpOperation(Integer tpOperation){
		this.intIdTpOperation = tpOperation;
	}
	public Integer getTpOperation(){
		return this.intIdTpOperation;
	}
	
	public String getResponseJson(){
		return this.responseJson;
	}
	public Object getResponseJsonObject(){
		return responseObject;
	}
	
	public void requestServer() throws Exception{
		
		try {
			
			this.responseJson = this.getWSJSONResponse();
			
			//this.responseObject = this.parseJSON(responseJson);
			
		} catch(Exception e) {
			throw e;
		}
	
	}
	
	private String buildJSON(){
		String json = "";
		
		json = "{";
		json = json + " \"Word\":\"" + this.strWord + "\",";
		json = json + " \"IdTpOperation\":" + this.intIdTpOperation;
		
		if (this.strIdMainDict != ""){
			json = json + ",";
			json = json + " \"IdMainDict\":\"" + this.strIdMainDict + "\"";
		}
		json = json + "}";
		
		return json;
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
	
	private String getWSJSONResponse() throws Exception{
		String urlWS = this.urlWMService + URLEncoder.encode(this.buildJSON());
		this.strIdMainDict = urlWS;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(urlWS);  
		
		try {  
			HttpResponse response = httpclient.execute(httpget);  
			 
			HttpEntity entity = response.getEntity();  
			 
			if (entity != null) {
				InputStream instream = entity.getContent();
				String result = toString(instream);  
				
				instream.close();
				
				return result;
			}
		
		} catch (Exception e){
			throw e;
		}	
		
		return null;
	}
	
	private Object parseJSON(String json) throws Exception{
		try {
			return new Gson().fromJson(json, Object.class);
		} catch (Exception e) {
			throw e;
		}
	}
}
