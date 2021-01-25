package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class BuildingPhotoTest {

    @Test
    public void testCreateBuildingPhoto(){
        BuildingType testType = new BuildingType("Supermarket", 2015, true);
        Building testBuilding = new Building(testType);
        BuildingPhoto testPhoto = new BuildingPhoto(testBuilding);

        Assert.assertEquals(testPhoto.getClass(), BuildingPhoto.class);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateBuildingPhotoIsNull() throws NullPointerException{
        Building b = null;
        BuildingPhoto bp = new BuildingPhoto(b);
    }
}



