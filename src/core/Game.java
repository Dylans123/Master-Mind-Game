/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.lang.System;
import java.util.ArrayList;
import java.util.Scanner;
import constants.Constants;

/**
 *
 * @author kwhiting
 */
public class Game implements IGame
{
    private int attempt;
    private Codebreaker codebreaker;
    private Codemaker codemaker;
    
    public Game()
    {
        // instantiate the instances of the member variables
        codemaker = new Codemaker();
        codebreaker = new Codebreaker();
        attempt = 0;
//        play();
    }
    
    public void play()
    {
        do
        {
            attempt++;
            System.out.println("**** Attempt " + attempt + " ****");
            ArrayList<Color> guess = codebreaker.getCodebreakerAttempt();
            codemaker.checkAttemptedCode(guess);
            ArrayList<Color> result = codemaker.getCodemakerResponse();
            
            System.out.println("Codemaker's response ");
        
            for(Color color : result)
            {
                System.out.println(color);
            }
        }while(attempt <= Constants.MAX_ATTEMPTS && !codemaker.isCodeGuessed());
    }

    /**
     * @return the attempt
     */
    public int getAttempt() {
        return attempt;
    }

    /**
     * @param attempt the attempt to set
     */
    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    /**
     * @return the codebreaker
     */
    public Codebreaker getCodebreaker() {
        return codebreaker;
    }

    /**
     * @param codebreaker the codebreaker to set
     */
    public void setCodebreaker(Codebreaker codebreaker) {
        this.codebreaker = codebreaker;
    }

    /**
     * @return the codemaker
     */
    public Codemaker getCodemaker() {
        return codemaker;
    }

    /**
     * @param codemaker the codemaker to set
     */
    public void setCodemaker(Codemaker codemaker) {
        this.codemaker = codemaker;
    }
}
