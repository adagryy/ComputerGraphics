/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.awt.Color;

/**
 *
 * @author grycz
 */
public class Line {
    private Point3D p1, p2;
    private Color color;
    
    public Line(Point3D p1, Point3D p2, Color color){
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;
    }
    
    public Point3D getP1(){
        return p1;
    }
    
    public Point3D getP2(){
        return p2;
    }
    
    public Color getColor(){
        return color;
    }
}
