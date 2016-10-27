/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testowy;

import java.awt.*;
import java.util.ArrayList;
import structures.*;

import javax.swing.JPanel;
/**
 *
 * @author grycz
 */
public class MyPanel extends JPanel{
        public int x,y;
        Configurator c;
        public ArrayList<PolygonsContainer> pc = null;
        
        public ArrayList<DrawLine> dll = null; //ArrayList of lines (line is a set of four numbers - coordinates)
        
    	public MyPanel(Configurator c, int x, int y) {
                setLayout(null);
                setBackground(c.panelColor);
                setBounds(c.panelStartX, c.panelStartY, c.panelWidth, c.panelHeight);
                this.x = x;
                this.y = y;
                this.c = c;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
                g2d.translate(c.panelWidth / 2, c.panelHeight / 2);
                if(pc != null){                    
                    for(int i = 0; i < c.buildings * 6; i++){   
                        g.setColor(pc.get(i).color);
                        g.fillPolygon(pc.get(i).xpts, pc.get(i).ypts, 4);
                        g.setColor(Color.blue);
                        g.drawPolygon(pc.get(i).xpts, pc.get(i).ypts, 4);
                    }
                }
	}
        
        public void setX(int x){
            this.x = x;
        }
        
        public void setY(int y){
            this.y = y;
        }
        
        @Override
        public int getX(){
            return x;
        }
        
        @Override
        public int getY(){
            return y;
        }
        
        public void setDll(ArrayList<DrawLine> arr){
            this.dll = arr;
        }
        
        public void setPols(ArrayList<PolygonsContainer> pc){
            this.pc = pc;
        }
}
