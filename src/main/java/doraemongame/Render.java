/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doraemongame;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author artif
 */
public class Render extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
           
            DoraemonGame.Game.repaint(g);
            DoraemonGame.doraemon.repaint(g);
            DoraemonCake.repaint(g);
            Boom.repaint(g);
            
      
        
           
            
    }

    
   
}
