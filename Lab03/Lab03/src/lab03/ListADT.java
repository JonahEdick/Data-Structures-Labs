package lab03;

//Implements a basic, "pure-ish" List ADT
//Written by Utility programmer

//Preconditions:  Appl programmer MUST provide an object named ListElement.
//                Appl programmer MUST provide a deep copy method named Clone -
//                      that returns a copy of the ListElement.
//                Appl programmer MUST provide a GetKey method that returns a 
//                      unique String representing a "key value".

public class ListADT {
    //This version is implemented via an sub-array of OBJECTS
    //Attributes
    private ListElement [ ] elements;
    private int numItems;
    private int current;
    private boolean exists;
    
    //Methods
    
    public boolean Delete(String searchValue) {
        //Description:     Locates the matching element and removes it if found.
        //Preconditions:   Provided searchValue must be properly loaded.
        //Postconditions:  When the ListADT does not "exist", nothing occurs,
        //                  the ListADT remains unchanged, and a false value 
        //                  is returned.
        //                 When the ListADT "exists" and the searchValue is 
        //                  found, the corresponding item is removed and we 
        //                  return a true value.
        //                 When the ListADT "exists"and the searchValue is 
        //                  NOT found, the ListADT remains unchanged and we 
        //                  return a false value.
        boolean deleted;
        boolean found;  //added for clarity
        
        if(!Exists( ))
            deleted = false;
        else {
            found = Search(searchValue);
            
            if(found) {
                /*
                elements[current] = elements[numItems - 1];
                elements[numItems - 1] = null; //defensive
                numItems--;
                */
                numItems--;
                elements[current] = elements[numItems];
                elements[numItems] = null; //defensive
            }
            
            deleted = found;  //added for clarity
        }
        
        return deleted;
    }
    
    public void Destroy( ) {
        //Description:     Sets the list back to an unusable state.
        //Preconditions:   None.
        //Postconditions:  The list shall no longer exist.
        if(Exists( )) {
            for(int cnt = 0; cnt < elements.length; cnt++) //defensive programming
                elements[cnt] = null;                      //defensive programming
            elements = null;
            numItems = -99;
            current = -1000;
            exists = false;
        }
    }
    
    public int Count( ) {
        //Description:     Gives back the number of elements in the ListADT.
        //Preconditions:   None.
        //Postconditions:  When the listADT was created, gives back the number 
        //                  of elements actually stored in the ListADT.
        //                 When the listADT was NOT created, gives back a
        //                  meaningless number.
        return numItems;
    }
    
    public boolean Search(String searchValue) {
        //Description:     Locates an element within the ListADT whose "key"
        //                  matches the given searchValue.
        //Preconditions:   A valid searchValue must be provided.
        //Postconditions:  When the given searchValue matches a key within
        //                  the ListADT, returns a true value and the 
        //                  "current" location will reflect the matching 
        //                  element.
        //                 When the given searchValue does NOT match any key
        //                  within the listADT, returns a false value and the 
        //                  "current" location will be meaningless (and perhaps
        //                  not usable).
        //                 When the listADT does NOT exist, returns a false 
        //                  value and the "current" location will be 
        //                  meaningless (and ABSOLUTELY not usable).
        boolean found;
        
        if(!Exists( ))
            found = false;
        else {
            Reset( );
            while(!AtEnd( ) &&
                    elements[current].GetKey( ).compareTo(searchValue) != 0)
                GetNext( );
            
            if(!AtEnd( ))// &&
                    //elements[current].GetKey( ).equals(searchValue))
                found = true;
            else
                found = false;
        }
        return found;
    }
    
    public boolean IsFull( ) {
        //Description:     Identifies if the list has no remaining capacity.
        //Preconditions:   List must exist.
        //Postconditions:  When the list  was created we return a true value 
        //                  when the list is full (i.e. no further capacity).
        //                 When the list does NOT exist we return a true value
        //                  (also has no capacity remaining).
        //                 When the list was NOT created we return a false 
        //                  value when the list has capacity.
        boolean full;
        /*
        if(!Exists( ) || numItems == elements.length)
            full = true;
        else
            full = false;
        */
        full = (!Exists( ) || numItems == elements.length);
        
        return full;
    }
    
    public boolean Exists( ) {
        //Description:     Identifies if the list was "created".
        //Preconditions:   None.
        //Postconditions:  When the list  was created we return a true value.
        //                 When the list was NOT created we return a false value.
                
        return exists;
    }
    
    public boolean IsEmpty( ) {
        //Description:     Identifies if the list is empty 
        //                  (i.e. no meaningful data).
        //Preconditions:   List must exist.
        //Postconditions:  When the list  is empty we return a true value.
        //                 When the list is NOT empty we return a false value.
        boolean empty;
        
        if(numItems == 0)
            empty = true;
        else
            empty = false;
        
        return empty;
    }
    
