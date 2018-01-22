package com.example;  
  
import classloader.ICalculator;  
  
public class CalculatorBasic implements ICalculator {  
  
    @Override  
    public String calculate(String expression) {  
        return expression;  
    }  
  
    @Override  
    public String getVersion() {  
        return "5.0";  
    }  
  
}