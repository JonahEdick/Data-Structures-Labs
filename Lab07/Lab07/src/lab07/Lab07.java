/*
Developer: Jonah C. Edick
Due Date: 05/08/2021
Project: Lab07
Description: This program is a revision of the Numbers Conversion Lab where we
             get a number and a base from a user so that it can convert it and
             display it to the user. This version implements recursion instead
             of utilizing a StackADT to display each digit. The function
             recursion stops when the quotient reaches 0.
*/

package lab07;

import java.util.Scanner;


public class Lab07 {
    public static void main(String[] args) {
        
        ConvertNumbers();
        
    }
    
    //ConvertNumbers
    public static void ConvertNumbers(){
        char userChoice;
        
        do{
            userChoice = GetUserChoice();
            PerformUserChoice(userChoice);
        }while(userChoice != 'Q');
    }
    
    //GetUserChoice
    public static char GetUserChoice(){
        char userChoice;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        System.out.print("What would you like to do?\n"
                       + "(C)onvert a number\n"
                       + "(Q)uit\n"
                       + "Choice: ");
        
        do{
            userChoice = kbd.nextLine().toUpperCase().charAt(0);
            
            System.out.println();
            
            if(userChoice != 'Q' && userChoice != 'C'){
                System.out.print("Please choose a valid choice.\n"
                               + "Choice: ");
            }
            
        }while(userChoice != 'Q' && userChoice != 'C');
        
        System.out.println();
        
        return userChoice;
    }
    
    //PerformUserChoice
    public static void PerformUserChoice(char userChoice){
        int userNumber, userBase;
        
        if(userChoice == 'C'){
            userNumber = GetNumber();
            userBase = GetBase();
            DisplayNumber(userNumber, userBase);
        }
        
        System.out.println("\n");
    }
    
    //GetNumber
    public static int GetNumber(){
        int userNumber;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        System.out.print("Number to convert: ");
        userNumber = kbd.nextInt();
        
        return userNumber;
    }    
    
    //GetBase
    public static int GetBase(){
        int userBase;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        do{
           System.out.print("Base to convet to (2-16): ");
           userBase = kbd.nextInt();
           
           if(userBase < 2 || userBase > 16){
               System.out.println("Please choose valid base.");
           }
        }while(userBase < 2 || userBase > 16);
        
        
        return userBase;
    }
    
    //Display
    public static void DisplayNumber(int userNumber, int userBase){
        
        System.out.print("Converted Number (" + userNumber + " to base " +
                          userBase + "): ");
        if(userNumber < 0){
            System.out.print("-");
            userNumber = userNumber * -1;
        }
        if(userNumber == 0)
            System.out.println(userNumber);
        else
            ConvertNumber(userNumber, userBase);
        
        System.out.println("\n");
        
    }    
    
    public static void ConvertNumber(int userNumber, int userBase){
        
        if(userNumber / userBase != 0){
            ConvertNumber(userNumber / userBase, userBase);
            DisplayDigit(userNumber % userBase);
        }
        else
            DisplayDigit(userNumber % userBase);
        
    }
    
    public static void DisplayDigit(int digit){
        
        if(digit == 15)
            System.out.print('F');
        if(digit == 14)
            System.out.print('E');
        if(digit == 13)
            System.out.print('D');
        if(digit == 12)
            System.out.print('C');
        if(digit == 11)
            System.out.print('B');
        if(digit == 10)
            System.out.print('A');
        if(digit < 10)
            System.out.print(digit);
    }
}
