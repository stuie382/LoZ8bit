package com.dag.editor;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class ZeldaMakerListener implements MouseListener {

    /*	Global Variables	*/
    private final ZeldaMakerWindow window; //The attatched window
    private BufferedWriter w = null; //The writer
    private BufferedReader r = null; //The reader

    public ZeldaMakerListener(ZeldaMakerWindow theWindow) {
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

        doStuff(e);

    }

    public void mouseReleased(MouseEvent e) {

    }

    private void doStuff(MouseEvent e) /*throws IOException*/ {
        for (int i = 0; i < 44; i++) {
            if (e.getComponent() == this.window.mapTile[i]) { //Cycles through all the tiles, checking if this is the one that was clicked on
                if (i < 42) { //If the tile selected is one of the first 41, which are the placeable tiles, it displays which tile is selected and selects it
                    this.window.mapTile[this.window.selected].setBorder(LineBorder.createGrayLineBorder());
                    this.window.mapTile[i].setBorder(BorderFactory.createLineBorder(Color.red));
                    this.window.selected = i;
                } else if (i == 42) { //If it is the saving button...

                    try {
                        w = new BufferedWriter(new FileWriter("New Map.txt")); //Gets ready to write to the file "New Map"
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    for (int j = 0; j < 176; j++) {

                        try {
                            w.write(Integer.toString(this.window.tileNum[j]));
                            w.newLine();
                            w.write(Integer.toString(this.window.walkable[j])); //Writes the info for each tile on the grid, saying which tile it is and whether you can walk on it
                            w.newLine();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    }

                    try {
                        for (int k = 0; k < 10; k++) { //Makes a default for monsters
                            w.write("none");
                            w.newLine();
                        }
                        if (w != null) {
                            w.flush();
                            w.close(); //Finishes the saving
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                } else if (i == 43) { //If the isLoading button is selected...
                    try {
                        r = new BufferedReader(new FileReader("Loading Data.txt")); //Opens the reader
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    int temp = 3;
                    for (int j = 0; j < 176; j++) { //For each tile in turn...

                        try {
                            temp = Integer.parseInt(r.readLine()); //Gets the tile's first number...
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        this.window.placedTImage[j] = new ImageIcon("ZeldaTiles/" + temp + ".gif"); //Uses this first number to place the tile and set its icon
                        this.window.tileNum[j] = temp;
                        this.window.placedTile[j].setIcon(this.window.placedTImage[j]);

                        try {
                            temp = Integer.parseInt(r.readLine()); //Gets the tile's second number
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        this.window.walkable[j] = temp;

                        if (temp == 1) {
                            this.window.placedTile[j].setBorder(BorderFactory.createLineBorder(Color.green)); //Uses this number to set and display whether you can walk on the tile
                        } else if (temp == 2) {
                            this.window.placedTile[j].setBorder(BorderFactory.createLineBorder(Color.red));
                        } else if (temp == 3) {
                            this.window.placedTile[j].setBorder(BorderFactory.createLineBorder(Color.yellow));
                        } else {
                            this.window.placedTile[j].setBorder(BorderFactory.createLineBorder(Color.blue));
                        }

                    }
                }
            }
        }
    }
}