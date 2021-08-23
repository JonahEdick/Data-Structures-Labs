package lab02;

import java.io.*;
import java.util.Scanner;

public class Lab02 {

    public static void main(String[] args) throws IOException {
        
        int[] reportDay, reportMonth, reportYear, numReports;
        double[] reportSnowfallAmount;
        
        //Set Up Parralell Arrays
        
        reportDay = new int[10000];
        reportMonth = new int[10000];
        reportYear = new int[10000];
        reportSnowfallAmount = new double[10000];
        numReports = new int[1];
        
        numReports[0] = LoadSnowDataReport(reportDay, reportMonth, reportYear,
                           reportSnowfallAmount);
        
        PerformEachRequetedTask(reportDay, reportMonth, reportYear,
                           reportSnowfallAmount, numReports);
        
        UpdateSnowDataReport(reportDay, reportMonth, reportYear,
                           reportSnowfallAmount, numReports);
        
    }
    
    private static int LoadSnowDataReport(int[] reportDay,
                                           int[] reportMonth,
                                           int[] reportYear, 
                                           double[] reportSnowfallAmount)
                                           throws IOException{
        File snowDataFile;
        Scanner snowDataFileSC;
        
        snowDataFile = new File("SnowDataFile.txt");
        snowDataFileSC = new Scanner(snowDataFile);
        
        int cnt = 0;
        while(cnt < 10000 && snowDataFileSC.hasNext()){
            reportMonth[cnt] = snowDataFileSC.nextInt();
            reportDay[cnt] = snowDataFileSC.nextInt();
            reportYear[cnt] = snowDataFileSC.nextInt();
            reportSnowfallAmount[cnt] = snowDataFileSC.nextDouble();
            cnt++;
        }
        
        snowDataFileSC.close();
        return cnt;
    }
    
    private static void PerformEachRequetedTask(int[] reportDay,
                                                int[] reportMonth,
                                                int[] reportYear, 
                                                double[] reportSnowfallAmount,
                                                int[] numReports){
        
        char chosenTask;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        do{
            System.out.println("Please choose from the tasks below:\n"
                             + "(A) Add a Day\n"
                             + "(B) Modify a Day\n"
                             + "(C) Display a Day\n"
                             + "(D) Display a Snowfall Report\n"
                             + "(Q) Quit\n"
                             + "Task: ");
            chosenTask = kbd.nextLine().toUpperCase().charAt(0);
            
            if(chosenTask == 'A'){
                AddDay(reportDay, reportMonth, reportYear, reportSnowfallAmount,
                        numReports);
            }
                
            if(chosenTask == 'B'){
                ModifyDay(reportDay, reportMonth, reportYear,
                          reportSnowfallAmount, numReports);
            }
            
            if(chosenTask == 'C'){
                DisplayDay(reportDay, reportMonth, reportYear,
                          reportSnowfallAmount, numReports);
            }
            
            if(chosenTask == 'D'){
                DisplaySnowfallReport(reportDay, reportMonth, reportYear,
                                      reportSnowfallAmount, numReports);
            }
            
            if(chosenTask != 'A' && chosenTask != 'B' && chosenTask != 'C'
                                 && chosenTask != 'D' && chosenTask != 'Q'){
                System.out.println("Please Select a valid choice.");
            }
            
        }while(chosenTask != 'Q');
        
    }
    
    private static void UpdateSnowDataReport(int[] reportDay,
                                             int[] reportMonth,
                                             int[] reportYear, 
                                             double[] reportSnowfallAmount,
                                             int[] numReports)
                                             throws IOException{
        File snowDataFile;
        PrintWriter snowDataFilePW;
        FileWriter snowDataFileFW;
        
        snowDataFile = new File("SnowDataFile.txt");
        snowDataFileFW = new FileWriter(snowDataFile);
        snowDataFilePW = new PrintWriter(snowDataFileFW);
        
        
        int cnt = 0;
                
        while(cnt < numReports[0]){
            
            snowDataFilePW.println(reportMonth[cnt]);
            snowDataFilePW.println(reportDay[cnt]);
            snowDataFilePW.println(reportYear[cnt]);
            snowDataFilePW.println(reportSnowfallAmount[cnt]);
            cnt++;
        } 
        
        snowDataFilePW.close();
        
    }
    
