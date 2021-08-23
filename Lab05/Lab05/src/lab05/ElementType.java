package lab05;

//Written by Appl programmer to hold the data for the Queue

public class ElementType {
    private long waitEnterTime;
    private long payEnterTime;
    private long recieveEnterTime;
    private long leaveTime;
    
    public ElementType(){
        //Default Constructor
    }
    
    private void Set(long waitEnterTimeU, long payEnterTimeU,
                     long recieveEnterTimeU, long leaveTimeU) {
        //Used exclusively for a cloned element.
        waitEnterTime    = waitEnterTimeU;
        payEnterTime     = payEnterTimeU;
        recieveEnterTime = recieveEnterTimeU;
        leaveTime        = leaveTimeU;
    }
    
    public void SetEnterTime(char timeType){
        switch (timeType){
            case 'W':
                waitEnterTime = System.currentTimeMillis();
                break;
            case 'P':
                payEnterTime = System.currentTimeMillis();
                break;
            case 'R':
                recieveEnterTime = System.currentTimeMillis();
                break;
            default:
                System.out.println("Error in processing customers.");
        }
    }
    
    public void SetLeaveTime(){
        leaveTime = System.currentTimeMillis();
    }
    
    public long GetWaitEnterTime(){
        return waitEnterTime;
    }
    
    public long GetPayEnterTime(){
        return payEnterTime;
    }
    
    public long GetRecieveEnterTime(){
        return recieveEnterTime;
    }
    
    public long GetLeaveTime(){
        return leaveTime;
    }
    
    public ElementType Clone( ) {
        ElementType clonedElement;
        
        clonedElement = new ElementType( );
        clonedElement.Set(waitEnterTime, payEnterTime, recieveEnterTime,
                          leaveTime);
        return clonedElement;
    }
}
