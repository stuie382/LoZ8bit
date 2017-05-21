package com.dag.projectile;

import com.dag.entity.monster.Monster;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
//import json.org.*;

public class Sword extends Projectile {

//	Global Variables

    //public JLabel theLabel = new JLabel("SAGFAD");

    public Sword(Monster theMonster) {

        super(theMonster);

        for (int i = 0; i < 4; i++) {
            try {
                this.cache[i] = ImageIO.read(new File("mis sprites/s" + (i + 1) + ".gif")); //Saves each of Link's pictures for easy access
                //this.linkPic[i].setOpaque(false);
            } catch (IOException e) {
            }
        }
        this.speed = 4;
        this.strength = 3;
        this.blockable = false;

    }

    public void run() {

        while (true) {
            if (this.visible) {
                this.move();
                this.linkHit();
            }
            if (checkBounds()) {
                this.visible = false;
            }
            try {
                Thread.sleep(this.speed); //Delay his next image change
            } catch (InterruptedException e) {
            }
        }

    }

}