/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testowy;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.JLabel;
import structures.*;

/**
 *
 * @author grycz
 */
public class MyFrame extends JFrame implements  KeyListener, ActionListener{
    public JButton jb, jb2;
    public MyPanel panel;
    public JLabel label1;
    private double initial_distance;
    public LinesContainer linesToDraw;
    Configurator c;
    Block block1, block2;
    ArrayList<Block> blocks = new ArrayList();
    public boolean flag = false;
    public MyFrame(Configurator c) {
            super("Hello World");
            this.c = c;
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);            
            setPreferredSize(new Dimension(c.windowWidth ,c.windowHeight));
            setLocation(c.windowStartX, c.windowStartY);
            panel = new MyPanel(c, c.panelStartX, c.panelStartY);  
            setFocusable(true);
            
            blocks.add(new Block(c.b1));            
            blocks.add(new Block(c.b2));
//            block1 = new Block(c.b1, linesToDraw);
//            block2 = new Block(c.b2, linesToDraw);
            
            
            this.initial_distance = c.initial_distance;
            
            add(panel);
            pack();           
            
            jb = new JButton("Wyjście");
            jb2 = new JButton("Przycisk");    
            
            jb.setBounds(c.buttonX, nthButton(1), c.buttonWidth, c.buttonHeight);
            jb2.setBounds(c.buttonX, nthButton(2), c.buttonWidth, c.buttonHeight);
            
            jb.addActionListener(this);
            jb2.addActionListener(this);
            
            addKeyListener(this);
            
            
            label1 = new JLabel("Distance: 1000px");
            label1.setBounds(c.buttonX, nthButton(3), c.buttonWidth, c.buttonHeight);

            add(label1);
            add(jb);
            add(jb2);
            
            performZoom(0);
            
