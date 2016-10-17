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
    Configurator c;
    Block block1;
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
            
            block1 = new Block(c.b1);            
            
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
        ArrayList<Line> lines = block1.lines();
        
        if(block1.distance <= 10 * abs(zoom_factor) && zoom_factor < 0)
            return;
        block1.distance += zoom_factor;

        label1.setText("Distance: " + Integer.toString((int)block1.distance) + "px");
        for(int i = 0; i < 12;i++){
            Point3D p1 = lines.get(i).getP1();
            Point3D p2 = lines.get(i).getP2();
            Color color = lines.get(i).getColor();

            drawLineList.add(execute_projection(p1.x, p1.y, p2.x, p2.y, p1.z, p2.z, color));                
        }
        panel.setDll(drawLineList);
        panel.repaint();
    }
    
    public void performTranslationZ(double translation_factor){
        block1.a.z += translation_factor;
        block1.bb.z+= translation_factor;
        block1.c.z += translation_factor;
        block1.d.z += translation_factor;
        block1.e.z += translation_factor;
        block1.f.z += translation_factor;
        block1.g.z += translation_factor;
        block1.h.z += translation_factor;
        System.out.println("Ok!");
        ArrayList<DrawLine> drawLineList = new ArrayList(); //lines to be drawn on JPanel
        ArrayList<Line> lines = block1.lines();
        
        for(int i = 0; i < 12;i++){
            Point3D p1 = lines.get(i).getP1();
            Point3D p2 = lines.get(i).getP2();
            Color color = lines.get(i).getColor();

            drawLineList.add(execute_projection(p1.x, p1.y, p2.x, p2.y, p1.z, p2.z, color));                
        }
        panel.setDll(drawLineList);
        panel.repaint();
    }
    
    public void performTranslationX(double translation_factor){
        block1.a.x += translation_factor;
        block1.bb.x+= translation_factor;
        block1.c.x += translation_factor;
        block1.d.x += translation_factor;
        block1.e.x += translation_factor;
        block1.f.x += translation_factor;
        block1.g.x += translation_factor;
        block1.h.x += translation_factor;
        System.out.println("Ok!");
        ArrayList<DrawLine> drawLineList = new ArrayList(); //lines to be drawn on JPanel
        ArrayList<Line> lines = block1.lines();
        
        for(int i = 0; i < 12;i++){
            Point3D p1 = lines.get(i).getP1();
            Point3D p2 = lines.get(i).getP2();
            Color color = lines.get(i).getColor();

            drawLineList.add(execute_projection(p1.x, p1.y, p2.x, p2.y, p1.z, p2.z, color));                
        }
        panel.setDll(drawLineList);
        panel.repaint();
    }
    
    public void performTranslationY(double translation_factor){
        block1.a.y += translation_factor;
        block1.bb.y+= translation_factor;
        block1.c.y += translation_factor;
        block1.d.y += translation_factor;
        block1.e.y += translation_factor;
        block1.f.y += translation_factor;
        block1.g.y += translation_factor;
        block1.h.y += translation_factor;
        System.out.println("Ok!");
        ArrayList<DrawLine> drawLineList = new ArrayList(); //lines to be drawn on JPanel
        ArrayList<Line> lines = block1.lines();
        
        for(int i = 0; i < 12;i++){
            Point3D p1 = lines.get(i).getP1();
            Point3D p2 = lines.get(i).getP2();
            Color color = lines.get(i).getColor();

            drawLineList.add(execute_projection(p1.x, p1.y, p2.x, p2.y, p1.z, p2.z, color));                
        }
        panel.setDll(drawLineList);
        panel.repaint();
    }
    public DrawLine execute_projection(double x1, double y1, double x2, double y2, double z1, double z2, Color color)
    {
        int x1_int = (int) x1, x2_int = (int) x2, 
            y2_int = (int) y2, y1_int = (int) y1, 
            z1_int = (int) z1, z2_int = (int) z2;
        DrawLine dl = new DrawLine();      
        dl.x1 = (x1_int * block1.distance) / (z1_int + block1.distance);
        dl.y1 = (y1_int * block1.distance) / (z1_int + block1.distance);
        dl.x2 = (x2_int * block1.distance) / (z2_int + block1.distance);
        dl.y2 = (y2_int * block1.distance) / (z2_int + block1.distance);
        dl.c = color;
        
        return dl;
    }
    
    
    public int nthButton(int n){
        return c.buttonMarginTop + (n - 1) * c.buttonHeight + c.distanceBetweenButtons * (n - 1);
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
