package edu.pitt.designs1635.ParkIt;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.maps.GeoPoint;

public class ParkingLocation implements Parcelable {

	private int m_locationID;
	private int m_uLat; //micro Latitude
	private int m_uLng; //micro Longitude
	private TYPE m_type;
	private PAYMENT_TYPE m_payment;
	private int m_limit;
	private int	m_mondayStart;
	private int	m_mondayEnd;
	private int	m_tuesdayStart;
	private int	m_tuesdayEnd;
	private int	m_wednesdayStart;
	private int	m_wednesdayEnd;
	private int	m_thursdayStart;
	private int	m_thursdayEnd;
	private int	m_fridayStart;
	private int	m_fridayEnd;
	private int	m_saturdayStart;
	private int	m_saturdayEnd;
	private int	m_sundayStart;
	private int	m_sundayEnd;
	private float m_rate;
	private RATE_TIME m_rateTime;
	private String m_garageRate;
	private String m_name;
	
	public static enum RATE_TIME 
	{ 
		HOURLY, 
		DAILY; 
		
		public String toString()
		{
			String val;
			
			switch(this)
			{
				case HOURLY: val = "Hourly"; break;
				case DAILY: val = "Daily"; break;
				default: val = ""; break;
			}
			
			return val;
			
		}
		
	};
	
	
	
	public static enum PAYMENT_TYPE 
	{ 
		CASH, 
		CREDIT,
		COIN;
		
		public String toString()
		{
			String val;
			
			switch(this)
			{
				case CASH: val = "Cash"; break;
				case CREDIT: val = "Debit/Credit"; break;
				case COIN: val = "Coin"; break;
				default: val = ""; break;
			}
			
			return val;
			
		}
	};
	
	public static enum TYPE 
	{ 
		METER, 
		PARKING_LOT,
		PARKING_GARAGE;
		
		public String toString()
		{
			String val;
			
			switch(this)
			{
				case METER: val = "Meter"; break;
				case PARKING_LOT: val = "Parking Lot"; break;
				case PARKING_GARAGE: val = "Parking Garage"; break;
				default: val = ""; break;
			}
			return val;
		}

		public int toInt()
		{
			int val;
			switch(this)
			{
				case METER: val = 0; break;
				case PARKING_LOT: val = 1; break;
				case PARKING_GARAGE: val = 2; break;
				default: val=-1; break;
			}
			return val;
		}
	};

	
	/**
	 * Gets the database ID of this parking location.
	 * 
	 * @return the database ID.
	 */
	public int getID() {
		return m_locationID;
		
	}


	/**
	 * Sets the database ID of this parking location.
	 * 
	 * @param ID - the database ID.
	 *
	 */
	public void setID(int ID) {
		this.m_locationID = ID;

	}

	/**
	 * Gets the latitude of this parking location.  This is in micro-latitude. To convert this to
	 * latitude divide by 1e6.
	 * 
	 * @return the micro latitude.
	 *
	 */

	public int getLatitude() {
		return m_uLat;
	}

	/**
	 * Sets the latitude of this parking location.  This is in micro-latitude. To do this take
	 * the latitude and multiple it by 1e6.
	 * 
	 * @param latitude - the micro latitude.
	 */
	public void setLatitude(int latitude) {
		this.m_uLat = latitude;
	}



	/**
	 * Gets the longitude of this parking location.  This is in micro-longitude. To convert this to
	 * longitude divide by 1e6.
	 * 
	 * @return the micro longitude.
	 *
	 */
	public int getLongitude() {
		return m_uLng;
	}



	/**
	 * Sets the longitude of this parking location.  This is in micro-longitude. To do this take
	 * the longitude and multiple it by 1e6.
	 * 
	 * @param longitude - the micro longitude.
	 */
	public void setLongitude(int longitude) {
		this.m_uLng = longitude;
	}



	/**
	 * Gets the type of parking location. TYPE.METER, TYPE.PARKING_LOT, or TYPE.PARKING_GARAGE.
	 * 
	 * @return the type
	 */
	public TYPE getType() {
		return m_type;
	}



