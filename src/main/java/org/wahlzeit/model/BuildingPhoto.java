package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuildingPhoto extends Photo{

    private Building building;

    /**
     * BuildingPhoto default constructor implements new object
     * @methodtype constructor
     */
    public BuildingPhoto(){
        super();
    }


    /**
     * BuildingPhoto constructor implements new object with delivered attribute
     * @param id PhotoId of BuildingPhoto
     * @methodtype constructor
     */
    public BuildingPhoto(PhotoId id){
        super(id);
    }

    /**
     * BuildingPhoto constructor implements a new object with delivered attribute
     * @param building Building object
     * @methodtype constructor
     */
    public BuildingPhoto(Building building){
        super();
        if(building == null){
            throw new NullPointerException("Building is null");
        }
        this.building = building;
    }

    /**
     * BuildingPhoto constructor implements new object with delivered attribute
     * @param rset ResultSet containing BuildingPhoto information
     * @methodtype initialization
     */
    public BuildingPhoto(ResultSet rset) throws SQLException {
        super(rset);
        if(rset == null){
            throw new NullPointerException("ResultSet is null");
        }
        readFrom(rset);
    }

    /**
     * reads ResultSet information into a building object
     * @param rset ResultSet containing BuildingPhoto information
     * @methodtype command
     */
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        this.building = new Building(
            rset.getInt("buildingCompletionYear"),
            rset.getDouble("buildingCoverage"),
            rset.getString("buildingType"),
            rset.getInt("buildingFloor"),
            rset.getBoolean("buildingPrivateUse"),
            rset.getBoolean("buildingPublicUse"),
            rset.getBoolean("buildingCommercialUse")
        );
    }

    /**
     * writes photo and buildingPhoto details from rset into update object
     * @param rset ResultSet containing BuildingPhoto information
     * @methodtype command
     */
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateInt("buildingCompletionYear", building.getCompletionYear());
        rset.updateDouble("buildingCoverage", building.getCoverage());
        rset.updateString("buildingType", building.getType());
        rset.updateInt("buildingFloor", building.getFloor());
        rset.updateBoolean("buildingPrivateUse", building.isPrivateUse());
        rset.updateBoolean("buildingPublicUse", building.isPublicUse());
        rset.updateBoolean("buildingCommercialUse", building.isCommercialUse());
    }


    /**
     * Getter method for building value object
     * @methodtype get
     * @return building
     */
    public Building getBuilding() {
        return building;
    }


    /**
     * Setter method for building object
     * @methodtype set
     * @param building Building object
     */
    public void setBuilding(Building building) {
        if(building == null){
            throw new IllegalArgumentException("Building is null");
        }
        this.building = building;
    }
}
