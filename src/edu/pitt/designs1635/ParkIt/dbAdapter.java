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
	public static final String KEY_LIMIT = "limits";
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
	public static final String[] ALL_COLS = {KEY_ROWID, KEY_LAT, KEY_LON, KEY_TYPE, KEY_NAME,
		KEY_PAYMENT, KEY_LIMIT, KEY_NOTES, KEY_RATE, KEY_RATETIME, KEY_GRATE, KEY_MONSTART,
		KEY_MONEND, KEY_TUESTART, KEY_TUEEND, KEY_WEDSTART, KEY_WEDEND, KEY_THUSTART, KEY_THUEND,
		KEY_FRISTART, KEY_FRIEND, KEY_SATSTART, KEY_SATEND, KEY_SUNSTART, KEY_SUNEND};

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static final String DATABASE_CREATE =
		"create table info (_id integer primary key autoincrement, "
		+ "lat integer not null, lon integer not null, type integer not null, "
		+ "name text, payment integer, limits integer, notes text, rate float, "
		+ "ratetime integer, grate text, monstart date, monend date, tuestart date, "
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

	public dbAdapter open() throws SQLException
	{
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void addDummyData()
	{
		ContentValues steps = new ContentValues();
		int lat, lon, type;
		float rate;
		String name;

		lat = 40444282;
		lon = -79953108;
		type = 0;
		rate = 0.75F;
		name = "Cathedral of Learning";

		steps.put(KEY_LAT, lat);
		steps.put(KEY_LON, lon);
		steps.put(KEY_TYPE, type);
		steps.put(KEY_RATE, rate);
		steps.put(KEY_NAME, name);

		if (mDb.query(INFO_TABLE, new String[] {KEY_ROWID}, KEY_NAME +"=?", new String[] {name}, null, null, KEY_ROWID).getCount() == 0)			  
			mDb.insert(INFO_TABLE, null, steps);

		lat = 40465282;
		lon = -79924608;
		type = 1;
		rate = 0.50F;
		name = "Somewhere else";

		steps.put(KEY_LAT, lat);
		steps.put(KEY_LON, lon);
		steps.put(KEY_TYPE, type);
		steps.put(KEY_RATE, rate);
		steps.put(KEY_NAME, name);

		if (mDb.query(INFO_TABLE, new String[] {KEY_ROWID}, KEY_LAT +"=? and " +KEY_LON +"=?", new String[] {name}, null, null, KEY_ROWID).getCount() == 0)			  
			mDb.insert(INFO_TABLE, null, steps);
	}

	public void close()
	{
		mDbHelper.close();
	}

	public Cursor fetchAllRows()
	{
		return mDb.query(INFO_TABLE, null, null, null, null, null, null);
	}

}