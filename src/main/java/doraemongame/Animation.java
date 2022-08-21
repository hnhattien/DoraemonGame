/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doraemongame;

import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author artif
 */
public class Animation {
    private ArrayList frames;
    public int currentFrameIndex = 0;
    private long animateTime;
    private long totalDuration;
    
    public Animation(){
        frames = new ArrayList();
        totalDuration = 0;
        start();
    }
    
    public synchronized void addFrame(Image image){
        frames.add(image);
    }
    
    public synchronized void start(){
        animateTime = 0;
        currentFrameIndex = 0;
    }
    
    public int frameLength(){
        return frames.size();
    }
    
    
    public Image getFrame(int i){
        return (Image)(this.frames.get(i));
    }
    
   
    
}
