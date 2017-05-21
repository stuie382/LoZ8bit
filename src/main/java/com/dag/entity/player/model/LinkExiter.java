package com.dag.entity.player.model;

import com.dag.entity.player.Link;

public class LinkExiter implements Runnable {

    private final Link link;

    public LinkExiter(Link theLink) {

        this.link = theLink;
    }

    //@Override
    public void run() {

        while (true) {

            switch (this.link.direction) {
                case "u":
                    if (this.link.linkCo[1] < 5) {
                        this.link.linkExit = "u";
                        //this.link.window.theMap.mapErase();
                        for (int i = 0; i < 10; i++) {
                            if (this.link.window.getTheMonster()[i] != null) {
                                this.link.window.getTheMonster()[i].HP = 0;
                            }
                        }
                    }
                    break;
                case "d":
                    if (this.link.linkCo[1] > 335) {
                        this.link.linkExit = "d";
                        //this.link.window.theMap.mapErase();
                        for (int i = 0; i < 10; i++) {
                            if (this.link.window.getTheMonster()[i] != null) {
                                this.link.window.getTheMonster()[i].HP = 0;
                            }
                        }
                    }
                    break;
                case "l":
                    if (this.link.linkCo[0] < 5) {
                        this.link.linkExit = "l";
                        //this.link.window.theMap.mapErase();
                        for (int i = 0; i < 10; i++) {
                            if (this.link.window.getTheMonster()[i] != null) {
                                this.link.window.getTheMonster()[i].HP = 0;
                            }
                        }
                    }
                    break;
                case "r":
                    if (this.link.linkCo[0] > 505) {
                        this.link.linkExit = "r";
                        //this.link.window.theMap.mapErase();
                        for (int i = 0; i < 10; i++) {
                            if (this.link.window.getTheMonster()[i] != null) {
                                this.link.window.getTheMonster()[i].HP = 0;
                            }
                        }
                    }
                    break;
            }

            switch (this.link.direction) {
                case "u":
                    if (this.link.linkCo[1] < -44) {
                        this.link.linkCo[1] = 384;
                        this.link.window.getTheMap().y = this.link.window.getTheMap().y - 1;
                        this.link.window.getTheMap().mapLoad(this.link.window.getTheMap().x, this.link.window.getTheMap().y);
                    }
                    break;
                case "d":
                    if (this.link.linkCo[1] > 384) {
                        this.link.linkCo[1] = -44;
                        this.link.window.getTheMap().y = this.link.window.getTheMap().y + 1;
                        this.link.window.getTheMap().mapLoad(this.link.window.getTheMap().x, this.link.window.getTheMap().y);
                    }
                    break;
                case "l":
                    if (this.link.linkCo[0] < -44) {
                        this.link.linkCo[0] = 554;
                        this.link.window.getTheMap().x = this.link.window.getTheMap().x - 1;
                        this.link.window.getTheMap().mapLoad(this.link.window.getTheMap().x, this.link.window.getTheMap().y);
                    }
                    break;
                case "r":
                    if (this.link.linkCo[0] > 554) {
                        this.link.linkCo[0] = -44;
                        this.link.window.getTheMap().x = this.link.window.getTheMap().x + 1;
                        this.link.window.getTheMap().mapLoad(this.link.window.getTheMap().x, this.link.window.getTheMap().y);
                    }
                    break;
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
        }

    }
}
