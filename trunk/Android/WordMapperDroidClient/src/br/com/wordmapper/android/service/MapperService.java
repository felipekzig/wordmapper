package br.com.wordmapper.android.service;

import br.com.wordmapper.service.container.MapperContainer;

import com.google.gson.Gson;

public class MapperService extends WMService {

	private MapperContainer responseObject;
	private MapperContainer requestObject;

	public MapperService(String word){
		super.setTpOperation(WMService.MAPPER_OPERATION);
		
		requestObject = new MapperContainer();
		requestObject.setWord(word);
	}	
	
	public MapperContainer getResponseObject(){
		return this.responseObject;
	}
	
	private void parseJson(){
		this.responseObject = new Gson().fromJson(this.getResponseJson(), MapperContainer.class);
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
