package org.wahlzeit.model;

import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.services.Persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class BuildingManager extends ObjectManager {

    //BuildingManager as Singleton
    protected static final BuildingManager instance = new BuildingManager();

    //Cache for building types
    protected HashMap<Integer, BuildingType> typesMap = new HashMap<Integer, BuildingType>();

    //Cache for concrete buildings
    protected HashMap<Integer, Building> buildingsMap = new HashMap<Integer, Building>();


    public static BuildingManager getInstance(){
        if(instance == null){
            return new BuildingManager();
        }
        return instance;
    }


    public Building createBuilding(BuildingType buildingType, String address){

        assertIsValidType(buildingType);
        BuildingType bt = this.typesMap.get(buildingType.hashCode());
        Building building = bt.createInstance();
        this.buildingsMap.put(building.hashCode(), building);
        return building;
    }

    protected BuildingType getOrCreateBuildingType(String typeName, int completionYear, boolean commercialUse){

        assertIsPositiveInteger(completionYear);

        BuildingType newBT = new BuildingType(typeName, completionYear, commercialUse);
        BuildingType existingBT = this.typesMap.get(newBT.hashCode());
        if(existingBT != null){
            return existingBT;
        }
        this.typesMap.put(newBT.hashCode(), newBT);
        return newBT;
    }


    private void assertIsValidType(BuildingType buildingType) {
        if(!this.typesMap.containsKey(buildingType.hashCode())) {
            throw new IllegalArgumentException("This Building Type doesn't exist");
        }
    }

    private void assertIsPositiveInteger(int i){
        if(i<0){
            throw new IllegalArgumentException("IntegerValue is negative");
        }
    }


    @Override
    protected Persistent createObject(ResultSet rset) throws SQLException {
        return null;
    }

}
