package edu.pitt.designs1635.Directions;

import edu.pitt.designs1635.Directions.DrivingDirectionsGoogleKML;

public class DrivingDirectionsFactory
{
	public static DrivingDirections createDrivingDirections ()
	{
		return new DrivingDirectionsGoogleKML ();
	}
}
