//Developer:    Jonah C. Edick
//Project:      Lab05
//Due Date:     April 16th, 2021
//Description:  This application is designed to manage a list of queues that 
//              the user can have customers enter and leave. The wait and pay
//              lines are both in association to each other. The two lines come
//              together after the pay lines. The only information that is taken
//              down about the customer is the enter and exit time from each
//              line (based off of system time). After the lines are finished.
//              The user can close and it will upload all the customers data
//              to a file and then the application will go through and process
//              report to the user showing averages, and each customer data.
package lab05;

import java.util.Scanner;
import java.io.*;

public class Lab05 {
    public static void main(String[] args) throws IOException {
        QueueADT waitLine1, waitLine2, payLine1, payLine2, recieveLine;
        int waitLineL, payLineL, recieveLineL, numCustomers;
        
        //Open Store.
        waitLineL = 15;
        payLineL = 10;
        recieveLineL = 5;
        
        waitLine1   = CreateLines(waitLineL);
        waitLine2   = CreateLines(waitLineL);
        payLine1    = CreateLines(payLineL);
        payLine2    = CreateLines(payLineL);
        recieveLine = CreateLines(recieveLineL);
        
        //All transactions while the store is open.
        numCustomers = PerformTransactions(waitLine1, waitLine2, payLine1, 
                                          payLine2, recieveLine);
        
        //Process Daily Sales Report.
        ProcessDailySalesReport(numCustomers);
        
    }
    
    public static QueueADT CreateLines(int lineL){
        QueueADT openedQueue;
        
        openedQueue = new QueueADT();
        
        openedQueue.Create(lineL);
                
        return openedQueue;
    }
    