	/**
	 * Sets the type of parking location. TYPE.METER, TYPE.PARKING_LOT, or TYPE.PARKING_GARAGE.
	 * 
	 * @param type - the type.
	 */
	public void setType(TYPE type) {
		this.m_type = type;
	}

	/**
	 * Sets the type of parking location. <br />
	 * TYPE.METER = 0 <br />
	 * TYPE.PARKING_LOT = 1 <br />
	 * TYPE.PARKING_GARAGE = 2 <br />
	 * 
	 * @param type - the type as an integer.
	 */
	public void setType(int type) {
		
		switch(type)
		{
			case 0: this.m_type = TYPE.METER; break;
			case 1: this.m_type = TYPE.PARKING_LOT; break;
			case 2: this.m_type = TYPE.PARKING_GARAGE; break;
			default: this.m_type = null; break; 

		}
	}

	/**
	 * Gets the payment type for this parking location. PAYMENT_TYPE.CASH, PAYMENT_TYPE.CREDIT, or PAYMENT_TYPE.COIN
	 * 
	 * @return the payment type.
	 */
	public PAYMENT_TYPE getPayment() {
		return m_payment;
	}



	/**
	 * Sets the payment type for this parking location. PAYMENT_TYPE.CASH, PAYMENT_TYPE.CREDIT, or PAYMENT_TYPE.COIN
	 * 
	 * @param payment - the payment type.
	 */
	public void setPayment(PAYMENT_TYPE payment) {
		this.m_payment = payment;
	}

	/**
	 * Sets the payment type for this parking location. <br />
	 * PAYMENT_TYPE.CASH = 0 <br />
	 * PAYMENT_TYPE.CREDIT = 1 <br />
	 * PAYMENT_TYPE.COIN = 2 <br />
	 * 
	 * @param payment - the payment type as an integer.
	 */
	public void setPayment(int payment) {
		switch(payment)
		{
			case 0: this.m_payment = PAYMENT_TYPE.CASH; break;
			case 1: this.m_payment = PAYMENT_TYPE.CREDIT; break;
			case 2: this.m_payment = PAYMENT_TYPE.COIN; break;
			default: this.m_payment = null; break; 

		}
		
	}
	
	

	/**
	 * Sets the length of time that the parking location can be used.  This is mainly for metered parking. In hours.
	 * 
	 * @return the time limit.
	 * 
	 */
	public int getLimit() {
		return m_limit;
	}



	/**
	 * Sets the length of time that the parking location can be used.  This is mainly for metered parking. In hours.
	 * 
	 * @param limit - the time limit.
	 */
	public void setLimit(int limit) {
		this.m_limit = limit;
	}

	/**
	 * Gets the time that this parking location opens on Monday. This is based on the 24hr clock.
	 * 
	 * @return the time.
	 */
	public int getMondayStart() {
		return m_mondayStart;
	}


	/**
	 * Sets the time that this parking location opens on Monday. This is based on the 24hr clock.
	 * 
	 * @param mondayStart - the time.
	 */
	public void setMondayStart(int mondayStart) {
		this.m_mondayStart = mondayStart;
	}



	/**
	 * Gets the time that this parking location closes on Monday. This is based on the 24hr clock.
	 * 
	 * @return the time.
	 */
	public int getMondayEnd() {
		return m_mondayEnd;
	}



	/**
	 * Sets the time that this parking location closes on Monday. This is based on the 24hr clock.
	 * 
	 * @param mondayEnd - the time.
	 */
	public void setMondayEnd(int mondayEnd) {
		this.m_mondayEnd = mondayEnd;
	}
	
	/**
	 * Gets the time that this parking location opens on Tuesday. This is based on the 24hr clock.
	 * 
	 * @return the time.
	 */
	public int getTuesdayStart() {
		return m_tuesdayStart;
	}


	/**
	 * Sets the time that this parking location opens on Tuesday. This is based on the 24hr clock.
	 * 
	 * @param tuesdayStart - the time.
	 */
	public void setTuesdayStart(int tuesdayStart) {
		this.m_tuesdayStart = tuesdayStart;
	}



