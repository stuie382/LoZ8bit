package com.dag.entity.monster;

import com.dag.MonsterChange;
import com.dag.ZeldaPlayWindow;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class RedDarknut extends Monster {

    public RedDarknut(int theX, int theY, ZeldaPlayWindow theWindow, int theNum) {

        super(theX, theY, theWindow, theNum);

        for (int i = 0; i < 8; i++) {
            try {
                this.monPic[i] = ImageIO.read(new File("src/main/resources/sprites/monster/Darknut/R" + i + ".gif"));
                //Saves each of Link's pictures for easy access
            } catch (IOException e) {
            }
        }
        this.switchImage = true;
        this.image = this.monPic[0];
        this.straightWalking = 3;
        this.speed = 11;
        this.HP = 4;
        this.strength = 2;
        this.damaging = true;

    }

    public void run() {

        this.appear();
        (new Thread(new MonsterChange(this))).start();

        while (true) {
            if (!this.hurt || !this.knockBack) {
                this.move();
                this.checkChange();
                this.invincible = this.defend();
                this.linkHit();
                this.monHit();
                try {
                    Thread.sleep(this.speed); //Delay his next image change
                } catch (InterruptedException e) {
                }
            } else {
                this.monockback();
            }
            hurtCount++;
            if (hurtCount == 69) {
                if (!this.knockBack) {
                    this.hurt = false;
                    this.invincible = false;
                    this.flash = false;
                } else {
                    hurtCount = 0;
                    this.knockBack = false;
                }
            }
            if (this.HP == 0) {
                this.die(); //Homestuck reference FTW!
                break;
            }
            try {
                Thread.sleep(1); //Delay his next image change
            } catch (InterruptedException e) {
            }
        }

    }

    private boolean defend() {
        if (this.window.thePlayer.sDirection.equals("u") && this.direction.equals("d")) {
            return true;
        } else if (this.window.thePlayer.sDirection.equals("d") && this.direction.equals("u")) {
            return true;
        } else if (this.window.thePlayer.sDirection.equals("r") && this.direction.equals("l")) {
            return true;
        } else if (this.window.thePlayer.sDirection.equals("l") && this.direction.equals("r")) {
            return true;
        }
        return false;
    }

}