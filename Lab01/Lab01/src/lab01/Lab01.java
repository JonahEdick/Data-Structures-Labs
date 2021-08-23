package lab01;

import java.util.Scanner;
import java.io.*;

public class Lab01 {

    public static void main(String[] args) throws IOException {
        
        double subTotal, tax, total;
        IceCreamContainer aContainer;
        char containerChoice;
        String priceFile;
        
        
        aContainer = new IceCreamContainer();
        
        priceFile = "priceFile.txt";
        
        aContainer.IceCreamContainer(priceFile);
        
        FillTheContainer(aContainer);
        
        subTotal = aContainer.ComputeSubtotal();
        
        tax = ComputeTax(subTotal);
        
        total = ComputeTotal(tax, subTotal);

        DisplayReceipt(aContainer, tax, subTotal, total);
        
        
    }
    
    public static void FillTheContainer(IceCreamContainer aContainer){
        char containerChoice, choice;
        
        containerChoice = GetContainerChoice();
        
        aContainer.SelectContainer(containerChoice);
        
        choice = 'A';
        
        while(choice != 'Q'){
            
            choice = GetChoice();
            
            PerformChoice(choice, aContainer);
        }
               
    }
    
    public static char GetChoice(){
        char choice;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        choice = 'A';
        
        while(choice != 'I' && choice != 'H' && choice != 'C' && choice != 'Q'){
            System.out.println("Please Select An Option!");
            System.out.println("Add (I)ce Cream");
            System.out.println("Add (H)ot Toppings");
            System.out.println("Add (C)andy");
            System.out.println("Add (Q)uit");
            
            choice = kbd.nextLine().toUpperCase().charAt(0);
            
            if(choice != 'I' && choice != 'H' && choice != 'C'
                    && choice != 'Q'){
                System.out.println("Please select a valid choice.");
            }
        }
        
        return choice;
    }
    
    public static void PerformChoice(char choice, IceCreamContainer aContainer){
        
        
        if(choice == 'I'){
            DispenseIceCream(aContainer);
        }
        if(choice == 'H'){
            DispenseHotTopping(aContainer);
        }
        if(choice == 'C'){
            DispenseCandy(aContainer);
        }
        
    }
    
    public static char GetContainerChoice(){
        char containerChoice, changeMind;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        changeMind = 'A';
        containerChoice = 'A';
        
        while(changeMind != 'N'){
            
            System.out.println("Please select a container!");
            
                System.out.println("(C)up");
                System.out.println("c(O)ne");
                System.out.println("(W)affle Cone\n");
                System.out.print("Container Choice: ");
                containerChoice = kbd.nextLine().toUpperCase().charAt(0);
            
            while(containerChoice != 'C' && containerChoice != 'O'
                    && containerChoice != 'W'){
                System.out.println("Please select a valid choice.");
                System.out.println("(C)up");
                System.out.println("c(O)ne");
                System.out.println("(W)affle Cone\n");
                System.out.print("Container Choice: ");
                containerChoice = kbd.nextLine().toUpperCase().charAt(0);
            }
            
            System.out.println("Would you like to change your container choice"
                               + "before we continue?");
            System.out.print("Continue: ");
            changeMind = kbd.nextLine().toUpperCase().charAt(0);
            
            while(changeMind != 'Y' && changeMind != 'N'){
                System.out.println("Please select a valid choice.");
                System.out.print("Continue: ");
                changeMind = kbd.nextLine().toUpperCase().charAt(0);        
            }
        
    }
        return containerChoice;
    }
        
        
    public static void DispenseIceCream(IceCreamContainer aContainer){
        
        double seconds;
        
        seconds = GetSeconds();
        
        aContainer.AddIceCream(seconds);
        
    }
    
    public static void DispenseCandy(IceCreamContainer aContainer){
        
        double seconds;
        
        seconds = GetSeconds();
        
        aContainer.AddCandy(seconds);
        
    }
    
    public static void DispenseHotTopping(IceCreamContainer aContainer){
        
        double seconds;
        
        seconds = GetSeconds();
        
        aContainer.AddHotTopping(seconds);
    }
    
    public static double GetSeconds(){
        Scanner kbd;
        double seconds;
        
        kbd = new Scanner(System.in);
        
        System.out.print("How many seconds of dispensing would you like: ");
        seconds = kbd.nextDouble();
        
        return seconds;        
    }
    
    public static double ComputeTax(double subTotal){
        double tax;
        
        tax = subTotal * 0.08;
        
        return tax;
    }
    
    public static double ComputeTotal(double tax, double subTotal){
        double total;
        
        total = subTotal + tax;
        
        return total;       
    }
    
    public static void DisplayReceipt(IceCreamContainer aContainer, double tax,
                                      double subTotal, double total){
        
        System.out.println("Here is your reciept!");
        System.out.println("Container Type: " + aContainer.GetContainerType());
        System.out.println("Ice Cream Amount: " + aContainer.GetIceCreamMass());
        System.out.println("Hot Topping Amount: "
                            + aContainer.GetHotToppingMass());
        System.out.println("Candy Amount: " + aContainer.GetCandyMass());
        System.out.println("Total Amount: " + aContainer.GetMass());
        System.out.printf("Sub Total: $%.2f\n",subTotal);
        System.out.printf("Tax: $%.2f\n",tax);
        System.out.printf("Total: $%.2f\n",total);
        System.out.println("\nHave A Wonderful Day!!!\n\n");
    }
}
