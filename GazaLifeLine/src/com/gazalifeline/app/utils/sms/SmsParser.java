package com.gazalifeline.app.utils.sms;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.gazalifeline.app.models.Sms;

public class SmsParser {

	public SmsParser() {
	}

	public ArrayList<Sms> readSms(Intent intent) {

		// Retrieves a map of extended data from the intent.
		final Bundle bundle = intent.getExtras();
		
		ArrayList<Sms> smsList = new ArrayList<Sms>();

		try {

			if (bundle != null) {
				
				final Object[] pdusObj = (Object[]) bundle.get("pdus");

				for (int i = 0; i < pdusObj.length; i++) {
					
					Sms sms = new Sms();

					SmsMessage currentMessage = SmsMessage
							.createFromPdu((byte[]) pdusObj[i]);

					sms.setNumber(currentMessage.getDisplayOriginatingAddress());
					sms.setText(currentMessage.getDisplayMessageBody());

					smsList.add(sms);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return smsList;
	}
}
