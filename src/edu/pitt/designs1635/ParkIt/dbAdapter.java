package edu.pitt.designs1635.ParkIt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import java.util.List;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;
import com.parse.FindCallback;

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

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static final String DATABASE_CREATE =
		"create table info (_id integer primary key autoincrement, "
		+ "lat integer not null, lon integer not null, type integer not null,"
		+ "name text, payment integer, limits integer, notes text, rate float,"
		+ "ratetime integer, grate text, monstart date, monend date, tuestart date,"
		+ "tueend date, wedstart date, wedend date, thustart date, thuend date,"
		+ "fristart date, friend date, satstart date, satend date,"
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

	public void close()
	{
		if(mDbHelper != null)
			mDbHelper.close();
	}

	public Cursor fetchAllRows()
	{
		return mDb.query(INFO_TABLE, null, null, null, null, null, null);
	}

	public boolean deletePoint(int lat, int lon)
	{
		String whereClause = KEY_LAT +"='" +lat +"'" +" AND "
					+KEY_LON +"='" +lon+"'";

		return mDb.delete(INFO_TABLE, whereClause, null) > 0;
	}

	public boolean abandonShip()
	{
		return mDb.delete(INFO_TABLE, null, null) > 0;
	}

	public int addPoint(ParseObject obj)
	{
		ContentValues vals = new ContentValues();
		vals.put(KEY_LAT, obj.getInt("lat"));
		vals.put(KEY_LON,obj.getInt("lon"));
		vals.put(KEY_TYPE, obj.getInt("type"));
		vals.put(KEY_NAME, obj.getString("name"));
		vals.put(KEY_PAYMENT, obj.getInt("payment"));
		vals.put(KEY_LIMIT, obj.getInt("limits"));
		vals.put(KEY_NOTES, obj.getString("notes"));
		vals.put(KEY_RATE, obj.getDouble("rate"));
		vals.put(KEY_RATETIME, obj.getInt("ratetime"));
		vals.put(KEY_GRATE, obj.getString("grate"));
		/*
		vals.put(KEY_MONSTART, ""+pl.getMondayStart());
		vals.put(KEY_MONEND, ""+pl.getMondayEnd());
		vals.put(KEY_TUESTART, ""+pl.getTuesdayStart());
		vals.put(KEY_TUEEND, ""+pl.getTuesdayEnd());
		vals.put(KEY_WEDSTART, ""+pl.getWednesdayStart());
		vals.put(KEY_WEDEND, ""+pl.getWednesdayEnd());
		vals.put(KEY_THUSTART, ""+pl.getThursdayStart());
		vals.put(KEY_THUEND, ""+pl.getThursdayEnd());
		vals.put(KEY_FRISTART, ""+pl.getFridayStart());
		vals.put(KEY_FRIEND, ""+pl.getFridayEnd());
		vals.put(KEY_SATSTART, ""+pl.getSaturdayStart());
		vals.put(KEY_SATEND, ""+pl.getSaturdayEnd());
		vals.put(KEY_SUNSTART, ""+pl.getSundayStart());
		vals.put(KEY_SUNEND, ""+pl.getSundayEnd());*/

		if (mDb.query(INFO_TABLE,
						new String[] {KEY_ROWID},
						KEY_LAT + "=? and " + KEY_LON +"=? and " + KEY_TYPE + "=?",
						new String[] {Integer.toString(obj.getInt("lat")), Integer.toString(obj.getInt("lon")), Integer.toString(obj.getInt("type"))},
						null,
						null,
						KEY_ROWID).getCount() != 0)
		{
			Log.i("PARKIT DATABASE", "ADD DIDN'T WORK BRO");
			return -1;
		}
		else
		{
			Log.i("PARKIT DATABASE", "ADD WORKED! CELEBRATE!");
			return (int) mDb.insert(INFO_TABLE, null, vals);
		}
	}

	//private boolean isNotEmpty;
	private ParseObject dataValUpdate;
	public boolean addRemotePoint(ParkingLocation pl)
	{
		dataValUpdate = new ParseObject("Points");
		//isNotEmpty = true;
		Parse.initialize(mCtx, "pAtl7R7WUbPl3RIVMD9Ov8UDVODGYSJ9tImxKTPQ", "cgjq64nO8l5RVbmrqYH3Nv2VC1zPyX4904htpXPy"); 
		ParseQuery query = new ParseQuery("Points");
		query.whereEqualTo("lat", pl.getLatitude());
		query.whereEqualTo("lon", pl.getLongitude());
        query.findInBackground(new FindCallback() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects.size() == 0) {
                    //isNotEmpty = true;
                    Log.i("PARKIT DB", "Did NOT find a dup!");
                }
                else if(e == null)
                {
                	Log.i("PARKIT DB", "Found a dup on database!");
                	dataValUpdate = objects.get(0);
                	try{
                		objects.get(0).delete();
                	}catch(ParseException pe){ Log.i("PARKIT DB", "Parse error code: "+pe.getCode()); }
                }
            }
        });

		if(addPoint(pl) != -1)
		{
			dataValUpdate.put("lat", pl.getLatitude());
			dataValUpdate.put("lon", pl.getLongitude());
			dataValUpdate.put("type", pl.getType().toInt());
			dataValUpdate.put("name", pl.getName());
			dataValUpdate.put("payment", pl.getPayment().toInt());
			dataValUpdate.put("limits", pl.getLimit());
			dataValUpdate.put("notes", "Empty");
			dataValUpdate.put("rate", pl.getRate());
			dataValUpdate.put("ratetime", pl.getRateTime().toString());
			dataValUpdate.put("grate", pl.getGarageRate());
			dataValUpdate.put("monstart", pl.getMondayStart());
			dataValUpdate.put("monend", pl.getMondayEnd());
			dataValUpdate.put("tuestart", pl.getTuesdayStart());
			dataValUpdate.put("tueend", pl.getTuesdayEnd());
			dataValUpdate.put("wedstart", pl.getWednesdayStart());
			dataValUpdate.put("wedend", pl.getWednesdayEnd());
			dataValUpdate.put("thustart", pl.getThursdayStart());
			dataValUpdate.put("thuend", pl.getThursdayEnd());
			dataValUpdate.put("fristart", pl.getFridayStart());
			dataValUpdate.put("friend", pl.getFridayEnd());
			dataValUpdate.put("satstart", pl.getSaturdayStart());
			dataValUpdate.put("satend", pl.getSaturdayEnd());
			dataValUpdate.put("sunstart", pl.getSundayStart());
			dataValUpdate.put("sunend", pl.getSundayEnd());
	        dataValUpdate.saveInBackground();

	        return true;
	    }
	    return false;

	}

	public int addPoint(ParkingLocation pl)
	{
		ContentValues vals = new ContentValues();
		vals.put(KEY_LAT, pl.getLatitude());
		vals.put(KEY_LON, pl.getLongitude());
		vals.put(KEY_TYPE, pl.getType().toInt());
		vals.put(KEY_NAME, pl.getName());
		vals.put(KEY_PAYMENT, pl.getPayment().toInt());
		vals.put(KEY_LIMIT, pl.getLimit());
		//vals.put(KEY_NOTES, pl.getNotes());
		vals.put(KEY_RATE, ""+pl.getRate());
		vals.put(KEY_RATETIME, pl.getRateTime().toString());
		vals.put(KEY_GRATE, pl.getGarageRate());
		vals.put(KEY_MONSTART, ""+pl.getMondayStart());
		vals.put(KEY_MONEND, ""+pl.getMondayEnd());
		vals.put(KEY_TUESTART, ""+pl.getTuesdayStart());
		vals.put(KEY_TUEEND, ""+pl.getTuesdayEnd());
		vals.put(KEY_WEDSTART, ""+pl.getWednesdayStart());
		vals.put(KEY_WEDEND, ""+pl.getWednesdayEnd());
		vals.put(KEY_THUSTART, ""+pl.getThursdayStart());
		vals.put(KEY_THUEND, ""+pl.getThursdayEnd());
		vals.put(KEY_FRISTART, ""+pl.getFridayStart());
		vals.put(KEY_FRIEND, ""+pl.getFridayEnd());
		vals.put(KEY_SATSTART, ""+pl.getSaturdayStart());
		vals.put(KEY_SATEND, ""+pl.getSaturdayEnd());
		vals.put(KEY_SUNSTART, ""+pl.getSundayStart());
		vals.put(KEY_SUNEND, ""+pl.getSundayEnd());

		if (mDb.query(INFO_TABLE,
						new String[] {KEY_ROWID},
						KEY_LAT + "=? and " + KEY_LON +"=? and " + KEY_TYPE + "=?",
						new String[] {Integer.toString(pl.getLatitude()), Integer.toString(pl.getLongitude()), Integer.toString(pl.getType().toInt())},
						null,
						null,
						KEY_ROWID).getCount() != 0)
		{
			return -1;
		}
		else
			return (int) mDb.insert(INFO_TABLE, null, vals);
	}
}