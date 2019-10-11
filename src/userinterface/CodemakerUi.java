/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import constants.Constants;
import core.Codemaker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author kwhiting
 */
public class CodemakerUi 
{
    private JPanel codemakerResponse;
    private JPanel secretCode;
    private JLabel[] secretLabels;
    private JLabel[][] responseLabels;
    private ImageIcon question;
    private JButton check;    
    private Codemaker codemaker;
    private boolean checkClicked;
    
    public CodemakerUi(Codemaker codemaker)
    {
        this.codemaker = codemaker;
        initComponents();
    }
    
    private void initComponents()
    {
        initCodemakerResponse();
        initSecretCode();
    }
    
    private void initCodemakerResponse()
    {
        codemakerResponse = new JPanel();
        codemakerResponse.setBorder(BorderFactory.createTitledBorder("Codemaker Response"));
        codemakerResponse.setMinimumSize(new Dimension(150, 100));
        codemakerResponse.setPreferredSize(new Dimension(150,100));
        codemakerResponse.setLayout(new GridLayout(Constants.MAX_ATTEMPTS, Constants.MAX_PEGS));
        
        // instantiate the Array with the size
        responseLabels = new JLabel[Constants.MAX_ATTEMPTS][Constants.MAX_PEGS];
        
        // create the array of JLabels for the code maker's response
        for (int row = 0; row < Constants.MAX_ATTEMPTS; row ++) 
        {			
            for(int col = 0; col < Constants.MAX_PEGS; col++)
            {
                // create the buttons
                responseLabels[row][col] = new JLabel();
                // set border, background and opacity of the buttons
                responseLabels[row][col].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
                responseLabels[row][col].setBackground(Color.DARK_GRAY);
                responseLabels[row][col].setOpaque(true);
                // add the button to the UI
                codemakerResponse.add(responseLabels[row][col]);
            }
        }
    }
    
    private void initSecretCode()
    {
        secretCode = new JPanel();
        secretCode.setBorder(BorderFactory.createTitledBorder("Secret Code"));
        secretCode.setMinimumSize(new Dimension(200, 60));
        secretCode.setPreferredSize(new Dimension(200,60));
        secretCode.setAlignmentY(JPanel.TOP_ALIGNMENT);
        
        // instantiate the Array with the size
        secretLabels = new JLabel[Constants.MAX_PEGS];
        
//        question = new ImageIcon( getClass().getResource("../images/question.jpg"));

        String fileName = "../images/question.jpg";
        URL imgURL = getClass().getResource(fileName);
        
        if(imgURL != null)
        {
            question = new ImageIcon(imgURL);
            question = imageResize(question);
        }
        else
        {
            System.err.println("Couldn't find file: " + fileName);
            question = null;
        }
        
        // counter for enhanced for loop
        int counter = 0;
        
        for (JLabel label : secretLabels) 
        {			
            label = new JLabel();
            label.setBackground(Color.LIGHT_GRAY);
            label.setIcon(imageResize(question));
            
            // add button to JPanel using FlowLayout
            secretCode.add(label);
            
            // increment the counter
            counter++;
        }
        
        // ghetto spacing
        JLabel space = new JLabel();
        space.setMinimumSize(new Dimension(100, 35));
        space.setPreferredSize(new Dimension(100, 35));
        secretCode.add(space);
        
        // add the check button
        JButton check = new JButton("Check");
        check.addActionListener(new CheckListener());
        setCheck(check);
        secretCode.add(getCheck());
    }
    
    
    // function to when the player either wins or loses the game
    public void displaySecretCode() {
        // remove everything currently in the secret code(the question mark images)
        secretCode.removeAll();
        JLabel label = new JLabel("The secret code was");
        
        // add the label to the secret code and then populate the secret code 
        // with the colors currently in the secret code arraylist
        secretCode.add(label);
        Set<Color> secretCodeArr = codemaker.getSecretCode();
        for(Color current: secretCodeArr) {
            RoundButton button = new RoundButton();
            button.setBackground(current);
            secretCode.add(button);
        }
        secretCode.revalidate();
        secretCode.repaint();
    }
    
    // function to display the red and white pegs after each attempt by the user
    public void displayCodemakerResponse(int row) {
        ArrayList<Color> codemakerResponseArr = codemaker.getCodemakerResponse();
        int count = 0;
        // for each color in the codemaker response if the color exits set it as the 
        // background color of the corresponding round button in the 2d array of response labels
        for(int i = 0; i < codemakerResponseArr.size(); ++i) {
            Color current = codemakerResponseArr.get(i);
            if(current != null) { 
                responseLabels[row][count].setOpaque(true);
                responseLabels[row][count].setBackground(current);
                ++count;
            }
        }
        // reset the array that stores the codemaker repsonse for the next round
        codemakerResponseArr.removeAll(codemakerResponseArr);
        codemaker.setCodemakerResponse(codemakerResponseArr);
    }
    
    /**
     * @return the codemakerResponse
     */
    public JPanel getCodemakerResponse() 
    {
        return codemakerResponse;
    }

    /**
     * @return the secretCode
     */
    public JPanel getSecretCode() 
    {
        return secretCode;
    }
    
    private ImageIcon imageResize(ImageIcon icon)
    {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }
    
    // listener to set the status of checkclicker to true when the user clicks the button
    private class CheckListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            checkClicked = true;
        }
    }

    /**
     * @return the check
     */
    public JButton getCheck() {
        return check;
    }

    /**
     * @param check the check to set
     */
    public void setCheck(JButton check) {
        this.check = check;
    }

    /**
     * @return the checkClicked
     */
    public boolean isCheckClicked() {
        return checkClicked;
    }

    /**
     * @param checkClicked the checkClicked to set
     */
    public void setCheckClicked(boolean checkClicked) {
        this.checkClicked = checkClicked;
    }
}
