public class Timer implements Event
{

    private static int ID = -1; // tracks timer's IDs across the list, dependent on previous Timer's ID
    private int id; //s id pecific to each Timer
    private int arrivalTime = 0; // simulation time when Timer is inserted + duration of Timer
    private static int insertionTime = 0; // simulation time when Timer is inserted

    /*
     * Timer constructor
     * @param int duration: span of time an Event stays in the list for
     * Initializes arrivalTime, ID, and id
     * @return : no return type
     */
    public Timer(int duration)
    {
        arrivalTime = duration + insertionTime; 
        ID++; // increments every time a new Timer object is created
        id = ID;
    }

    /*
     * Sets the insertion time to current time
     * Insertion time = simulation time when the Event is inserted into the list
     * @param int currentTime: simulation time when the Event is inserted
     * @return: void
     */
    public void setInsertionTime(int currentTime)
    {
        insertionTime = currentTime;
    }

    /*
     * Gets the insertion time
     * @return: int insertion time
     */
    public int getInsertionTime()
    {
        return insertionTime;
    }

    /*
     * Gets the arrival time
     * @return int arrivalTime: duration + insertion time
     */
    public int getArrivalTime()
    {
       return arrivalTime;
    }

    /*
     * Cancels the Timer with the specified ID at currentTime
     * Prints the cancellation message
     * @param int currentTime
     * @return: void
     */
    public void cancel(int currentTime)
    {
        System.out.println("Timer " + id + " canceled at time: " + currentTime);
    }

    /*
     * Handles the Event (Timer) with the specified ID
     * Prints the handle message
     * @return: void
     */
    public void handle()
    {
        System.out.println("Timer " + id +  " handled (arrival time: " + arrivalTime + ")");
    }

    /*
     * Gets the id specific to an Event
     * @return int id
     */
    public int getID()
    {
        return id;
    }

    /*
     * Overloaded toString method that forms the Timer ID, insertion time, and arrival time
     * into one string.
     * @return String timerString
     */
    public String toString()
    {
        String timerString = "Timer " + ID + " (insertion time: " + insertionTime + ", arrival time: " + arrivalTime + ")";
        return timerString;
    }
}
