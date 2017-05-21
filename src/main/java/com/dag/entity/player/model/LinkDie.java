package com.dag.entity.player.model;

import com.dag.entity.player.Link;

public class LinkDie implements Runnable {

    private final Link link;

    public LinkDie(Link theLink) {

        this.link = theLink;
    }

    //@Override
    public void run() {
        while (true) {
            if (!this.link.linkSlash) {

                if (link.HP < 1) {
                    this.link.lose = true;
                }

            }

            try {
                Thread.sleep(10); //Delay his next image change
            } catch (InterruptedException e) {
            }
            //
        }

    }
}
