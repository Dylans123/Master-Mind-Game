/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import constants.Constants;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author kwhiting
 */
public class Codemaker implements ICodemaker
{
    // member variables
    private Set<Color> secretCode;
    private ArrayList<Color> codemakerResponse;
    private boolean codeGuessed; 
    
    public Codemaker()
    {
        // instantiate the member variable 
        codeGuessed = false; 
        secretCode = new HashSet();     
        codemakerResponse = new ArrayList();
        
        // call the method to generate the secret code
        generateSecretCode();
    }
    
    public void generateSecretCode()
    {
        Random random = new Random();
        
        //randomly select four of the eight colors to be the secret code, only 
        // use each color once
        while(secretCode.size() < Constants.MAX_PEGS)
        {
            // randomly select an index into the ArrayList of the 8 Colors
            int index = random.nextInt(Constants.COLORS);
            
            // get that color from the ArrayList of colors
            Color selectedColor = Constants.codeColors.get(index);
            
            // add it to the Set
            secretCode.add(selectedColor);
        }
        
        System.out.println("generated the secret code! ");
        
        for(Color color : secretCode)
        {
            System.out.println(color.toString());
        }
        
    }
    
    public void checkAttemptedCode(ArrayList<Color> attempt)
    {
        // correct color in correct position is a red peg
        int redPegs = 0;
        // correct color in the wrong position is a white peg
        int whitePegs = 0;
        // incorrect color has no peg 
        // keep track of which pegs we already counted
        Set<Color> countedPegs = new HashSet();
        
        System.out.println("Codemaker is checking codebreaker attempt");
        
        // convert the Set to an ArrayList
        List<Color> secretList = new ArrayList<Color>(secretCode);
        
        // is it an exact match?            
        if(secretList.equals(attempt))
        {
            redPegs += 4; 
            whitePegs = 0;
            System.out.println("You guessed it!");
        }
        // not an exact match, is it even in the secret code
        else
        {                     
            // check for correct color and correct position
            for(int peg = 0; peg < Constants.MAX_PEGS; peg++)
            {
                if(secretList.get(peg) == attempt.get(peg))
                {
                    System.out.println("Found correct color in correct position!");
                    // correct peg in correct location, add a red peg
                    redPegs++;
                    countedPegs.add(attempt.get(peg));
                }
            }
            
            // check for any match
            for(Color color : attempt)
            {
                // check if any of the colors are correct
                if(secretList.contains(color))       
                {
                    for(int peg = 0; peg < Constants.MAX_PEGS; peg++)
                    {

                        if((secretList.get(peg) != attempt.get(peg)) && 
                           (secretList.contains(attempt.get(peg)))  &&
                           !countedPegs.contains(attempt.get(peg))) 
                        {
                            System.out.println("Found correct color in wrong position");
                            // add a white peg
                            whitePegs++;
                            countedPegs.add(attempt.get(peg));
                        }
                    }
                }
            }
        }
        
        evaluatePegs(redPegs, whitePegs);
    }

    private void evaluatePegs(int red, int white)
    {
        // clear the codemakerResponse
        codemakerResponse.removeAll(codemakerResponse);
        
        System.out.println("Red pegs " + red + " white pegs " + white);
        
        if(red == Constants.MAX_PEGS)
        {
            codeGuessed = true;
        }
        
        for(int r = 0; r < red; r++)
        {
            codemakerResponse.add(Color.RED);
        }
        
        for(int w = 0; w < white; w++)
        {
            codemakerResponse.add(Color.WHITE);
        }
    }
    
    /**
     * @return the secretCode
     */
    public Set<Color> getSecretCode() 
    {
        return secretCode;
    }

    /**
     * @param secretCode the secretCode to set
     */
    public void setSecretCode(Set<Color> secretCode) 
    {
        this.secretCode = secretCode;
    }

    /**
     * @return the codemakerResponse
     */
    public ArrayList<Color> getCodemakerResponse() 
    {
        return codemakerResponse;
    }

    /**
     * @param codemakerResponse the codemakerResponse to set
     */
    public void setCodemakerResponse(ArrayList<Color> codemakerResponse) 
    {
        this.codemakerResponse = codemakerResponse;
    }

    /**
     * @return the codeGuessed
     */
    public boolean isCodeGuessed() {
        return codeGuessed;
    }

    /**
     * @param codeGuessed the codeGuessed to set
     */
    public void setCodeGuessed(boolean codeGuessed) {
        this.codeGuessed = codeGuessed;
    }
    
    
}
