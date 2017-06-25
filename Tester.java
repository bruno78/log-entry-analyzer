
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log");
        log.printAll();
    }
    
    public void testUniqueIps() {  
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log");
        System.out.println("There are " + log.countUniqueIPs() + " unique IPs.");
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log");
        log.printAllHigherThanNum(201);
        log.readFile("weblog1_log");
        log.printAllHigherThanNum(400);
    }
    
    public void testUniqueIpVisitsOnDay(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog-short_log");
        String date = "Sep 14";
        ArrayList<String> uniqueIPVisitsOnDay = new ArrayList<String>(log.uniqueIPVisitsOnDay(date));
        System.out.println("The number of visits on " + date + " were: " + uniqueIPVisitsOnDay.size());
        System.out.println("These visits were ");
        for (String visit : uniqueIPVisitsOnDay){
            System.out.println(visit);
        }
        date = "Sep 30";
        uniqueIPVisitsOnDay = new ArrayList<String>(log.uniqueIPVisitsOnDay(date));
        System.out.println("The number of visits on " + date + " were: " + uniqueIPVisitsOnDay.size());
        System.out.println("These visits were ");
        for (String visit : uniqueIPVisitsOnDay){
            System.out.println(visit);
        }
        log.readFile("weblog1_log");
        date = "Mar 24";
        uniqueIPVisitsOnDay = new ArrayList<String>(log.uniqueIPVisitsOnDay(date));
        System.out.println("The number of visits on " + date + " were: " + uniqueIPVisitsOnDay.size());
        System.out.println("These visits were ");
        for (String visit : uniqueIPVisitsOnDay){
            System.out.println(visit);
        }
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log");
        int low = 200;
        int high = 299;
        System.out.println("Unique IPs in range between " + low + " and " + high + 
                " are: " + log.countUniqueIPsInRange(low,high));
        
        log.readFile("short-test_log");
        low = 300;
        high = 399;
        System.out.println("Unique IPs in range between " + low + " and " + high + 
                " are: " + log.countUniqueIPsInRange(low,high));
        
        
        log.readFile("weblog1_log");
        low = 200;
        high = 299;
        System.out.println("Unique IPs in range between " + low + " and " + high + 
                " are: " + log.countUniqueIPsInRange(low,high)); // Returns 69(?)
    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log");
        System.out.println(log.countsVisitsPerIP());
        
    }
    
    public void testMostNumberVisitsByIp(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log");
        HashMap<String, Integer> counts = new HashMap<String, Integer>(log.countsVisitsPerIP());
        System.out.println(log.mostNumberVisitsByIp(counts));
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog3-short_log");
        HashMap<String, Integer> counts = new HashMap<String, Integer>(log.countsVisitsPerIP());
        System.out.println(log.iPsMostVisits(counts));
    }
    
    public void testIPsForDays(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog3-short_log");
        System.out.println(log.iPsForDays());
    }
    
    public void TestDayWithMostIPVisits(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ips = log.iPsForDays();
        System.out.println(log.dayWithMostIPVisits(ips));
    }
    
    public void TestIPsWithMostVisitsOnDay(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ips = log.iPsForDays();
        String day = "Sep 30";
        System.out.println(log.iPsWithMostVisitsOnDay(day, ips));
    }
    
    public void examTest(){
        
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog1_log");
        HashMap<String, Integer> counts = new HashMap<String, Integer>(log.countsVisitsPerIP());
        System.out.println(log.mostNumberVisitsByIp(counts));
        
        log = new LogAnalyzer();
        log.readFile("weblog1_log");
        counts = new HashMap<String, Integer>(log.countsVisitsPerIP());
        System.out.println(log.iPsMostVisits(counts));
        
        log = new LogAnalyzer();
        log.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> ips = log.iPsForDays();
        System.out.println(log.dayWithMostIPVisits(ips));
        
        log = new LogAnalyzer();
        log.readFile("weblog1_log");
        ips = log.iPsForDays();
        String day = "Mar 17";
        System.out.println(log.iPsWithMostVisitsOnDay(day, ips));
    
    }
    
    public void examTest2(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        System.out.println("There are " + log.countUniqueIPs() + " unique IPs.");
        
        log = new LogAnalyzer();
        log.readFile("weblog2_log");
        String date = "Sep 24";
        ArrayList<String> uniqueIPVisitsOnDay = new ArrayList<String>(log.uniqueIPVisitsOnDay(date));
        System.out.println("The number of visits on " + date + " were: " + uniqueIPVisitsOnDay.size());
        
        log.readFile("weblog2_log");
        int low = 200;
        int high = 299;
        System.out.println("Unique IPs in range between " + low + " and " + high + 
                " are: " + log.countUniqueIPsInRange(low,high));
        
        log = new LogAnalyzer();
        log.readFile("weblog2_log");
        HashMap<String, Integer> counts = new HashMap<String, Integer>(log.countsVisitsPerIP());
        System.out.println(log.mostNumberVisitsByIp(counts));
        
        log = new LogAnalyzer();
        log.readFile("weblog2_log");
        counts = new HashMap<String, Integer>(log.countsVisitsPerIP());
        System.out.println(log.iPsMostVisits(counts));
        
        log = new LogAnalyzer();
        log.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ips = log.iPsForDays();
        System.out.println(log.dayWithMostIPVisits(ips));
        
        log = new LogAnalyzer();
        log.readFile("weblog2_log");
        ips = log.iPsForDays();
        String day = "Sep 29";
        System.out.println(log.iPsWithMostVisitsOnDay(day, ips));
    }
}
