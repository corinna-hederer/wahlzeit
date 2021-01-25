package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class BuildingTest {

    @Test
    public void testIsEqual(){
        BuildingType test = new BuildingType("test", 2021, false);
        Building a = new Building(test, "address");
        Building b = new Building(test, "address");

        boolean isEqual = a.isEqual(b);
        Assert.assertTrue(isEqual);
    }

    @Test
    public void testIsNotEqual(){
        BuildingType x = new BuildingType("test", 2021, false);
        BuildingType y = new BuildingType("default", 2021, false);
        Building a = new Building(x, "address");
        Building b = new Building(y, "address");

        boolean isNotEqual = a.isEqual(b);
        Assert.assertFalse(isNotEqual);
    }
}