	/**
	 * Gets the time that this parking location closes on Tuesday. This is based on the 24hr clock.
	 * 
	 * @return the time.
	 */
	public int getTuesdayEnd() {
		return m_tuesdayEnd;
	}



	/**
	 * Sets the time that this parking location closes on Tuesday. This is based on the 24hr clock.
	 * 
	 * @param tuesdayEnd - the time.
	 */
	public void setTuesdayEnd(int tuesdayEnd) {
		this.m_tuesdayEnd = tuesdayEnd;
	}
	
	/**
	 * Gets the time that this parking location opens on Wednesday. This is based on the 24hr clock.
	 * 
	 * @return the time.
	 */
	public int getWednesdayStart() {
		return m_wednesdayStart;
	}


	/**
	 * Sets the time that this parking location opens on Wednesday. This is based on the 24hr clock.
	 * 
	 * @param wednesdayStart - the time.
	 */
	public void setWednesdayStart(int wednesdayStart) {
		this.m_wednesdayStart = wednesdayStart;
	}



	/**
	 * Gets the time that this parking location closes on Wednesday. This is based on the 24hr clock.
	 * 
	 * @return the time.
	 */
	public int getWednesdayEnd() {
		return m_wednesdayEnd;
	}



	/**
	 * Sets the time that this parking location closes on Wednesday. This is based on the 24hr clock.
	 * 
	 * @param wednesdayEnd - the time.
	 */
	public void setWednesdayEnd(int wednesdayEnd) {
		this.m_wednesdayEnd = wednesdayEnd;
	}
	
	/**
	 * Gets the time that this parking location opens on Thursday. This is based on the 24hr clock.
	 * 
	 * @return the time.
	 */
	public int getThursdayStart() {
		return m_thursdayStart;
	}


	/**
	 * Sets the time that this parking location opens on Thursday. This is based on the 24hr clock.
	 * 
	 * @param thursdayStart - the time.
	 */
	public void setThursdayStart(int thursdayStart) {
		this.m_thursdayStart = thursdayStart;
	}



	/**
	 * Gets the time that this parking location closes on Thursday. This is based on the 24hr clock.
	 * 
	 * @return the time.
	 */
	public int getThursdayEnd() {
		return m_thursdayEnd;
	}



	/**
	 * Sets the time that this parking location closes on Thursday. This is based on the 24hr clock.
	 * 
	 * @param thursdayEnd - the time.
	 */
	public void setThursdayEnd(int thursdayEnd) {
		this.m_thursdayEnd = thursdayEnd;
	}
	
	
	/**
	 * Gets the time that this parking location opens on Friday. This is based on the 24hr clock.
	 * 
	 * @return the time.
	 */
	public int getFridayStart() {
		return m_fridayStart;
	}


	/**
	 * Sets the time that this parking location opens on Friday. This is based on the 24hr clock.
	 * 
	 * @param fridayStart - the time.
	 */
	public void setFridayStart(int fridayStart) {
		this.m_fridayStart = fridayStart;
	}



	/**
	 * Gets the time that this parking location closes on Friday. This is based on the 24hr clock.
	 * 
	 * @return the time.
	 */
	public int getFridayEnd() {
		return m_fridayEnd;
	}



	/**
	 * Sets the time that this parking location closes on Friday. This is based on the 24hr clock.
	 * 
	 * @param fridayEnd - the time.
	 */
	public void setFridayEnd(int fridayEnd) {
		this.m_fridayEnd = fridayEnd;
	}

	/**
	 * Gets the time that this parking location opens on Saturday. This is based on the 24hr clock.
	 * 
	 * @return the time.
	 */
	public int getSaturdayStart() {
		return m_saturdayStart;
	}



	/**
	 * Sets the time that this parking location opens on Saturday. This is based on the 24hr clock.
	 * 
	 * @param saturdayStart - the time.
	 */
	public void setSaturdayStart(int saturdayStart) {
		this.m_saturdayStart = saturdayStart;
	}

