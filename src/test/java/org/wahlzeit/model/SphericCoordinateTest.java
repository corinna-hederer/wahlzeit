package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class SphericCoordinateTest {

    @Test
    public void testIsEqual() {
        SphericCoordinate a = SphericCoordinate.getOrCreateSphericCoordinate(10, 5, 1);
        SphericCoordinate b = SphericCoordinate.getOrCreateSphericCoordinate(10, 5, 1);

        boolean isEqual = a.isEqual(b);

        Assert.assertTrue(isEqual);
    }

    @Test
    public void testIsNotEqual() {
        SphericCoordinate a = SphericCoordinate.getOrCreateSphericCoordinate(10, 5, 1);
        SphericCoordinate b = SphericCoordinate.getOrCreateSphericCoordinate(15, 10, 5);

        boolean isEqual = a.isEqual(b);

        Assert.assertFalse(isEqual);
    }

    @Test
    public void testAsCartesianCoordinate() {
        SphericCoordinate a = SphericCoordinate.getOrCreateSphericCoordinate(1.57080, 0.46365, 2.23607);

        CartesianCoordinate b = a.asCartesianCoordinate();
        CartesianCoordinate correctValue = CartesianCoordinate.getOrCreateCartesianCoordinate(0.0, 1.0, 2.0);

        Assert.assertEquals(correctValue.getxCoordinate(), b.getxCoordinate(), 0.00001);
        Assert.assertEquals(correctValue.getyCoordinate(), b.getyCoordinate(), 0.00001);
        Assert.assertEquals(correctValue.getzCoordinate(), b.getzCoordinate(), 0.00001);

    }

    @Test
    public void testGetCartesianDistance(){
        SphericCoordinate a = SphericCoordinate.getOrCreateSphericCoordinate(0.95532,0.78540, 1.73205); //CartesianCoordinate(1,1,1)
        SphericCoordinate b = SphericCoordinate.getOrCreateSphericCoordinate(0.95532,0.78540,3.46410); //CartesianCoordinate(2,2,2)

        double distance = a.getCartesianDistance(b);
        double correctValue = Math.sqrt(3);

        Assert.assertEquals(correctValue, distance, 0.00001);

    }

    @Test
    public void testGetCentralAngle(){
        SphericCoordinate a = SphericCoordinate.getOrCreateSphericCoordinate(0.78540, 1.57080, 1.41421); //CartesianCoordinate(1,1,0)
        SphericCoordinate b = SphericCoordinate.getOrCreateSphericCoordinate(2.35620, 1.57080, 1.41421); //CartesianCoordinate(-1,1,0)

        double centralAngle = Math.toDegrees(a.getCentralAngle(b));
        double correctValue = 90;

        Assert.assertEquals(correctValue, centralAngle, 0.01);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSphericCoordinateClassInvariant() throws IllegalArgumentException{
        SphericCoordinate a = SphericCoordinate.getOrCreateSphericCoordinate(Double.NaN, 1.0, 2.0);
    }

    @Test(expected = NullPointerException.class)
    public void testCentralAngleNull() throws NullPointerException{
        SphericCoordinate a = SphericCoordinate.getOrCreateSphericCoordinate(1.0,1.0,1.0);
        SphericCoordinate b = null;
        a.getCentralAngle(b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidSphericCoordinate() throws IllegalArgumentException{
        SphericCoordinate a = SphericCoordinate.getOrCreateSphericCoordinate(Double.NaN, Double.NaN, Double.NaN);
    }
    
    //Shareability
    @Test
    public void testSameSphericCoordinate(){
        SphericCoordinate a = SphericCoordinate.getOrCreateSphericCoordinate(1,2,3);
        SphericCoordinate b = SphericCoordinate.getOrCreateSphericCoordinate(1,2,3);

        Assert.assertEquals(a, b);
    }

}
