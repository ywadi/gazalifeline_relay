package com.gazalifeline.app.networks;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface IGazaApi {

	@GET("/service/liveMobs/ping/{phoneNumber}")
	void ping(@Path("phoneNumber") String phoneNumber, Callback<String> cb);

	@FormUrlEncoded
	@POST("/service/sms/sendSmsData")
	void sendSmsData(@Field("userNumber") String userNumber,
			@Field("Data") String data, @Field("relayNumber") String relayNumber, Callback<String> cb);

}
