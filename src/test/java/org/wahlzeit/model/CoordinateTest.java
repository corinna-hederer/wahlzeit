package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class CoordinateTest {

    @Test
    public void testIsEqual() {
        Coordinate c1 = new Coordinate(1.1,2.02, 3.003);
        Coordinate c2 = new Coordinate(1.1,2.02,3.003);

        boolean isEqual = c1.isEqual(c2);

        Assert.assertTrue(isEqual);
    }
    
    @Test
    public void testGetDistance() {
        Coordinate c1 = new Coordinate(1,1, 1);
        Coordinate c2 = new Coordinate(2,2,2);

        double distance = c1.getDistance(c2);
        double correctValue = Math.sqrt(3);

        Assert.assertEquals(correctValue, distance, 0.00001);
    }

}
