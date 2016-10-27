/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testowy;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
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
    Configurator c;
    Block block1, block2;
    ArrayList<Block> blocks = new ArrayList();
    public boolean flag = false;
    public MyFrame(Configurator c) {
            super("GRAKOM Simulator");
            this.c = c;
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);            
            setPreferredSize(new Dimension(c.windowWidth ,c.windowHeight));
            setLocation(c.windowStartX, c.windowStartY);
            panel = new MyPanel(c, c.panelStartX, c.panelStartY);  
            setFocusable(true);
            
            blocks.add(new Block(c.b1, Color.red));            
            blocks.add(new Block(c.b2, Color.green));
            
            this.initial_distance = c.initial_distance;
            
            add(panel);
            pack();           
            
            jb = new JButton("Wyjście");
//            jb2 = new JButton("Przycisk");    
            
            jb.setBounds(c.buttonX, nthButton(1), c.buttonWidth, c.buttonHeight);
//            jb2.setBounds(c.buttonX, nthButton(2), c.buttonWidth, c.buttonHeight);
            
            jb.addActionListener(this);
//            jb2.addActionListener(this);
            
            addKeyListener(this);
            
            
            label1 = new JLabel("Distance: 1000px");
            label1.setBounds(c.buttonX, nthButton(2), c.buttonWidth, c.buttonHeight);

            add(label1);
            add(jb);
