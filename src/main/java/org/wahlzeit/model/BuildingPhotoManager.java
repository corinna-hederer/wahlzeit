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
    
    /**
     * create a BuildingPhoto File to save picture
     * @methodtype command
     * @param file File where photo is stored
     * @return BuildingPhoto Object
     * @throws Exception
     */
    public BuildingPhoto createPhoto(File file) throws Exception {
        PhotoId id = PhotoId.getNextId();
        BuildingPhoto pic = (BuildingPhoto) PhotoUtil.createPhoto(file, id);
        addPhoto(pic);
        return pic;
    }
}
