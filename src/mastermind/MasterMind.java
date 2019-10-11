/*
 * Karin Whiting
 * COP 3330 Object Oriented Programming
 * University of Central Florida
 */
package mastermind;

import core.Game;
import javax.swing.JOptionPane;
import userinterface.MasterMindUi;

/**
 *
 * @author kwhiting
 */
public class MasterMind 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        System.out.println("Welcome to MasterMind!");
        JOptionPane.showMessageDialog(null, "Let's Play MasterMind!");
        Game game = new Game();
        MasterMindUi ui = new MasterMindUi(game);
    }
}
