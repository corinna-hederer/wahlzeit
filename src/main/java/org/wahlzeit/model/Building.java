package org.wahlzeit.model;

/**
 * A building is a relatively permanent enclosed construction over a plot of land, having usually a roof and walls,
 * and is used for any of a wide variety of activities
 */

public class Building {

    private int completionYear;     //Year, in which building was completed (e.g. '1925')
    private double coverage;        //constructed area in m² (e.g. 650.8)
    private String type;            //kind of building (e.g. residence, cinema, hotel, shop, gym,...)
    private int floor;              //Number of floors (e.g. 3)
    private boolean privateUse;     //true, if building is a residential/terrace house, apartment building,...
    private boolean publicUse;      //true, if building is city hall, school, church...
    private boolean commercialUse;  //true, if building is supermarket, store, office building...


    /**
     * Creates a building instance using the arguments completionYear, coverage, type, floor, privateUse, publicUse,
     * commercialUse
     * @methodtype constructor
     * @param completionYear Year of completion
     * @param coverage built surface area
     * @param type sort of edifice
     * @param floor number of storeys
     * @param privateUse only for private usage
     * @param publicUse publicly accessible
     * @param commercialUse for commercial usage
     */
    public Building(int completionYear, double coverage, String type, int floor, boolean privateUse, boolean publicUse,
                    boolean commercialUse){
        this.completionYear = completionYear;
        this.coverage = coverage;
        this.type = type;
        this. floor = floor;
        this. privateUse = privateUse;
        this.publicUse = publicUse;
        this. commercialUse = commercialUse;
    }


    /**
     * Getter methods for completionYear, coverage, type, floor, privateUse, publicUse and commercialUse
     * @methodtype get
     */
    public int getCompletionYear() {
        return completionYear;
    }

    public double getCoverage() {
        return coverage;
    }

    public String getType(){
        return type;
    }

    public int getFloor(){
        return floor;
    }

    public boolean isPrivateUse() {
        return privateUse;
    }

    public boolean isPublicUse() {
        return publicUse;
    }

    public boolean isCommercialUse() {
        return commercialUse;
    }


    /**
     * Setter methods completionYear, coverage, type, floor, privateUse, publicUse and commercialUse
     * @methodtype set
     */
    public void setCompletionYear(int completionYear) {
        this.completionYear = completionYear;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setPrivateUse(boolean privateUse) {
        this.privateUse = privateUse;
    }

    public void setPublicUse(boolean publicUse) {
        this.publicUse = publicUse;
    }

    public void setCommercialUse(boolean commercialUse) {
        this.commercialUse = commercialUse;
    }
    
        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return completionYear == building.completionYear &&
                Double.compare(building.coverage, coverage) == 0 &&
                floor == building.floor &&
                privateUse == building.privateUse &&
                publicUse == building.publicUse &&
                commercialUse == building.commercialUse &&
                Objects.equals(type, building.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(completionYear, coverage, type, floor, privateUse, publicUse, commercialUse);
    }
    
}
