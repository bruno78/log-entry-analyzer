
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for ( String line : fr.lines()){
             records.add(WebLogParser.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){ 
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for (LogEntry le : records){
             String ip = le.getIpAddress();
             if (!uniqueIps.contains(ip)){
                 uniqueIps.add(ip);
             }
         }
         return uniqueIps.size();
     }
     
     public void printAllHigherThanNum(int num){
         for (LogEntry le : records){
             int statusCode = le.getStatusCode();
             if(statusCode > num){
                 System.out.println(statusCode);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for (LogEntry le : records){
             String date = le.getAccessTime().toString();
             String ip = le.getIpAddress();
             if(date.contains(someday) && !uniqueIps.contains(ip)) {
                 uniqueIps.add(ip);
             }
             
         }
         return uniqueIps; 
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             String ip = le.getIpAddress();
             if ((statusCode <= high && statusCode >= low) && !uniqueIps.contains(ip)) {
                 uniqueIps.add(ip);
             }
         }
         return uniqueIps.size();
    }
    
    public HashMap<String, Integer> countsVisitsPerIP(){
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records){
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)){
                counts.put(ip, 1);
            }
            else {
                counts.put(ip, counts.get(ip)+1);
            }
        }
        return counts;
    }
    
    public int mostNumberVisitsByIp(HashMap<String, Integer> counts){
        int maxVisitNum = 0;
        for (String ip : counts.keySet()){
            if(counts.get(ip) > maxVisitNum){
                maxVisitNum = counts.get(ip);
            }
        }
        return maxVisitNum;
    }
    
    public ArrayList<String> iPsMostVisits (HashMap<String, Integer> counts) {
        ArrayList<String> iPsMostsVisits = new ArrayList<String>();
        int maxVisits = mostNumberVisitsByIp(counts); 
        for (String ip : counts.keySet()){
            if(counts.get(ip) == maxVisits){
                iPsMostsVisits.add(ip);
            }
        }
        return iPsMostsVisits;
    }
    
    private String parseAccessTime(LogEntry le){
        String date = le.getAccessTime().toString();
        String shortDate = date.substring(date.indexOf(" ")+1, date.indexOf(" ")+7);
        return shortDate;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> ipDays = 
                new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records){
            String date = parseAccessTime(le);
            String ip = le.getIpAddress();
            if(!ipDays.containsKey(date)){
                ArrayList<String> list = new ArrayList<String>();
                list.add(ip);
                ipDays.put(date, list);
            }
            else {
                ArrayList<String> list = ipDays.get(date);
                list.add(ip);
                ipDays.put(date, list);
            }
        }
        return ipDays;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> records){
        int mostIps = 0;
        String date = "";
        for (String d : records.keySet()){
            int currVal = records.get(d).size();
            if (currVal > mostIps){
                mostIps = currVal;
                date = d;
            }
        }
        return date;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(String day, 
                                            HashMap<String, ArrayList<String>> records){
        if(records.containsKey(day)){
            ArrayList<String> ipList = records.get(day);
            // map (HashMap) IPs into ip and occurr
            HashMap<String, Integer>ipFreqs = new HashMap<String, Integer>();
            for (String ip : ipList){
                if(!ipFreqs.containsKey(ip)){
                    ipFreqs.put(ip, 1);
                }
                else {
                    ipFreqs.put(ip, ipFreqs.get(ip)+1);
                }
            }
            // apply methods to calculate max
            // verify which ips are equals to max 
            // store into list
            return iPsMostVisits(ipFreqs);
        } else {
            System.out.println("Day not found");
            return null;
        } 
    }
}
