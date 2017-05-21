package com.dag.entity.player.model;

import com.dag.entity.player.Link;

public class LinkChange implements Runnable {

    private final Link link;

    public LinkChange(Link theLink) {

        this.link = theLink;
    }

    //@Override
    public void run() {
        while (true) {
            if (!this.link.linkSlash) {
                switch (this.link.direction) {
                    case "u":  //If Link is walking upwards...
                        if (this.link.image == this.link.linkPic[0]) { //If he has one icon for walking upwards...
                            this.link.image = this.link.linkPic[1]; //Switch to the other
                        } else { //Repeat for all other directions and images
                            this.link.image = this.link.linkPic[0];
                        }
                        break;
                    case "d":
                        if (this.link.image == this.link.linkPic[4]) {
                            this.link.image = this.link.linkPic[5];
                        } else {
                            this.link.image = this.link.linkPic[4];
                        }
                        break;
                    case "r":
                        if (this.link.image == this.link.linkPic[2]) {
                            this.link.image = this.link.linkPic[3];
                        } else {
                            this.link.image = this.link.linkPic[2];
                        }
                        break;
                    case "l":
                        if (this.link.image == this.link.linkPic[6]) {
                            this.link.image = this.link.linkPic[7];
                        } else {
                            this.link.image = this.link.linkPic[6];
                        }

                        break;
                }
                try {
                    Thread.sleep(100); //Delay his next image change
                } catch (InterruptedException e) {
                }
            } else {
                switch (this.link.sDirection) {
                    case "u":  //If Link is walking upwards...
                        this.link.image = this.link.linkPic[8];
                        break;
                    case "d":
                        this.link.image = this.link.linkPic[10];
                        break;
                    case "r":
                        this.link.image = this.link.linkPic[9];
                        break;
                    case "l":
                        this.link.image = this.link.linkPic[11];
                        break;
                }

                try {
                    Thread.sleep(5); //Delay his next image change
                } catch (InterruptedException e) {
                }
            }
            //
        }

    }
}
