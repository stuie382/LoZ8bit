package com.dag.editor;

import com.dag.ZeldaPlacementListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class ZeldaMakerWindow extends JFrame {

    public final JLabel[] mapTile = new JLabel[44]; //The labels for the selecting buttons
    public final ImageIcon[] placedTImage = new ImageIcon[176]; //The images of the tiles that are placed
    public final JLabel[] placedTile = new JLabel[176]; //The labels for the placed tiles
    public final int[] walkable = new int[176]; //Whether the tile is one that you can step on, enter, etc.
    public final int[] tileNum = new int[176]; //Which image the tile has
    public int selected = 3; //Which tile is selected to place

    private ZeldaMakerWindow() {
        super("Zelda Tile Editor");
        this.setSize(300, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Basics

        BorderLayout border = new BorderLayout();
        JPanel tilePanel = new JPanel(new GridLayout(11, 4));
        JPanel placePanel = new JPanel(new GridLayout(11, 16)); //The grid for playing is 16x11.
        this.setLayout(border);
        this.add(tilePanel, BorderLayout.WEST);
        this.add(placePanel, BorderLayout.CENTER);

        for (int i = 0; i < 44; i++) { //Initializes all of the tile selecting buttons to their respective numbers
            ImageIcon[] tilePic = new ImageIcon[44];
            tilePic[i] = new ImageIcon("ZeldaTiles/" + i + ".gif");
            mapTile[i] = new JLabel(tilePic[i]);
            ZeldaMakerListener selector = new ZeldaMakerListener(this);
            mapTile[i].addMouseListener(selector);
            tilePanel.add(mapTile[i]);
            mapTile[i].setBorder(LineBorder.createGrayLineBorder());
        }

        for (int i = 0; i < 176; i++) { //Initializes all of the placed tiles to their default values, to be edited
            placedTImage[i] = new ImageIcon("ZeldaTiles/" + 3 + ".gif");
            placedTile[i] = new JLabel(placedTImage[i]);
            ZeldaPlacementListener placer = new ZeldaPlacementListener(this);
            placedTile[i].addMouseListener(placer);
            placePanel.add(placedTile[i]);
            walkable[i] = 1;
            tileNum[i] = 3;
            placedTile[i].setBorder(BorderFactory.createLineBorder(Color.green));
        }

        this.pack();
        this.setVisible(true); //Final adjustments

    }

    public static void main(String[] args) {
        ZeldaMakerWindow theZeldaMakerWindow = new ZeldaMakerWindow();
    }
}
