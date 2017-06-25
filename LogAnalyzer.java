
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
}
