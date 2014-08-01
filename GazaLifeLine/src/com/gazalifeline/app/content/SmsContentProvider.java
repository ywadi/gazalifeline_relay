package com.gazalifeline.app.content;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.tjeannin.provigen.ProviGenOpenHelper;
import com.tjeannin.provigen.ProviGenProvider;

public class SmsContentProvider extends ProviGenProvider {

	@SuppressWarnings("rawtypes")
	private static Class[] contracts = new Class[] { SmsContract.class };

	@SuppressWarnings("rawtypes")
	@Override
	public Class[] contractClasses() {
		return contracts;
	}

	@Override
	public SQLiteOpenHelper openHelper(Context context) {
		return new ProviGenOpenHelper(context, "db_messages", null, 1,
				contracts);
	}

}
