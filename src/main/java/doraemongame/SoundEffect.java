/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doraemongame;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author artif
 */
public class SoundEffect {
   private AudioInputStream  backgroundMusic;
   private AudioInputStream boomSound;
   private Clip clipBoomSound;
   private Clip clipBackgroundSound;
    public SoundEffect() throws LineUnavailableException, IOException {
       try {
           backgroundMusic = AudioSystem.getAudioInputStream(getClass().getResource("/sound/bg.wav"));
           boomSound = AudioSystem.getAudioInputStream(getClass().getResource("/sound/boomget.wav"));
       } catch (UnsupportedAudioFileException ex) {
           Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
       }
        clipBackgroundSound = AudioSystem.getClip();
        clipBoomSound = AudioSystem.getClip();
        clipBoomSound.open(boomSound);
        clipBackgroundSound.open(backgroundMusic);
        clipBackgroundSound.loop(Clip.LOOP_CONTINUOUSLY);
        
    }
    
    public void playBackgroundMusic(){
        clipBackgroundSound.start();             
    }
    public void stopAllSound(){
        clipBackgroundSound.stop();
        clipBoomSound.stop();
    }
    
    public synchronized void playBoomSound(){                   
                clipBoomSound.start();   
    }
    
    public void stopBoomSound(){
        clipBoomSound.stop();
    }
    public void resetPositionBoomSound(){
        clipBoomSound.setFramePosition(0);
    }
}
