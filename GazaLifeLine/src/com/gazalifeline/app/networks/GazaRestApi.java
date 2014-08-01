package com.gazalifeline.app.networks;

import retrofit.RestAdapter;

import com.gazalifeline.app.utils.Constants;

public class GazaRestApi implements Constants {

	private static IGazaApi api;
	
	private GazaRestApi() {
	}
	
	public static IGazaApi getInstance() {
		
		if (api == null) {
			
				RestAdapter mAdapter = new RestAdapter.Builder()
				.setEndpoint(API_URL)
//				.setLogLevel(LogLevel.FULL)
				.build(); 
				
				api = mAdapter.create(IGazaApi.class);
		}
		
		return api;
	}
}
