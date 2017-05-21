package com.dag;

import com.dag.entity.monster.Monster;
import com.dag.entity.monster.MonsterFactory;
import com.dag.entity.player.Link;
import com.dag.map.Map;
import org.pmw.tinylog.Logger;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.util.Random;

public class ZeldaPlayWindow {


    public Link thePlayer; //The instance of Link
    private final Monster[] theMonster = new Monster[10];
    private Map theMap; //The instance of the Map in the background (not a minimap)
    public boolean intro = true;
    private final JFrame parentFrame;



    public ZeldaPlayWindow(final JFrame frame) {
        Logger.trace("Enter ZeldaPlayWindow");
        parentFrame = frame;
        setupGlobals();
        new MonsterFactory().monLoad(theMap, theMonster, this);

        parentFrame.setVisible(true);
        try {
            Thread.sleep(3000); //Delay his next image change
        } catch (InterruptedException e) {
            Logger.error("Constructor delay image:" + e);
        }
        this.intro = false;
        Logger.trace("Exit ZeldaPlayWindow");
    }

    private void setupGlobals() {
        thePlayer = new Link(this);
        parentFrame.add(thePlayer);
        theMap = new Map(this);
        parentFrame.add(theMap, BorderLayout.CENTER);
    }

    public Map getTheMap() {
        return theMap;
    }

    public void setTheMap(Map theMap) {
        this.theMap = theMap;
    }

    public Monster[] getTheMonster() {
        return theMonster;
    }
}
