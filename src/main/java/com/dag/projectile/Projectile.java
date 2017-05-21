package com.dag.projectile;

import com.dag.entity.monster.Monster;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class Projectile implements Runnable {

    int speed; // Higher speed means they move slower, not faster.
    int strength;
    public int x, y;
    // --Commented out by Inspection (21/05/2017 08:09):public boolean damaging = false;
    boolean blockable;
    public boolean visible = false;
    public String direction;
    private final Monster monster;
    public BufferedImage image;
    public final BufferedImage[] cache = new BufferedImage[4];

    Projectile(Monster theMonster) {
        x = 0;
        y = 0;
        this.monster = theMonster;
    }

    public void run() {

    }

    void move() {
        if (Objects.equals(direction, "u")) {
            y = y - 1;
        } else if (Objects.equals(direction, "r")) {
            x = x + 1;
        } else if (Objects.equals(direction, "d")) {
            y = y + 1;
        } else if (Objects.equals(direction, "l")) {
            x = x - 1;
        }
    }

    void linkHit() {
        if (!this.monster.window.thePlayer.invincible && !this.monster.window.thePlayer.hurt) {
            if ((this.monster.window.thePlayer.linkCo[0] + 17) >= x && (this.monster.window.thePlayer.linkCo[0] + 17) <= (x + 34) && (this.monster.window.thePlayer.linkCo[1] + 17) >= y && (this.monster.window.thePlayer.linkCo[1] + 17) <= (y + 34)) {
                if (checkDamaging()) {
                    this.monster.window.thePlayer.hurt = true;
                    this.monster.window.thePlayer.invincible = true;
                    this.monster.window.thePlayer.HP = this.monster.window.thePlayer.HP - strength;
                    this.monster.window.thePlayer.timer.restart();
                }
                this.visible = false;
            }
        }
    }

    private boolean checkDamaging() {
        if (blockable && !this.monster.window.thePlayer.linkSlash) {
            if (Objects.equals(this.monster.window.thePlayer.sDirection, "u") && Objects.equals(this.direction, "d")) {
                return false;
            } else if (Objects.equals(this.monster.window.thePlayer.sDirection, "d") && Objects.equals(this.direction, "u")) {
                return false;
            } else if (Objects.equals(this.monster.window.thePlayer.sDirection, "r") && Objects.equals(this.direction, "l")) {
                return false;
            } else if (Objects.equals(this.monster.window.thePlayer.sDirection, "l") && Objects.equals(this.direction, "r")) {
                return false;
            }
        } else if (!visible) {
            return false;
        }
        return true;
    }

    boolean checkBounds() {
        return x < -34 || x > 544 || y < -34 || y > 374;
    }
}