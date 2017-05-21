package com.dag.entity.player.model;

import com.dag.entity.player.Link;

public class LinknockBack implements Runnable {

    private final Link link;

    public LinknockBack(Link theLink) {

        this.link = theLink;
    }

    //@Override
    public void run() {
        int count = 0;
        while (true) {
            if (this.link.hurt) {
                if (this.link.linkWalk) {
                    switch (this.link.sDirection) {
                        case "u":  //If Link is walking upwards...

                            this.link.linkCo[1] = this.link.linkCo[1] + 1;

                            break;
                        case "d":

                            this.link.linkCo[1] = this.link.linkCo[1] - 1;

                            break;
                        case "r":

                            this.link.linkCo[0] = this.link.linkCo[0] - 1;

                            break;
                        case "l":

                            this.link.linkCo[0] = this.link.linkCo[0] + 1;

                            break;
                    }
                }
                count++;
                if (count > 68) {
                    count = 0;
                    this.link.hurt = false;
                }
            }

            try {
                Thread.sleep(1); //Delay his next image change
            } catch (InterruptedException e) {
            }

        }

    }
}
