package com.gaziuni.hayatayolverapp;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLooper;
import org.robolectric.annotation.Config;
import org.robolectric.Shadows;




import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@Config(sdk = Build.VERSION_CODES.P)

@RunWith(RobolectricTestRunner.class)
public class ExampleUnitTest {
    @Mock
    private Geocoder mockGeocoder;
    private UserActivity userActivity;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        userActivity = new UserActivity();
        userActivity.geocoder = mockGeocoder;
    }
    @Test
    public void testCurrentLocation() throws IOException {
        // Arrange
        Location mockLocation = Mockito.mock(Location.class);
        Mockito.when(mockLocation.getLatitude()).thenReturn(40.7128);
        Mockito.when(mockLocation.getLongitude()).thenReturn(-74.0060);
        List<Address> mockAddressList = new ArrayList<>();
        Address mockAddress = Mockito.mock(Address.class);
        mockAddress.setLatitude(40.7128);
        mockAddress.setLongitude(-74.0060);
        mockAddress.setCountryName("United States");
        mockAddressList.add(mockAddress);
        Mockito.when(mockGeocoder.getFromLocation(anyDouble(), anyDouble(), anyInt())).thenReturn(mockAddressList);
        userActivity.currentLocation();
    }

}