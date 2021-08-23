package lab01;

import java.util.Scanner;
import java.io.*;

public class IceCreamContainer {
    private char containerType;
    private double containerMax;
    private double cupPrice;
    private double conePrice;
    private double waffleConePrice;
    private int iceCreamRate;
    private int hotToppingRate;
    private int candyRate;
    private double cupMax;
    private double coneMax;
    private double waffleConeMax;
    private double pricePerHectoGram;
    private double iceCreamMass;
    private double hotToppingMass;
    private double candyMass;
    private double totalMass;
    
    public void IceCreamContainer(String fileName) throws IOException {
    
        Scanner fileSC;
        File priceFile;
        
        priceFile = new File(fileName);
        
        fileSC = new Scanner(priceFile);
        
        cupPrice = fileSC.nextDouble();
        conePrice = fileSC.nextDouble();
        waffleConePrice = fileSC.nextDouble();
        iceCreamRate = fileSC.nextInt();
        hotToppingRate = fileSC.nextInt();
        candyRate = fileSC.nextInt();
        cupMax = fileSC.nextDouble();
        coneMax = fileSC.nextDouble();
        waffleConeMax = fileSC.nextDouble();
        pricePerHectoGram = fileSC.nextDouble();
        
        fileSC.close();
        
}
    
    public void AddIceCream(double numSeconds){
                
        iceCreamMass = numSeconds * iceCreamRate;
        
        totalMass = GetMass();
        
        if(totalMass >= containerMax){
            
            iceCreamMass = iceCreamMass - (numSeconds * iceCreamRate);
            
            System.out.println("That much Ice Cream will cause you to overflow!"
                                + "\nPlease put a less amount on!");
        }
    }
    
    public void AddHotTopping(double numSeconds){
        
        hotToppingMass = numSeconds * hotToppingRate;
        
        totalMass = GetMass();
        
        if(totalMass > containerMax){
            
            hotToppingMass = hotToppingMass - (numSeconds * hotToppingRate);
            
            System.out.println("That much candy will cause you to overflow!\n"
                                + "Please put a less amount on!");
        }
    }
    
    public void AddCandy(double numSeconds){
        
        candyMass = numSeconds * candyRate;
        
        totalMass = GetMass();
        
        if(totalMass > containerMax){
            
            candyMass = candyMass - (numSeconds * candyRate);
            
            System.out.println("That much candy will cause you to overflow!\n"
                                + "Please put a less amount on!");
        }
        
        
        
    }
    
    public void SelectContainer(char containerChoice){
        
        totalMass = GetMass();
        
        if(totalMass == 0){
            containerType = containerChoice;
            if(containerType == 'C')
                containerMax = cupMax;
            if(containerType == 'O')
                containerMax = coneMax;
            if(containerType == 'W')
                containerMax = waffleConeMax;
        }
        else
            System.out.println("You cannot change your container type.");
    }
    
    public double GetMass(){
        
        totalMass = iceCreamMass + hotToppingMass + candyMass;
        
        return totalMass;
    }
    
    public double GetContainerMax(){
        
        return containerMax;
        
    }
    
    public char GetContainerType(){
        return containerType;
    }
    
    public double GetIceCreamMass(){
        
        return iceCreamMass;
        
    }
    
    public double GetHotToppingMass(){
        
        return hotToppingMass; 
        
    }
    
    public double GetCandyMass(){
        
        return candyMass;
        
    }
     
    public double ComputeSubtotal(){
        double subTotal;
        
        subTotal = 0;
        
        if(containerType == 'C'){
            subTotal = ((pricePerHectoGram/100) * (iceCreamMass + hotToppingMass 
                                        + candyMass)) + cupPrice;
        }
        if(containerType == 'O'){
            subTotal = ((pricePerHectoGram/100) * (iceCreamMass + hotToppingMass 
                                        + candyMass)) + conePrice;
        }
        if(containerType == 'W'){
            subTotal = ((pricePerHectoGram/100) * (iceCreamMass + hotToppingMass 
                                        + candyMass)) + waffleConePrice;
    }
    
    return subTotal;
        
    }

}