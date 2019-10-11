package userinterface;

import constants.Constants;
import core.Codebreaker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kwhiting
 */
public class CodebreakerUi 
{
    private JPanel codebreakerAttempt;
    private JPanel codebreakerColors;
    private RoundButton[] buttons;
    private RoundButton[][] attempts;
    
    private Codebreaker codebreaker;
    private Color colorSelected;
    
    public CodebreakerUi(Codebreaker codebreaker)
    {
        this.codebreaker = codebreaker;
        initComponents();
    }
    
    private void initComponents()
    {
        initCodebreakerColors();
        initCodebreakerAttempt();
    }
    
    private void initCodebreakerColors()
    {
        codebreakerColors = new JPanel();
        codebreakerColors.setBorder(BorderFactory.createTitledBorder("Codebreaker Colors"));
        codebreakerColors.setMinimumSize(new Dimension(200, 65));
        codebreakerColors.setPreferredSize(new Dimension(200,65));
        
        // instantiate the Array with the size
        buttons = new RoundButton[Constants.COLORS];
        
        // counter for enhanced for loop
        int index = 0;
        
        // put client properties on the buttons so we
        // know which one it is
        for (RoundButton button : buttons) 
        {			
            // create the buttons
            button = new RoundButton();
            Color color = Constants.codeColors.get(index);
            button.setBackground(color);
            button.putClientProperty("color", color);
            
            // set the tooltip
            if(color == Color.BLUE)
                button.setToolTipText("BLUE");
            else if(color == Color.BLACK)
                button.setToolTipText("BLACK");
            else if(color == Color.GREEN)
                button.setToolTipText("GREEN");
            else if(color == Color.ORANGE)
                button.setToolTipText("ORANGE");
            else if(color == Color.PINK)
                button.setToolTipText("PINK");
            else if(color == Color.RED)
                button.setToolTipText("RED");            
            else if(color == Color.YELLOW)
                button.setToolTipText("YELLOW");
            else if(color == Color.WHITE)
                button.setToolTipText("WHITE");
                        
            // add an ActionListener
            button.addActionListener(new ColorListener());
            
            // add button to JPanel using FlowLayout
            codebreakerColors.add(button);
            
            // increment the counter
            index++;
        }	
    }
    
    private void initCodebreakerAttempt()
    {
        codebreakerAttempt = new JPanel();
        codebreakerAttempt.setBorder(BorderFactory.createTitledBorder("Codebreaker Attempt"));
        codebreakerAttempt.setMinimumSize(new Dimension(100, 100));
        codebreakerAttempt.setPreferredSize(new Dimension(100, 100));
        
        // set the layout manager to use GridLayout
        codebreakerAttempt.setLayout(new GridLayout(Constants.MAX_ATTEMPTS, Constants.MAX_PEGS));
        
        // instantiate the Array with the size
        attempts = new RoundButton[Constants.MAX_ATTEMPTS][Constants.MAX_PEGS];
        
        // create the array of JButtons for the code breaker's attempts
        for (int row = 0; row < Constants.MAX_ATTEMPTS; row ++) 
        {			
            for(int col = 0; col < Constants.MAX_PEGS; col++)
            {
                // create the buttons
                attempts[row][col] = new RoundButton();
                
                // if it isn't the last row then disable the button
                if(row != (Constants.MAX_ATTEMPTS - 1))
                    attempts[row][col].setEnabled(false);
                
                // add the button to the UI
                attempts[row][col].putClientProperty("row", row);
                // add an attempt listener to perform an action when a color is added to the attempt
                attempts[row][col].addActionListener(new AttemptListener());
                
                codebreakerAttempt.add(attempts[row][col]);
            }
        }
    }

    /**
     * @return the codebreakerAttempt
     */
    public JPanel getCodebreakerAttempt() 
    {
        return codebreakerAttempt;
    }

    /**
     * @return the codebreakerColors
     */
    public JPanel getCodebreakerColors() 
    {
        return codebreakerColors;
    }
    
    private void enableDisableButtons(int row) {
        for(int i = 0; i < attempts[row].length; ++i) {
            attempts[row][i].setEnabled(false);
        }
        if(row != 0) {
            for(int i = 0; i < attempts[row - 1].length; ++i) {
                attempts[row - 1][i].setEnabled(true);
            }
        }
    }
    
    // class to set color selected to the color of the button that is currently selected.
    private class ColorListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            RoundButton button = (RoundButton)ae.getSource();
            colorSelected = (Color)button.getClientProperty("color");
        }
    }
    
    // class that listens for a user to add a color to their attmept
    // and allows the user to select the colors for their attempt. At the end of
    // their attempt it enables the next row for further attempts.
    private class AttemptListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            RoundButton button = (RoundButton)ae.getSource();
            ArrayList<Color> current = codebreaker.getCodebreakerAttempt();
            if(!(current.contains(colorSelected))) {
                button.setBackground(colorSelected);
                current.add(colorSelected);
                codebreaker.setCodebreakerAttempt(current);
            }
            if(current.size() == Constants.MAX_PEGS) {
                int row = (int)button.getClientProperty("row");
                enableDisableButtons(row);
            }
        }
    }    
}
