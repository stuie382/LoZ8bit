package com.dag.entity.player.model;

import com.dag.entity.monster.MonsterFactory;
import com.dag.entity.player.Link;

public class LinkEnterer implements Runnable {

    private final Link link;

    public LinkEnterer(Link theLink) {

        this.link = theLink;
    }

    //@Override
    public void run() {
        MonsterFactory mf = new MonsterFactory();
        while (true) {

            switch (this.link.direction) {
                case "u":
                    if (this.link.linkCo[1] == 340) {
                        mf.monLoad(link.window.getTheMap(),link.window.getTheMonster(), link.window);
                        this.link.linkCo[1] = this.link.linkCo[1] - 2;
                        this.link.linkExit = "n";
                        this.link.direction = "n";
                    }
                    break;
                case "d":
                    if (this.link.linkCo[1] == 0) {
                        mf.monLoad(link.window.getTheMap(),link.window.getTheMonster(), link.window);
                        this.link.linkCo[1] = this.link.linkCo[1] + 2;
                        this.link.linkExit = "n";
                        this.link.direction = "n";
                    }
                    break;
                case "l":
                    if (this.link.linkCo[0] == 510) {
                        mf.monLoad(link.window.getTheMap(),link.window.getTheMonster(), link.window);
                        this.link.linkCo[0] = this.link.linkCo[0] - 2;
                        this.link.linkExit = "n";
                        this.link.direction = "n";
                    }
                    break;
                case "r":
                    if (this.link.linkCo[0] == 0) {
                        mf.monLoad(link.window.getTheMap(),link.window.getTheMonster(), link.window);
                        this.link.linkCo[0] = this.link.linkCo[0] + 2;
                        this.link.linkExit = "n";
                        this.link.direction = "n";
                    }
                    break;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
            }
        }

    }
}
