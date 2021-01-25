package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class BuildingType {

    private static BuildingManager buildingManager = BuildingManager.getInstance();

    //Type Hierarchy
    protected BuildingType superType = null;
    protected Set<BuildingType> subTypes = new HashSet<BuildingType>();

    private String typeName;
    private int completionYear;     //Year, in which building was completed (e.g. '1925')
    private boolean commercialUse;  //true, if building is supermarket, store, office building...

    /**
     * @methodtype constructor
     */
    public BuildingType(String typeName, int completionYear, boolean commercialUse){
        this.typeName = typeName;
        this.completionYear = completionYear;
        this.commercialUse = commercialUse;
    }


    /**
     * @methodtype getter
     */
    public String getTypeName(){
        return typeName;
    }
    public int getCompletionYear() {
        return completionYear;
    }
    public boolean isCommercialUse() {
        return commercialUse;
    }
    public BuildingType getSuperType(){
        return superType;
    }


    public void setSuperType(BuildingType buildingType){
        superType = buildingType;
    }

    public Iterator<BuildingType> getSubTypeIterator(){
        return subTypes.iterator();
    }

    public void addSubType(BuildingType buildingType){
        if(buildingType == null){
            throw new IllegalArgumentException("Sub Type is null");
        }
        buildingType.setSuperType(this);
        subTypes.add(buildingType);
    }

    public boolean hasInstance(Building building){
        if(building.getBuildingType() == this){
            return true;
        }
        for(BuildingType type : subTypes){
            if(type.hasInstance(building)){
                return true;
            }
        }
        return false;
    }

    public Building createInstance(){
        return new Building(this);
    }


    public boolean isSubType(BuildingType buildingType){
        BuildingType currentType = superType;
        while(currentType != null){
            if(currentType.equals(buildingType)){
                return true;
            }
            currentType = currentType.getSuperType();
        }
        return false;
    }


   public boolean isEqual(BuildingType building){
        boolean isEqualType = typeName.equals(building.typeName);
        boolean isEqualYear = completionYear == building.completionYear;
        boolean isEqualUse = commercialUse == building.commercialUse;
        return isEqualType && isEqualYear && isEqualUse;
   }

   @Override
    public boolean equals(Object o){
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       BuildingType buildingType = (BuildingType) o;
       return isEqual(buildingType);
   }

   @Override
    public int hashCode(){
        return Objects.hash(this.typeName, this.completionYear, this.commercialUse);
   }
}
