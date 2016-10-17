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
    //We are getting screen dimensions here
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public int width = (int) screenSize.getWidth();
    public int height = (int) screenSize.getHeight();
    
    //Setting application window properties
    public int windowWidth = (int)(1 * width);
    public int windowHeight = (int) (1 * height);        
    public int windowStartX = 0;
    public int windowStartY = 0;
    
    
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
        {100, 300, 100},
        {500, 300, 100},
        {500, 500, 100},
        {100, 500, 100},            
        {100, 300, 600},
        {500, 300, 600},
        {500, 500, 600},
        {100, 500, 600}
    };
    
    public double b2[][] = {
        {400, 700, 100},
        {600, 900, 100},
        {400, 1100,100},
        {200, 900, 100},            
        {400, 700, 800},
        {600, 900, 800},
        {400, 1100,800},
        {200, 900, 800},  
    };
//    public ArrayList<Point3D> vertices = {new Point3D(1,2,3)};
}