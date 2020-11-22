package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BuildingPhotoManager extends PhotoManager{

    protected static final BuildingPhotoManager instance = new BuildingPhotoManager();

    /**
     * BuildingPhotoManager constructor implements a new object
     * @methodtype constructor
     */
    public BuildingPhotoManager() {
        super();
    }

    /**
     * create a BuildingPhoto Object
     * @methodtype command
     * @param rset ResultSet
     * @return BuildingPhoto Object
     * @throws SQLException
     */
    protected BuildingPhoto createObject(ResultSet rset) throws SQLException {
        return BuildingPhotoFactory.getInstance().createBuildingPhoto(rset);
    }
}
