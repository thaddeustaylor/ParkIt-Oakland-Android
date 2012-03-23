package edu.pitt.designs1635.ParkIt;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class MyLocationListener implements LocationListener
{
    @Override
    public void onLocationChanged(Location loc)
    {
        
    }

    @Override
    public void onProviderDisabled(String provider)
    {   } 

    @Override
    public void onProviderEnabled(String provider)
    {   } 

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {   } 

}