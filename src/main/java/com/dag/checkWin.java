package com.dag;

import com.dag.entity.player.Link;

public class checkWin implements Runnable {

    private final Link link;

    public checkWin(Link theLink) {

        this.link = theLink;
    }

    //@Override
    public void run() {
        while (true) {
            if (this.link.triforceCollected[0] && this.link.triforceCollected[1] && this.link.triforceCollected[2]) {

                this.link.win = true;

            }

            try {
                Thread.sleep(10); //Delay his next image change
            } catch (InterruptedException e) {
            }
            //
        }

    }
}
