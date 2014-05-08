/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cc_package;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author Semih
 */
public class CC_Actions {
    
    static String URL = "http://themoneyconverter.com/rss-feed/";
    private String fromc;
    private String toc;
    private HashMap map;
        
    public  CC_Actions() {
        
        map = new HashMap();
        this.update("USD");

    }
    
     public void update(String funits) {
         
         fromc = funits;
         SyndFeed feed = null;
         
         try {
            URL feedUrl = new URL(URL+fromc+"/rss.xml");

            SyndFeedInput input = new SyndFeedInput();
            feed = input.build(new XmlReader(feedUrl));
         
         }
         catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
         }
         if (feed != null) {
            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
               String tt = entry.getTitle();
               String ds = entry.getDescription().getValue();
               System.out.println("Title: "+tt+"\nDescription: "+ds);
            
           double rate;
           int st = ds.indexOf("=") + 1;
           int en = ds.indexOf(".", st) + 6;
           rate = Double.parseDouble(ds.substring(st, en).replace(",", ""));
           map.put(tt, rate);
            }
         } 
         else 
         {
           System.out.println("RSS Feed unavailable.");
         }
     }
       public String getUnits() 
       {
           
        return fromc;
        
       }
       
       public double getRate(String ToUnits) 
               
       {
       double rate = 0 ;
        String conversion = ToUnits+"/"+fromc;
        
        
        try {
             
            rate = (Double) map.get(conversion);
            
        } catch (Exception e) {
            
            System.out.println("ERROR: "+e.getMessage());
            
        }
        
        return rate;
    }
       
}



    

