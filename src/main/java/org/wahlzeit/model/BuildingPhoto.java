package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Documentation of instantiation process
 *
 * Sequence of method calls that leads to new object:
 * 1. BuildingPhotoManager::createPhoto -> BuildingPhotoManager is called, createPhoto() from super class PhotoManager is executed to create new object
 * 2. PhotoUtil::createPhoto -> createPhoto() from PhotoUtils is called by PhotoManager
 * 3. BuildingPhotoFactory::createBuildingPhoto -> createBuildingPhoto() in BuildingPhotoFactory is called by PhotoUtil
 * 4. BuildingPhoto::BuildingPhoto -> BuildingPhotoFactory creates BuildingPhoto object by calling the BuildingPhoto constructor
 * 5. Photo::Photo -> BuildingPhoto calls constructor of super class Photo
 *
 * Object creation as a point in the solution space:
 * 1. Delegation:       separate-object     -> BuildingPhotoManager
 * 2. Selection:        by-subclassing      -> extends Photo object
 * 3. Configuration:    in-code             -> PhotoFactory::getInstance
 * 4. Instantiation:    in-code             -> BuildingPhoto constructor called
 * 5. Initialization:   by-fixed-signature  -> fixed field assignment in constructor
 * 6. Building:         default             -> new object creates dependant object structure
 */

@PatternInstance(
        patternName = "Abstract Factory",
        participants = {"AbstractProduct", "ConcreteProduct"},
        participantClasses = {"Photo", "PhotoFactory", "BuildingPhoto", "BuildingPhotoFactory"}
)

public class BuildingPhoto extends Photo{

    private static BuildingManager buildingManager = BuildingManager.getInstance();

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
        BuildingType buildingType = buildingManager.getOrCreateBuildingType(
                rset.getString("typeName"),
                rset.getInt("completionYear"),
                rset.getBoolean("CommercialUse")

        );
        this.building = buildingManager.createBuilding(
                buildingType,
                rset.getString("address")
        );


    }

    /**
     * writes photo and buildingPhoto details from rset into update object
     * @param rset ResultSet containing BuildingPhoto information
     * @methodtype command
     */
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateString("typeName", building.getBuildingType().getTypeName());
        rset.updateInt("completionYear", building.getBuildingType().getCompletionYear());
        rset.updateBoolean("commercialUse", building.getBuildingType().isCommercialUse());
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
