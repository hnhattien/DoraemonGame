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
public class Boom {
    public static Thread boomFallingThread;
    private static int WIDTH = 70;
    private static int HEIGHT = 71;
    public Render render;
    private Rectangle boomWrap;
    public Boom(int x, int y, int width, int height) {
        boomWrap = new Rectangle(x,y,width, height);
        
        render = new Render();
    }
    public Rectangle getBoomWrap(){
        return boomWrap;
    }
    public static int getWidthBoom(){
        return WIDTH;
    }
    public static int getHeightBoom(){
        return HEIGHT;
    }
    public void setPosX(int x){
        this.boomWrap.setLocation(x,(int)this.boomWrap.getY());
    }
    
    public void setPosY(int y){
        this.boomWrap.setLocation((int)this.boomWrap.getX(),y);        
    }
    
    public int getPosX(){
        return (int)boomWrap.getX();
    }
    public int getPosY(){
        return (int)boomWrap.getY();
    }
    
    public static void FallingObject(){
        boomFallingThread = new Thread(new Runnable(){
            public void run(){
                
                while(true){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Boom.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(!DoraemonGame.isStopGame()){
                        for(int i = 0 ; i < DoraemonGame.getBoomList().size(); i++){
                            
                            Rectangle rect = DoraemonGame.getBoomList().get(i).boomWrap;
                            if(rect.getY()<DoraemonGame.getHeightFrame()){
                                rect.setLocation((int)rect.getX(), (int)rect.getY()+DoraemonGame.doraemon.getLevel());
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
        
        boomFallingThread.start();
        
    }
    
    public static void repaint(Graphics g){
        
       
    }
}
