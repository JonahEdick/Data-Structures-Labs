package lab05;

        //Preconditions:    Appl programmer must provide an ElementType class.
        //                  ElementType class must implement a deep copy 
        //                          Clone method.

public class QueueADT {
    //Attributes
    private boolean exists;
    private int front;
    private int back;
    ElementType [ ] elements;
    
    //Methods
    public QueueADT( ) {
        //Description:      Instantiates a QueueADT in an unusable form.
        //Preconditions:    None.
        //Postconditions:   QueueADT is instantiated but unusable.
        exists = false;
        front = -100;
        back = -200;
        elements = null;
    }
    
    public ElementType Dequeue( ) {
        //Description:      Gives back the element from the front of the queue.
        //Preconditions:    Queue must exist.
        //Postconditions:   When the queue is not empty, the front element is 
        //                      returned.  The front element will no longer
        //                      be in the queue.
        //                  When the queue is empty, a null value is returned.
        ElementType dequeuedElement;
        
        if(!IsEmpty( )) {
            dequeuedElement = elements[front];
            elements[front] = null;   //defensive
            front = (front + 1) % elements.length;
        }
        else
            dequeuedElement = null;
        
        return dequeuedElement;
    }
    
    public boolean Enqueue(ElementType givenElement) {
        //Description:      Adds the given Element to the back of the queue.
        //Preconditions:    None.
        //Postconditions:   When the given element was added to the back of 
        //                      the queue, returns a true value.
        //                  When the given element was NOT added to the back
        //                      of the queue (full?, not created?,
        //                      givenElement not instantiated, etc.),
        //                      returns a false value.
        boolean added;
        
        if(givenElement != null && exists && !IsFull( )) {
            elements[back] = givenElement.Clone( );
            back = (back + 1) % elements.length;
            added = true;
        }
        else
            added = false;
        return added;
    }
    
    public boolean Exists( ) {
        //Description:      Identifies whether the queue has been created or not.
        //Preconditions:    None.
        //Postconditions:   When the queue was created, returns a true value.
        //                  When the queue has not been created, 
        //                      returns a false value.
        return exists;
    }
    
    public void Create(int specifiedSize) {
        //Description:      Establishes a queue of a specified size in a 
        //                      usable state.  If the queue was previously
        //                      created, this destroys it first.
        //Preconditions:    Must be instantiated.
        //                  Specified size must be greater than 0.
        //Postconditions:   QueueADT will exist.
        //                  QueueADT will have the specified capacity.
        //                  QueueADT will be empty.
        //Defensive---v
        if(exists)
            Destroy( );
        //Defensive---^
        
        exists = true;
        front = 0;
        back = 0;
        elements = new ElementType[specifiedSize + 1];
    }
    
    public void Destroy( ) {
        //Description:      Destroys the queue putting it in an unusable state.
        //Preconditions:    None.
        //Postconditions:   Queue will not be in a usable state.
        if(exists)
            for(int cnt = 0; cnt < elements.length; cnt++)
                elements[cnt] = null;
        exists = false;
        front = -100;
        back = -200;
        elements = null;
    }
    
    public boolean IsFull( ) {
        //Description:      Returns a true value when the Queue is full
        //                      (has no ability to hold more data).
        //                  Returns a false value when the Queue has 
        //                      remaining capacity.
        //Preconditions:    Queue must exist (have been created).
        //Postconditions:   When the Queue is full, returns true.
        //                  When the Queue has remaining capacity, 
        //                      returns false.
        boolean full;
            //back == elements.length - 1
            /*
            if(back + 1 == elements.length && front == 0) //tests wrap-around
                full = true;
            else
                if(back + 1 == front)//tests all other instances
                    full = true;
                else
                    full = false;
            */  //   v----WRAP-AROUND------------v
            full = (((back + 1) % elements.length) == front);
        
        return full;
    }
    
    public boolean IsEmpty( ) {
        //Description:      Returns a true value when the Queue is empty.
        //                  Returns a false value when the Queue has data in it.
        //Preconditions:    Queue must exist (have been created).
        //Postconditions:   When the Queue is empty, returns true.
        //                  When the Queue contains data, returns false.
        boolean empty;
        
        if(front == back)
            empty = true;
        else
            empty = false;
        
        //empty = (front == back);
        
        return empty;
    }
}
