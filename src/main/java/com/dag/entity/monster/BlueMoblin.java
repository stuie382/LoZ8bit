package com.dag.entity.monster;

import com.dag.MonsterChange;
import com.dag.ZeldaPlayWindow;
import com.dag.projectile.Arrow;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BlueMoblin extends Monster {

    public BlueMoblin(int theX, int theY, ZeldaPlayWindow theWindow, int theNum) {

        super(theX, theY, theWindow, theNum);

        for (int i = 0; i < 8; i++) {
            try {
                this.monPic[i] = ImageIO.read(new File("src/main/resources/sprites/monster/Moblin/B" + i + ".gif"));
            } catch (IOException e) {
            }
        }
        this.switchImage = true;
        this.image = this.monPic[0];
        this.straightWalking = 3;
        this.speed = 11;
        this.HP = 3;
        this.strength = 2;
        this.damaging = true;
        this.shootRate = 1;
        this.projectile = new Arrow(this);
        (new Thread(this.projectile)).start();

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
                if (this.checkShoot()) {
                    this.shoot();
                }
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