    public static int PerformTransactions(QueueADT waitLine1,
                                           QueueADT waitLine2,
                                           QueueADT payLine1,
                                           QueueADT payLine2,
                                           QueueADT recieveLine)
                                           throws IOException{
        boolean enterResult, exitResult;
        int numCustomers;
        char userChoice, timeType;
        Scanner kbd;
        File transFile;
        PrintWriter transFilePW;
        ElementType newCustomer, customer;
        
        kbd = new Scanner(System.in);
        
        numCustomers = 0;
        
        transFile = new File("transFile.txt");
        transFilePW = new PrintWriter(transFile);
        
        do{
            
            //Get User Choice
            System.out.print(
            "Poultry-Fil-B. Drive Thru Manager\n"
          + "   Wait Lines (Add new customers to Wait Lines.)\n"
          + "(1) Wait Line 1\n"
          + "(2) Wait Line 2\n"
          + "   Pay Lines (Move people from Wait Lines to Pay Lines.)\n"
          + "(3) Pay Line 1\n"
          + "(4) Pay Line 2\n"
          + "   Recieve Lines (Move people from Pay Lines to Recieve Line.)\n"
          + "(5) Recieve Line 1\n"
          + "(6) Recieve Line 2\n"
          + "   Recieve Window (Finish the current customer's order.)\n"
          + "(7) Finish Current Order\n"
          + "Process Choice: ");
            userChoice = kbd.nextLine().toUpperCase().charAt(0);
            System.out.println("\n");
            
            //Add to Wait Line 1
            if(userChoice == '1'){
                AddToWaitLine(waitLine1, userChoice);
            }
            
            //Add to Wait Line 2
            if(userChoice == '2'){
                AddToWaitLine(waitLine2, userChoice);
            }
            
            //Get Order from Wait Line 1
            if(userChoice == '3'){
                AddToPayLine(waitLine1, payLine1, userChoice);
            }
            
            //Get Order from Wait Line 2
            if(userChoice == '4'){
                AddToPayLine(waitLine2, payLine2, userChoice);
            }
            
            //Get Payment from Pay Line 1
            if(userChoice == '5'){
                AddToRecieveLine(payLine1, recieveLine, userChoice);
            }
            
            //Get Payment from Pay Line 2
            if(userChoice == '6'){
                AddToRecieveLine(payLine2, recieveLine, userChoice);
            }
            
            //Give Customer Their Order
            if(userChoice == '7'){
                numCustomers = FinishCustomerOrder(recieveLine, numCustomers);
            }
            
            if(userChoice == 'Q' &&
               (!waitLine1.IsEmpty() || !waitLine2.IsEmpty()
               || !payLine1.IsEmpty() || !payLine2.IsEmpty()
               || !recieveLine.IsEmpty()))
                System.out.println(
      "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
    + "You cannot close until all customers in line have been taken care of.\n"
    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            
        }while(userChoice != 'Q' || !waitLine1.IsEmpty() || !waitLine2.IsEmpty()
            || !payLine1.IsEmpty() || !payLine2.IsEmpty() 
            || !recieveLine.IsEmpty());
        
        //Close All Lines.
          waitLine1.Destroy();
          waitLine2.Destroy();
           payLine1.Destroy();
           payLine2.Destroy();
        recieveLine.Destroy();
        
        return numCustomers;
    }
    
    public static void AddToWaitLine(QueueADT waitLine, char userChoice){
        boolean enterResult;
        char timeType;
        ElementType newCustomer;
        
        newCustomer = new ElementType();
        timeType = 'W';
        newCustomer.SetEnterTime(timeType);
        enterResult = waitLine.Enqueue(newCustomer);
        if(enterResult == true){
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Successfully Added Customer to Wait Line "
                              + userChoice +"!");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        else{
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Wait Line "+ userChoice +" is currently full.");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
    
    public static void AddToPayLine(QueueADT waitLine, QueueADT payLine,
                                    char userChoice){
        char timeType;
        ElementType customer;
        
        if(userChoice == '3')
            userChoice = '1';
        else
            userChoice = '2';
        
        if(!payLine.IsFull()){
            
            customer = waitLine.Dequeue();
            
            if(customer != null){
            timeType = 'P';
            customer.SetEnterTime(timeType);
            payLine.Enqueue(customer);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                             + "~~~~~~~~~~~~~~~~~");
            System.out.println("Successfully moved customer from Wait Line "
                              + userChoice + " to Pay Line " + userChoice 
                              + "!");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                             + "~~~~~~~~~~~~~~~~~");
            }
            else{
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("There are no customers in Wait Line "
                                 + userChoice + ".");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
            
        }
        else{
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Pay Line "+ userChoice +" is currently full.");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
    
    public static void AddToRecieveLine(QueueADT payLine, QueueADT recieveLine,
                                        char userChoice){
        char timeType;
        ElementType customer;
        
        if(userChoice == '5')
            userChoice = '1';
        else
            userChoice = '2';
        
        if(!recieveLine.IsFull()){
            
            customer = payLine.Dequeue();
            
            if(customer != null){
            timeType = 'R';
            customer.SetEnterTime(timeType);
            recieveLine.Enqueue(customer);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                             + "~~~~~~~~~~~~~~~~~");
            System.out.println("Successfully moved customer from Pay Line "
                              + userChoice + " to Recieve Line!");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                             + "~~~~~~~~~~~~~~~~~");
            }
            else{
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("There are no customers in Pay Line "
                                 + userChoice + ".");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
        else{
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Recieve Line is currently full.");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
    
    public static int FinishCustomerOrder(QueueADT recieveLine, 
                                          int numCustomers) throws IOException{
        ElementType customer;
        File transFile;
        PrintWriter transFilePW;
        FileWriter transFileFW;
        
        customer = recieveLine.Dequeue();
        
        if(customer != null){
            customer.SetLeaveTime();
            
            transFile = new File("transFile.txt");
            transFileFW = new FileWriter(transFile, true);
            transFilePW = new PrintWriter(transFileFW);
            
            transFilePW.println(customer.GetWaitEnterTime());
            transFilePW.println(customer.GetPayEnterTime());
            transFilePW.println(customer.GetRecieveEnterTime());
            transFilePW.println(customer.GetLeaveTime());
            
            transFilePW.close();
            
            numCustomers++;
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Customer Order "+ numCustomers +" Completed!");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        else{
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("There is no customer at the recieve window.");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
            
        
        return numCustomers;
    }
    
    public static void ProcessDailySalesReport(int numCustomers)
                                               throws IOException{
        File transFile;
        Scanner transFileSC;
        long waitEnterTime, payEnterTime, recieveEnterTime, leaveTime,
             waitTime, payTime, recieveTime, totalTime;
        
        double averageWaitTime, averagePayTime, averageRecieveTime, averageTime;
        
        
        waitTime = 0;
        payTime = 0;
        recieveTime = 0;
        totalTime = 0;
        
        transFile = new File("transFile.txt");
        transFileSC = new Scanner(transFile);
        
        //Calculate Averages
        while(transFileSC.hasNext()){
            waitEnterTime = transFileSC.nextLong();
            payEnterTime = transFileSC.nextLong();
            recieveEnterTime = transFileSC.nextLong();
            leaveTime = transFileSC.nextLong();
            
            waitTime = waitTime + (payEnterTime - waitEnterTime);
            payTime = payTime + (recieveEnterTime - payEnterTime);
            recieveTime = recieveTime + (leaveTime - recieveEnterTime);
            totalTime = totalTime + (leaveTime - waitEnterTime);
        }
        
        transFileSC.close();
        
        averageWaitTime = (1.0 * waitTime) / numCustomers;
        averagePayTime = (1.0 * payTime) / numCustomers;
        averageRecieveTime = (1.0 * recieveTime) / numCustomers;
        averageTime = (1.0 * totalTime) / numCustomers;
        
        //Display Average Header
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                         + "~~~~~~~~~~~~~~");
        System.out.println("           Poultry-Fil-B. Daily Sales Report");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                         + "~~~~~~~~~~~~~~");
        System.out.printf("Average Customer Time: %36.2f\n",averageTime);
        System.out.printf("Average Wait Time: %40.2f\n",averageWaitTime);
        System.out.printf("Average Pay Time: %41.2f\n",averagePayTime);
        System.out.printf("Average Recieve Time: %37.2f\n",averageRecieveTime);
        System.out.printf("Number of Customers Processed: %28d\n",numCustomers);
        
        //Display Customer List
        transFile = new File("transFile.txt");
        transFileSC = new Scanner(transFile);
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                         + "~~~~~~~~~~~~~~");
        System.out.println("Cust#    Wait Time   Pay Time    Recieve Time"
                         + "    Total Time");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                         + "~~~~~~~~~~~~~~");
        
        int cnt = 1;
        while(transFileSC.hasNext()){
            waitEnterTime = transFileSC.nextLong();
            payEnterTime = transFileSC.nextLong();
            recieveEnterTime = transFileSC.nextLong();
            leaveTime = transFileSC.nextLong();
            
            waitTime = payEnterTime - waitEnterTime;
            payTime = recieveEnterTime - payEnterTime;
            recieveTime = leaveTime - recieveEnterTime;
            totalTime = leaveTime - waitEnterTime;
            
            System.out.printf("%5d%13d%11d%16d%14d\n",
                              cnt, waitTime, payTime, recieveTime, totalTime);
            cnt++;
        }
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                         + "~~~~~~~~~~~~~~");
        
    }
}