	/**
	 * Gets the time that this parking location closes on Saturday. This is based on the 24hr clock.
	 * 
	 * @return the time.
	 */
	public int getSaturdayEnd() {
		return m_saturdayEnd;
	}



	/**
	 * Sets the time that this parking location closes on Saturday. This is based on the 24hr clock.
	 * 
	 * @param saturdayEnd - the time.
	 */
	public void setSaturdayEnd(int saturdayEnd) {
		this.m_saturdayEnd = saturdayEnd;
	}
	
	
	/**
	 * Gets the time that this parking location opens on Sunday. This is based on the 24hr clock.
	 * 
	 * @return the time.
	 */
	public int getSundayStart() {
		return m_sundayStart;
	}


	/**
	 * Sets the time that this parking location opens on Sunday. This is based on the 24hr clock.
	 * 
	 * @param sundayStart - the time.
	 */
	public void setSundayStart(int sundayStart) {
		this.m_sundayStart = sundayStart;
	}



	/**
	 * Gets the time that this parking location closes on Sunday. This is based on the 24hr clock.
	 * 
	 * @return the time.
	 */
	public int getSundayEnd() {
		return m_sundayEnd;
	}



	/**
	 * Sets the time that this parking location closes on Sunday. This is based on the 24hr clock.
	 * 
	 * @param sundayEnd - the time.
	 */
	public void setSundayEnd(int sundayEnd) {
		this.m_sundayEnd = sundayEnd;
	}



	/**
	 * Gets the rate of the parking location. In dollars.
	 * 
	 * @return - the rate.
	 */
	public float getRate() {
		return m_rate;
	}



	/**
	 * Sets the rate of the parking location. In dollars.
	 * 
	 * @param rate - the rate. 
	 */
	public void setRate(float rate) {
		this.m_rate = rate;
	}



	/**
	 * @return the rate increment.
	 */
	public RATE_TIME getRateTime() {
		return m_rateTime;
	}



	/**
	 * Sets the rate increment. (ie hourly, or daily)
	 * 
	 * @param rateTime - the rate increment. 
	 */
	public void setRateTime(RATE_TIME rateTime) {
		this.m_rateTime = rateTime;
	}

	/**
	 * Sets the rate increment. 
	 * HOURLY = 0
	 * DAILY = 1
	 * 
	 * @param rateTime - the rate increment as an integer. 
	 */
	public void setRateTime(int rateTime) {
		switch(rateTime)
		{
			case 0: this.m_rateTime = RATE_TIME.HOURLY; break;
			case 1: this.m_rateTime = RATE_TIME.DAILY; break;
			default: this.m_rateTime = null; break; 

		}
		
		
	}


	/**
	 * Gets the rate for a garage that doesn't have a straight forward hourly or daily rate.
	 * 
	 * @return the string with the parking rates.
	 */
	public String getGarageRate() {
		return m_garageRate;
	}



	/**
	 * Sets the rate for a garage that doesn't have a straight forward hourly or daily rate.
	 * 
	 * @param m_garageRate - a complex string that stores rates for garages. (format TBD)
	 */
	public void setGarageRate(String garageRate) {
		this.m_garageRate = garageRate;
	}


	/**
	 * Gets the name of this parking location.
	 * 
	 * @return the name.
	 */
	public String getName()
	{
		return m_name;
	}
	
	
	
	/**
	 * Sets the name of this parking location.
	 * 
	 * @param name - the name.
	 */
	public void setName(String name)
	{
		this.m_name = name;
	}

