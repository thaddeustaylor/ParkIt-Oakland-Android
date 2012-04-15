package edu.pitt.designs1635.ParkIt.Directions;

import edu.pitt.designs1635.ParkIt.Directions.DrivingDirectionsGoogleKML;

public class DrivingDirectionsFactory
{
	public static DrivingDirections createDrivingDirections ()
	{
		return new DrivingDirectionsGoogleKML ();
	}
}