    private static void AddDay(int[] reportDay, int[] reportMonth, 
                               int[] reportYear, double[] reportSnowfallAmount,
                               int[] numReports){
        
        int[] addDayRequest;
        
        addDayRequest = new int[3];
        
        GetReportData(addDayRequest);
        
        CheckForValidAdd(addDayRequest, reportDay, reportMonth, reportYear,
                reportSnowfallAmount, numReports);
        
        
    }
    
    private static void ModifyDay(int[] reportDay, int[] reportMonth,
                                   int[] reportYear,
                                   double[] reportSnowfallAmount,
                                   int[] numReports){
        int[] modifyDayRequest;
        
        modifyDayRequest = new int[3];
        
        GetReportData(modifyDayRequest);
        
        ModifyDate(reportDay, reportMonth, reportYear, reportSnowfallAmount,
                   modifyDayRequest, numReports);
    }
    
    private static void DisplayDay(int[] reportDay, int[] reportMonth,
                                   int[] reportYear,
                                   double[] reportSnowfallAmount,
                                   int[] numReports){
        int[] dayReportRequest;
        
        dayReportRequest = new int[3]; 
        
        dayReportRequest[0] = GetReportMonth();
        
        dayReportRequest[1] = GetReportDay(dayReportRequest[0]);
        
        dayReportRequest[2] = GetReportYear();
        
        DisplayDailyReport(dayReportRequest, reportDay, reportMonth, reportYear,
                           reportSnowfallAmount, numReports);
        
        
    }
    
    private static void DisplaySnowfallReport(int[] reportDay, int[] reportMonth,
                                   int[] reportYear,
                                   double[] reportSnowfallAmount,
                                   int[] numReports){
        
        int[] monthlyReportRequest;
        
        monthlyReportRequest = new int[2];
        
        monthlyReportRequest[0] = GetReportMonth();
        
        monthlyReportRequest[1] = GetReportYear();
        
        DisplayMonthlyReport(monthlyReportRequest, reportDay, reportMonth,
                             reportYear, reportSnowfallAmount, numReports);
    }
 
    private static void GetReportData(int[] reportDataRequest){
        
        reportDataRequest[0] = GetReportMonth();
        reportDataRequest[1] = GetReportDay(reportDataRequest[0]);
        reportDataRequest[2] = GetReportYear();
        
    }
    
    private static void CheckForValidAdd(int[] addDayRequest, int[] reportDay,
                                         int[] reportMonth, int[] reportYear,
                                         double[] reportSnowfallAmount,
                                         int[] numReports){
        
        Scanner kbd;
        int cnt = 0;
        boolean doSearch;
        
        kbd = new Scanner(System.in);
        
        doSearch = true;
        if(numReports[0] == 10000){
            System.out.println("Snow Data Report Is Full.\n\n");
        }
        else{
        while(cnt <= numReports[0] && doSearch != false){
            if(addDayRequest[0] == reportMonth[cnt] && 
               addDayRequest[1] == reportDay[cnt] &&
               addDayRequest[2] == reportYear[cnt]){
                System.out.println("The date " + addDayRequest[0] + "/" +
                                   addDayRequest[1] + "/" + addDayRequest[2] +
                               " already exists in the Snowfall Report\n\n." );
                
                doSearch = false;                
            }
            
            if(cnt == numReports[0] && doSearch == true){
                reportMonth[cnt] = addDayRequest[0];
                reportDay[cnt] = addDayRequest[1];
                reportYear[cnt] = addDayRequest[2];
                System.out.print("Snowfall Amount (In Inches): ");
                reportSnowfallAmount[cnt] = kbd.nextDouble();
                numReports[0]++;
                doSearch = false;
            }
            
            cnt++;
        }
        }
    }
    
