package com.gazalifeline.app.utils.sms;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.gazalifeline.app.content.controller.DBSmsController;
import com.gazalifeline.app.models.Sms;
import com.gazalifeline.app.networks.GazaRestApi;
import com.gazalifeline.app.utils.Constants;
import com.gazalifeline.app.utils.SharedPrefManager;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		SmsParser factory = new SmsParser();
		postSms(context, factory.readSms(intent), SharedPrefManager.getInstance(context)
				.getString(Constants.PREF_USER_NUMBER));
	}

	private void postSms(final Context context, ArrayList<Sms> list, String userNumber) {

		Log.d(getClass().getSimpleName(), "post sms");

		if (TextUtils.isEmpty(userNumber))
			return;

		if (list == null || list.isEmpty())
			return;

		for (final Sms item : list) {

			Log.d(getClass().getSimpleName(), "posting sms");

			GazaRestApi.getInstance().sendSmsData(userNumber, item.getText(),
					item.getNumber(), new Callback<String>() {

						@Override
						public void success(String arg0, Response arg1) {
							
							if (item.getId() > 0) {
								// remove cached-sms from database when successfully posted
								DBSmsController.delete(context, item.getId());
							}
						}

						@Override
						public void failure(RetrofitError arg0) {
							
							// add sms to cache when fail to post
							DBSmsController.add(context, item);
						}
					});
		}
	}
}
