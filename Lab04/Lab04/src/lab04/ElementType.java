package lab04;

public class ElementType {
    private int remainder;
    
    public void Set(int userRemainder) {
        remainder = userRemainder;
    }
    
    public int Get( ) {
        return remainder;
    }
    
    public ElementType Clone( ) {
        ElementType clonedElement;
        clonedElement = new ElementType( );
        clonedElement.Set(remainder);
        return clonedElement;
    }
}
