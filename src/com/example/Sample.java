package com.example;  
  
public class Sample {  
  
    private Sample instance;  
  
    public void setSample(Object instance) {  
        System.out.println(instance.toString());  
        this.instance = (Sample) instance;  
    } 
    
    public static void say(){
    	System.out.println("5555555555");
    }
} 