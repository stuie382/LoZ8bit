package com.dag;

import com.dag.entity.player.Link;

public class BoundCheckHit implements Runnable {

    private final Link link;

    public BoundCheckHit(Link theLink) {

        this.link = theLink;
    }

    //@Override
    public void run() {

        while (true) {
            if (link.hurt) {

                this.link.linkWalk = isWalkable();

            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
        }
    }

    private boolean isWalkable() {
        int x, y;
        double mapX, mapY;
        int tileNum;

        switch (this.link.sDirection) {
            case "u":
                x = this.link.linkCo[0] + 14;
                y = this.link.linkCo[1] + 34;
                if (y > 340) {
                    return false;
                }
                break;
            case "r":
                x = this.link.linkCo[0];
                y = this.link.linkCo[1] + 18;
                if (x < 34) {
                    return false;
                }
                break;
            case "d":
                x = this.link.linkCo[0] + 14;
                y = this.link.linkCo[1];
                if (y < 34) {
                    return false;
                }
                break;
            default:
                x = this.link.linkCo[0] + 34;
                y = this.link.linkCo[1] + 18;
                if (x > 510) {
                    return false;
                }
                break;
        }

        mapX = x / 34;
        mapY = y / 34;

        x = (int) Math.floor(mapX);
        y = (int) Math.floor(mapY);

        tileNum = (y * 16) + x;

        return this.link.window.getTheMap().walkable[tileNum] == 1;
    }
}
