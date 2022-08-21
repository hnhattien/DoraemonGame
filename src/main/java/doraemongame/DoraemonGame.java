/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doraemongame;

import java.awt.Cursor;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;



import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author artif
 */
public class DoraemonGame implements ActionListener, MouseListener, KeyListener{

    /**
     * @param args the command line arguments
     */
    
    
    
    private static boolean STOP_GAME = true;
    private static boolean MENU_SCREEN = true;
    private static final int MAX_SCORE_BOOM = 5;
    
    public static boolean isPlayedSound = true;
    public static JButton startBtn;  
    public static JButton audioBtn;
    public static JLabel MenuBackgroundLabel;
    public static SoundEffect musicPlayer;
    private static int RealSizeFrame = 800;
    
    public static DoraemonGame Game;
    public static Doraemon doraemon;
    private static final int WIDTH = 900;
    private static final int HEIGHT = 500;
    public Render render;
    public Image doraemonIMG;
    
    public Image backgroundImage;
    public static JFrame frame;
    public Image doraemoncakeImg;
    public Image boomImg;
    private static ArrayList<DoraemonCake> doraemonCakeList;
    private static ArrayList<Boom> boomList;
    public DoraemonGame() {
        
        frame = new JFrame();
        Timer timer = new Timer(20,this);
        render = new Render();
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        doraemonIMG = new ImageIcon(getClass().getResource("/img/leftdoraemoncharacter1.png")).getImage();
        backgroundImage = new ImageIcon(getClass().getResource("/img/theme1.jpg")).getImage();
        doraemoncakeImg = new ImageIcon(getClass().getResource("/img/DoraemonCake.png")).getImage();       
        boomImg = new ImageIcon(getClass().getResource("/img/boom.png")).getImage();        
        doraemon = new Doraemon(Doraemon.initialDoraemonX,Doraemon.initialDoraemonY,Doraemon.getRectDoraemonWidth(),Doraemon.getRectDoraemonHeight());
        
        doraemonCakeList = new ArrayList<DoraemonCake>();
        boomList = new ArrayList<Boom>();
        frame.setResizable(false);
        frame.setTitle("Doraemon Game - Hoàng Nhật Tiến - 0396 - CS311D1");
        doraemonCakeList.add(new DoraemonCake(50, -10,DoraemonCake.getWidthCake(),DoraemonCake.getHeightCake()));
        doraemonCakeList.add(new DoraemonCake(200, -30,DoraemonCake.getWidthCake(),DoraemonCake.getHeightCake()));
        doraemonCakeList.add(new DoraemonCake(350, -10,DoraemonCake.getWidthCake(), DoraemonCake.getHeightCake()));
        doraemonCakeList.add(new DoraemonCake(500, -50,DoraemonCake.getWidthCake(), DoraemonCake.getHeightCake()));
        doraemonCakeList.add(new DoraemonCake(650, -10,DoraemonCake.getWidthCake(), DoraemonCake.getHeightCake()));
        doraemonCakeList.add(new DoraemonCake(70, -10,DoraemonCake.getWidthCake(), DoraemonCake.getHeightCake()));
        doraemonCakeList.add(new DoraemonCake(120, -10,DoraemonCake.getWidthCake(), DoraemonCake.getHeightCake()));
        
        boomList.add(new Boom(50,DoraemonGame.HEIGHT+100,Boom.getWidthBoom(), Boom.getHeightBoom()));
        boomList.add(new Boom(400,DoraemonGame.HEIGHT+200,Boom.getWidthBoom(), Boom.getHeightBoom()));
        boomList.add(new Boom(200,DoraemonGame.HEIGHT+300,Boom.getWidthBoom(), Boom.getHeightBoom()));
        boomList.add(new Boom(650,DoraemonGame.HEIGHT+500,Boom.getWidthBoom(), Boom.getHeightBoom()));
       DoraemonCake.FallingObject();
       Boom.FallingObject();
        
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        Doraemon.collisionObjectDetection();
        
       timer.start();
        
       try{
            this.musicPlayer = new SoundEffect();
        }
        catch(Exception e){ }
     
    }
    
    public static boolean isMenuScreen(){
        return MENU_SCREEN;
    }
    public static int getRealSizeFrame(){
        return RealSizeFrame;
    }
    public static boolean isStopGame(){
        return STOP_GAME;
    }
    public static void StopGame(){
        DoraemonGame.STOP_GAME = true;
        
    }
    
    public static int getMaxScoreBoom(){
        return MAX_SCORE_BOOM;
    }
    public void setDoraemonImage(Image image){
        
        this.doraemonIMG = image;
    }
    public static void cancelStopGame(){
         DoraemonGame.STOP_GAME = false;
    }
    public static ArrayList<DoraemonCake> getDoraemonCakeList(){
        return doraemonCakeList;
    }
    public static int getWidthFrame(){
        return WIDTH;
    }
    public static int getHeightFrame(){
        return HEIGHT;
    }
    public static ArrayList<Boom> getBoomList(){
        return boomList;
    }
    
