package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class LocationTest {

    @Test
    public void instantiateLocation() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1.0,2.0,3.0);
        Location location = new Location(cartesianCoordinate);

        Assert.assertNotNull(location);
        Assert.assertEquals(location.cartesianCoordinate, cartesianCoordinate);
    }
}