    private static void ModifyDate(int[] reportDay, int[] reportMonth,
                                   int[] reportYear, 
                                   double[] reportSnowfallAmount,
                                   int[] modifyDayRequest, int[] numReports){
        Scanner kbd;
        int cnt = 0;
        boolean validModify;
        
        kbd = new Scanner(System.in);
        
        validModify = false;
        
        while(cnt <= numReports[0] - 1 && validModify == false){
            if(modifyDayRequest[0] == reportMonth[cnt] && 
               modifyDayRequest[1] == reportDay[cnt] &&
               modifyDayRequest[2] == reportYear[cnt]){
                System.out.println("The date " + modifyDayRequest[0] + "/" +
                                   modifyDayRequest[1] + "/" 
                                 + modifyDayRequest[2] +
                                 " exists in the Snowfall Report.\n" );
                System.out.print("Please enter new Snowfall Amount: ");
                reportSnowfallAmount[cnt] = kbd.nextDouble();
                
                validModify = true;
            }
            
            if(cnt == numReports[0] - 1){
                System.out.print("The date " + modifyDayRequest[0] + "/"
                                 + modifyDayRequest[1] + "/" 
                                 + modifyDayRequest[2]
                                 + " does not exist within the report\n\n");
            }
            
            cnt++;
        }
    }
    
    private static int GetReportMonth(){
        int monthRequest;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        System.out.print("Please Select a Month (1-12): ");
        
        do{
            monthRequest = kbd.nextInt();
            
            if(monthRequest < 1 || monthRequest > 12){
                System.out.print("\nPlease select a valid Month(1-12): ");
            }
            
        }while(monthRequest < 1 || monthRequest > 12);
         
        return monthRequest;
    }
    
    private static int GetReportDay(int requestedMonth){
        int dayRequest, dayMaxRange;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        dayMaxRange = 31;
        
        if(requestedMonth == 1 || requestedMonth == 3 || requestedMonth == 5 ||
           requestedMonth == 7 || requestedMonth == 8 || requestedMonth == 10 ||
           requestedMonth == 12){
            dayMaxRange = 31;
        }
        if(requestedMonth == 4 || requestedMonth == 6 || requestedMonth == 9 ||
           requestedMonth == 11){
            dayMaxRange = 30;
        }
        if(requestedMonth == 2){
            dayMaxRange = 28;
        }
        System.out.print("Please Select a day (1-" + dayMaxRange + "): ");
        
        do{
            dayRequest = kbd.nextInt();
            
            if(dayRequest < 1 || dayRequest > dayMaxRange){
                System.out.print("\nPlease select a day(1-" + dayMaxRange +
                                 "): ");
            }
            
        }while(dayRequest < 1 || dayRequest > dayMaxRange);
        
        return dayRequest;
    }
    
    private static int GetReportYear(){
        int yearRequest;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        System.out.print("Please Select a Year (Greater than 0): ");
        
        do{
            yearRequest = kbd.nextInt();
            
            if(yearRequest < 1){
                System.out.print("\nPlease select a valid Year(Greater Than 0):"
                               + " ");
            }
            
        }while(yearRequest < 1);
        
        return yearRequest;
    }
    
    private static void DisplayDailyReport(int[] dayReportRequest, 
                                           int[] reportDay, int[] reportMonth,
                                           int[] reportYear,
                                           double[] reportSnowfallAmount,
                                           int[] numReports){
        boolean dateFound;
        
        int cnt = 0;
        
        dateFound = false;
        
        while(cnt <= numReports[0] && dateFound != true){
            if(dayReportRequest[0] == reportMonth[cnt] &&
               dayReportRequest[1] == reportDay[cnt] &&
               dayReportRequest[2] == reportYear[cnt]){
                System.out.println("\nDate: " + dayReportRequest[0] + "/" +
                                    dayReportRequest[1] + "/" 
                                  + dayReportRequest[2]);
                System.out.println("Snowfall Amount: "
                                  + reportSnowfallAmount[cnt] + " Inches\n\n");
                dateFound = true;
            }
            if(cnt == numReports[0]){
                System.out.println("The entered date was not found.");
            }
            cnt++;
        }
    }
    
    private static void DisplayMonthlyReport(int[] monthlyReportRequest, 
                                             int[] reportDay, int[] reportMonth,
                                             int[] reportYear,
                                             double[] reportSnowfallAmount,
                                             int[] numReports){
        
        int cnt = 0;
        
        System.out.println("\nDate            Snowfall Amount (Inches)");
        
        while(cnt < numReports[0]){
            if(monthlyReportRequest[0] == reportMonth[cnt] &&
               monthlyReportRequest[1] == reportYear[cnt]){
                System.out.println(reportMonth[cnt] + "/" + reportDay[cnt] +
                                   "/" + reportYear[cnt] + "            " +
                                   reportSnowfallAmount[cnt]);
            }
            cnt++;
        }
        System.out.println("\n\n");
        
    }
    
}
