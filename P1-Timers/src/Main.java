import java.util.Scanner; // import Scanner class 
import java.util.ArrayList; // import ArrayList class
import java.io.*; // import io*

public class Main 
{
    public static void main(String[] args) throws Exception 
    {
        String filename; // name of the file to be read
        Scanner scnr = new Scanner(System.in); //Scanner for user input

        System.out.print("\nEnter filename: ");
        filename = scnr.next(); 
        System.out.println(); // empty line after entering filename

        File openFile = new File(filename); // opening the file
        Scanner filescan = new Scanner(openFile); //connecting the scanner and the file

        ArrayEventList eventList = new ArrayEventList(); // eventList object used to maintain Events
        ArrayList<Timer> tempList = new ArrayList<Timer>(); // local arraylist of Timers used for binary search

        while(filescan.hasNext()) // reads the file until runs out of tokens to read
        {
            String token = filescan.next(); // holds the command read from the file
            if(token.equals("I")) // insertion case
            {
                Timer timerObj = new Timer(filescan.nextInt()); // declaring Timer object
                eventList.insert(timerObj); // inserting object into eventList
                tempList.add(timerObj); // adding object to local arraylist
            }
            else if(token.equals("R")) // remove case
            {
                Event temp = eventList.removeFirst(); // holds the removed Event in temp
                if(temp == null) // if the list is empty and remove command is given
                {
                    continue; // avoids null pointer exception
                }
                tempList.remove(temp); // removes first Timer from the list
            }
            else if(token.equals("C")) // cancel case
            {
                int timerID = filescan.nextInt(); // holds the ID of the Timer to be cancelled
                for(int i = 0; i < tempList.size(); i++)
                {
                    if(tempList.get(i).getID() == timerID) // searches for Timer in local arraylist
                    {
                        if(eventList.remove(tempList.get(i))) // if Timer exists and is removed
                            break; // breaks from the loop
                        else
                            continue; // otherwise continues with the program
                    }
                }
            }
        }// end while filescan.hasNext()

        System.out.println("\nFuture event list size: " + eventList.size()); // prints size
        System.out.println("Future event list capacity: " + eventList.capacity()); // prints capacity

        scnr.close();
        filescan.close();
    }
}
