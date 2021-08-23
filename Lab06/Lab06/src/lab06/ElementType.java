package lab06;

public class ElementType {
    private int orderNumber;
    private String orderName;
    private String streetAddress;
    private String city;
    private String state;
    private int zip;
    private String phoneNum;
    private int raisonPieNum;
    private int twinkiePieNum;
    private int nachoPieNum;
    
    public ElementType(){
        //Null Constructor
    }
    
    public void Set(int iOrderNumber, String iOrderName, String iStreetAddress,
                    String iCity, String iState, int iZip, String iPhoneNum,
                    int iRaisonPieNum, int iTwinkiePieNum, int iNachoPieNum){
        orderNumber = iOrderNumber;
        orderName = iOrderName;
        streetAddress = iStreetAddress;
        city = iCity;
        state = iState;
        zip = iZip;
        phoneNum = iPhoneNum;
        raisonPieNum = iRaisonPieNum;
        twinkiePieNum = iTwinkiePieNum;
        nachoPieNum = iNachoPieNum; 
    }
            
    
    public int GetNachoPieNum(){
        return nachoPieNum;
    }    
    
    public int GetTwinkiePieNum(){
        return twinkiePieNum;
    }    

    public int GetRaisonPieNum(){
        return raisonPieNum;
    }
    
    public String GetPhoneNumber(){
        return phoneNum;
    }
    
    public int GetZip(){
        return zip;
    }    
    
    public String GetState(){
        return state;
    }    
    
    public String GetCity(){
        return city;
    }    
    
    public String GetStreetAddress(){
        return streetAddress;
    }
    
    public String GetOrderName(){
        return orderName;
    }
    
    public int GetOrderNumber(){
        return orderNumber;        
    }
    
    public ElementType Clone(){
        ElementType clonedElement;
        
        clonedElement = new ElementType();
        clonedElement.Set(orderNumber, orderName, streetAddress, city, state,
                zip, phoneNum, raisonPieNum, twinkiePieNum, nachoPieNum);
        
        return clonedElement;
    }
}
