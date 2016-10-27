/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testowy;

import java.awt.Color;
import structures.Point3D;

/**
 *
 * @author grycz
 */
public class MyPolygon implements Comparable{
    public Point3D p1, p2, p3, p4;
    public int covers;
    Configurator c;
    public double distance = 0;
    public Color color;
    
    public MyPolygon(Point3D p1, Point3D p2, Point3D p3, Point3D p4, Configurator c, Color color){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.covers = 0;
        this.c = c;
        this.color = color;
    }
    
    @Override
    public int compareTo(Object obj){
        MyPolygon myPolygon = (MyPolygon) obj;
        int x = (this.covers - myPolygon.covers);
        if(x < 0)
            return 1;
        else if(x == 0){
            double comp = this.distance - myPolygon.distance;
            if(comp < 0)
                return 1;
            else if (comp == 0)
                return 0;
            else 
                return -1;
        }
        else 
            return -1;
    }
    
    public Point3D getPoint(int i){
        if(i == 0)
            return p1;
        else if(i == 1)
            return p2;
        else if(i == 2)
            return p3;
        else    
            return p4;        
    }
    
    public double massCenter()
    {
        Point3D pp1 = new Point3D(((p1.x + p2.x) / 2), ((p1.y + p2.y) / 2), ((p1.z + p2.z) / 2));
        Point3D pp2 = new Point3D(((p3.x + p4.x) / 2), ((p3.y + p4.y) / 2), ((p3.z + p4.z) / 2));
        Point3D middler = new Point3D(((pp1.x + pp2.x) / 2), ((pp1.y + pp2.y) / 2), ((pp1.z + pp2.z) / 2));
        return Math.sqrt(middler.x * middler.x + middler.y * middler.y + middler.z * middler.z);
//        return Math.abs(middler.z);
    }
}