//            add(jb2);
            
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
            performTranslationY(50.0);
        }
        if(c == 'Q' || c == 'q'){
            performTranslationY(-50.0);
        }         
        if(c == 't' || c == 'T'){
            performRotation(true);
        }   
        if(c == 'Y' || c == 'y'){
            performRotation(false);
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
        ArrayList<MyPolygon> polygons = new ArrayList();          
        if(c.initial_distance <= 10 * abs(zoom_factor) && zoom_factor < 0)
            return;
        
        c.initial_distance += zoom_factor;
        
        for(int it = 0; it < c.buildings; it++){
            label1.setText("Distance: " + Integer.toString((int)c.initial_distance ) + "px");
            polygons = setPols(blocks.get(it).color, polygons, blocks.get(it).a, blocks.get(it).bb, blocks.get(it).c, blocks.get(it).d, blocks.get(it).e,
                    blocks.get(it).f, blocks.get(it).g, blocks.get(it).h, c);
        }
        process(polygons);
        
        Collections.sort(polygons);

        panel.setPols(executePolygon_projection(polygons));        
        panel.repaint();
    }
    
    public void performTranslationZ(double translation_factor){
        ArrayList<MyPolygon> polygons = new ArrayList();        
        for(int it = 0; it < c.buildings; it++){
            blocks.get(it).a.z += translation_factor;
            blocks.get(it).bb.z+= translation_factor;
            blocks.get(it).c.z += translation_factor;
            blocks.get(it).d.z += translation_factor;
            blocks.get(it).e.z += translation_factor;
            blocks.get(it).f.z += translation_factor;
            blocks.get(it).g.z += translation_factor;
            blocks.get(it).h.z += translation_factor;
            polygons = setPols(blocks.get(it).color, polygons, blocks.get(it).a, blocks.get(it).bb, blocks.get(it).c, blocks.get(it).d, blocks.get(it).e,
                    blocks.get(it).f, blocks.get(it).g, blocks.get(it).h, c);
        }
        process(polygons);
        
        Collections.sort(polygons);
        panel.setPols(executePolygon_projection(polygons));
        panel.repaint();
    }
    
    public void performTranslationX(double translation_factor){
        ArrayList<MyPolygon> polygons = new ArrayList();
        for(int it = 0; it < c.buildings; it++){
            blocks.get(it).a.x += translation_factor;
            blocks.get(it).bb.x+= translation_factor;
            blocks.get(it).c.x += translation_factor;
            blocks.get(it).d.x += translation_factor;
            blocks.get(it).e.x += translation_factor;
            blocks.get(it).f.x += translation_factor;
            blocks.get(it).g.x += translation_factor;
            blocks.get(it).h.x += translation_factor;
            
            polygons = setPols(blocks.get(it).color, polygons, blocks.get(it).a, blocks.get(it).bb, blocks.get(it).c, blocks.get(it).d, blocks.get(it).e,
                    blocks.get(it).f, blocks.get(it).g, blocks.get(it).h, c);
        }
        
        process(polygons);
        
        Collections.sort(polygons);
        System.out.println("ppppppppppppppppppppppp");
        for( int i = 0; i < 6 * c.buildings; i++){
            System.out.print(polygons.get(i).covers + "[" + (int) polygons.get(i).distance + "], ");
//            System.out.println();
        }
        panel.setPols(executePolygon_projection(polygons));
        panel.repaint();
    }
    
    public void performTranslationY(double translation_factor){
        ArrayList<MyPolygon> polygons = new ArrayList();        
        for(int it = 0; it < c.buildings; it++){
            blocks.get(it).a.y += translation_factor;
            blocks.get(it).bb.y+= translation_factor;
            blocks.get(it).c.y += translation_factor;
            blocks.get(it).d.y += translation_factor;
            blocks.get(it).e.y += translation_factor;
            blocks.get(it).f.y += translation_factor;
            blocks.get(it).g.y += translation_factor;
            blocks.get(it).h.y += translation_factor;

            polygons = setPols(blocks.get(it).color, polygons, blocks.get(it).a, blocks.get(it).bb, blocks.get(it).c, blocks.get(it).d, blocks.get(it).e,
                    blocks.get(it).f, blocks.get(it).g, blocks.get(it).h, c);
        }
        process(polygons);
        
        Collections.sort(polygons);
        
        panel.setPols(executePolygon_projection(polygons));
        panel.repaint();
    }
    
    public void performRotation(boolean flag){
        ArrayList<MyPolygon> polygons = new ArrayList(); 

        for(int it = 0; it < c.buildings; it++){           
            blocks.get(it).a = multiplyMatrices(blocks.get(it).a, flag);
            blocks.get(it).bb= multiplyMatrices(blocks.get(it).bb, flag);
            blocks.get(it).c = multiplyMatrices(blocks.get(it).c, flag);
            blocks.get(it).d = multiplyMatrices(blocks.get(it).d, flag);
            blocks.get(it).e = multiplyMatrices(blocks.get(it).e, flag);
            blocks.get(it).f = multiplyMatrices(blocks.get(it).f, flag);
            blocks.get(it).g = multiplyMatrices(blocks.get(it).g, flag);
            blocks.get(it).h = multiplyMatrices(blocks.get(it).h, flag);
            
            polygons = setPols( blocks.get(it).color, polygons, blocks.get(it).a, blocks.get(it).bb, blocks.get(it).c, blocks.get(it).d, blocks.get(it).e,
                    blocks.get(it).f, blocks.get(it).g, blocks.get(it).h, c);
        }
        process(polygons);
        
        Collections.sort(polygons);
        
        panel.setPols(executePolygon_projection(polygons));
        panel.repaint();
    }
    
    public Point3D multiplyMatrices(Point3D point, boolean flag){
        double a, b, cc;
        double angleMatrix[][] = new double[4][4];
        if(flag == true)
            angleMatrix = c.angleMatrixforward;
        else
            angleMatrix = c.angleMatrixreverse;
        a = point.x * angleMatrix[0][0] + angleMatrix[0][1] * point.y + angleMatrix[0][2] * point.z;
        b = point.x * angleMatrix[1][0] + angleMatrix[1][1] * point.y + angleMatrix[1][2] * point.z;
        cc= point.x * angleMatrix[2][0] + angleMatrix[2][1] * point.y + angleMatrix[2][2] * point.z;
        return new Point3D(a, b, cc);
    }
   
    public int nthButton(int n){
        return c.buttonMarginTop + (n - 1) * c.buttonHeight + c.distanceBetweenButtons * (n - 1);
    }
    
    public ArrayList<MyPolygon> process(ArrayList<MyPolygon> myPolygon){//sprawdza, 
        ArrayList<MyPolygon> mp = new ArrayList<MyPolygon>();
        
        for(int i = 0; i < myPolygon.size(); i++){
            MyPolygon temp = myPolygon.get(i);//biorę wielokąt z listy
            double A,B,C,D;
            Point3D p1 = temp.p1;//obieram na tym wielokącie 3 punkty (najłatwiej wziąć jego wierzchołki)
            Point3D ref1 = temp.p2.substract(p1);
            Point3D ref2 = temp.p3.substract(p1);
            
            A = ref1.y * ref2.z - ref1.z * ref2.y; // wyznaczam współczynniki równania ogólnego płaszczyzny przechodzącej przez te punkty
            B = ref1.z * ref2.x - ref1.x * ref2.z;
            C = ref1.x * ref2.y - ref1.y * ref2.x;
            
            D = -1 * (A * p1.x + B * p1.y + C * p1.z);
            for(int x = 0 ; x < myPolygon.size(); x++){                
                if(x != i){
                    MyPolygon checked = myPolygon.get(x);
                        //liczę odległość punktów od płaszczyzny
                        double point_distance1 = (A * checked.p1.x + B * checked.p1.y + C * checked.p1.z + D ) / (Math.sqrt(A * A + B * B + C * C));
                        double point_distance2 = (A * checked.p2.x + B * checked.p2.y + C * checked.p2.z + D ) / (Math.sqrt(A * A + B * B + C * C));
                        double point_distance3 = (A * checked.p3.x + B * checked.p3.y + C * checked.p3.z + D ) / (Math.sqrt(A * A + B * B + C * C));
                        double point_distance4 = (A * checked.p4.x + B * checked.p4.y + C * checked.p4.z + D ) / (Math.sqrt(A * A + B * B + C * C));
                        double observer_distance = ( D ) / (Math.sqrt(A * A + B * B + C * C));//obserwator w pkt (0,0,0)
                        
                        double point_distance;
                        
                        if(checkValues(point_distance1, point_distance2, point_distance3, point_distance4)){//jeśli cały wielokąt leży po tej samej stronie płaszczyzny, to...
                            if(point_distance1 * observer_distance > 0){ 
                                temp.covers++;//punkty po tej samej stronie
                                break;
                            }
                        }else{
                            Point3D pp2 = new Point3D(((checked.p1.x + checked.p3.x) / 2), ((checked.p1.y + checked.p3.y) / 2), ((checked.p1.z + checked.p3.z) / 2));

                            point_distance = (A * pp2.x + B * pp2.y + C * pp2.z + D ) / (Math.sqrt(A * A + B * B + C * C));
                            if(point_distance * observer_distance > 0){ 
                                temp.covers++;//punkty po tej samej stronie
                                break;
                            }
                        }
                }
            }
            temp.distance = temp.massCenter();
            mp.add(temp);
        }     
        return mp;
    }
    
    public ArrayList<PolygonsContainer> executePolygon_projection(ArrayList<MyPolygon> mp){//robi rzutowanie punktu 3D na płaszczyznę 2D
        ArrayList<PolygonsContainer> pc = new ArrayList();
        
        for(int i = 0; i < mp.size(); i++){
            PolygonsContainer polContainer = new PolygonsContainer();
            polContainer.xpts[0] = (int) ((mp.get(i).p1.x * c.initial_distance ) / ( mp.get(i).p1.z ));
            polContainer.ypts[0] = (int) ((mp.get(i).p1.y * c.initial_distance ) / ( mp.get(i).p1.z ));
            
            polContainer.xpts[1] = (int) ((mp.get(i).p2.x * c.initial_distance ) / ( mp.get(i).p2.z ));
            polContainer.ypts[1] = (int) ((mp.get(i).p2.y * c.initial_distance ) / ( mp.get(i).p2.z ));
            
            polContainer.xpts[2] = (int) ((mp.get(i).p3.x * c.initial_distance ) / ( mp.get(i).p3.z ));
            polContainer.ypts[2] = (int) ((mp.get(i).p3.y * c.initial_distance ) / ( mp.get(i).p3.z ));

            polContainer.xpts[3] = (int) ((mp.get(i).p4.x * c.initial_distance ) / ( mp.get(i).p4.z ));
            polContainer.ypts[3] = (int) ((mp.get(i).p4.y * c.initial_distance ) / ( mp.get(i).p4.z ));  
            
            polContainer.color = mp.get(i).color;
            
            pc.add(polContainer);
        }
        
        return pc;
    }
    
    public ArrayList<MyPolygon> setPols(Color color, ArrayList<MyPolygon> polygons, Point3D a, Point3D bb, Point3D c, Point3D d, Point3D e, Point3D f, Point3D g, Point3D h, Configurator config){
        polygons.add(new MyPolygon(a, bb, c, d, config, color));
        polygons.add(new MyPolygon(a, bb, f, e, config, color));
        polygons.add(new MyPolygon(bb, c, g, f, config, color));
        polygons.add(new MyPolygon(c, d, h, g, config, color));
        polygons.add(new MyPolygon(a, d, h, e, config, color));
        polygons.add(new MyPolygon(e, f, g, h, config, color));
        
        return polygons;
    }
    
    
    public void wypisz(Block block){
//        System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTT");
//           System.out.println(block.a.x + ", " + block.a.y + ", " + block.a.z);
//           System.out.println(block.bb.x + ", " + block.bb.y + ", " + block.bb.z);
//           System.out.println(block.c.x + ", " + block.c.y + ", " + block.c.z);
//           System.out.println(block.d.x + ", " + block.d.y + ", " + block.d.z);
//           System.out.println(block.e.x + ", " + block.e.y + ", " + block.e.z);
//           System.out.println(block.f.x + ", " + block.f.y + ", " + block.f.z);
//           System.out.println(block.g.x + ", " + block.g.y + ", " + block.g.z);
//           System.out.println(block.h.x + ", " + block.h.y + ", " + block.h.z);
//           System.out.println("======================");
    }
    
    public boolean checkValues(double d1, double d2, double d3, double d4){
        return (d1 > 0 && d2 > 0 && d3 > 0 && d4 > 0) || (d1 < 0 && d2 < 0 && d3 < 0 && d4 < 0);
    }
}




