package edu.pitt.designs1635.ParkIt;

public class ParkingLocation {

	private int 	m_locationID;
	private double 	m_lat;
	private double 	m_lng;
	private String 	m_type;
	private String 	m_payment;
	private int 	m_limit;
	private int		m_mondayStart;
	private int		m_mondayEnd;
	private int		m_tuesdayStart;
	private int		m_tuesdayEnd;
	private int		m_wednesdayStart;
	private int		m_wednesdayEnd;
	private int		m_thursdayStart;
	private int		m_thursdayEnd;
	private int		m_fridayStart;
	private int		m_fridayEnd;
	private int		m_saturdayStart;
	private int		m_saturdayEnd;
	private int		m_sundayStart;
	private int		m_sundayEnd;
	private float	m_rate;
	private String	m_rateTime;
	private String	m_garageRate;
	
	
	
	public int getID() {
		return m_locationID;
	}



	public void setID(int ID) {
		this.m_locationID = ID;
	}



	public double getLatitude() {
		return m_lat;
	}



	public void setLatitude(double latitude) {
		this.m_lat = latitude;
	}



	public double getLongitude() {
		return m_lng;
	}



	public void setLongitude(double longitude) {
		this.m_lng = longitude;
	}



	public String getType() {
		return m_type;
	}



	public void setType(String m_type) {
		this.m_type = m_type;
	}



	public String getPayment() {
		return m_payment;
	}



	public void setPayment(String m_payment) {
		this.m_payment = m_payment;
	}



	public int getLimit() {
		return m_limit;
	}



	public void setLimit(int m_limit) {
		this.m_limit = m_limit;
	}



	public int getMondayStart() {
		return m_mondayStart;
	}



	public void setMondayStart(int m_mondayStart) {
		this.m_mondayStart = m_mondayStart;
	}



	public int getMondayEnd() {
		return m_mondayEnd;
	}



	public void setMondayEnd(int m_mondayEnd) {
		this.m_mondayEnd = m_mondayEnd;
	}



	public int getTuesdayStart() {
		return m_tuesdayStart;
	}



	public void setTuesdayStart(int m_tuesdayStart) {
		this.m_tuesdayStart = m_tuesdayStart;
	}



	public int getTuesdayEnd() {
		return m_tuesdayEnd;
	}



	public void setTuesdayEnd(int m_tuesdayEnd) {
		this.m_tuesdayEnd = m_tuesdayEnd;
	}



	public int getWednesdayStart() {
		return m_wednesdayStart;
	}



	public void setWednesdayStart(int m_wednesdayStart) {
		this.m_wednesdayStart = m_wednesdayStart;
	}



	public int getWednesdayEnd() {
		return m_wednesdayEnd;
	}



	public void setWednesdayEnd(int m_wednesdayEnd) {
		this.m_wednesdayEnd = m_wednesdayEnd;
	}



	public int getThursdayStart() {
		return m_thursdayStart;
	}



	public void setThursdayStart(int m_thursdayStart) {
		this.m_thursdayStart = m_thursdayStart;
	}



	public int getThursdayEnd() {
		return m_thursdayEnd;
	}



	public void setThursdayEnd(int m_thursdayEnd) {
		this.m_thursdayEnd = m_thursdayEnd;
	}



	public int getFridayStart() {
		return m_fridayStart;
	}



	public void setFridayStart(int m_fridayStart) {
		this.m_fridayStart = m_fridayStart;
	}



	public int getFridayEnd() {
		return m_fridayEnd;
	}



	public void setFridayEnd(int m_fridayEnd) {
		this.m_fridayEnd = m_fridayEnd;
	}



	public int getSaturdayStart() {
		return m_saturdayStart;
	}



	public void setSaturdayStart(int m_saturdayStart) {
		this.m_saturdayStart = m_saturdayStart;
	}



	public int getSaturdayEnd() {
		return m_saturdayEnd;
	}



	public void setSaturdayEnd(int m_saturdayEnd) {
		this.m_saturdayEnd = m_saturdayEnd;
	}



	public int getSundayStart() {
		return m_sundayStart;
	}



	public void setSundayStart(int m_sundayStart) {
		this.m_sundayStart = m_sundayStart;
	}



	public int getSundayEnd() {
		return m_sundayEnd;
	}



	public void setSundayEnd(int m_sundayEnd) {
		this.m_sundayEnd = m_sundayEnd;
	}



	public float getRate() {
		return m_rate;
	}



	public void setRate(float m_rate) {
		this.m_rate = m_rate;
	}



	public String getRateTime() {
		return m_rateTime;
	}



	public void setRateTime(String m_rateTime) {
		this.m_rateTime = m_rateTime;
	}



	public String getGarageRate() {
		return m_garageRate;
	}



	public void setGarageRate(String m_garageRate) {
		this.m_garageRate = m_garageRate;
	}



	public ParkingLocation()
	{
		m_locationID = 0;
		m_lat = 0.0;
		m_lng = 0.0;
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
	 * Create 
	 * 
	 * @param ID - the database ID of this parking location
	 * @param lat - the latitude of this parking location
	 * @param lng - the longitude of this parking location
	 * @param type - the type of parking location - "Meter", "Parking Lot", or "Parking Garage"
	 * @param payment - the method of payment - "Cash", "Credit", or "Coin"
	 * @param limit - the length of time you can park at a location.  This is mainly for metered parking.
	 * @param mondayStart - the start time for the hours of operation on Monday.
	 * @param mondayEnd - the end time for the hours of operation on Monday.
	 * @param tuesdayStart - the start time for the hours of operation on Tuesday.
	 * @param tuesdayEnd - the end time for the hours of operation on Tuesday.
	 * @param wednesdayStart - the start time for the hours of operation on Wednesday.
	 * @param wednesdayEnd - the start time for the hours of operation on Monday.
	 * @param thursdayStart - the start time for the hours of operation on Monday.
	 * @param thursdayEnd - the start time for the hours of operation on Monday.
	 * @param fridayStart - the start time for the hours of operation on Monday.
	 * @param fridayEnd - the start time for the hours of operation on Monday.
	 * @param saturdayStart - the start time for the hours of operation on Monday.
	 * @param saturdayEnd - the start time for the hours of operation on Monday.
	 * @param sundayStart - the start time for the hours of operation on Monday.
	 * @param sundayEnd - the start time for the hours of operation on Monday.
	 * @param rate - the rate.
	 * @param rateTime - the time increment of the rate(ie hourly, daily)
	 * @param garageRate - a complex string that stores rates for garages. (format TBD)
	 */
	public ParkingLocation(int ID, double lat, double lng,
			String type, String payment, int limit, int mondayStart,
			int mondayEnd, int tuesdayStart, int tuesdayEnd,
			int wednesdayStart, int wednesdayEnd, int thursdayStart,
			int thursdayEnd, int fridayStart, int fridayEnd,
			int saturdayStart, int saturdayEnd, int sundayStart,
			int sundayEnd, float rate, String rateTime,
			String garageRate) {

		this.m_locationID = ID;
		this.m_lat = lat;
		this.m_lng = lng;
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
