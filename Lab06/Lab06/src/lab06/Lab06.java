/*
Developer: Jonah C. Edick
Due Date: 4/28/2021
Project: Lab06
Description: This program is designed to use a linked structure to load a list
of orders from a file and then give the use the ability to add, delete, display,
and clear the orders from the list. This implements the use of Nodes to make the
size of the list as large as the user wants as long as the system has available
memory.
*/
package lab06;

import java.util.Scanner;
import java.io.*;

public class Lab06 {
    public static void main(String[] args)throws IOException{
        
        ProcessOrders();
        
    }
    
    public static void ProcessOrders()throws IOException{
        Node startSale;
        String[] orderFiles;
        Selection loadOrders, saveOrders, emptyOrders, addOrder, deleteOrder,
                displayBills;
        char userChoice;
        
        startSale = new Node();
        
        orderFiles = new String[2]; //0=LoadFile, 1=SaveFile
        orderFiles[0] = " ";
        
        do{   
            
            loadOrders = new Selection();
            loadOrders.Set('L', "Load Orders",
            orderFiles[0].equals(" ") && startSale.GetData() == null);
        
            saveOrders = new Selection();
            saveOrders.Set('S', "Save Orders", !(startSale.GetData() == null));
        
            emptyOrders = new Selection();
            emptyOrders.Set('E', "Empty Orders",
                    !(startSale.GetData() == null));
        
            addOrder = new Selection();
            addOrder.Set('A', "Add Order", !(orderFiles[0].equals(" ")));
        
            deleteOrder = new Selection();
            deleteOrder.Set('D', "Delete Order",
                    !(startSale.GetData() == null));
        
            displayBills = new Selection();
            displayBills.Set('B', "Bill Display",
                    !(startSale.GetData() == null));
            
            DisplayMenu(loadOrders, saveOrders, emptyOrders, addOrder,
                    deleteOrder, displayBills);
            
            System.out.println("(Q) Quit Program");
            
            userChoice = GetUserChoice();
            
            startSale = PerformUserChoice(userChoice, startSale, loadOrders,
                    saveOrders, emptyOrders, addOrder, deleteOrder,
                    displayBills, orderFiles);
            
        }while(userChoice != 'Q');
    }
    
    public static void DisplayMenu(Selection loadOrders, Selection saveOrders,
            Selection emptyOrders, Selection addOrder, Selection deleteOrder,
            Selection displayBills){
        
        System.out.println("Select An Option Below");
        DisplaySelection(loadOrders);
        DisplaySelection(saveOrders);
        DisplaySelection(emptyOrders);
        DisplaySelection(addOrder);
        DisplaySelection(deleteOrder);
        DisplaySelection(displayBills);
    }
    
    public static void DisplaySelection(Selection selection){
        
        if(selection.GetValidation() == true){
            System.out.println("(" + selection.GetCode() + ") "
                    + selection.GetDescription());
        }
        
    }
    
    public static char GetUserChoice(){
        char userChoice;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        System.out.print("Selection: ");
        userChoice = kbd.nextLine().toUpperCase().charAt(0);
        
        System.out.println();
        
        return userChoice;
    }
    
    public static Node PerformUserChoice(char userChoice, Node startSale,
            Selection loadOrders, Selection saveOrders, Selection emptyOrders,
            Selection addOrder, Selection deleteOrder, Selection displayBills,
            String[] orderFiles)throws IOException {
        
        if(userChoice == loadOrders.GetCode()){
            if(loadOrders.GetValidation())
                startSale = LoadOrders(orderFiles, startSale);
            else
                System.out.println("Error - Invalid Selection");
            return startSale;
        }
        
        if(userChoice == saveOrders.GetCode()){
            if(saveOrders.GetValidation())
                SaveOrders(orderFiles, startSale);
            else
                System.out.println("Error - Invalid Selection");
            return startSale;
        }
        
        if(userChoice == addOrder.GetCode()){
            if(addOrder.GetValidation())
                startSale = AddOrder(startSale);
            else
                System.out.println("Error - Invalid Selection");
            return startSale;
        }
        
        if(userChoice == emptyOrders.GetCode()){
            if(emptyOrders.GetValidation())
                startSale = EmptyOrders(startSale, orderFiles);
            else
                System.out.println("Error - Invalid Selection");
            return startSale;
        }
        
        if(userChoice == deleteOrder.GetCode()){
            if(deleteOrder.GetValidation())
                DeleteOrder(startSale);
            else
                System.out.println("Error - Invalid Selection");
            return startSale;
        }
        
        if(userChoice == displayBills.GetCode()){
            if(displayBills.GetValidation())
                BillDisplay(startSale);
            else
                System.out.println("Error - Invalid Selection");
            return startSale;
        }
        
        if(userChoice == 'Q'){
            return startSale;
        }
        
        System.out.println("Error - Invalid Selection");
        return startSale;
    }
    
