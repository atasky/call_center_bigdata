package com.calleva.similarity;


@SuppressWarnings("serial")
public class Levenshtein implements SimilarityMeasure {
    
    public static void main (String[] args) {
        
    	String s1 = "Adobe CreativeSuite 5 Master Collection from cheap d1x";
        String s2 = "Adobe CreativeSuite 5 Master Collection from cheap d1x";
        Levenshtein l = new Levenshtein();
        System.out.println(l.similarity(s1, s2));
    }
    public double distance(String s1, String s2) {
       
    	return LevenshteinDistance(s1, s2) / Math.max(s1.length(), s2.length());
    }

    public double similarity(String s1, String s2) {
        return 1.0 - distance(s1, s2);
    }
    public double LevenshteinDistance (String s0, String s1) {                          
        int len0 = s0.length() + 1;                                                     
        int len1 = s1.length() + 1;                                                     
                                                                                        
        // the array of distances                                                       
        int[] cost = new int[len0];                                                     
        int[] newcost = new int[len0];                                                  
                                                                                        
        // initial cost of skipping prefix in String s0                                 
        for (int i = 0; i < len0; i++) cost[i] = i;                                     
                                                                                        
        // dynamically computing the array of distances                                  
                                                                                        
        // transformation cost for each letter in s1                                    
        for (int j = 1; j < len1; j++) {                                                
            // initial cost of skipping prefix in String s1                             
            newcost[0] = j;                                                             
                                                                                        
            // transformation cost for each letter in s0                                
            for(int i = 1; i < len0; i++) {                                             
                // matching current letters in both strings                             
                int match = (s0.charAt(i - 1) == s1.charAt(j - 1)) ? 0 : 1;             
                                                                                        
                // computing cost for each transformation                               
                int cost_replace = cost[i - 1] + match;                                 
                int cost_insert  = cost[i] + 1;                                         
                int cost_delete  = newcost[i - 1] + 1;                                  
                                                                                        
                // keep minimum cost                                                    
                newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }                                                                           
                                                                                        
            // swap cost/newcost arrays                                                 
            int[] swap = cost; cost = newcost; newcost = swap;                          
        }                                                                               
                                                                                        
        // the distance is the cost for transforming all letters in both strings        
        return cost[len0 - 1];                                                          
    }

	public double similarity(String[] x, String[] y) {
		
		return 0;
	}
}
