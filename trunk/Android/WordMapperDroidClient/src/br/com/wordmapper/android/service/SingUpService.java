package br.com.wordmapper.android.service;

import android.util.Log;

import br.com.wordmapper.android.utils.AppSettings;
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
			
			Log.d(AppSettings.TAG, this.getResponseJson());
			
			this.parseJson();
		} catch (Exception e) {
			Log.e(AppSettings.TAG, "Fudeo", e);
		}
		
	}
}
