package lab06;

public class Selection {
    
    private char code;
    private String description;
    private boolean validation;
    
    public void Set(char iCode, String iDescription, boolean iValidation){
        code = iCode;
        description = iDescription;
        validation = iValidation;
    }
    
    public char GetCode(){
        return code;
    }
    
    public String GetDescription(){
        return description;
    }
    
    public boolean GetValidation(){
        return validation;
    }
}
