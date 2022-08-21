package doraemongame;





import static doraemongame.DoraemonGame.doraemon;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author artif
 */
public class Doraemon{
    
    private boolean gameOver = false;
    private int scoreCake = 0; 
    private int scoreBoom = 0;
    private static Rectangle doraemonWrap;
    private Render render;
    private boolean jumpup = false;
    private int Level = 1;
    
    private Animation goleftFrame;
    private Animation gorightFrame;
    private boolean EndJump = true;
    private static int rectDoraemonWidth = 100;
    private static int rectDoraemonHeight = 100;
    
    public static final int initialDoraemonX = (int)DoraemonGame.getWidthFrame()/2+200;
    public static final int initialDoraemonY = (int)DoraemonGame.getHeightFrame()/3+190;
    private boolean isGoNewLevel = false;
    public Doraemon(int x, int y, int width, int height){
       super();
        doraemonWrap = new Rectangle(x, y, width, height);
        goleftFrame = new Animation();
        gorightFrame = new Animation();
        goleftFrame.addFrame((new ImageIcon(getClass().getResource("/img/leftdoraemoncharacter1.png"))).getImage());
        goleftFrame.addFrame((new ImageIcon(getClass().getResource("/img/leftdoraemoncharacter2.png"))).getImage());
        goleftFrame.addFrame((new ImageIcon(getClass().getResource("/img/leftdoraemoncharacter3.png"))).getImage());
        goleftFrame.addFrame((new ImageIcon(getClass().getResource("/img/leftdoraemoncharacter4.png"))).getImage());
        goleftFrame.addFrame((new ImageIcon(getClass().getResource("/img/leftdoraemoncharacter5.png"))).getImage());
        goleftFrame.addFrame((new ImageIcon(getClass().getResource("/img/leftdoraemoncharacter6.png"))).getImage());
        gorightFrame.addFrame((new ImageIcon(getClass().getResource("/img/doraemoncharacter1.png"))).getImage());
        gorightFrame.addFrame((new ImageIcon(getClass().getResource("/img/doraemoncharacter2.png"))).getImage());
        gorightFrame.addFrame((new ImageIcon(getClass().getResource("/img/doraemoncharacter3.png"))).getImage());
        gorightFrame.addFrame((new ImageIcon(getClass().getResource("/img/doraemoncharacter4.png"))).getImage());
        gorightFrame.addFrame((new ImageIcon(getClass().getResource("/img/doraemoncharacter5.png"))).getImage());
        gorightFrame.addFrame((new ImageIcon(getClass().getResource("/img/doraemoncharacter6.png"))).getImage());
        
        render = new Render();
        
    }
    public int getPosX(){
        return (int)doraemonWrap.getX();
    }
    public boolean isEndJump(){
        return EndJump;
    }
    public int getPosY(){
        return (int)doraemonWrap.getY();
    }
 
    
    
    public void GoRight(){
        
        
               
               
               
               if(gorightFrame.currentFrameIndex==gorightFrame.frameLength()-1){
                   gorightFrame.currentFrameIndex=0;
               }
        
               new Thread(new Runnable(){
                   public void run(){
                       if(doraemonWrap.getX() < DoraemonGame.getRealSizeFrame()){
                           
                           doraemonWrap.setLocation((int)doraemonWrap.getX()+20,(int)doraemonWrap.getY());
                       }
                   }
               }).start();
               
               DoraemonGame.Game.setDoraemonImage(gorightFrame.getFrame(gorightFrame.currentFrameIndex));
               Doraemon.setRectDoraemonWidth(goleftFrame.getFrame(goleftFrame.currentFrameIndex).getWidth(null));
               Doraemon.setRectDoraemonHeight(goleftFrame.getFrame(goleftFrame.currentFrameIndex).getHeight(null)); 
               gorightFrame.currentFrameIndex++;
               render.repaint();
        
    }
    public void GoLeft(){
           
        new Thread(new Runnable(){
            public void run(){
                if(doraemonWrap.getX()>0){
                    doraemonWrap.setLocation((int)doraemonWrap.getX()-20,(int)doraemonWrap.getY());
                }
                
            }
        }).start();
        DoraemonGame.Game.setDoraemonImage(goleftFrame.getFrame(goleftFrame.currentFrameIndex));
          /*        DoraemonGame.rectDoraemonWidth = goleft.getFrame(goleft.currentFrameIndex).getWidth(null); 
               DoraemonGame.rectDoraemonHeight = goleft.getFrame(goleft.currentFrameIndex).getHeight(null); */
               goleftFrame.currentFrameIndex++;
               render.repaint();
               
        if(goleftFrame.currentFrameIndex==goleftFrame.frameLength()-1){
            goleftFrame.currentFrameIndex=0;
        }
    }
    public void resetScore(){
        doraemon.scoreBoom = 0;
        doraemon.scoreCake = 0;
    }
    public void Jump(){
         
     
    new Thread(new Runnable(){
             public void run(){
                 jumpup = true;
                if(jumpup){
                    doraemon.EndJump = false;
                     for(int i = 0 ; i < 150 ; i++){
                     
                        if(Doraemon.initialDoraemonY - doraemonWrap.getY() > 150 ){
                            break;
                        }
                        else{
                            doraemonWrap.setLocation((int)doraemonWrap.getX(), (int)doraemonWrap.getY()-1);
                        }
                        try{
                            Thread.sleep(2);
                            render.repaint();



                        } catch(InterruptedException e){ }
                     
              
                    }
                     jumpup = false;
                     
                 }
               
                 if(!jumpup){
                     for(int i = (int)doraemonWrap.getY() ; i <= Doraemon.initialDoraemonY ; i++){
                             doraemonWrap.setLocation((int)doraemonWrap.getX(), i);
                             try{
                                 Thread.sleep(1);
                             }catch(InterruptedException e) { }
                     }         
                     doraemon.EndJump = true;
                 }
                 
             }
         }).start();   
    }
    
