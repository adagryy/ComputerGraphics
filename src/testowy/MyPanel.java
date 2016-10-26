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
//                g2d.translate(0, c.panelHeight );
                //g2d.scale(1, -1);
                //g2d.translate((int) c.panelWidth / 2, (int) 0.75 * c.panelHeight);
                if(dll != null)
                {
                    for(int i = 0; i < c.buildings * 12;i++){
                        g2d.setColor(dll.get(i).c);
                        g2d.drawLine((int) dll.get(i).x1, (int)  dll.get(i).y1,
                                     (int)  dll.get(i).x2, (int)  dll.get(i).y2);
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
}
