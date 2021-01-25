package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class BuildingManagerTest {

    @Test
    public void testGetClassInstance(){
        BuildingManager manager = BuildingManager.getInstance();
        Assert.assertEquals(BuildingManager.class, manager.getClass());
    }

    @Test
    public void testCreateBuilding(){
        BuildingType type = BuildingManager.getInstance().getOrCreateBuildingType("test", 2021, true);
        Building build = BuildingManager.getInstance().createBuilding(type, "address");
        Building ing = BuildingManager.getInstance().createBuilding(type, "address");

        Assert.assertSame(build.getBuildingType(), ing.getBuildingType());
        Assert.assertEquals("test", build.getBuildingType().getTypeName());
        Assert.assertEquals(2021, build.getBuildingType().getCompletionYear());
        Assert.assertTrue(build.getBuildingType().isCommercialUse());
    }

    @Test
    public void testGetOrCreateBuildingType(){
        BuildingType a = BuildingManager.getInstance().getOrCreateBuildingType("default", 2021, false);
        BuildingType b = BuildingManager.getInstance().getOrCreateBuildingType("default", 2021, false);
        BuildingType c = BuildingManager.getInstance().getOrCreateBuildingType("default", 2020, true);

        Assert.assertNotNull(a);
        Assert.assertNotNull(b);
        Assert.assertEquals(a, b);
        Assert.assertNotEquals(a, c);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertInvalidBuildingType(){
        BuildingType a = new BuildingType("New Type", 2010, false);
        BuildingManager.getInstance().createBuilding(a, "default");
    }
}
