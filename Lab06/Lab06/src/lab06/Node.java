package lab06;

public class Node {
    private ElementType data;
    private Node next; //address of the next node in the linked structure
    
    public void SetData(ElementType uData) {
        data  = uData;
    }
    
    public ElementType GetData( ) {
        return data;
    }
    
    public void SetNextAddress(Node uNext) {
        next = uNext;
    }
    
    public Node GetNextAddress( ) {
        return next;
    }
}
