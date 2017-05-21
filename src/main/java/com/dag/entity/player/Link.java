package com.dag.entity.player;

import com.dag.BoundCheck;
import com.dag.BoundCheckHit;
import com.dag.InvincibleActionListener;
import com.dag.InvincibleFlash;
import com.dag.entity.player.model.LinkActionListenerDown;
import com.dag.entity.player.model.LinkActionListenerDownR;
import com.dag.entity.player.model.LinkActionListenerLeft;
import com.dag.entity.player.model.LinkActionListenerLeftR;
import com.dag.entity.player.model.LinkActionListenerRight;
import com.dag.entity.player.model.LinkActionListenerRightR;
import com.dag.entity.player.model.LinkActionListenerUp;
import com.dag.entity.player.model.LinkActionListenerUpR;
import com.dag.entity.player.model.LinkActionListenerX;
import com.dag.entity.player.model.LinkActionListenerZ;
import com.dag.entity.player.model.LinkActionListenerZR;
import com.dag.entity.player.model.LinkChange;
import com.dag.entity.player.model.LinkDie;
import com.dag.entity.player.model.LinkEnterer;
import com.dag.entity.player.model.LinkExiter;
import com.dag.entity.player.model.LinkSlice;
import com.dag.entity.player.model.LinknockBack;
import com.dag.ZeldaPlayWindow;
import com.dag.checkWin;
import com.dag.getTri;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Link extends JPanel {

    public BufferedImage image; //The image that will be drawn of Link
    public final BufferedImage[] linkPic = new BufferedImage[12]; //Our saved images of Link
    public final int[] linkCo = new int[2]; //0 is link's horizontal coordinates, 1 is his veritcal coordinates
    public int HP = 10;
    public boolean linkWalk = true;
    public boolean linkSlash = false;
    public boolean canSlash = true;
    public boolean hurt = false;
    public boolean invincible = true;
    public boolean visible = true;
    // --Commented out by Inspection (21/05/2017 08:09):public boolean flash = false;
    public boolean win = false;
    public boolean lose = false;
    public final boolean[] triforceCollected = new boolean[3];
    public final ZeldaPlayWindow window;
    public String direction = "n"; //The direction link is WALKING (not facing)
    public String sDirection = "u"; //The direction link is facing (but not necessarily walking)
    public String linkExit = "n";
    public final Timer timer;

    private static final int characterSizeX = 300;
    private static final int characterSizeY = 200;

    public Link(ZeldaPlayWindow theWindow) {
        super();
        this.window = theWindow;
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(Color.WHITE); //Initializes some stuff
        linkCo[0] = characterSizeX;
        linkCo[1] = characterSizeY;
        triforceCollected[0] = false;
        triforceCollected[1] = false;
        triforceCollected[2] = false;

        for (int i = 0; i < 12; i++) {
            try {
                this.linkPic[i] = ImageIO.read(new File("src/main/resources/sprites/link/" + i + ".gif")); //Saves each of Link's pictures for easy access
            } catch (IOException e) {
            }
        }
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");                  //-|
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");             //|
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");               //|
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");               //|
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "ReleasedU");   //|
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), "ReleasedR");//|
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "ReleasedD"); //|
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), "ReleasedL"); //|Binds the keys to the actions Link can take
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("x"), "X");
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0), "Z");
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, true), "ZR");
        //|
        LinkActionListenerUp theLinkActionListenerUp = new LinkActionListenerUp(this);
        this.getActionMap().put("up", theLinkActionListenerUp);                                                //|
        LinkActionListenerRight theLinkActionListenerRight = new LinkActionListenerRight(this);
        this.getActionMap().put("right", theLinkActionListenerRight);                                       //|
        LinkActionListenerDown theLinkActionListenerDown = new LinkActionListenerDown(this);
        this.getActionMap().put("down", theLinkActionListenerDown);                                         //|
        LinkActionListenerLeft theLinkActionListenerLeft = new LinkActionListenerLeft(this);
        this.getActionMap().put("left", theLinkActionListenerLeft);                                         //|
        LinkActionListenerUpR theLinkActionListenerUpR = new LinkActionListenerUpR(this);
        this.getActionMap().put("ReleasedU", theLinkActionListenerUpR);                                    //|
        LinkActionListenerRightR theLinkActionListenerRightR = new LinkActionListenerRightR(this);
        this.getActionMap().put("ReleasedR", theLinkActionListenerRightR);                                //|
        LinkActionListenerLeftR theLinkActionListenerLeftR = new LinkActionListenerLeftR(this);
        this.getActionMap().put("ReleasedL", theLinkActionListenerLeftR);                                    //|
        LinkActionListenerDownR theLinkActionListenerDownR = new LinkActionListenerDownR(this);
        this.getActionMap().put("ReleasedD", theLinkActionListenerDownR);                                   //-|
        LinkActionListenerZ theLinkActionListenerZ = new LinkActionListenerZ(this);
        this.getActionMap().put("Z", theLinkActionListenerZ);
        LinkActionListenerZR theLinkActionListenerZR = new LinkActionListenerZR(this);
        this.getActionMap().put("ZR", theLinkActionListenerZR);
        LinkActionListenerX theLinkActionListenerX = new LinkActionListenerX(this);
        this.getActionMap().put("X", theLinkActionListenerX);

        this.image = this.linkPic[0]; //Starts Link's image out at standing upwards

        InvincibleActionListener theInvincibleActionListener = new InvincibleActionListener(this);
        timer = new Timer(500, theInvincibleActionListener);
        timer.setRepeats(false);
        timer.start();

        (new Thread(new LinkChange(this))).start(); //Starts the thread which allows Link to change icons while walking
        (new Thread(new BoundCheck(this))).start();
        (new Thread(new LinkSlice(this))).start();
        (new Thread(new LinknockBack(this))).start();
        (new Thread(new BoundCheckHit(this))).start();
        (new Thread(new InvincibleFlash(this))).start();
        (new Thread(new LinkExiter(this))).start();
        (new Thread(new LinkEnterer(this))).start();
        (new Thread(new LinkDie(this))).start();
        (new Thread(new getTri(this))).start();
        (new Thread(new checkWin(this))).start();

    }

}