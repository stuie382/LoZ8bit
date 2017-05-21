package com.dag;

import com.dag.entity.monster.Monster;
import com.dag.entity.player.Link;

public class InvincibleFlash implements Runnable {

    private Monster monster;
    private Link link;
    private final boolean forLink;

    public InvincibleFlash(Link thePlayer) {
        link = thePlayer;
        forLink = true;
    }

    public InvincibleFlash(Monster theMonster) {
        monster = theMonster;
        forLink = false;
    }

    public void run() {
        while (true) {
            if (this.forLink) {
                this.link.visible = !(this.link.invincible && this.link.visible);
            } else {
                this.monster.visible = !(this.monster.flash && this.monster.visible);
            }
            try {
                Thread.sleep(1); //Delay his next image change
            } catch (InterruptedException e) {
            }
        }
    }
}