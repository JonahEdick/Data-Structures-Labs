//Developer:    Jonah C. Edick
//Project:      Lab04
//Due Date:     March 24th, 2021
//Description:  The following program utilized a StackADT to take a number from
//              the user and converts it do a base given by the user. It uses
//              the stack to push the remainders created and keeps them in an
//              order that will properly display the result when popped.
//              The program can perform any number of conversions.
package lab04;

import java.util.Scanner;

public class Lab04 {
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
        StackADT remainderStack;
        
        if(userChoice == 'C'){
            userNumber = GetNumber();
            userBase = GetBase();
            remainderStack = ConvertNumber(userNumber, userBase);
            DisplayNumber(userNumber, userBase, remainderStack);
            remainderStack.Destroy();
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
    
    //Convert
    public static StackADT ConvertNumber (int userNumber, int userBase){
        int cnt, tempQ, tempR;
        ElementType remainder;
        StackADT remainderStack;
        remainder = new ElementType();
        
        tempQ = userNumber;
        
        cnt = 0;
        
        while(tempQ != 0){
            tempQ = tempQ / userBase;
            cnt++;
        }
        
        remainderStack = new StackADT(cnt);
        
        while(userNumber != 0 && remainderStack.IsFull() == false){
            remainder = new ElementType();
            tempR = userNumber % userBase;
            if(tempR < 0)
                tempR = tempR * -1;
            
            remainder.Set(tempR);
            remainderStack.Push(remainder);
            userNumber = userNumber / userBase;
        }
        
        return remainderStack;
    }
    
    //Display
    public static void DisplayNumber(int userNumber, int userBase,
                                        StackADT remainderStack){
        ElementType retRemainder;
        
        System.out.print("Converted Number (" + userNumber + " to base " +
                          userBase + "): ");
        if(userNumber < 0)
            System.out.print("-");
        while(remainderStack.IsEmpty() == false){
            retRemainder = remainderStack.Pop();
            if(retRemainder.Get() < 10)
                System.out.print(retRemainder.Get());
            if(retRemainder.Get() == 10)
                System.out.print('A');
            if(retRemainder.Get() == 11)
                System.out.print('B');
            if(retRemainder.Get() == 12)
                System.out.print('C');
            if(retRemainder.Get() == 13)
                System.out.print('D');
            if(retRemainder.Get() == 14)
                System.out.print('E');
            if(retRemainder.Get() == 15)
                System.out.print('F');
        }
        
        System.out.println("\n");
        
    }
}
