package com.dag.entity.monster;

import com.dag.MonsterChange;
import com.dag.ZeldaPlayWindow;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BlueGoriya extends Monster {

    public BlueGoriya(int theX, int theY, ZeldaPlayWindow theWindow, int theNum) {

        super(theX, theY, theWindow, theNum);

        for (int i = 0; i < 8; i++) {
            try {
                this.monPic[i] = ImageIO.read(new File("src/main/resources/sprites/monster/Goriya/B" + i + ".gif"));
            } catch (IOException e) {
            }
        }
        this.switchImage = true;
        this.image = this.monPic[0];
        this.straightWalking = 3;
        this.speed = 10;
        this.HP = 7;
        this.strength = 3;
        this.damaging = true;

    }

    public void run() {

        this.appear();
        (new Thread(new MonsterChange(this))).start();

        while (true) {
            if (!this.hurt || !this.knockBack) {
                this.move();
                this.checkChange();
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
}