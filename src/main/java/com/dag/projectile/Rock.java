package com.dag.projectile;

import com.dag.entity.monster.Monster;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
//import json.org.*;

public class Rock extends Projectile {

//	Global Variables

    //public JLabel theLabel = new JLabel("SAGFAD");"mis sprites/rock.gif"

    public Rock(Monster theMonster) {

        super(theMonster);

        for (int i = 0; i < 4; i++) {
            try {
                this.cache[i] = ImageIO.read(new File("mis sprites/rock.gif")); //Saves each of Link's pictures for easy access
                //this.linkPic[i].setOpaque(false);
            } catch (IOException e) {
            }
        }
        this.speed = 4;
        this.strength = 1;
        blockable = true;

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