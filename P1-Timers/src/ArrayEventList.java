public class ArrayEventList implements FutureEventList
{
    private static Event [] eventList; // array that holds the Event objects
    private static int simulationTime; // simulation time common to all objects in the list
    private int size = 0; // number of elements in the list

    /*
     * ArrayEventList constructor
     * Initializes array eventList with 5 spaces
     */
    public ArrayEventList()
    {
         eventList = new Event [5]; // initial array of Event type
    }
    
    /*
     * Removes the Event at the first index [0] of the array
     * Shifts all the elements succeeding the first element by one index to the left
     * Sets the last space to null
     * Reduces the size of the list by 1 (as a result of the removal of an element)
     * Sets the simulation time to the insertion time of the removed Timer
     * Calls the handle() method
     * @return Event e: the removed Event
     */
    public Event removeFirst()
    {
        Event e = eventList[0]; // first Event that will be removed
        
        if(e == null)
        {
            return e; // in the case of an empty list, handles null pointers
        }

        for(int i = 0; i <= size - 1; i++)
        {
            if(i == (size - 1)) // sets the last spot to be empty
            {
                eventList[i] = null;
            }
            else // moves every element to the left by one
            {
                eventList[i] = eventList[i+1];
            }
        }

        simulationTime = e.getArrivalTime(); //updates the simulation time for the entire list
        e.setInsertionTime(simulationTime); //updates the static variable insertion time that will affect future Timers
        size--;//updates the size of the list
        e.handle();// calls the handle method for this Event 

        return e;
    }

    /*
     * Removes a specified Event e from the list
     * Calls recSearch to recursively search for the index of Event to be removed
     * If the Event exists, then all the Events succeeding the removed element is moved to the left by one
     * Size is reduced by one as the result of the removal of an element
     * Calls the cancel() method
     * Sets boolean found 
     * @param Event e: the Event that must be removed from the list
     * @return boolean found: true if the Event exists in the list, false if it doesn't
     */
    public boolean remove(Event e)
    {
        boolean found = false; // if Event e exists or not in the list
        int index = recSearch(0, size, e); // calls recSearch that searches for the index of Event e, returns -1 if doesn't
        if(index != -1) // if the event exists
        {
            for(int i = index; i < size; i++)
            {
                eventList[i] = eventList[i+1]; // shift everything after the index of Event e to the left
            }
            size--; // reduces size by 1
            found = true; // the Event e is found and removed
            e.cancel(simulationTime); // Event e is cancelled
        }

        return found;
    }

    /*
     * Inserts Event e into the list
     * Sorts the list according the Events' arrival times
     * @param Event e: the Event to be inserted in the list
     * @return: void
     */
    public void insert(Event e)
    {
        Event tempEvent; // temporary Event object used while sorting
    
        if(eventList[0] == null)// sets the first element in the array
        {
            eventList[0] = e;
            size++;
        }
        else
        {
            if((size) < eventList.length) //if the array is NOT full
            {
                eventList[size] = e;
                size++; //update the size of the array
            }
            else // if the array IS full
            {
                doubleArrLength(); //double the size of the array
                eventList[size] = e;
                size++;//update the size of the array
            }
        }

        //sorts the array in ascending order of arrival time of Events
        for(int i = 0; i < size-1; i++)
        {
            int minIndex = i;
            for(int j = i+1; j < size; j++)
            {
                if(eventList[j].getArrivalTime() < eventList[i].getArrivalTime())
                {
                    minIndex = j;
                }
                tempEvent = eventList[minIndex];
                eventList[minIndex] = eventList[i];
                eventList[i] = tempEvent;
            }                                  
        }
        System.out.println(e.toString()); // prints the insertion message
    }

    /*
     * Returns how many Event objects are in the list
     * @return int size
     */
    public int size()
    {
        if(size < 0)
            return 0;

        return size;
    }

    /*
     * Returns how many objects the list can hold aka the length of the arrat
     * @return int capacity
     */
    public int capacity()
    {
        return eventList.length;
    }

    /*
     * Returns the simulationTime
     */
    public int getSimulationTime()
    {
        return simulationTime;
    }

    /*
     * Private helper method that resizes the array (eventList) when more Events than its capacity
     * are inserted.
     * @return void
     */
    private void doubleArrLength()
    {
        Event [] resizedArr = new Event[eventList.length * 2]; // temporary array with double the capacity
            
        for(int i = 0; i < eventList.length; i++)
        {
            resizedArr[i] = eventList[i]; // copies the array's elements from eventList to resizedArray
        }
        eventList = resizedArr; // sets eventList reference to resizedArray reference
    }

    private int recSearch(int low, int high, Event e)
    {
        int mid = (low + high) / 2; // midpoint index

        if(eventList[mid] == null) // empty eventList case
        {
            return -1;
        }
        else if(eventList[mid] == e) // middle Event is the wanted Event
        {
            return mid;
        }
        else if(e.getArrivalTime() < eventList[mid].getArrivalTime()) // gets the left half
        {
            return recSearch(low, mid - 1, e);
        }
        else if(e.getArrivalTime() > eventList[mid].getArrivalTime()) // gets the right half
        {
            return recSearch(mid + 1, high, e);
        }
        else // Event e is not found in the array
        {
            return -1; 
        }
    }
}

