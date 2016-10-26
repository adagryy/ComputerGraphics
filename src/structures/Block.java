/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;
import testowy.MyFrame;

/**
 *
 * @author grycz
 */
public class Block {
    public double distance = 1000;
    private double [][] b;
    public Point3D a,bb,c,d,e,f,g,h;
//    private LinesContainer linesToDraw;
            
    public Block(double[][] b){
        this.b = b;
//        this.linesToDraw = linesToDraw;
        a = new Point3D(b[0][0], b[0][1], b[0][2]);
       bb = new Point3D(b[1][0], b[1][1], b[1][2]);
        c = new Point3D(b[2][0], b[2][1], b[2][2]);
        d = new Point3D(b[3][0], b[3][1], b[3][2]);
        e = new Point3D(b[4][0], b[4][1], b[4][2]);
        f = new Point3D(b[5][0], b[5][1], b[5][2]);
        g = new Point3D(b[6][0], b[6][1], b[6][2]);
        h = new Point3D(b[7][0], b[7][1], b[7][2]);
    }
    
    public ArrayList<Line> lines(){
        ArrayList<Line> lines = new ArrayList();
        
//        {100, 300, 100},
//        {500, 300, 100},
//        {500, 500, 100},
//        {100, 500, 100},            
//        {100, 300, 600},
//        {500, 300, 600},
//        {500, 500, 600},
//        {100, 500, 600}
        
        Color color = Color.black;//base of a cuboid
        lines.add(new Line(a,bb, color));
        lines.add(new Line(bb,c, color));
        lines.add(new Line(c,d, color));
        lines.add(new Line(d,a, color));
        
        color = Color.black;//top of the cuboid
        lines.add(new Line(e,f, color));
        lines.add(new Line(f,g, color));
        lines.add(new Line(g,h, color));
        lines.add(new Line(h,e, color));
        
        color = Color.black;//edges of the cuboid
        lines.add(new Line(a,e, color));
        lines.add(new Line(bb,f, color));
        lines.add(new Line(c,g, color));
        lines.add(new Line(d,h, color));
        
//        color = Color.red;//base of a cuboid
//        myFrame.setLines(new Line(a,bb, Color.yellow));
//        myFrame.setLines(new Line(bb,c, color));
//        myFrame.setLines(new Line(c,d, color));
//        myFrame.setLines(new Line(d,a, Color.blue));
//        
//        color = Color.black;//top of the cuboid
//        myFrame.setLines(new Line(e,f, color));
//        myFrame.setLines(new Line(f,g, color));
//        myFrame.setLines(new Line(g,h, color));
//        myFrame.setLines(new Line(h,e, color));
//        
//        color = Color.green;//edges of the cuboid
//        myFrame.setLines(new Line(a,e, color));
//        myFrame.setLines(new Line(bb,f, color));
//        myFrame.setLines(new Line(c,g, color));
//        myFrame.setLines(new Line(d,h, color));
        return lines;
    }
}
        