    public void resetGame(){
        
        doraemon.resetScore();
        doraemonCakeList.clear();
        boomList.clear();
        doraemon.setLevel(1);
        doraemonCakeList.add(new DoraemonCake(50, -10,DoraemonCake.getWidthCake(),DoraemonCake.getHeightCake()));
        doraemonCakeList.add(new DoraemonCake(200, -30,DoraemonCake.getWidthCake(),DoraemonCake.getHeightCake()));
        doraemonCakeList.add(new DoraemonCake(350, -10,DoraemonCake.getWidthCake(), DoraemonCake.getHeightCake()));
        doraemonCakeList.add(new DoraemonCake(500, -50,DoraemonCake.getWidthCake(), DoraemonCake.getHeightCake()));
        doraemonCakeList.add(new DoraemonCake(650, -10,DoraemonCake.getWidthCake(), DoraemonCake.getHeightCake()));
        doraemonCakeList.add(new DoraemonCake(70, -10,DoraemonCake.getWidthCake(), DoraemonCake.getHeightCake()));
        doraemonCakeList.add(new DoraemonCake(120, -10,DoraemonCake.getWidthCake(), DoraemonCake.getHeightCake()));
        
        boomList.add(new Boom(50,DoraemonGame.HEIGHT+100,Boom.getWidthBoom(), Boom.getHeightBoom()));
        boomList.add(new Boom(400,DoraemonGame.HEIGHT+200,Boom.getWidthBoom(), Boom.getHeightBoom()));
        boomList.add(new Boom(200,DoraemonGame.HEIGHT+300,Boom.getWidthBoom(), Boom.getHeightBoom()));
        boomList.add(new Boom(650,DoraemonGame.HEIGHT+500,Boom.getWidthBoom(), Boom.getHeightBoom()));
        
    }
    public void actionPerformed(ActionEvent ev){
        
        
        render.repaint();
    }
   
    
    public static void popUpReplayMenu(){
        Game.resetGame();
           
        MENU_SCREEN = true;
        
        frame.setAlwaysOnTop(true);
        frame.getContentPane().removeAll();
        Game.setMenuScreen();
        
        
    }
    
    public void setMenuScreen(){
        
        startBtn = new JButton();
        audioBtn = new JButton();
        
       
       
       
        startBtn.setLocation(400, 200);
        ImageIcon turnOnIcon = new ImageIcon(getClass().getResource("/img/turn-on.png"));
        ImageIcon turnOffIcon = new ImageIcon(getClass().getResource("/img/turn-off.png"));
        
        startBtn.setSize(100,40);
        
        MenuBackgroundLabel = new JLabel();
        frame.setAlwaysOnTop(true);
        MenuBackgroundLabel.setIcon(new ImageIcon(getClass().getResource("/img/theme5.jpg")));
        MenuBackgroundLabel.setSize(1000, 1000);
        MenuBackgroundLabel.setVisible(true);
        startBtn.setText("Start");     
        startBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        audioBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        audioBtn.setIcon(turnOnIcon);
        
        audioBtn.setLocation(10, 200);
        audioBtn.setSize(24,24);
        audioBtn.setOpaque(false);
        audioBtn.setBorder(null);
        audioBtn.setContentAreaFilled(false);

        audioBtn.setVisible(true);
        frame.add(startBtn);
        frame.add(audioBtn);
        
        frame.add(MenuBackgroundLabel);
        
        frame.setVisible(true);
         
        startBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                
                MENU_SCREEN = false;
                startBtn.setVisible(false);
                MenuBackgroundLabel.setVisible(false);
                cancelStopGame();  
                frame.add(render);
                render.repaint();
                
                
            }
        });
        
        
        audioBtn.addActionListener(new ActionListener(){
        
            
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(isPlayedSound == true){
                    audioBtn.setIcon(turnOffIcon);
                    musicPlayer.stopAllSound();
                    isPlayedSound = false;
                }
                else{
                    audioBtn.setIcon(turnOnIcon); 
                    musicPlayer.playBackgroundMusic();
                    isPlayedSound = true;
                }
                
                
            
            }
        });
        
        
        
    }
    void repaint(Graphics g) {
       
        if(!MENU_SCREEN){
            g.drawImage(backgroundImage, 0, 0, null);
            g.drawImage(doraemonIMG, doraemon.getPosX(), doraemon.getPosY(), null);

            for(int i = 0 ; i < doraemonCakeList.size() ; i++){
                g.drawImage(doraemoncakeImg,doraemonCakeList.get(i).getPosX(),doraemonCakeList.get(i).getPosY(),null);
            }
            for(int i = 0 ; i < boomList.size() ; i++){
                g.drawImage(boomImg,boomList.get(i).getPosX(),boomList.get(i).getPosY(),null);
            }
        }
    }
    
    public static void main(String[] args) {
        
        Game = new DoraemonGame();
        Game.setMenuScreen();
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
     
    }

    @Override
    public void mouseEntered(MouseEvent e) {
            
    }

    @Override
    public void mouseExited(MouseEvent e) {
     
    }

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(doraemon.isEndJump()){
            if(e.getKeyCode() == 32){
                doraemon.Jump();        
            }
            else if(e.getKeyCode() == KeyEvent.VK_UP){
                doraemon.Jump();
            }
        }
        
        if(e.getKeyCode() == 39){
            
            doraemon.GoRight();
        }
        if(e.getKeyCode() == 37){
           
            doraemon.GoLeft();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    
    }

   
   


    
}