    void drawAnimate(Graphics g,Image image){
        g.drawImage(image, 0, 0, null);
        render.repaint();   
    }
    public void setLevel(int level){
        this.Level = level;
    }
    public void nextLevel(){
        doraemon.isGoNewLevel = true;
        setLevel(this.Level+1);
        try {
            DoraemonGame.StopGame();
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            
        }
        
        DoraemonGame.cancelStopGame();
        doraemon.isGoNewLevel = false;
        this.scoreCake = 0;
        render.repaint();
    }
    
    public int getLevel(){
        return Level;
    }
    public static void collisionObjectDetection(){
        
        new Thread(new Runnable(){
            public void run(){
                DoraemonCake cake;
                while(true){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                    
                    }
                    if(!doraemon.gameOver){
                        for(int i = 0 ; i < DoraemonGame.getDoraemonCakeList().size(); i++){
                            cake = DoraemonGame.getDoraemonCakeList().get(i);
                            if(doraemonWrap.intersects(cake.getDoraemonCakeWrap())){
                                cake.setPosY((int)(DoraemonGame.getHeightFrame()+10000));
                                doraemon.scoreCake++;
                                if(doraemon.scoreCake == doraemon.getLevel() + 2){
                                    doraemon.nextLevel();
                                    
                                }
                            }
                        }
                        Boom boom;
                        for(int i = 0 ; i < DoraemonGame.getBoomList().size(); i++){
                            boom = DoraemonGame.getBoomList().get(i);
                            
                            if(doraemonWrap.intersects(boom.getBoomWrap())){
                                
                                DoraemonGame.musicPlayer.stopBoomSound();
                                DoraemonGame.musicPlayer.resetPositionBoomSound();
                                DoraemonGame.musicPlayer.playBoomSound();
                                boom.setPosY((int)(Math.random()*10000));
                                doraemon.scoreBoom++;
                                if(doraemon.scoreBoom == DoraemonGame.getMaxScoreBoom()){
                                    doraemon.gameOver = true;                        
                                     if(DoraemonGame.isStopGame() == false){
                                        DoraemonGame.StopGame();
                                    }
                                            try{
                                                Thread.sleep(5000);
                                            } catch(Exception e) {}
                                            DoraemonGame.popUpReplayMenu();
                                            doraemon.gameOver = false;
                                }
                                
                            }
                        }
                    }
                    
                }
            }
        }).start();       
    }
    public static int getRectDoraemonWidth(){
        return rectDoraemonWidth;
    }
    public static int getRectDoraemonHeight(){
        return rectDoraemonHeight;
    }
    public static void setRectDoraemonWidth(int width){
        rectDoraemonWidth = width;
    }

    public static void setRectDoraemonHeight(int height) {
        rectDoraemonHeight = height;
    }
    
    void repaint(Graphics g) {
        if(!DoraemonGame.isMenuScreen()){
                for(int i = 0 ; i < doraemon.scoreCake ; i++){
                    Image image = new ImageIcon(getClass().getResource("/img/iconCake.png")).getImage();
                g.drawImage(image, i*25, 0, null);
            }
            for(int i = doraemon.scoreCake; i < Level+2; i++){                
                Image image = new ImageIcon(getClass().getResource("/img/iconColorlessCake.png")).getImage();
                g.drawImage(image, (i)*25, 0, null);
            }

            for(int i = 0 ; i < doraemon.scoreBoom ; i++){
                    Image image = new ImageIcon(getClass().getResource("/img/iconBoom.png")).getImage();
                g.drawImage(image, i*25, 20, null);
            }
            for(int i = doraemon.scoreBoom; i < 5; i++){                
                Image image = new ImageIcon(getClass().getResource("/img/iconColorlessBoom.png")).getImage();
                g.drawImage(image, (i)*25, 20, null);
            }

            
            g.setColor(Color.white);
            g.setFont(new Font("Arial",1,100));
            if(gameOver){
                g.drawString("Game Over", 200, DoraemonGame.getHeightFrame()/2);    
            }
            
            if(doraemon.isGoNewLevel){
                g.drawString("Level "+Level, 200, DoraemonGame.getHeightFrame()/2);
            }
            
        }
    }
    
}
