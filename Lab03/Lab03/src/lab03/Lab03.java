package lab03;

import java.util.Scanner;
import java.io.*;

public class Lab03 {
    public static void main(String[] args)throws IOException{
        char userChoice;
        ListADT theInventory;
        
        theInventory = new ListADT();
        
        do{
           userChoice = GetChoice();
           PerformChoice(userChoice, theInventory);
        }while(userChoice != 'Q');
        
    }
    
    public static char GetChoice(){
        char userChoice;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
           System.out.println("Please select an option below.");
           System.out.print("(L)oad\n"
                            + "(S)ave\n"
                            + "(A)dd Car\n"
                            + "(R)emove Car\n"
                            + "(G)et Specific Car\n"
                            + "(Y)ear & Class Display\n"
                            + "(D)isplay Full Inventory\n"
                            + "(Q)uit\n"
                            + "Option: ");
           userChoice = kbd.nextLine().toUpperCase().charAt(0);
           System.out.println();
        
        return userChoice;
    }
    
    public static void PerformChoice(char userChoice, ListADT theInventory)
                                                         throws IOException{
        
        switch(userChoice){
            case 'L':
                Load(theInventory);
                break;
            case 'S':
                Save(theInventory);
                break;
            case 'A':
                AddCar(theInventory);
                break;
            case 'R':
                RemoveCar(theInventory);
                break;
            case 'G':
                DisplaySpecifiedCar(theInventory);
                break;
            case 'Y':
                DisplayCarCatYear(theInventory);
                break;
            case 'D':
                DisplayAll(theInventory);
                break;
            case 'Q':
                break;
            default:
                System.out.println("Please Choose a valid option.");
                break;
    }
        
    }
    
    public static void Load(ListADT theInventory)throws IOException{
        String inventoryFile, inputVehicleID, inputYear, inputMake, inputModel,
               inputClass;
        double inputMiles, inputPrice;
        File inputFile;
        Scanner kbd, inputFileSC;
        ListElement carAdd;
        
        kbd = new Scanner(System.in);
        
        System.out.print("Please type inventory file (ignore .txt): ");
        inventoryFile = kbd.nextLine() + ".txt";
        
        inputFile = new File(inventoryFile);
        inputFileSC = new Scanner(inputFile);
        
        theInventory.Create(10000);
        
        do{
            inputVehicleID = inputFileSC.nextLine();
            inputYear = inputFileSC.nextLine();
            inputMake = inputFileSC.nextLine();
            inputModel = inputFileSC.nextLine();
            inputMiles = inputFileSC.nextDouble();
            inputFileSC.nextLine();
            inputClass = inputFileSC.nextLine();
            inputPrice = inputFileSC.nextDouble();
            inputFileSC.nextLine();
            
            carAdd = new ListElement();
            carAdd.Set(inputVehicleID, inputYear, inputMake, inputModel,
                       inputMiles, inputClass, inputPrice);
            
            theInventory.Add(carAdd);
            
        }while(theInventory.IsFull() == false && inputFileSC.hasNext());
        
        inputFileSC.close();
        
        System.out.println("\nInventory Loaded\n");
    }
    
    public static void Save(ListADT theInventory)throws IOException{
        String outputInventoryFile;
        File outputFile;
        FileWriter outputFileFW;
        PrintWriter outputFilePW;
        Scanner kbd;
        ListElement savedElement;
        
        kbd = new Scanner(System.in);
        
        theInventory.Reset();
        
        savedElement = new ListElement();
        
        System.out.print("Please type output file (ignore .txt): ");
        outputInventoryFile = kbd.nextLine() + ".txt";
        
        outputFile = new File(outputInventoryFile);
        outputFileFW = new FileWriter(outputFile);
        outputFilePW = new PrintWriter(outputFileFW);
        
        do{
            
            savedElement = theInventory.Retrieve();
            outputFilePW.println(savedElement.GetKey());
            outputFilePW.println(savedElement.GetYear());
            outputFilePW.println(savedElement.GetMake());
            outputFilePW.println(savedElement.GetModel());
            outputFilePW.println(savedElement.GetMiles());
            outputFilePW.println(savedElement.GetClassification());
            outputFilePW.println(savedElement.GetPrice());
            theInventory.GetNext();
            
        }while(theInventory.AtEnd() == false);
        
        outputFilePW.close();
        
        System.out.println("\nInventory Saved\n");
        
    }
    
