package lab03;

//Written by Application programmer
//Vehicle object: Contains attributes for vehicle as well as the ability to
//                clone itself.


public class ListElement {
    private String vehicleID;
    private String year;
    private String make;
    private String model;
    private double miles;
    private String classification;
    private double price;
    
    public void Set(String userVehicleID, String userYear, String userMake,
                    String userModel, double userMiles,
                    String userClassification, double userPrice) {
        vehicleID = userVehicleID;
        year = userYear;
        make = userMake;
        model = userModel;
        miles = userMiles;
        classification = userClassification;
        price = userPrice;
    }
    
        public String GetKey( ) {
        return vehicleID;
    }
        
    public String GetYear( ) {
        return year;
    }
    
    public String GetMake( ) {
        return make;
    }
    
    public String GetModel( ) {
        return model;
    }
    
    public double GetMiles(){
        return miles;
    }
        
    public String GetClassification(){
        return classification;
    }
    
    public double GetPrice(){
        return price;
    }
    
    public ListElement Clone( ) {  //Performs a DEEP COPY
        ListElement clonedElement;
        
        clonedElement = new ListElement( );
        clonedElement.Set(vehicleID, year, make, model, miles, classification,
                          price);
        
        return clonedElement;
    }
}