    public ListElement Retrieve( ) {
        //Description:     Gives back a copy of the "current" list element.
        //Preconditions:   "Current" element must be "set" before using this.
        //Postconditions:  When the list does not exist, the list is empty, or 
        //                  the "current" list element indicates the end,
        //                      A null value is returned.
        //                 Otherwise
        //                      The "current" list element is returned.
        ListElement currentElement;
        
        if(!exists || IsEmpty( ) || AtEnd( ))
            currentElement = null;
        else
            //currentElement = elements[current];//breaks encapsulation
            currentElement = elements[current].Clone( );
        
        return currentElement;
    }
    
    public boolean GetNext( ) {
        //Description:     Sets the "current" list location to the next element.
        //Preconditions:   None.
        //Postconditions:  When the list does not exist,
        //                  There will be NO change to the list and we 
        //                      return false.
        //                 When the list does exist or are not at the end,
        //                  The "current" element will be advanced 
        //                      to the next element and we return true.
        //                 When the list does exist or we are at the end,
        //                  There will be NO change to the list and we 
        //                      return false.
        boolean success;
        
        if(AtEnd( ) || !exists)
            success = false;
        else {
            current++;
            success = true;
        }
        
        return success;
    }
    
    public void Reset( ) {
        //Description:     Sets the "current" list location to the first element.
        //Preconditions:   None.
        //Postconditions:  When the list does not exist,
        //                  No changes are made to the status of the list.
        //                 When the list exists.
        //                  The "current" element will be the first element.
        if(exists)
            current = 0;
    }
    
    public boolean AtEnd( ) {
        //Description:     Identifies if we are beyond the end of the list data.
        //Preconditions:   None.
        //Postconditions:  When list does not exist,
        //                  The result of this is meaningless.
        //                 When the list does exist,
        //                  Returns true if we are beyond the end and returns
        //                      false if we are "currently" within the data.
        boolean end;
        if(exists && current >= numItems)  //should NEVER be allowed to be >
            end = true;
        else
            if(exists && current < numItems)
                end = false;
            else
                end = true;  //list does not exist - safer to say we are at end.
        return end;
    }
    
    public ListADT( ) {
        //Description:     Initializes the ListADT to an unusable state.
        //Preconditions:   None.
        //Postconditions:  A ListADT instance exists in an UNUSABLE state.
        elements = null;
        numItems = -99;
        current = -1000;
        exists = false;
    }
    
    public ListADT(int specifiedSize) {
        //Description:     Establishes a new "list" of a specified capacity that 
        //                  will be in an empty and usable state.
        //Preconditions:   Specified capacity must be > 1.
        //Postconditions:  A usable list shall exist.
        //                 List will have the specified capacity.
        //                 List will be empty.
        Create(specifiedSize);
    }
    
    public void Create(int specifiedSize) {
        //Description:     Establishes a new "list" of a specified capacity that 
        //                  will be in an empty and usable state.
        //Preconditions:   Specified capacity must be > 1.
        //Postconditions:  A usable list shall exist.
        //                 List will have the specified capacity.
        //                 List will be empty.
        elements = new ListElement[specifiedSize];
        numItems = 0;
        current = 0;
        exists = true;
    }
    
    public boolean Add(ListElement providedElement) {
        //Description:     Adds a copy of the provided element to the list.
        //Preconditions:   Provided element must be instantiated (exist).
        //                 Provided element must be fully and properly loaded.
        //                 Element must be unique (i.e. is not in list already).
        //Postconditions:  When the list DOES NOT EXIST:
        //                      We will return a false value.
        //                 When the list is NOT full:
        //                      Copy of provided element will be added to list.
        //                      We will return a true value.
        //                 When the list IS full:
        //                      The provided element will NOT be added to the 
        //                          list.
        //                      The list status (num elements, current, etc.)
        //                          shall remain unchanged.
        //                      We will return a false value.
        boolean success;
        
        if(exists == false || numItems == elements.length)
            success = false;
        else {
            success = true;
            elements[numItems] = providedElement.Clone( );//DEEP COPY
           /* System.out.printf("IN ADD:%3d  %20s%10d%10d\n", 
                    numItems, elements[numItems].GetItemName( ), 
                    elements[numItems].GetItemSize( ), 
                    elements[numItems].GetItemQuantity( ));*/
            numItems++;
        }
        
        return success;
    }
    
    /*
    public void Debug( ) {
        for(Reset( ); !AtEnd( ); GetNext( ))
            System.out.printf("%3d  %-20s%10d%10d\n", 
                    current, elements[current].GetItemName( ), 
                    elements[current].GetItemSize( ), 
                    elements[current].GetItemQuantity( ));
    }
    */
}