	/**
	 * Default constructor. Sets all member fields to 0 and "" depending on the field type.
	 */
	public ParkingLocation()
	{
		m_locationID = 0;
		m_uLat = 0;
		m_uLng = 0;
		m_type = TYPE.METER;
		m_payment = PAYMENT_TYPE.COIN;
		m_limit = 0;
		m_mondayStart = 0;
		m_mondayEnd = 0;
		m_tuesdayStart = 0;
		m_tuesdayEnd = 0;
		m_wednesdayStart  = 0;
		m_wednesdayEnd  = 0;
		m_thursdayStart  = 0;
		m_thursdayEnd = 0;
		m_fridayStart = 0;
		m_fridayEnd = 0;
		m_saturdayStart = 0;
		m_saturdayEnd = 0;
		m_sundayStart = 0;
		m_sundayEnd = 0;
		m_rate = 0.0f;
		m_rateTime = RATE_TIME.HOURLY;
		m_garageRate = "";
		m_name = "";
	}



	/**
	 * Constructor with all member fields. To convert latitude and longitude to 
	 * micro-latitude and micro-longitude multiple by 1e6.
	 * 
	 * Example: lat = 40.444282;
	 * 			uLat = lat * 1e6;
	 * 
	 * @param ID - the database ID of this parking location.
	 * @param ulat - the micro latitude of this parking location.
	 * @param ulng - the micro longitude of this parking location.
	 * @param type - the type of parking location - "Meter", "Parking Lot", or "Parking Garage"
	 * @param payment - the method of payment - "Cash", "Credit", or "Coin"
	 * @param limit - the length of time you can park at a location.  This is mainly for metered parking.
	 * @param mondayStart - the start time for the hours of operation on Monday.
	 * @param mondayEnd - the end time for the hours of operation on Monday.
	 * @param tuesdayStart - the start time for the hours of operation on Tuesday.
	 * @param tuesdayEnd - the end time for the hours of operation on Tuesday.
	 * @param wednesdayStart - the start time for the hours of operation on Wednesday.
	 * @param wednesdayEnd - the end time for the hours of operation on Wednesday.
	 * @param thursdayStart - the start time for the hours of operation on Thursday.
	 * @param thursdayEnd - the end time for the hours of operation on Thursday.
	 * @param fridayStart - the start time for the hours of operation on Friday.
	 * @param fridayEnd - the end time for the hours of operation on Friday.
	 * @param saturdayStart - the start time for the hours of operation on Saturday.
	 * @param saturdayEnd - the end time for the hours of operation on Saturday.
	 * @param sundayStart - the start time for the hours of operation on Sunday.
	 * @param sundayEnd - the end time for the hours of operation on Sunday.
	 * @param rate - the rate.
	 * @param rateTime - the time increment of the rate(ie hourly, daily)
	 * @param garageRate - a complex string that stores rates for garages. (format TBD)
	 * @param name - the name of the parking location.
	 */
	public ParkingLocation(int ID, int ulat, int ulng,
			TYPE type, PAYMENT_TYPE payment, int limit, int mondayStart,
			int mondayEnd, int tuesdayStart, int tuesdayEnd,
			int wednesdayStart, int wednesdayEnd, int thursdayStart,
			int thursdayEnd, int fridayStart, int fridayEnd,
			int saturdayStart, int saturdayEnd, int sundayStart,
			int sundayEnd, float rate, RATE_TIME rateTime,
			String garageRate, String name) {

		this.m_locationID = ID;
		this.m_uLat = ulat;
		this.m_uLng = ulng;
		this.m_type = type;
		this.m_payment = payment;
		this.m_limit = limit;
		this.m_mondayStart = mondayStart;
		this.m_mondayEnd = mondayEnd;
		this.m_tuesdayStart = tuesdayStart;
		this.m_tuesdayEnd = tuesdayEnd;
		this.m_wednesdayStart = wednesdayStart;
		this.m_wednesdayEnd = wednesdayEnd;
		this.m_thursdayStart = thursdayStart;
		this.m_thursdayEnd = thursdayEnd;
		this.m_fridayStart = fridayStart;
		this.m_fridayEnd = fridayEnd;
		this.m_saturdayStart = saturdayStart;
		this.m_saturdayEnd = saturdayEnd;
		this.m_sundayStart = sundayStart;
		this.m_sundayEnd = sundayEnd;
		this.m_rate = rate;
		this.m_rateTime = rateTime;
		this.m_garageRate = garageRate;
		this.m_name = name;
	}

	
	/**
	 * Helper method to return the lat/long as a GeoPoint.
	 * 
	 * @return the GeoPoint of this location.
	 */
	public GeoPoint getGeoPoint()
	{
		return new GeoPoint(m_uLat, m_uLng);
	}
	
