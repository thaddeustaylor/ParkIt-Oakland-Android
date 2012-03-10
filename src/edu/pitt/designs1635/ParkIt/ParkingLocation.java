package edu.pitt.designs1635.ParkIt;

public class ParkingLocation {

	private int 			m_locationID;
	private int 			m_uLat; //micro Latitude
	private int 			m_uLng; //micro Longitude
	private TYPE 			m_type;
	private PAYMENT_TYPE 	m_payment;
	private int 			m_limit;
	private int				m_mondayStart;
	private int				m_mondayEnd;
	private int				m_tuesdayStart;
	private int				m_tuesdayEnd;
	private int				m_wednesdayStart;
	private int				m_wednesdayEnd;
	private int				m_thursdayStart;
	private int				m_thursdayEnd;
	private int				m_fridayStart;
	private int				m_fridayEnd;
	private int				m_saturdayStart;
	private int				m_saturdayEnd;
	private int				m_sundayStart;
	private int				m_sundayEnd;
	private float			m_rate;
	private RATE_TIME		m_rateTime;
	private String			m_garageRate;
	
	public static enum RATE_TIME 
	{ 
		HOURLY, 
		DAILY 
	};
	
	public static enum PAYMENT_TYPE 
	{ 
		CASH, 
		CREDIT,
		COIN
	};
	
	public static enum TYPE 
	{ 
		METER, 
		PARKING_LOT,
		PARKING_GARAGE
	};

	
	public int getID() {
		return m_locationID;
	}



	public void setID(int ID) {
		this.m_locationID = ID;
	}



	public int getLatitude() {
		return m_uLat;
	}



	public void setLatitude(int latitude) {
		this.m_uLat = latitude;
	}



	public int getLongitude() {
		return m_uLng;
	}



	public void setLongitude(int longitude) {
		this.m_uLng = longitude;
	}



	public TYPE getType() {
		return m_type;
	}



	public void setType(TYPE type) {
		this.m_type = type;
	}



	public PAYMENT_TYPE getPayment() {
		return m_payment;
	}



	public void setPayment(PAYMENT_TYPE payment) {
		this.m_payment = payment;
	}



	/**
	 * Sets the length of time that the parking location can be used.  This is mainly for metered parking. In hours.
	 * 
	 * @return limit - the time limit.
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
	 * @return a string that has the parking rates if this parking location is a garage.
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
	 * Default constructor. Sets all member fields to 0 and "" depending on the field type.
	 */
	public ParkingLocation()
	{
		m_locationID = 0;
		m_uLat = 0;
		m_uLng = 0;
		m_type = "";
		m_payment = "";
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
		m_rateTime = "";
		m_garageRate = "";
	}



	/**
	 * Constructor with all member fields. To convert latitude and longitude to 
	 * micro-latitude and micro-longitude multiple by 1E6(100,000).
	 * 
	 * Example: lat = 40.444282;
	 * 			uLat = lat * 1E6;
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
	 */
	public ParkingLocation(int ID, int ulat, int ulng,
			String type, String payment, int limit, int mondayStart,
			int mondayEnd, int tuesdayStart, int tuesdayEnd,
			int wednesdayStart, int wednesdayEnd, int thursdayStart,
			int thursdayEnd, int fridayStart, int fridayEnd,
			int saturdayStart, int saturdayEnd, int sundayStart,
			int sundayEnd, float rate, String rateTime,
			String garageRate) {

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
	}

	
	
}