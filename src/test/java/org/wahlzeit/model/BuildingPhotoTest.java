package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class BuildingPhotoTest {

    @Test
    public void testCreateBuildingPhoto(){
        Building testBuilding = new Building(1955, 1200.75, "Apartment Building", 5,
                true, false, false);

        BuildingPhoto testPhoto = new BuildingPhoto(testBuilding);

        Assert.assertEquals(testPhoto.getClass(), BuildingPhoto.class);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateBuildingPhotoIsNull() throws NullPointerException{
        Building b = null;
        BuildingPhoto bp = new BuildingPhoto(b);
    }
}
