package br.com.wordmapper.android.service;

import com.google.gson.Gson;

import br.com.wordmapper.service.container.DefineContainer;

public final class DefineService extends WMService {

	private DefineContainer responseObject;
	private DefineContainer requestObject;

	public DefineService(String word){
		super.setTpOperation(WMService.DEFINE_OPERATION);
		
		requestObject = new DefineContainer();
		requestObject.setWord(word);
	}	
	
	public DefineService(String word, String idMainDict){
		super.setTpOperation(WMService.DEFINE_OPERATION);
		
		requestObject = new DefineContainer();
		requestObject.setWord(word);
		requestObject.setIdMainDict(idMainDict);
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
