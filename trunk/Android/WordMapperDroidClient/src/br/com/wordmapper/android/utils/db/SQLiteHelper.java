package br.com.wordmapper.android.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
	
	private String scriptCreate;
	private String scriptDelete;

	public SQLiteHelper(Context context, String dbName, int dbVersion, String scriptCreate, String scriptDelete) {
		super(context, dbName, null, dbVersion);
		
		this.scriptCreate = scriptCreate;
		this.scriptDelete = scriptDelete;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(this.scriptCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (this.scriptDelete!=null)	db.execSQL(this.scriptDelete);
		this.onCreate(db);
	}

}
