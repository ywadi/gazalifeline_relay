package com.gazalifeline.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.google.gson.Gson;

public class SharedPrefManager {

	private static SharedPrefManager MANAGER;

	// Shared Preferences
	private static SharedPreferences mSharedPreferences;

	private final static String PREF_NAME = "com.gazalifeline";

	public static SharedPrefManager getInstance(Context context) {

		if (MANAGER == null || mSharedPreferences == null)
			MANAGER = new SharedPrefManager(context);

		return MANAGER;
	}

	private SharedPrefManager(Context context) {
		mSharedPreferences = context.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
	}

	/**
	 * 
	 * @param key
	 * @param object
	 * 
	 *            save object in shared preferences
	 */
	public void putObject(String key, Object object) {
		Editor editor = mSharedPreferences.edit();

		if (object == null) {
			editor.putString(key, null);
			editor.commit();
			return;
		}

		Gson gson = new Gson();
		String jsonObject = gson.toJson(object);
		editor.putString(key, jsonObject);
		editor.commit();
	}

	/**
	 * 
	 * @param key
	 * @param clazz (type)
	 * @return object classType
	 * 
	 *         get saved object from the shared preferences
	 */
	public <T> T getObject(String key, Class<T> clazz) {

		String json = mSharedPreferences.getString(key, null);
		if (json == null) {
			return null;
		}

		Log.e("SharedPrefManager", json);

		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}

	public void putBoolean(String name, boolean val) {

		Editor editor = mSharedPreferences.edit();
		editor.putBoolean(name, val);
		editor.commit();
	}

	public boolean getBoolean(String name) {

		return mSharedPreferences.getBoolean(name, false);
	}

	public void putString(String name, String val) {

		Editor editor = mSharedPreferences.edit();
		editor.putString(name, val);
		editor.commit();
	}

	public String getString(String name) {

		return mSharedPreferences.getString(name, null);
	}

	public void putInteger(String name, int val) {

		Editor editor = mSharedPreferences.edit();
		editor.putInt(name, val);
		editor.commit();
	}

	public int getInteger(String name) {

		return mSharedPreferences.getInt(name, 0);
	}

	public void putLong(String name, long val) {

		Editor editor = mSharedPreferences.edit();
		editor.putLong(name, val);
		editor.commit();
	}

	public long getLong(String name) {

		return mSharedPreferences.getLong(name, 0);
	}

	public void clearAll() {

		mSharedPreferences.edit().clear().commit();
	}

	public void clearByKey(String key) {

		Editor editor = mSharedPreferences.edit();
		editor.remove(key);
		editor.commit();
	}
}
