package com.hacknight.util;
/**
 * To make get time stamp and find difference
 * @author Devaraj
 *
 */
public class TimeStamp {
    /**
     * To get epoch time 
     * @return long
     */
    public static long getEpochTime(){
  	 	long epoch = System.currentTimeMillis()/1000;
  	 	return epoch;
    }
    
    /**
     * To get difference between to epochs
     * @param presentEpoch
     * @param receivedEpoch
     * @return {@link Long} EPoch
     */
    public static long getEpochDifference(String presentEpoch , String receivedEpoch){
     long  differnce   = Long.parseLong(presentEpoch)-Long.parseLong(receivedEpoch);
  	return differnce; 
    }

}
