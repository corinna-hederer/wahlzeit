package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;


@PatternInstance(
        patternName = "Singleton",
        participants = {},
        participantClasses = {"PhotoManager", "BuildingPhotoManager"}
)

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
        if(rset == null){
            throw new IllegalArgumentException("ResultSet is null");
        }
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
        if(file == null){
            throw new IllegalArgumentException("File is null");
        }
        PhotoId id = PhotoId.getNextId();
        BuildingPhoto pic = (BuildingPhoto) PhotoUtil.createPhoto(file, id);
        addPhoto(pic);
        return pic;
    }

}
