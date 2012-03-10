package edu.pitt.designs1635.ParkIt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class dbAdapter 
{

	public static final String KEY_ROWID = "_id";
	public static final String KEY_LAT = "lat";
	public static final String KEY_LON = "lon";
	public static final String KEY_TYPE = "type";
	public static final String KEY_NAME = "name";
	public static final String KEY_PAYMENT = "payment";
	public static final String KEY_LIMIT = "limit";
	public static final String KEY_NOTES = "notes";
	public static final String KEY_RATE = "rate";
	public static final String KEY_RATETIME = "ratetime";
	public static final String KEY_GRATE = "grate";
	public static final String KEY_MONSTART = "monstart";
	public static final String KEY_MONEND = "monend";
	public static final String KEY_TUESTART = "tuestart";
	public static final String KEY_TUEEND = "tueend";
	public static final String KEY_WEDSTART = "wedstart";
	public static final String KEY_WEDEND = "wedend";
	public static final String KEY_THUSTART = "thustart";
	public static final String KEY_THUEND = "thuend";
	public static final String KEY_FRISTART = "fristart";
	public static final String KEY_FRIEND = "friend";
	public static final String KEY_SATSTART = "satstart";
	public static final String KEY_SATEND = "satend";
	public static final String KEY_SUNSTART = "sunstart";
	public static final String KEY_SUNEND = "sunend";

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static final String DATABASE_CREATE =
		"create table info (_id integer primary key autoincrement, "
		+ "lat double not null, lon double not null, type text not null, "
		+ "name text, payment text, limit integer, notes text, rate float, "
		+ "ratetime text, grate text, monstart date, monend date, tuestart date, "
		+ "tueend date, wedstart date, wedend date, thustart date, thurend date, "
		+ "fristart date, friend date, satstart date, satend date, "
		+ "sunstart date, sunend date);";

	private static final String DATABASE_NAME = "data";
	private static final String INFO_TABLE = "info";
	private static final int DATABASE_VERSION = 1;
	private final Context mCtx;

	/**
	 * Class constructor.  Retains calling application's context, so that it
	 * can be used in additional functions.
	 * 
	 * @param ctx	Calling application's context
	 */
	public dbAdapter(Context ctx)
	{
		this.mCtx = ctx;

	}

	/**
	 * Inner class providing a database upgrade process.
	 */	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) 
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			//If we ever need to update the db
		}
	}

}