package com.gazalifeline.app.content.controller;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.gazalifeline.app.content.SmsContract;
import com.gazalifeline.app.models.Sms;

public class DBSmsController {

	private final static String TAG = DBSmsController.class.getSimpleName();

	public static Uri add(Context context, Sms sms) {

		if (sms == null || context == null) {

			return null;
		}

		ContentValues values = new ContentValues();

		values.put(SmsContract.RELAY_NUMBER, sms.getRelayNumber());
		values.put(SmsContract.TEXT, sms.getText());

		Uri insertionUri = context.getContentResolver().insert(
				SmsContract.CONTENT_URI, values);

		if (insertionUri != null) {
			Log.i(TAG, "insert: " + insertionUri.toString());
		} else {
			Log.e(TAG, "insert: failed");
		}

		return insertionUri;
	}

	public static Cursor get(Context context, String where) {

		if (context == null) {
			return null;
		}

		Cursor cursor = context.getContentResolver().query(
				SmsContract.CONTENT_URI, null, where, null, null);

		if (cursor != null) {
			Log.i(TAG, "get");
		} else {
			Log.e(TAG, "get failed");
		}

		return cursor;
	}

	public static Cursor get(Context context, String where,
			String[] selectionArgs) {

		if (context == null) {
			return null;
		}

		Cursor cursor = context.getContentResolver().query(
				SmsContract.CONTENT_URI, null, where, selectionArgs, null);

		if (cursor != null) {
			Log.i(TAG, "get");
		} else {
			Log.e(TAG, "get failed");
		}

		return cursor;
	}

	public static boolean update(Context context, Sms sms) {

		if (sms == null || context == null) {
			return false;
		}

		ContentValues values = new ContentValues();

		values.put(SmsContract.RELAY_NUMBER, sms.getRelayNumber());
		values.put(SmsContract.TEXT, sms.getText());

		int rowsAffected = context.getContentResolver().update(
				SmsContract.CONTENT_URI, values,
				SmsContract._ID + " = " + sms.getId(), null);

		if (rowsAffected > 0) {

			Log.i(TAG, "updated");
		} else {

			Log.e(TAG, "update failed");
		}

		return rowsAffected > 0 ? true : false;
	}

	public static boolean delete(Context context, int id) {

		if (context == null || id < 1) {
			return false;
		}

		int rowsAffected = context.getContentResolver().delete(
				SmsContract.CONTENT_URI, SmsContract._ID + " = " + id, null);

		if (rowsAffected > 0) {

			Log.i(TAG, "deleted");
		} else {

			Log.e(TAG, "delete failed");
		}

		return rowsAffected > 0 ? true : false;
	}

	public static boolean delete(Context context, String where,
			String[] selectionArgs) {

		if (context == null || TextUtils.isEmpty(where)) {
			return false;
		}

		int rowsAffected = context.getContentResolver().delete(
				SmsContract.CONTENT_URI, where, selectionArgs);

		if (rowsAffected > 0) {

			Log.i(TAG, "deleted");
		} else {

			Log.e(TAG, "delete failed");
		}

		return rowsAffected > 0 ? true : false;
	}

	/**
	 * get all messages from cursor
	 * 
	 * @return
	 */
	public static ArrayList<Sms> getListFromCursor(Cursor cursor) {

		if (cursor == null) {
			return null;
		}

		if (cursor.getCount() == 0) {
			return null;
		}

		ArrayList<Sms> list = new ArrayList<Sms>();

		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

			Sms sms = new Sms();

			sms.setId(cursor.getInt(cursor.getColumnIndex(SmsContract._ID)));

			sms.setRelayNumber(cursor.getString(cursor
					.getColumnIndex(SmsContract.RELAY_NUMBER)));

			sms.setText(cursor.getString(cursor
					.getColumnIndex(SmsContract.TEXT)));

			list.add(sms);
		}

		return list;
	}

	/**
	 * get the first item from cursor
	 * 
	 * @param cursor
	 * @return the first message
	 */
	public static Sms getItemFromCursor(Cursor cursor) {

		if (cursor == null) {

			return null;
		}

		if (cursor.getCount() == 0) {

			return null;
		}

		Sms sms = new Sms();

		sms.setId(cursor.getInt(cursor.getColumnIndex(SmsContract._ID)));

		sms.setRelayNumber(cursor.getString(cursor
				.getColumnIndex(SmsContract.RELAY_NUMBER)));

		sms.setText(cursor.getString(cursor.getColumnIndex(SmsContract.TEXT)));

		return sms;
	}

}
