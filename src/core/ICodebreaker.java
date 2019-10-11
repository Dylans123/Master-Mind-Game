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
public interface ICodebreaker 
{
    public void checkCode(ArrayList<Color> attempt);
}
