package com.dag.projectile;

import com.dag.entity.monster.Monster;
import org.pmw.tinylog.Logger;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Arrow extends Projectile {

    public Arrow(Monster theMonster) {
        super(theMonster);
        Logger.trace("Creatng an arrow");
        loadImages();
        this.speed = 5;
        this.strength = 2;
        this.blockable = true;
    }

    private void loadImages() {
        for (int i = 0; i < 4; i++) {
            try {
                this.cache[i] = ImageIO.read(new File("src/main/resources/sprites/mis/" + (i + 1) + ".gif"));
                //Saves each of Arrow's pictures for easy access
            } catch (IOException e) {
                Logger.error("Problem loading arrow resources " + i + ": " +  e);
                System.exit(5);
            }
        }
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