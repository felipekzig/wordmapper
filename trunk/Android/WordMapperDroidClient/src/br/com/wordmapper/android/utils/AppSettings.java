package br.com.wordmapper.android.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.com.wordmapper.android.utils.db.SQLiteHelper;

public final class AppSettings {

	public final static String TAG = "WordMapperDroid";	
	
	public static String userLicense = "bbfe1353662ae40f53e64e7189ef45e";
	public static String idDefaultDict = "wn";
	public static String idDefNewWindow = "N";

	private static final String DB_NAME = "WordMapper";
	private static final int DB_VERSION = 1;
	
	private static final String TABLE_NAME = "wmSettings";
	private static final String SCRIPT_CREATE = " create table " + TABLE_NAME + " ( license	VARCHAR(32) NOT NULL, IdDefaultDict CHAR(10) NOT NULL, IdDefNewWindow CHAR(1) NULL )";
	private static final String SCRIPT_DELETE = "";
	
	public static void loadSettings(Context context){
		SQLiteHelper dbHelper = new SQLiteHelper(context, DB_NAME, DB_VERSION, SCRIPT_CREATE, SCRIPT_DELETE);	
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		String[] columns = new String[]{"license", "IdDefaultDict", "IdDefNewWindow"};
		
		Cursor query = db.query(TABLE_NAME, columns, null, null, null, null, null);
		query.moveToFirst();
		if (query.isFirst()){
			userLicense = query.getString(0);
			idDefaultDict = query.getString(1);
			idDefNewWindow = query.getString(2);
		}
		query.close();
		
		db.close();
	}
	
	public static void saveSettings(Context context){
		SQLiteHelper dbHelper = new SQLiteHelper(context, DB_NAME, DB_VERSION, SCRIPT_CREATE, SCRIPT_DELETE);	
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		Cursor query = db.query(TABLE_NAME, new String[]{"license"}, null, null, null, null, null);
		if (query.isFirst()){
			update(dbHelper);	
		} else {
			insert(dbHelper);
		}
		query.close();
		
		db.close();
	}
	
	private static long insert(SQLiteHelper dbHelper){
		Log.i(AppSettings.TAG, "INSERINDO");
		
		ContentValues values = new ContentValues();
		values.put("license", userLicense);
		values.put("IdDefaultDict", idDefaultDict);
		values.put("IdDefNewWindow", idDefNewWindow);
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long id = db.insert(TABLE_NAME, null, values);
		db.close();
		
		return id;
	}
	
	private static long update(SQLiteHelper dbHelper){
		Log.i(AppSettings.TAG, "ATUALIZANDO");
		ContentValues values = new ContentValues();
		values.put("IdDefaultDict", idDefaultDict);
		values.put("IdDefNewWindow", idDefNewWindow);
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long id = db.update(TABLE_NAME, values, "license = ?", new String[]{ String.valueOf(AppSettings.userLicense)});
		db.close();
		
		return id;
	}	

}
