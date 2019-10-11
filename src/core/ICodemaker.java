/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author kwhiting
 */
public interface ICodemaker 
{
    
     public void generateSecretCode();
     public void checkAttemptedCode(ArrayList<Color> attempt);
}
