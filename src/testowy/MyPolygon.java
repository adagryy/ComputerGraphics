/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testowy;

import structures.Point3D;

/**
 *
 * @author grycz
 */
public class MyPolygon implements Comparable{
    public Point3D p1, p2, p3, p4;
    public int covers;
    Configurator c;
    
    public MyPolygon(Point3D p1, Point3D p2, Point3D p3, Point3D p4, Configurator c){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.covers = 0;
        this.c = c;
    }
    
    @Override
    public int compareTo(Object obj){
        MyPolygon myPolygon = (MyPolygon) obj;
        int x = (this.covers - myPolygon.covers);
        if(x < 0)
            return -1;
        else if(x == 0)
            return 0;
        else 
            return 1;
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
}
