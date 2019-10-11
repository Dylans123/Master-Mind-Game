/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.util.ArrayList;
import constants.Constants;
import java.util.Scanner;

/**
 *
 * @author kwhiting
 */
public class Codebreaker implements ICodebreaker
{

    // member variables
    private ArrayList<Color> codebreakerAttempt;
    //private Game game;
    
    public Codebreaker()
    {
        // instanatiate the member variables
        codebreakerAttempt = new ArrayList<>();
    }
    
    public void consoleAttempt()
    {
        // reset the codebreakerAttempt
        codebreakerAttempt.removeAll(codebreakerAttempt);
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nEnter your colors in left to right order\n" +
            "Use BLUE, BLACK, ORANGE, WHITE, YELLOW, RED, GREEN, PINK:");
               
        while(codebreakerAttempt.size() < Constants.MAX_PEGS)
        {
            String guess = scanner.next();
            
            switch(guess.toUpperCase())
            {
                case "BLUE":
                    System.out.println("You entered blue");
                    
                    if(codebreakerAttempt.contains(Color.BLUE))
                        System.out.println("Blue already selected");
                    else
                        codebreakerAttempt.add(Color.BLUE);
                    break;
                case "BLACK":
                    System.out.println("You entered black");
                    
                    if(codebreakerAttempt.contains(Color.BLACK))
                        System.out.println("Black already selected");
                    else
                        codebreakerAttempt.add(Color.BLACK);
                    break;
                case "GREEN":
                    System.out.println("You entered green");
                    
                    if(codebreakerAttempt.contains(Color.GREEN))
                        System.out.println("Green already selected");
                    else
                        codebreakerAttempt.add(Color.GREEN);
                    break;
                case "ORANGE":
                    System.out.println("You entered orange");

                    if(codebreakerAttempt.contains(Color.ORANGE))
                        System.out.println("Orange already selected");
                    else
                        codebreakerAttempt.add(Color.ORANGE);
                    break;
                case "PINK":
                    System.out.println("You entered pink");

                    if(codebreakerAttempt.contains(Color.PINK))
                        System.out.println("Pink already selected");
                    else
                        codebreakerAttempt.add(Color.PINK);
                    break;
                case "RED":
                    System.out.println("You entered red");

                    if(codebreakerAttempt.contains(Color.RED))
                        System.out.println("Red already selected");
                    else
                        codebreakerAttempt.add(Color.RED);
                    break;
                case "YELLOW":
                    System.out.println("You entered yellow");

                    if(codebreakerAttempt.contains(Color.YELLOW))
                        System.out.println("Yellow already selected");
                    else
                        codebreakerAttempt.add(Color.YELLOW);
                    break;
                case "WHITE":
                    System.out.println("You entered white");

                    if(codebreakerAttempt.contains(Color.WHITE))
                        System.out.println("White already selected");
                    else
                        codebreakerAttempt.add(Color.WHITE);
                    break;
                default:
                    System.out.println("Invalid color choice, try again");
                    break;
            }
            
            if(codebreakerAttempt.size() < Constants.MAX_PEGS)
                System.out.println("Next color");
        }   
        
        System.out.println("Codebreaker's attempt");
        
        for(Color color : codebreakerAttempt)
        {
            System.out.println(color);
        }
    }
    
    // Method to remove all of the colors from the arraylist \
    // named codebreakerAttempt
    public void removeAll() {
        codebreakerAttempt.removeAll(codebreakerAttempt);
    }
    /**
     * @return the codebreakerAttempt
     */
    public ArrayList<Color> getCodebreakerAttempt() 
    {
        // temporary code to test backend
        // consoleAttempt();
        return codebreakerAttempt;
    }

    /**
     * @param codebreakerAttempt the codebreakerAttempt to set
     */
    public void setCodebreakerAttempt(ArrayList<Color> codebreakerAttempt) 
    {
        this.codebreakerAttempt = codebreakerAttempt;
    }
    
    @Override
    public void checkCode(ArrayList<Color> attempt) 
    {
    }
   
}
