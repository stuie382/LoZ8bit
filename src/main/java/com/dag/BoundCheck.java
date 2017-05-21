package com.dag;

import com.dag.entity.player.Link;

public class BoundCheck implements Runnable {

    private final Link link;

    public BoundCheck(Link theLink) {

        this.link = theLink;
    }

    //@Override
    public void run() {

        while (true) {
            if (!link.hurt) {
                if (this.link.linkExit.equals("n")) {
                    this.link.linkWalk = isWalkable();
                } else {
                    if (!this.link.window.getTheMap().isMapLoading()) {
                        setLinkDirection();
                        this.link.linkWalk = true;
                    } else {
                        this.link.linkWalk = false;
                    }
                }
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
        }

    }

    private void setLinkDirection() {
        if (this.link.linkExit.equals("r")) {
            this.link.direction = "r";
        }
        if (this.link.linkExit.equals("l")) {
            this.link.direction = "l";
        }
        if (this.link.linkExit.equals("d")) {
            this.link.direction = "d";
        }
        if (this.link.linkExit.equals("u")) {
            this.link.direction = "u";
        }
    }

    private boolean isWalkable() {

        int x, y;
        double mapX, mapY;
        int tileNum;

        switch (this.link.direction) {
            case "n":
                return false;
            case "u":
                x = this.link.linkCo[0] + 14;
                y = this.link.linkCo[1] + 14;
                if (y == 0) {
                    return true;
                }
                break;
            case "r":
                x = this.link.linkCo[0] + 32;
                y = this.link.linkCo[1] + 18;
                if (x >= 539) {
                    return true;
                }
                break;
            case "d":
                x = this.link.linkCo[0] + 14;
                y = this.link.linkCo[1] + 30;
                if (y >= 369) {
                    return true;
                }
                break;
            default:
                x = this.link.linkCo[0] + 2;
                y = this.link.linkCo[1] + 18;
                if (x == 0) {
                    return true;
                }
                break;
        }

        mapX = x / 34;
        mapY = y / 34;

        x = (int) Math.floor(mapX);
        y = (int) Math.floor(mapY);

        tileNum = (y * 16) + x;

        return !link.linkExit.equals("n") || this.link.window.getTheMap().walkable[tileNum] == 1;

    }
}
