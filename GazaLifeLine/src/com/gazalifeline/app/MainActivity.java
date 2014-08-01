package com.gazalifeline.app;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gazalifeline.app.networks.GazaRestApi;
import com.gazalifeline.app.utils.Constants;
import com.gazalifeline.app.utils.SharedPrefManager;
import com.gazalifeline.app.utils.scheduler.Scheduler;

public class MainActivity extends ActionBarActivity implements Constants {

	private EditText etPhone;
	private Button btnSend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		setViews();
	}

	private void initViews() {

		etPhone = (EditText) findViewById(R.id.etPhone);
		btnSend = (Button) findViewById(R.id.btnSend);
		btnSend.setOnClickListener(mOnClickListener);
	}

	private void setViews() {

		String phone = SharedPrefManager.getInstance(this).getString(
				PREF_USER_NUMBER);
		etPhone.setText(TextUtils.isEmpty(phone) ? "" : phone);
	}

	private View.OnClickListener mOnClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			send();
		}
	};

	private void send() {

		if (isValidNumber()) {
			saveNumber();
			ping();
		}
	}

	private boolean isValidNumber() {

		String phone = etPhone.getText().toString();

		etPhone.setError(null);

		if (TextUtils.isEmpty(phone)) {

			etPhone.setError("Required!");
			return false;
		}

		if (!Patterns.PHONE.matcher(phone).matches()) {

			etPhone.setError("Enter a valid number!");
			return false;
		}

		return true;
	}

	private void saveNumber() {

		SharedPrefManager.getInstance(this).putString(PREF_USER_NUMBER,
				etPhone.getText().toString());
	}

	private void ping() {

		GazaRestApi.getInstance().ping(etPhone.getText().toString(),
				new Callback<String>() {

					@Override
					public void success(String arg0, Response arg1) {
						Toast.makeText(getApplicationContext(),
								"Sent!",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void failure(RetrofitError error) {

						if (error.isNetworkError())
							Toast.makeText(getApplicationContext(),
									"No intennet connection!",
									Toast.LENGTH_SHORT).show();
					}
				});
		
		Scheduler.getInstance(this).schedulePing();
	}

}
