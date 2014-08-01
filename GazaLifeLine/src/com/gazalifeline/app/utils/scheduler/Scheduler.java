package com.gazalifeline.app.utils.scheduler;

import java.util.concurrent.TimeUnit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class Scheduler {

	private static Scheduler mInstance;
	private Context mContext;

	private Scheduler() {
	}

	private Scheduler(Context context) {
		mContext = context;
	}

	public static Scheduler getInstance(Context context) {

		if (mInstance == null) {
			mInstance = new Scheduler(context);
		}

		return mInstance;
	}

	public void schedulePing() {

		AlarmManager mAlarmManager = (AlarmManager) mContext
				.getSystemService(Context.ALARM_SERVICE);

		Intent intent = new Intent(mContext, SchedulerReceiver.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(mContext, 1,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);

		mAlarmManager
				.setRepeating(AlarmManager.RTC_WAKEUP,
						System.currentTimeMillis(),
						TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES),
						alarmIntent);
	}

}
