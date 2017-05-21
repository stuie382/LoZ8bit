package com.dag;

import com.dag.editor.ZeldaMakerWindow;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ZeldaPlacementListener implements MouseListener {

    /*	Global Variables	*/
    private final ZeldaMakerWindow window;

    public ZeldaPlacementListener(ZeldaMakerWindow theWindow) {
        this.window = theWindow;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
        //	Do Nothing
    }

    public void mouseExited(MouseEvent e) {
        //	Do Nothing
    }

    public void mousePressed(MouseEvent e) {

        for (int i = 0; i < 176; i++) { //Cycles through each tile, checking if it is the one being changed
            if (e.getComponent() == this.window.placedTile[i]) {
                if (e.getButton() == 1) {
                    this.window.placedTImage[i] = new ImageIcon("ZeldaTiles/" + this.window.selected + ".gif");
                    this.window.placedTile[i].setIcon(this.window.placedTImage[i]); //If you left clicked, it will set the tile to the one you have selected
                    this.window.tileNum[i] = this.window.selected;
                } else if (e.getButton() == 3) {
                    if (this.window.walkable[i] == 1) {
                        this.window.walkable[i]++;
                        this.window.placedTile[i].setBorder(BorderFactory.createLineBorder(Color.red)); //If you right click, it will change the tile's number indicating whether you can walk on it to one higher than before and show what kind of tile it is
                    } else if (this.window.walkable[i] == 2) {
                        this.window.walkable[i]++;
                        this.window.placedTile[i].setBorder(BorderFactory.createLineBorder(Color.yellow));
                    } else if (this.window.walkable[i] == 3) {
                        this.window.walkable[i]++;
                        this.window.placedTile[i].setBorder(BorderFactory.createLineBorder(Color.blue));
                    } else if (this.window.walkable[i] == 4) {
                        this.window.walkable[i] = 1;
                        this.window.placedTile[i].setBorder(BorderFactory.createLineBorder(Color.green));
                    }
                }
            }
        }

    }

    public void mouseReleased(MouseEvent e) {

    }
}