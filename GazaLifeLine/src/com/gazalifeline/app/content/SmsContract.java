package com.gazalifeline.app.content;

import android.net.Uri;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.annotation.Column.Type;
import com.tjeannin.provigen.annotation.ContentUri;

public interface SmsContract extends ProviGenBaseContract {

	String CONTENT_AUTHORITY = "com.gazalifeline.app";
	String TABLE_NAME = "message";
	int LOADER_ID = 1;

	@Column(Type.TEXT)
	public static final String RELAY_NUMBER = "relay_number";

	@Column(Type.TEXT)
	public static final String TEXT = "text";

	@ContentUri
	public static final Uri CONTENT_URI = Uri.parse("content://"
			+ CONTENT_AUTHORITY + "/" + TABLE_NAME);
}
