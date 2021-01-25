package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class BuildingTypeTest {

    @Test
    public void testAddSubType(){
        BuildingType sub = new BuildingType("sub", 2021, false);
        BuildingType sup = new BuildingType("sup", 2021, true);

        sup.addSubType(sub);
        Assert.assertEquals(sub.superType, sup);
    }

    @Test
    public void testHasInstance(){
        BuildingType sub = new BuildingType("sub", 2021, false);
        BuildingType sup = new BuildingType("sup", 2021, true);

        sup.addSubType(sub);
        Building a = new Building(sub);
        Assert.assertTrue(sup.hasInstance(a));
    }

    @Test
    public void testHasNoInstance(){
        BuildingType sub = new BuildingType("sub", 2021, false);
        BuildingType sup = new BuildingType("sup", 2021, true);

        sup.addSubType(sub);
        Building a = new Building(sup);
        Assert.assertFalse(sub.hasInstance(a));
    }

    @Test
    public void testCreateInstance(){
        BuildingType a = new BuildingType("test", 2021, false);

        Assert.assertEquals("test", a.getTypeName());
        Assert.assertEquals(2021, a.getCompletionYear());
        Assert.assertFalse(a.isCommercialUse());
    }

    @Test
    public void testIsSubType(){
        BuildingType sub = new BuildingType("sub", 2021, false);
        BuildingType sup = new BuildingType("sup", 2021, true);
        BuildingType supsup = new BuildingType("supsup", 2021, true);
        BuildingType supsupsup = new BuildingType("supsupsup", 2021, true);

        supsupsup.addSubType(supsup);
        supsup.addSubType(sup);
        sup.addSubType(sub);

        Assert.assertTrue(sub.isSubType(supsupsup));
        Assert.assertTrue(sup.isSubType(supsupsup));
    }

    @Test
    public void testIsNoSubType(){
        BuildingType sub = new BuildingType("sub", 2021, false);
        BuildingType sup = new BuildingType("sup", 2021, true);
        BuildingType test = new BuildingType("test", 2021, true);
        BuildingType supsup = new BuildingType("supsup", 2021, true);
        BuildingType supsupsup = new BuildingType("supsupsup", 2021, true);

        supsupsup.addSubType(supsup);
        supsup.addSubType(sup);
        sup.addSubType(sub);
        supsupsup.addSubType(test);

        Assert.assertFalse(sub.isSubType(test));
    }

    @Test
    public void testIsEqual(){
        BuildingType a = new BuildingType("test", 2021, false);
        BuildingType b = new BuildingType("test", 2021, false);

        boolean isEqual = a.equals(b);
        Assert.assertTrue(isEqual);
    }

    @Test
    public void testIsNotEqual(){
        BuildingType a = new BuildingType("test", 2021, false);
        BuildingType b = new BuildingType("test", 2021, true);

        boolean isEqual = a.equals(b);
        Assert.assertFalse(isEqual);
    }

}
