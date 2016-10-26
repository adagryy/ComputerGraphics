/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

/**
 *
 * @author grycz
 */
public class Point3D {
    public double x, y, z;
    
    public Point3D(){}
    
    public Point3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    @Override
    public String toString(){
        return "("+x+","+y+","+z+")";
    }
    
    public void setPoint(Point3D point){
        this.x = point.x;
        this.y = point.y;
        this.z = point.z;
    }
    
    public Point3D substract(Point3D point){
        Point3D p = new Point3D();
        p.x = this.x - point.x;
        p.y = this.y - point.y;
        p.z = this.z - point.z;  
        return p;
    }
}