    public static void AddCar(ListADT theInventory){
        ListElement addedElement;
        String addVehicleID, addYear, addMake, addModel,
               addClassification;
        double addMiles, addPrice;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        addedElement = new ListElement();
        
        if(theInventory.IsFull() == false){
            System.out.print("Enter Vehicle ID: ");
            addVehicleID = kbd.nextLine();
            System.out.print("Enter Vehicle Year: ");
            addYear = kbd.nextLine();
            System.out.print("Enter Vehicle Make: ");
            addMake = kbd.nextLine();
            System.out.print("Enter Vehicle Model: ");
            addModel = kbd.nextLine();
            System.out.print("Enter Vehicle Miles: ");
            addMiles = kbd.nextDouble();
            kbd.nextLine();
            System.out.print("Enter Vehicle Class: ");
            addClassification = kbd.nextLine();
            System.out.print("Enter Vehicle Price: ");
            addPrice = kbd.nextDouble();
            kbd.nextLine();
            
            addedElement.Set(addVehicleID, addYear, addMake, addModel, addMiles,
                             addClassification, addPrice);
            
            theInventory.Add(addedElement);
            
            System.out.println("\nCar Added!\n");
            
        }
        else{
            System.out.println("\nInventory is Currently Full.\n");
        }
        
    }
    
    public static void RemoveCar(ListADT theInventory){
        ListElement removeElement;
        String removeVehicleID;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        removeElement = new ListElement();
        
        if(theInventory.IsEmpty() == false){
            System.out.print("Enter Vehicle ID to delete: ");
            removeVehicleID = kbd.nextLine();
            
            if(theInventory.Delete(removeVehicleID) == true)
            System.out.println("\nVehicle Deleted\n");
            else
            System.out.println("\nVehicle Not Found\n");            
        }
    }
    
    public static void DisplaySpecifiedCar(ListADT theInventory){
        ListElement specificCar;
        Scanner kbd;
        String specifiedVehicleID;
        
        kbd = new Scanner(System.in);
        
        specificCar = new ListElement();
        
        if(theInventory.IsEmpty() == false){
            System.out.print("Enter Vehicle ID: ");
            specifiedVehicleID = kbd.nextLine();
            System.out.println();
            
            theInventory.Search(specifiedVehicleID);
            
            specificCar = theInventory.Retrieve();
            
            System.out.println("Vehicle ID: " + specificCar.GetKey());
            System.out.println("Vehicle Year: " + specificCar.GetYear());
            System.out.println("Vehicle Make: " + specificCar.GetMake());
            System.out.println("Vehicle Model: " + specificCar.GetModel());
            System.out.println("Vehicle Miles: " + specificCar.GetMiles());
            System.out.println("Vehicle Class: " + 
                                specificCar.GetClassification());
            System.out.println("Vehicle Price: $" + specificCar.GetPrice());
            System.out.println("\n");
        }
    }
    
    public static void DisplayCarCatYear(ListADT theInventory){
        ListElement searchedCars;
        String searchedYear, searchedClass, listYear, listClass;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        searchedCars = new ListElement();
        
        theInventory.Reset();
        
        System.out.print("Enter Vehicle Year: ");
        searchedYear = kbd.nextLine();
        System.out.print("Enter Vehicle Class: ");
        searchedClass = kbd.nextLine();
        System.out.println();
        
        System.out.printf("%-12s %-12s %-10s %-10s %12s %-9s %10s\n",
                              "Vehicle ID", "Year", "Make",
                              "Model", "Miles", "Class", "Price");
        
        while(theInventory.AtEnd() == false){
            searchedCars = theInventory.Retrieve();
            listYear = searchedCars.GetYear();
            listClass = searchedCars.GetClassification();
            
            if(searchedYear.compareTo(listYear) == 0 &&
               searchedClass.compareTo(listClass) == 0){
               
               System.out.printf("%-12s %-12s %-10s %-10s %12.2f %-9s %10.2f\n",
                              searchedCars.GetKey(),
                              searchedCars.GetYear(),
                              searchedCars.GetMake(),
                              searchedCars.GetModel(),
                              searchedCars.GetMiles(),
                              searchedCars.GetClassification(),
                              searchedCars.GetPrice());
            }
            theInventory.GetNext();
        }
        System.out.println("\n");
    }
    
    public static void DisplayAll(ListADT theInventory){
        ListElement searchedCars;
        
        searchedCars = new ListElement();
        
        theInventory.Reset();
        
        System.out.printf("%-12s %-12s %-10s %-10s %12s %-9s %10s\n",
                              "Vehicle ID", "Year", "Make",
                              "Model", "Miles", "Class", "Price");
        
        while(theInventory.AtEnd() == false){
            searchedCars = theInventory.Retrieve();
            
            System.out.printf("%-12s %-12s %-10s %-10s %12.2f %-9s %10.2f\n",
                              searchedCars.GetKey(),
                              searchedCars.GetYear(),
                              searchedCars.GetMake(),
                              searchedCars.GetModel(),
                              searchedCars.GetMiles(),
                              searchedCars.GetClassification(),
                              searchedCars.GetPrice());
            
            theInventory.GetNext();
        }
        System.out.println("\n");
    }
}