    public static Node LoadOrders(String[] orderFiles, Node startSale)
                                                            throws IOException{
        int tempOrderNum, tempZip, tempRNum, tempTNum, tempNNum;
        String tempName, tempAddress, tempCity, tempState, tempPhone;
        File salesFile;
        ElementType tempOrder;
        Scanner salesFileSC, kbd;
        
        kbd = new Scanner(System.in);
        
        System.out.print("Enter File To Load In (DO NOT INCLUDE .txt): ");
        
        orderFiles[0] = kbd.nextLine();
        
        System.out.println();
        
        salesFile = new File(orderFiles[0]);
        salesFileSC = new Scanner(salesFile);
        
        startSale = new Node();
        
        while(salesFileSC.hasNext()){
            tempOrderNum = salesFileSC.nextInt();
            salesFileSC.nextLine();
            tempName = salesFileSC.nextLine();
            tempAddress = salesFileSC.nextLine();
            tempCity = salesFileSC.nextLine();
            tempState = salesFileSC.nextLine();
            tempZip = salesFileSC.nextInt();
            salesFileSC.nextLine();
            tempPhone = salesFileSC.nextLine();
            tempRNum = salesFileSC.nextInt();
            tempTNum = salesFileSC.nextInt();
            tempNNum = salesFileSC.nextInt();
            tempOrder = SetTempElement(tempOrderNum, tempName, tempAddress,
                                       tempCity, tempState, tempZip, tempPhone,
                                       tempRNum, tempTNum, tempNNum);
            
            startSale = Add(startSale, tempOrder);
        }
        
        return startSale;
    }
    
    public static Node Add(Node startSale, ElementType tempElement){
        Node tempOrder;
        
        tempOrder = new Node();
        tempOrder.SetData(tempElement);
        tempOrder.SetNextAddress(null);
        
        tempOrder.SetNextAddress(startSale);
        startSale = tempOrder;        
        
        return startSale;
    }
    
    public static ElementType SetTempElement(int orderNum, String orderName,
            String orderAddress, String orderCity, String orderState,
            int orderZip, String orderPhoneNum, int orderRPieNum, int orderTPieNum,
            int orderNPieNum){
        ElementType tempElement;
        
        tempElement = new ElementType();
        
        tempElement.Set(orderNum, orderName, orderAddress, orderCity,
                orderState, orderZip, orderPhoneNum, orderRPieNum, orderTPieNum,
                orderNPieNum);
        
        return tempElement;
    }
    
    public static void SaveOrders(String[] orderFiles, Node startSale)
                                                            throws IOException{
        Scanner kbd;
        Node currentSale;
        File saveFile;
        FileWriter saveFileFW;
        PrintWriter saveFilePW;
        
        kbd = new Scanner(System.in);
        
        System.out.print("Enter File To Save To (DO NOT INCLUDE .txt): ");
        
        orderFiles[1] = kbd.nextLine();
        
        System.out.println();
        
        saveFile = new File(orderFiles[1]);
        saveFileFW = new FileWriter(saveFile);
        saveFilePW = new PrintWriter(saveFileFW);
        
        currentSale = startSale;
        
        while(currentSale.GetData() != null){
            //Upload To File
            saveFilePW.println(currentSale.GetData().GetOrderNumber());
            saveFilePW.println(currentSale.GetData().GetOrderName());
            saveFilePW.println(currentSale.GetData().GetStreetAddress());
            saveFilePW.println(currentSale.GetData().GetCity());
            saveFilePW.println(currentSale.GetData().GetState());
            saveFilePW.println(currentSale.GetData().GetZip());
            saveFilePW.println(currentSale.GetData().GetPhoneNumber());
            saveFilePW.println(currentSale.GetData().GetRaisonPieNum());
            saveFilePW.println(currentSale.GetData().GetTwinkiePieNum());
            saveFilePW.println(currentSale.GetData().GetNachoPieNum());
            
            currentSale = currentSale.GetNextAddress();
        }
        
        saveFilePW.close();
    }
    
