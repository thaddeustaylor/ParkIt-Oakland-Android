package edu.pitt.designs1635.Directions;

import java.util.ArrayList;
import java.util.List;

import edu.pitt.designs1635.Directions.Placemark;
import edu.pitt.designs1635.Directions.Route;
import com.google.android.maps.GeoPoint;

public class RouteImpl implements Route
{
	private String totalDistance;
	private List<GeoPoint> geoPoints;
	private List<Placemark> placemarks;

	public void setTotalDistance(String totalDistance) {
		this.totalDistance = totalDistance;
	}

	public String getTotalDistance() {
		return totalDistance;
	}
	
	public void setGeoPoints(List<GeoPoint> geoPoints) {
		this.geoPoints = geoPoints;
	}

	public List<GeoPoint> getGeoPoints() {
		return geoPoints;
	}
	
	public void addGeoPoint (GeoPoint point)
	{
		if (geoPoints == null) {
			geoPoints = new ArrayList<GeoPoint>();
		}
		geoPoints.add(point);
	}

	public void setPlacemarks(List<Placemark> placemarks) {
		this.placemarks = placemarks;
	}

	public List<Placemark> getPlacemarks() {
		return placemarks;
	}
	
	public void addPlacemark (Placemark mark)
	{
		if (placemarks == null) {
			placemarks = new ArrayList<Placemark>();
		}
		placemarks.add(mark);
	}
}
