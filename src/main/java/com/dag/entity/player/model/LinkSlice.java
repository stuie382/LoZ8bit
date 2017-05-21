package com.dag.entity.player.model;

import com.dag.entity.player.Link;

public class LinkSlice implements Runnable {

    private final Link link;

    public LinkSlice(Link theLink) {

        this.link = theLink;
    }

    //@Override
    public void run() {
        while (true) {
            if (this.link.linkSlash) {
                switch (this.link.sDirection) {
                    case "u":  //If Link is walking upwards...

                        this.link.image = this.link.linkPic[8]; //Switch to the other


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
                    Thread.sleep(150); //Delay his next image change
                } catch (InterruptedException e) {
                }

                this.link.linkSlash = false;

                switch (this.link.sDirection) {
                    case "u":  //If Link is walking upwards...

                        this.link.image = this.link.linkPic[0]; //Switch to the other


                        break;
                    case "d":

                        this.link.image = this.link.linkPic[4];

                        break;
                    case "r":

                        this.link.image = this.link.linkPic[2];

                        break;
                    case "l":

                        this.link.image = this.link.linkPic[6];

                        break;
                }

				/*try{
                    Thread.sleep(800); //Delay his next image change
				}
				catch (InterruptedException e){
				}*/

            }
            try {
                Thread.sleep(5); //Delay his next image change
            } catch (InterruptedException e) {
            }
        }

    }
}