//    public DrawLine execute_projection(double x1, double y1, double x2, double y2, double z1, double z2, Color color)
//    {
//        int x1_int = (int) x1, x2_int = (int) x2, 
//            y2_int = (int) y2, y1_int = (int) y1, 
//            z1_int = (int) z1, z2_int = (int) z2;
//        DrawLine dl = new DrawLine();      
//        dl.x1 = (x1_int * c.initial_distance ) / ( z1_int );
//        dl.y1 = (y1_int * c.initial_distance ) / ( z1_int );
//        dl.x2 = (x2_int * c.initial_distance ) / ( z2_int );
//        dl.y2 = (y2_int * c.initial_distance ) / ( z2_int );
//
//        dl.c = color;
//        
//        return dl;
//    }


//                    for(int j = 0; j < 4; j++){
//                        //liczę odległość punktów od płaszczyzny
//                        double point_distance = (A * checked.getPoint(j).x + B * checked.getPoint(j).y + C * checked.getPoint(j).z + D ) / (Math.sqrt(A * A + B * B + C * C));
//                        double observer_distance = ( D ) / (Math.sqrt(A * A + B * B + C * C));//obserwator w pkt (0,0,0)
//
//                        if(point_distance * observer_distance > 0){ 
//                            temp.covers++;//punkty po tej samej stronie
//                            break;
//                        }
//                    }