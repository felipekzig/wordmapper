package br.com.wordmapper.android.service;

import br.com.wordmapper.service.container.DefineContainer;

import com.google.gson.Gson;

public class MapperService extends WMService {

	private DefineContainer responseObject;
	private DefineContainer requestObject;

	public MapperService(String word){
		super.setTpOperation(WMService.DEFINE_OPERATION);
		
		requestObject = new DefineContainer();
		requestObject.setWord(word);
	}	
	
	public DefineContainer getResponseObject(){
		return this.responseObject;
	}
	
	private void parseJson(){
		this.responseObject = new Gson().fromJson(this.getResponseJson(), DefineContainer.class);
	}
	
	public void execute() throws Exception{
		try {
			super.requestServer(requestObject.getJson());
			
			this.parseJson();
		} catch (Exception e) {
			throw e;
		}
		
	}
}
