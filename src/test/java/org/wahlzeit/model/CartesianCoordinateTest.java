package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class CartesianCoordinateTest {
    @Test
    public void testIsEqual() {
        CartesianCoordinate c1 = new CartesianCoordinate(1.1,2.02, 3.003);
        CartesianCoordinate c2 = new CartesianCoordinate(1.1,2.02,3.003);

        boolean isEqual = c1.isEqual(c2);

        Assert.assertTrue(isEqual);
    }

    @Test
    public void testIsNotEqual() {
        CartesianCoordinate c1 = new CartesianCoordinate(2.1,2, 3);
        CartesianCoordinate c2 = new CartesianCoordinate(1.1,2,3);

        boolean isEqual = c1.isEqual(c2);

        Assert.assertFalse(isEqual);
    }


    @Test
    public void testGetCartesianDistance(){
        CartesianCoordinate a = new CartesianCoordinate(1,1, 1);
        CartesianCoordinate b = new CartesianCoordinate(2,2,2);

        double distance = a.getCartesianDistance(b);
        double correctValue = Math.sqrt(3);

        Assert.assertEquals(correctValue, distance, 0.000001);

    }

    @Test
    public void testAsSphericCoordinate(){
        CartesianCoordinate a = new CartesianCoordinate(0.0, 1.0, 2.0);
        SphericCoordinate b = a.asSphericCoordinate();
        SphericCoordinate correctValue = new SphericCoordinate(1.5707963267949,0.46364760900081 , 2.2360679774998);

        Assert.assertEquals(correctValue.getPhi(), b.getPhi(), 0.00001);
        Assert.assertEquals(correctValue.getTheta(), b.getTheta(), 0.00001);
        Assert.assertEquals(correctValue.getRadius(), b.getRadius(), 0.00001);

    }
    
    @Test
    public void testOriginAsSphericCoordinate(){
        CartesianCoordinate a = new CartesianCoordinate(0.0, 0.0, 0.0);
        SphericCoordinate b = a.asSphericCoordinate();
        SphericCoordinate correctValue = new SphericCoordinate(0.0, 0.0, 0.0);

        Assert.assertEquals(correctValue.getPhi(), b.getPhi(), 0.0);
        Assert.assertEquals(correctValue.getTheta(), b.getTheta(), 0.0);
        Assert.assertEquals(correctValue.getRadius(), b.getRadius(), 0.0);

    }
    
    @Test
    public void testGetCentralAngle(){
        CartesianCoordinate a = new CartesianCoordinate(1,1,1);
        CartesianCoordinate b = new CartesianCoordinate( 1,1,1);

        double centralAngle = a.getCentralAngle(b);
        double correctValue = 0.0;

        Assert.assertEquals(correctValue, centralAngle, 0.01);

    }
    
    @Test(expected = NullPointerException.class)
    public void testCartesianDistanceNull() throws NullPointerException{
        CartesianCoordinate a = new CartesianCoordinate(1.0,1.0,1.0);
        CartesianCoordinate b = null;
        a.getCartesianDistance(b);
    }

    @Test(expected = AssertionError.class)
    public void testCartesianCoordinateClassInvariant() throws AssertionError{
        CartesianCoordinate a = new CartesianCoordinate(Double.NaN, 1.0, 2.0);
    }

    @Test(expected = AssertionError.class)
    public void testSetInvalidCartesianCoordinate() throws AssertionError{
        CartesianCoordinate a = new CartesianCoordinate(1.0,1.0,1.0);
        a.setxCoordinate(Double.NaN);
        a.setyCoordinate(Double.NaN);
        a.setzCoordinate(Double.NaN);
    }

}

