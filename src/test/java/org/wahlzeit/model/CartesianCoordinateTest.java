package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class CartesianCoordinateTest {
    @Test
    public void testIsEqual() {
        CartesianCoordinate c1 = CartesianCoordinate.getOrCreateCartesianCoordinate(1.1,2.02, 3.003);
        CartesianCoordinate c2 = CartesianCoordinate.getOrCreateCartesianCoordinate(1.1,2.02,3.003);

        boolean isEqual = c1.isEqual(c2);

        Assert.assertTrue(isEqual);
    }

    @Test
    public void testIsNotEqual() {
        CartesianCoordinate c1 = CartesianCoordinate.getOrCreateCartesianCoordinate(2.1,2, 3);
        CartesianCoordinate c2 = CartesianCoordinate.getOrCreateCartesianCoordinate(1.1,2,3);

        boolean isEqual = c1.isEqual(c2);

        Assert.assertFalse(isEqual);
    }


    @Test
    public void testGetCartesianDistance(){
        CartesianCoordinate a = CartesianCoordinate.getOrCreateCartesianCoordinate(1,1, 1);
        CartesianCoordinate b = CartesianCoordinate.getOrCreateCartesianCoordinate(2,2,2);

        double distance = a.getCartesianDistance(b);
        double correctValue = Math.sqrt(3);

        Assert.assertEquals(correctValue, distance, 0.000001);

    }

    @Test
    public void testAsSphericCoordinate(){
        CartesianCoordinate a = CartesianCoordinate.getOrCreateCartesianCoordinate(0.0, 1.0, 2.0);
        SphericCoordinate b = a.asSphericCoordinate();
        SphericCoordinate correctValue = SphericCoordinate.getOrCreateSphericCoordinate(1.5707963267949,0.46364760900081 , 2.2360679774998);

        Assert.assertEquals(correctValue.getPhi(), b.getPhi(), 0.00001);
        Assert.assertEquals(correctValue.getTheta(), b.getTheta(), 0.00001);
        Assert.assertEquals(correctValue.getRadius(), b.getRadius(), 0.00001);

    }

    @Test
    public void testOriginAsSphericCoordinate(){
        CartesianCoordinate a = CartesianCoordinate.getOrCreateCartesianCoordinate(0.0, 0.0, 0.0);
        SphericCoordinate b = a.asSphericCoordinate();
        SphericCoordinate correctValue = SphericCoordinate.getOrCreateSphericCoordinate(0.0, 0.0, 0.0);

        Assert.assertEquals(correctValue.getPhi(), b.getPhi(), 0.0);
        Assert.assertEquals(correctValue.getTheta(), b.getTheta(), 0.0);
        Assert.assertEquals(correctValue.getRadius(), b.getRadius(), 0.0);

    }

    @Test
    public void testGetCentralAngle(){
        CartesianCoordinate a = CartesianCoordinate.getOrCreateCartesianCoordinate(1,1,1);
        CartesianCoordinate b = CartesianCoordinate.getOrCreateCartesianCoordinate( 1,1,1);

        double centralAngle = a.getCentralAngle(b);
        double correctValue = 0.0;

        Assert.assertEquals(correctValue, centralAngle, 0.01);

    }

    @Test(expected = NullPointerException.class)
    public void testCartesianDistanceNull() throws NullPointerException{
        CartesianCoordinate a = CartesianCoordinate.getOrCreateCartesianCoordinate(1.0,1.0,1.0);
        CartesianCoordinate b = null;
        a.getCartesianDistance(b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCartesianCoordinateClassInvariant() throws IllegalArgumentException{
        CartesianCoordinate a = CartesianCoordinate.getOrCreateCartesianCoordinate(Double.NaN, 1.0, 2.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidCartesianCoordinate() throws IllegalArgumentException{
        CartesianCoordinate a = CartesianCoordinate.getOrCreateCartesianCoordinate(Double.NaN,Double.NaN, Double.NaN);
    }
    
    //Shareability
    @Test
    public void testSameCartesianCoordinate(){
        CartesianCoordinate a = CartesianCoordinate.getOrCreateCartesianCoordinate(1,1,1);
        CartesianCoordinate b = CartesianCoordinate.getOrCreateCartesianCoordinate(1,1,1);

        Assert.assertEquals(a, b);
    }

}