    public static Node AddOrder(Node startSale){
        char orderValid;
        ElementType tempElement;
        Node currentSale, nextSale;
        int tempOrderNum, tempZip, tempRPieNum, tempTPieNum, tempNPieNum;
        String tempName, tempAddress, tempCity, tempState,
               tempPhoneNum;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        //Get Order Information
        do{
            System.out.println("Enter The Following Order Information");
            System.out.print("Enter Name: ");
            tempName = kbd.nextLine();
            System.out.print("Enter Address: ");
            tempAddress = kbd.nextLine();
            System.out.print("Enter City: ");
            tempCity = kbd.nextLine();
            System.out.print("Enter State: ");
            tempState = kbd.nextLine();
            System.out.print("Enter Zip Code: ");
            tempZip = kbd.nextInt();
            kbd.nextLine();
            System.out.print("Enter Phone Number: ");
            tempPhoneNum = kbd.nextLine();
            
            do{
                do{
                System.out.print("Number of Rasin Pies: ");
                tempRPieNum = kbd.nextInt();
                kbd.nextLine();
                CheckNumPies(tempRPieNum);
                }while(tempRPieNum < 0);
            
                do{
                System.out.print("Number of Twinkie Pies: ");
                tempTPieNum = kbd.nextInt();
                kbd.nextLine();
                CheckNumPies(tempTPieNum);
                }while(tempTPieNum < 0);
            
                do{
                System.out.print("Number of Nacho Pies: ");
                tempNPieNum = kbd.nextInt();
                kbd.nextLine();
                CheckNumPies(tempNPieNum);
                }while(tempNPieNum < 0);
                
                if(tempRPieNum == 0 && tempTPieNum == 0 && tempNPieNum == 0){
                    System.out.println("There must be at least 1 pie in order");
                }
                
            }while(tempRPieNum == 0 && tempTPieNum == 0 && tempNPieNum == 0);
            
            do{
                System.out.print("Is The Order Info Correct: ");
                orderValid = kbd.nextLine().toUpperCase().charAt(0);
                
                if(orderValid != 'Y' && orderValid != 'N'){
                    System.out.println("Must Answer Y or N");
                }
            }while(orderValid != 'Y' && orderValid != 'N');
            
        }while(orderValid != 'Y');
        
        //Get Order Number        
        
        if(startSale.GetData() == null){
            tempOrderNum = 1;
            
            tempElement = SetTempElement(tempOrderNum, tempName, tempAddress,
                                         tempCity, tempState, tempZip,
                                         tempPhoneNum, tempRPieNum, tempTPieNum,
                                         tempNPieNum);
            
            startSale = Add(startSale, tempElement);
        }else{
            tempOrderNum = startSale.GetData().GetOrderNumber() + 1;
            
            tempElement = SetTempElement(tempOrderNum, tempName, tempAddress,
                                         tempCity, tempState, tempZip,
                                         tempPhoneNum, tempRPieNum, tempTPieNum,
                                         tempNPieNum);
            
            startSale = Add(startSale, tempElement);
        }
        
        
        return startSale;
    }
    
    public static Node EmptyOrders(Node startSale, String[] orderFiles){
        
        orderFiles[0] = " ";
        
        while(startSale.GetData() != null){
            startSale.SetData(null);
            startSale = startSale.GetNextAddress();
        }
        
        return startSale;
    }
    
    public static void DeleteOrder(Node startSale){
        int deleteNum;
        Node currentSale;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        System.out.println("Current Order List.");
        
        DisplayOrders(startSale);
        
        System.out.print("Enter Order Number To Delete: ");
        deleteNum = kbd.nextInt();
        
        currentSale = startSale;
        
        while(currentSale.GetData() != null &&
                currentSale.GetData().GetOrderNumber() != deleteNum){
            currentSale = currentSale.GetNextAddress();
        }
        
        if(currentSale.GetData() == null){
            System.out.println("Error- Order Not Found.");
        }
        else{
            while(currentSale.GetData() != null){
                currentSale.SetData(currentSale.GetNextAddress().GetData());
                currentSale = currentSale.GetNextAddress();
            }
        }
        
        System.out.println();
        
    }
    
    public static void BillDisplay(Node startSale){
        
        System.out.println("Bill Display (Current Order In Memory)");
        
        DisplayOrders(startSale);
        
        System.out.println();
        
    }
    
    public static void DisplayOrders(Node startSale){
        Node currentSale;
        
        currentSale = startSale;
        
        while(currentSale.GetData() != null){
            //Upload To File
            System.out.printf("%-3d ",currentSale.GetData().GetOrderNumber());
            System.out.printf("%-15s ", currentSale.GetData().GetOrderName());
          System.out.printf("%-30s ", currentSale.GetData().GetStreetAddress());
            System.out.printf("%-10s ", currentSale.GetData().GetCity());
            System.out.printf("%-3s ", currentSale.GetData().GetState());
            System.out.printf("%-7d ", currentSale.GetData().GetZip());
            System.out.printf("%-15s ", currentSale.GetData().GetPhoneNumber());
            System.out.printf("%5d ", currentSale.GetData().GetRaisonPieNum());
            System.out.printf("%5d ", currentSale.GetData().GetTwinkiePieNum());
            System.out.printf("%5d\n", currentSale.GetData().GetNachoPieNum());
            
            currentSale = currentSale.GetNextAddress();
        }
    }
    
    public static void CheckNumPies(int numPies){
        
        if(numPies < 0){
            System.out.println("Cannot have negative pies in order.");
        }
    }
    
}
