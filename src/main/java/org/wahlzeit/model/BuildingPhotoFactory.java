package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BuildingPhotoFactory extends PhotoFactory{

    /**
     * @methodtype constructor
     */
    protected BuildingPhotoFactory() {
        // do nothing
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static BuildingPhotoFactory instance = null;

    /**
     * Public singleton access method.
     */
    public static synchronized BuildingPhotoFactory getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("setting generic BuildingPhotoFactory");
            setInstance(new BuildingPhotoFactory());
        }

        return instance;
    }


    /**
     * Method to set the singleton instance of PhotoFactory.
     */
    protected static synchronized void setInstance(BuildingPhotoFactory buildingPhotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize BuildingPhotoFactory twice");
        }

        instance = buildingPhotoFactory;
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }


    /**
     * @methodtype factory
     */
    public BuildingPhoto createBuildingPhoto() {
        return new BuildingPhoto();
    }


    /**
     * @methodtype factory
     */
    public BuildingPhoto createBuildingPhoto(PhotoId id) {
        return new BuildingPhoto(id);
    }


    /**
     *
     */
    public BuildingPhoto createBuildingPhoto(ResultSet rs) throws SQLException {
        return new BuildingPhoto(rs);
    }

    /**
     *
     */
    public PhotoFilter createPhotoFilter() {
        return new PhotoFilter();
    }

    /**
     *
     */
    public PhotoTagCollector createPhotoTagCollector() {
        return new PhotoTagCollector();
    }


}
