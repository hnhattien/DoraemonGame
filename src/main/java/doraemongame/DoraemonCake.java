/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doraemongame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author artif
 */
public class DoraemonCake extends Object{
    public static Thread cakeFallingThread;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 71;
    
    public Render render;
    private Rectangle doraemonCakeWrap;
    public DoraemonCake(int x, int y, int width, int height) {
        doraemonCakeWrap = new Rectangle(x,y,width, height);
        
        render = new Render();
    }
    public static int getWidthCake(){
        return WIDTH;
    }
    public static int getHeightCake(){
        return HEIGHT;
    }
     
    public void setPosX(int x){
        this.doraemonCakeWrap.setLocation(x,(int)this.doraemonCakeWrap.getY());
    }
    public Rectangle getDoraemonCakeWrap(){
        return doraemonCakeWrap;
    }
    public void setPosY(int y){
        this.doraemonCakeWrap.setLocation((int)this.doraemonCakeWrap.getX(),y);        
    }
    
    public int getPosX(){
        return (int)doraemonCakeWrap.getX();
    }
    public int getPosY(){
        return (int)doraemonCakeWrap.getY();
    }
    public static void FallingObject(){
        cakeFallingThread = new Thread(new Runnable(){
            public void run(){
                
                while(true){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DoraemonCake.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(!DoraemonGame.isStopGame()){
                        
                        for(int i = 0 ; i < DoraemonGame.getDoraemonCakeList().size(); i++){
                            
                            Rectangle rect = DoraemonGame.getDoraemonCakeList().get(i).doraemonCakeWrap;
                            if(rect.getY()<DoraemonGame.getHeightFrame()){
                                rect.setLocation((int)rect.getX(), (int)rect.getY()+(DoraemonGame.doraemon.getLevel()));
                                
                            }
                            else{

                                rect.setLocation((int)(Math.random()*DoraemonGame.getRealSizeFrame())+50, (int)-(Math.random()*2000));
                            }
                            try {
                                Thread.sleep(2);
                            } catch (InterruptedException ex) {

                            }
                        
                        }
                    }
                    
                }     
            }
            
        });
        
        DoraemonCake.cakeFallingThread.start();
        
    }
    
    public static void repaint(Graphics g){
        
    }
}
