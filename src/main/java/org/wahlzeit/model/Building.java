package org.wahlzeit.model;

import java.util.Objects;

/**
 * Documentation of instantiation process
 *
 * Sequence of method calls that leads to new object:
 * 1. BuildingManager::createBuilding -> createBuilding() from BuildingManager is called to initiate object creation
 * 2. BuildingType::createInstance -> createInstance() from class BuildingType is called to get the type of demanded Building instance
 * 3. Building::Building -> Building constructor is called, Building object is created
 *
 * Object creation as a point in the solution space:
 * 1. Delegation:       separate-object     -> BuildingManager and BuildingType
 * 2. Selection:        on-the-spot         -> hard-coded constructor, createInstance() always creates Building instance
 * 3. Configuration:    in-code             -> no mapping in configuration necessary
 * 4. Instantiation:    in-code             -> createInstance() calls Building constructor
 * 5. Initialization:   default             -> fixed field assignment in constructor
 * 6. Building:         default             -> new object creates dependant object structure
 */

/**
 * A building is a relatively permanent enclosed construction over a plot of land, having usually a roof and walls,
 * and is used for any of a wide variety of activities
 */

public class Building {

    private BuildingType buildingType;
    protected String address;



    /**
     * @methodtype constructor
     */
    public Building(BuildingType buildingType){
        this.buildingType = buildingType;
    }

    public Building(BuildingType buildingType, String address){
        this.buildingType = buildingType;
        this.address = address;
    }


    /**
     * @methodtype get
     */
    public BuildingType getBuildingType() {
        return buildingType;
    }
    public String getAddress(){
        return address;
    }

    /**
      * @methodtype set
     */
      public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }
    public void setAddress(String address){
          this.address = address;
    }


    public boolean isEqual(Building building){
          boolean isEqualBuildingType = this.buildingType.equals(building.buildingType);
          boolean isEqualAddress = this.address.equals(building.address);
          return isEqualBuildingType && isEqualAddress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return isEqual(building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.buildingType, this.address);
    }
}