            setVisible(true);
    }
        
        
    @Override
    public void keyReleased(KeyEvent evt) {
    }
 
    @Override
    public void keyPressed(KeyEvent evt) {      
        char c = evt.getKeyChar();
        if(c == 'o' || c == 'O'){
            performZoom(50);
        }
        if(c == 'i' || c == 'I'){
            performZoom(-50);
        }
        if(c == 'w' || c == 'W'){
            performTranslationZ(50.0);
        }
        if(c == 's' || c == 'S'){
            performTranslationZ(-50.0);
        }   
        if(c == 'D' || c == 'd'){
            performTranslationX(50.0);
        }
        if(c == 'A' || c == 'a'){
            performTranslationX(-50.0);
        }  
        if(c == 'z' || c == 'Z'){
            performTranslationY(-50.0);
        }
        if(c == 'Q' || c == 'q'){
            performTranslationY(50.0);
        }         
        if(c == 't' || c == 'T'){
            performRotation();
        }   
    } 
    @Override
    public void keyTyped(KeyEvent evt) {
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == jb)
            System.exit(0);        
    }
    
    public void performZoom(int zoom_factor){
        ArrayList<DrawLine> drawLineList = new ArrayList(); //lines to be drawn on JPanel
        
        if(this.initial_distance <= 10 * abs(zoom_factor) && zoom_factor < 0)
            return;
        this.initial_distance += zoom_factor;
        
        for(int it = 0; it < c.buildings; it++){
            ArrayList<Line> lines = new ArrayList();
            lines = blocks.get(it).lines();
    //        System.out.println()
            label1.setText("Distance: " + Integer.toString((int)this.initial_distance ) + "px");
            for(int i = 0; i < 12;i++){//12 - amount of edges in cuboid
                Point3D p1 = lines.get(i).getP1();
                Point3D p2 = lines.get(i).getP2();
                Color color = lines.get(i).getColor();

                drawLineList.add(execute_projection(p1.x, p1.y, p2.x, p2.y, p1.z, p2.z, color));                
            }
        }
        panel.setDll(drawLineList);
        panel.repaint();
    }
    
    public void performTranslationZ(double translation_factor){
        ArrayList<DrawLine> drawLineList = new ArrayList(); //lines to be drawn on JPanel
        for(int it = 0; it < c.buildings; it++){
            blocks.get(it).a.z += translation_factor;
            blocks.get(it).bb.z+= translation_factor;
            blocks.get(it).c.z += translation_factor;
            blocks.get(it).d.z += translation_factor;
            blocks.get(it).e.z += translation_factor;
            blocks.get(it).f.z += translation_factor;
            blocks.get(it).g.z += translation_factor;
            blocks.get(it).h.z += translation_factor;
            System.out.print(blocks.get(it).a.z);
            System.out.print(blocks.get(it).bb.z);
            System.out.print(blocks.get(it).c.z);
            System.out.print(blocks.get(it).d.z);
            System.out.print(blocks.get(it).e.z);
            System.out.print(blocks.get(it).f.z);
            System.out.print(blocks.get(it).g.z);
            System.out.print(blocks.get(it).h.z);
            System.out.println();
            ArrayList<Line> lines = blocks.get(it).lines();

            for(int i = 0; i < 12;i++){
                Point3D p1 = lines.get(i).getP1();
                Point3D p2 = lines.get(i).getP2();
                Color color = lines.get(i).getColor();

                drawLineList.add(execute_projection(p1.x, p1.y, p2.x, p2.y, p1.z, p2.z, color));                
            }
        }
        panel.setDll(drawLineList);
        panel.repaint();
    }
    
    public void performTranslationX(double translation_factor){
        ArrayList<DrawLine> drawLineList = new ArrayList(); //lines to be drawn on JPanel
        for(int it = 0; it < c.buildings; it++){
            blocks.get(it).a.x += translation_factor;
            blocks.get(it).bb.x+= translation_factor;
            blocks.get(it).c.x += translation_factor;
            blocks.get(it).d.x += translation_factor;
            blocks.get(it).e.x += translation_factor;
            blocks.get(it).f.x += translation_factor;
            blocks.get(it).g.x += translation_factor;
            blocks.get(it).h.x += translation_factor;
            System.out.println("Ok!");

            ArrayList<Line> lines = blocks.get(it).lines();

            for(int i = 0; i < 12;i++){
                Point3D p1 = lines.get(i).getP1();
                Point3D p2 = lines.get(i).getP2();
                Color color = lines.get(i).getColor();

                drawLineList.add(execute_projection(p1.x, p1.y, p2.x, p2.y, p1.z, p2.z, color));                
            }
        }
        panel.setDll(drawLineList);
        panel.repaint();
    }
    
    public void performTranslationY(double translation_factor){
        ArrayList<DrawLine> drawLineList = new ArrayList(); //lines to be drawn on JPanel
        for(int it = 0; it < c.buildings; it++){
            blocks.get(it).a.y += translation_factor;
            blocks.get(it).bb.y+= translation_factor;
            blocks.get(it).c.y += translation_factor;
            blocks.get(it).d.y += translation_factor;
            blocks.get(it).e.y += translation_factor;
            blocks.get(it).f.y += translation_factor;
            blocks.get(it).g.y += translation_factor;
            blocks.get(it).h.y += translation_factor;
            System.out.println("Ok!");

            ArrayList<Line> lines = blocks.get(it).lines();

            for(int i = 0; i < 12;i++){
                Point3D p1 = lines.get(i).getP1();
                Point3D p2 = lines.get(i).getP2();
                Color color = lines.get(i).getColor();

                drawLineList.add(execute_projection(p1.x, p1.y, p2.x, p2.y, p1.z, p2.z, color));                
            }
        }
        panel.setDll(drawLineList);
        panel.repaint();
    }
    
    public void performRotation(){
        ArrayList<DrawLine> drawLineList = new ArrayList(); //lines to be drawn on JPanel
        System.out.println("II!");
        for(int it = 0; it < c.buildings; it++){           
            ArrayList<Line> lines = blocks.get(it).lines();          
            blocks.get(it).a = multiplyMatrices(blocks.get(it).a);
            blocks.get(it).bb= multiplyMatrices(blocks.get(it).bb);
            blocks.get(it).c = multiplyMatrices(blocks.get(it).c);
            blocks.get(it).d = multiplyMatrices(blocks.get(it).d);
            blocks.get(it).e = multiplyMatrices(blocks.get(it).e);
            blocks.get(it).f = multiplyMatrices(blocks.get(it).f);
            blocks.get(it).g = multiplyMatrices(blocks.get(it).g);
            blocks.get(it).h = multiplyMatrices(blocks.get(it).h);
            for(int i = 0; i < 12;i++){
                Point3D p1 = lines.get(i).getP1();
                Point3D p2 = lines.get(i).getP2();
                Color color = lines.get(i).getColor();
                drawLineList.add(execute_projection(p1.x, p1.y, p2.x, p2.y, p1.z, p2.z, color));                
            }
        }
        panel.setDll(drawLineList);
        panel.repaint();
    }
    
    public Point3D multiplyMatrices(Point3D point){
        double a, b, cc;
        a = point.x * c.angleMatrix[0][0] + c.angleMatrix[0][1] * point.y + c.angleMatrix[0][2] * point.z;
        b = point.x * c.angleMatrix[1][0] + c.angleMatrix[1][1] * point.y + c.angleMatrix[1][2] * point.z;
        cc= point.x * c.angleMatrix[2][0] + c.angleMatrix[2][1] * point.y + c.angleMatrix[2][2] * point.z;
        return new Point3D(a, b, cc);
    }
    public DrawLine execute_projection(double x1, double y1, double x2, double y2, double z1, double z2, Color color)
    {
        int x1_int = (int) x1, x2_int = (int) x2, 
            y2_int = (int) y2, y1_int = (int) y1, 
            z1_int = (int) z1, z2_int = (int) z2;
        DrawLine dl = new DrawLine();      
        dl.x1 = (x1_int * this.initial_distance ) / (z1_int + this.initial_distance );
        dl.y1 = (y1_int * this.initial_distance ) / (z1_int + this.initial_distance );
        dl.x2 = (x2_int * this.initial_distance ) / (z2_int + this.initial_distance );
        dl.y2 = (y2_int * this.initial_distance ) / (z2_int + this.initial_distance );
        dl.c = color;
        
        return dl;
    }
    
    public int nthButton(int n){
        return c.buttonMarginTop + (n - 1) * c.buttonHeight + c.distanceBetweenButtons * (n - 1);
    }
    
    public void setLines(Line lc){
        this.linesToDraw.sceneLines.add(lc);
    }
}


























//        @Override
//	public void actionPerformed(ActionEvent e) {
//		Object source = e.getSource();
//                if(source == jb && flag == false){
//                    panel.setX(panel.getX() + 10);
////                    panel.setX(1);
//                    panel.repaint();
//                    System.out.println(panel.getX());
//                    if(panel.getX() > 90)
//                        flag = true;
//                }else{
//                    panel.setX(panel.getX() - 10);
////                    panel.setX(1);
//                    panel.repaint();
//                    System.out.println(panel.getX());
//                    if(panel.getX() < 10)
//                        flag = false;
//                }
//	}