	/**
	 * Constructor to create a Parking Location with just a point.
	 * 
	 * @param latitude - the micro latitude.
	 * @param longitude - the micro longitude.
	 */
	public ParkingLocation(int latitude, int longitude)
	{
		this.m_uLat = latitude;
		this.m_uLng = longitude;
	
		m_locationID = 0;
		m_type = TYPE.METER;
		m_payment = PAYMENT_TYPE.COIN;
		m_limit = 0;
		m_mondayStart = 0;
		m_mondayEnd = 0;
		m_tuesdayStart = 0;
		m_tuesdayEnd = 0;
		m_wednesdayStart  = 0;
		m_wednesdayEnd  = 0;
		m_thursdayStart  = 0;
		m_thursdayEnd = 0;
		m_fridayStart = 0;
		m_fridayEnd = 0;
		m_saturdayStart = 0;
		m_saturdayEnd = 0;
		m_sundayStart = 0;
		m_sundayEnd = 0;
		m_rate = 0.0f;
		m_rateTime = RATE_TIME.HOURLY;
		m_garageRate = "";
		m_name = "";
	}


	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void writeToParcel(Parcel p, int flags) {
		p.writeInt(m_locationID);
		p.writeInt( m_uLat ); //micro Latitude
		p.writeInt(m_uLng ); //micro Longitude
		p.writeInt( m_type.ordinal() ) ;
		p.writeInt( m_payment.ordinal() );
		p.writeInt( m_limit );
		p.writeInt(	m_mondayStart );
		p.writeInt(	m_mondayEnd );
		p.writeInt(	m_tuesdayStart );
		p.writeInt(	m_tuesdayEnd );
		p.writeInt(	m_wednesdayStart );
		p.writeInt(	m_wednesdayEnd );
		p.writeInt(	m_thursdayStart );
		p.writeInt(	m_thursdayEnd );
		p.writeInt(	m_fridayStart );
		p.writeInt(	m_fridayEnd );
		p.writeInt(	m_saturdayStart );
		p.writeInt(	m_saturdayEnd );
		p.writeInt(	m_sundayStart );
		p.writeInt(	m_sundayEnd );
		p.writeFloat( m_rate );
		p.writeInt( m_rateTime.ordinal() );
		p.writeString( m_garageRate );
		p.writeString(  m_name );
		
		
	}
	
	public static final Parcelable.Creator<ParkingLocation> CREATOR
    					= new Parcelable.Creator<ParkingLocation>() 
    					{
							public ParkingLocation createFromParcel(Parcel in) 
							{
								return new ParkingLocation(in);
							}

							public ParkingLocation[] newArray(int size) {
								return new ParkingLocation[size];
							}
    					
    					
    					};
    private ParkingLocation(Parcel in)
	{
		m_locationID = in.readInt();
		m_uLat = in.readInt();
		m_uLng = in.readInt();
		this.setType(in.readInt());
		this.setPayment(in.readInt());
		m_limit = in.readInt();
		m_mondayStart = in.readInt();
		m_mondayEnd = in.readInt();
		m_tuesdayStart = in.readInt();
		m_tuesdayEnd = in.readInt();
		m_wednesdayStart  = in.readInt();
		m_wednesdayEnd  = in.readInt();
		m_thursdayStart  = in.readInt();
		m_thursdayEnd = in.readInt();
		m_fridayStart = in.readInt();
		m_fridayEnd = in.readInt();
		m_saturdayStart = in.readInt();
		m_saturdayEnd = in.readInt();
		m_sundayStart = in.readInt();
		m_sundayEnd = in.readInt();
		m_rate = in.readFloat();
		this.setRateTime(in.readInt());
		m_garageRate = in.readString();
		m_name = in.readString();
	}

}
