package com.dag.map;

import com.dag.ZeldaPlayWindow;
import org.pmw.tinylog.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Map extends JPanel {

    private final BufferedImage[] theTileI = new BufferedImage[176];
    private final BufferedImage[] heartI = new BufferedImage[3];
    private final BufferedImage[] specialScreen = new BufferedImage[3];
    private BufferedImage image;
    private BufferedImage triforce;
    private BufferedImage black;
    public final int[] walkable = new int[176];
    private BufferedReader r = null;
    public final ZeldaPlayWindow window;
    public int x, y;
    private final String[] heart = new String[5];
    private boolean isLoading;

    public Map(ZeldaPlayWindow theWindow) {
        super(new GridLayout(11, 16));
        Logger.trace("Enter Map");
        this.window = theWindow;
        x = 0;
        y = 0;
        mapLoad(x, y);
        for (int i = 0; i < 3; i++) { //Sets each tile individually to its image and whether it is walkable
            try {
                heartI[i] = ImageIO.read(new File("src/main/resources/sprites/heart/" + i + ".gif"));
                specialScreen[i] = ImageIO.read(new File("src/main/resources/sprites/screen/" + i + ".bmp"));
            } catch (IOException e) {
                Logger.error("Exception: " + e);
                System.exit(2);
            }
        }
        try {
            black = ImageIO.read(new File("src/main/resources/sprites/tiles/ZeldaTiles/5.GIF"));
            triforce = ImageIO.read(new File("src/main/resources/sprites/mis/triforce.gif"));
        } catch (IOException e) {
            Logger.error("Problem loading file:" + e);
            System.exit(3);
        }
        (new Thread(new mapRefresher(this))).start();
    }

    public final void setIsLoading(final boolean setFlag) {
        isLoading = setFlag;
    }

    public final boolean isMapLoading() {
        return isLoading;
    }

    public void mapLoad(int mapX, int mapY) {
        isLoading = true;
        int temp;
        try {
            String fileName = "src/main/resources/sprites/maps/" + mapX + " " + mapY + ".txt";
            r = new BufferedReader(new FileReader(fileName)); //Reads from the file specified for the Map
        } catch (IOException e) {
            Logger.error("Problem loading map information for grid - X:" + mapX + ", Y:" + mapY);
            Logger.error("Exception: " + e);
        }

        for (int i = 0; i < 176; i++) { //Sets each tile individually to its image and whether it is walkable
            String fileName = "Not a file";
            try {
                temp = Integer.parseInt(r.readLine());
                fileName = "src/main/resources/sprites/tiles/ZeldaTiles/"
                        + temp + ".GIF";
                theTileI[i] = ImageIO.read(new File(fileName));
                temp = Integer.parseInt(r.readLine());
                walkable[i] = temp;
            } catch (IOException e) {
                Logger.error("File couldn't be loaded: " + fileName + ", exiting");
                Logger.error("Exception: " + e);
                System.exit(1);
            }
        }
        isLoading = false;
        //Starts the thread which refreshes the Map and moves Link's position
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        //	True if nothing has been drawn yet.
        if (image == null) {
            this.image = (BufferedImage) this.createImage(544, 176); //Creates the image of the game Map
            Graphics2D gc = this.image.createGraphics();
            g2.drawImage(this.image, null, 0, 0);
            //This is a new line of code to load our image
        } else {
            g2.drawImage(this.image, null, 0, 0); //Draws the image in the correct position
            this.mapRefresh(g2);
        }
    }

    private void mapRefresh(Graphics2D g2) {
        if (!this.window.thePlayer.win && !this.window.thePlayer.lose && !this.window.intro) {
            if (this.window.thePlayer.linkExit.equals("n")) {
                for (int i = 0; i < 16; i++) { //column
                    for (int j = 0; j < 11; j++) { //row
                        g2.drawImage(theTileI[i + (j * 16)], i * 34, j * 34, null); //Draws each tile in turn
                    }
                }
            } else {
                for (int i = 0; i < 16; i++) { //column
                    for (int j = 0; j < 11; j++) { //row
                        g2.drawImage(black, i * 34, j * 34, null); //Draws each tile in turn
                    }
                }
            }
            if (this.window.thePlayer.visible) {
                if (!this.window.thePlayer.linkSlash) {
                    g2.drawImage(this.window.thePlayer.image, this.window.thePlayer.linkCo[0], this.window.thePlayer.linkCo[1], null); //Draws Link
                } else {
                    if (this.window.thePlayer.image == this.window.thePlayer.linkPic[8]) {
                        g2.drawImage(this.window.thePlayer.image, this.window.thePlayer.linkCo[0], this.window.thePlayer.linkCo[1] - 34, null); //Draws Link
                    } else if (this.window.thePlayer.image == this.window.thePlayer.linkPic[9]) {
                        g2.drawImage(this.window.thePlayer.image, this.window.thePlayer.linkCo[0], this.window.thePlayer.linkCo[1], null); //Draws Link
                    } else if (this.window.thePlayer.image == this.window.thePlayer.linkPic[10]) {
                        g2.drawImage(this.window.thePlayer.image, this.window.thePlayer.linkCo[0], this.window.thePlayer.linkCo[1], null); //Draws Link
                    } else if (this.window.thePlayer.image == this.window.thePlayer.linkPic[11]) {
                        g2.drawImage(this.window.thePlayer.image, this.window.thePlayer.linkCo[0] - 35, this.window.thePlayer.linkCo[1], null); //Draws Link
                    }
                }
            }
            for (int i = 0; i < 10; i++) {
                if (this.window.getTheMonster()[i] != null && this.window.getTheMonster()[i].visible) {
                    g2.drawImage(this.window.getTheMonster()[i].image, this.window.getTheMonster()[i].x, this.window.getTheMonster()[i].y, null);
                }
            }
            for (int i = 0; i < 10; i++) {
                if (this.window.getTheMonster()[i] != null && this.window.getTheMonster()[i].projectile != null && this.window.getTheMonster()[i].projectile.visible) {
                    g2.drawImage(this.window.getTheMonster()[i].projectile.image, this.window.getTheMonster()[i].projectile.x, this.window.getTheMonster()[i].projectile.y, null);
                }
            }
            for (int i = 0; i < 10; i++) {
                if (i < this.window.thePlayer.HP) {
                    if (i % 2 == 0) {
                        heart[i / 2] = "half";
                    } else {
                        heart[(i - 1) / 2] = "full";
                    }
                } else if (i % 2 == 0) {
                    heart[i / 2] = "empty";
                }
            }

            for (int i = 0; i < 5; i++) {
                switch (heart[i]) {
                    case "full":
                        g2.drawImage(heartI[2], (i * 34) + 374, 34, null);
                        break;
                    case "half":
                        g2.drawImage(heartI[1], (i * 34) + 374, 34, null);
                        break;
                    default:
                        g2.drawImage(heartI[0], (i * 34) + 374, 34, null);
                        break;
                }
            }

            if (x == 3 && y == 0 && !this.window.thePlayer.triforceCollected[0]) {
                g2.drawImage(triforce, 170, 238, null);
            } else if (x == 3 && y == 3 && !this.window.thePlayer.triforceCollected[1]) {
                g2.drawImage(triforce, 374, 102, null);
            } else if (x == 0 && y == 3 && !this.window.thePlayer.triforceCollected[2]) {
                g2.drawImage(triforce, 34, 204, null);
            }
        } else if (this.window.intro) {
            g2.drawImage(specialScreen[0], 0, 0, null);
        } else if (this.window.thePlayer.lose) {
            g2.drawImage(specialScreen[1], 0, 0, null);
        } else {
            g2.drawImage(specialScreen[2], 0, 0, null);
        }
        g2.dispose();
    }
}
