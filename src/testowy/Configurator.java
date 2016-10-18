/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testowy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import structures.*;

/**
 *
 * @author grycz
 */
public class Configurator {    
    //Set here amount of the buildings
    public final int buildings = 2;
    
    //Sets initial distance of the scene from projection surface
    public double initial_distance = 3000;
    
    //Rotation angle step
    public double angle = 10;
    
    //We are getting screen dimensions here
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public int width = (int) screenSize.getWidth();
    public int height = (int) screenSize.getHeight();
    
    //Setting application window properties
    public int windowWidth = (int)(0.6 * width);
    public int windowHeight = (int) (0.7 * height);        
    public int windowStartX = 10;
    public int windowStartY = 20;
    
    
    //Setting panel (JPanel) properties
    public int panelWidth = (int) (0.8 * windowWidth);
    public int panelHeight = (int) (0.9 * windowHeight);    
    public Color panelColor = Color.LIGHT_GRAY;
    public int panelStartX = 10;//It forces to start draw panel from point (10,10) in JFrame
    public int panelStartY = 10;
    
    //Setting button(s) properties
    public int buttonX = (int) (0.83 * windowWidth);
    public int buttonHeight = 40;
    public int buttonWidth = 150;
    public int buttonMarginTop = 10;
    public int distanceBetweenButtons = 25;
    
    public double b1[][] = {
//        {100, 300, 100},
//        {500, 300, 100},
//        {500, 500, 100},
//        {100, 500, 100},            
//        {100, 300, 600},
//        {500, 300, 600},
//        {500, 500, 600},
//        {100, 500, 600}
        {50, 150, 100},
        {300, 150, 100},
        {300, 250, 100},
        {50, 250, 100},            
        {50, 150, 500},
        {300, 150, 500},
        {300, 250, 500},
        {50, 250, 500} 
    };
    
    public double b2[][] = {
        {200, 300, 100},
        {300, 400, 100},
        {200, 500,100},
        {100, 400, 100},            
        {200, 300, 600},
        {300, 400, 600},
        {200, 500, 600},
        {100, 400, 600}, 
    };
    
    public double angleMatrix[][] = {
        {Math.cos(Math.toRadians(angle)), 0, Math.sin(Math.toRadians(angle)), 0},
        {0, 1, 0, 0},
        {-1 * Math.sin(Math.toRadians(angle)), 0, Math.cos(Math.toRadians(angle)), 0},
        {0, 0, 0, 1}
    };
}