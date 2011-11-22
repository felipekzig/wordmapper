package br.com.wordmapper.android.service;

import br.com.wordmapper.service.container.UserContainer;

import com.google.gson.Gson;

public final class SingUpService extends WMService {
	
	private UserContainer responseObject;
	private UserContainer requestObject;
	
	public SingUpService(UserContainer user){
		super.setTpOperation(WMService.SINGUP_OPERATION);
		
		requestObject = user;

	}
	
	public UserContainer getResponseObject(){
		return this.responseObject;
	}
	
	private void parseJson(){
		this.responseObject = new Gson().fromJson(this.getResponseJson(), UserContainer.class);
	}
	
	public void execute(){
		try {
			super.requestServer(requestObject.getJson());
			
			this.parseJson();
		} catch (Exception e) {
			// E agora?
		}
		
	}
}
