package lab04;

public class StackADT {
    //"Preconditions/Assumptions":
    //  Appl programmer must provide an ElementType object to hold their data.
    //  Appl programmer must provide a Clone method that performs a deep copy.

    //Attributes
    private ElementType [ ] elements;
    private int top;  //numItems in actuality
    private boolean exists;
    
    //Methods
    public int Count( ) {
        //Description:      Identifies the number of elements in the stack.
        //Preconditions:    Stack must have been created (exists).
        //Postconditions:   Returns the number of elements in the stack.
        //                  The stack remains unchanged.
        return top;
    }
    
    public void Destroy( ) {
        //Description:      Removes all data and sets stack to an 
        //                      unusable state.
        //Preconditions:    Stack must have been created (exists).
        //Postconditions:   Stack will no longer exist and will be in an
        //                      unusable state.
        /*DEFENSIVE*/
        for(int cnt = 0; cnt < elements.length; cnt++)
            elements[cnt] = null;
        
        elements = null;
        top = -100;
        exists = false;
        
    }
    
    public boolean IsEmpty( ) {
        //Description:      Identifies if the stack is empty.
        //Preconditions:    Stack must have been created (exists).
        //Postconditions:   When the stack is empty, returns a true value.
        //                  When the stack is full, returns a false value.
        //                  The stack remains unchanged.
        boolean empty;
        
        empty = (top == 0);
        
        return empty;
    }
    
    public boolean IsFull( ) {
        //Description:      Identifies if the stack is full (i.e. has no more
        //                      capacity).
        //Preconditions:    Stack must have been created (exists).
        //Postconditions:   When the stack is full, returns a true value.
        //                  When the stack is not full, returns a false value.
        //                  The stack remains unchanged.
        boolean full;
        
        if(top > elements.length) 
            System.out.println("Stack is corrupted:  improper top.");
        if(top == elements.length)
            full = true;
        else
            full = false;
        
        return full;
    }
    
    public boolean IsCreated( ) {
        //Description:      Identifies if the stack has been created (exists).
        //Preconditions:    None.
        //Postconditions:   When stack was created, returns a true value.
        //                  When stack was NOT created, returns a false value.
        //                  The stack remains unchanged.
        return exists;
    }
    
    public void Push(ElementType newElement) {
        //Description:      Places a deep copy of the given element on the top 
        //                      of the stack.
        //Preconditions:    Stack must not be full.
        //                  Stack must have been created (exists).
        //                  newElement must be instantiated and passed in.
        //Postconditions:   A copy of the given element will be on the top
        //                      of the stack.
        elements[top] = newElement.Clone( );
        top++;
    }
    
    public ElementType Pop( ) {
        //Description:      Returns the top element.
        //Preconditions:    Stack must not be empty.
        //                  Stack must exist (i.e. been created).
        //Postconditions:   Returned element will be the top element.
        //                  Top element is removed from the stack.
        ElementType returnElement;
        /*
        returnElement = elements[top - 1];
        elements[top - 1] = null; //defensive coding
        top--;
        */
        top--;
        returnElement = elements[top];
        elements[top] = null; //defensive coding
        
        return returnElement;
    }
    
    public ElementType Peek( ) {
        //Description:      Gives back a copy of the top element.
        //Preconditions:    Stack must not be empty.
        //                  Stack must exist (i.e. been created).
        //Postconditions:   A copy of the top element will be returned.
        //                  The stack remains unchanged.
        /* This could be done with a pop then push. */
        return elements[top - 1].Clone( );
    }
    
    public StackADT( ) {
        //Description:      DEFAULT CONSTRUCTOR - sets stack to an unusable 
        //                      state.
        //Preconditions:    None.
        //Postconditions:   Stack does not exist and is is an unusable state.
        elements = null;
        top = -100;
        exists = false;
    }
    
    public StackADT(int specifiedSize) {
        //Description:      PARAMETERIZED CONSTRUCTOR - Establishes a stack of 
        //                      a specified size that is usable and in an 
        //                      empty state.
        //Preconditions:    Stack must not exist.
        //                  Must provide a specified size > 0.
        //Postconditions:   Stack exists and is in an empty state.
        Create(specifiedSize);
    }
    
    public void Create(int specifiedSize) {
        //Description:      Establishes a stack of a specified size that is 
        //                      usable and in an empty state.
        //Preconditions:    Stack must not exist.
        //                  Must provide a specified size > 0.
        //Postconditions:   Stack exists and is in an empty state.
        elements = new ElementType[specifiedSize];
        top = 0;
        exists = true;
    }
}
