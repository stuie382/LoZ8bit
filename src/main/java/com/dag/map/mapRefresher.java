package com.dag.map;

class mapRefresher implements Runnable {

    private final Map theMap;

    public mapRefresher(Map myMap) {

        this.theMap = myMap;
    }

    //@Override
    public void run() {
        while (true) {
            if (!this.theMap.window.thePlayer.linkSlash && !this.theMap.window.thePlayer.hurt) {
                if (this.theMap.window.thePlayer.linkWalk) {
                    if (this.theMap.window.thePlayer.direction.equals("u")) {
                        //If Link is moving, move him a pixel in the right direction
                        this.theMap.window.thePlayer.linkCo[1] = this.theMap.window.thePlayer.linkCo[1] - 1;
                    } else if (this.theMap.window.thePlayer.direction.equals("d")) {
                        //Repeat the process for the other directions
                        this.theMap.window.thePlayer.linkCo[1] = this.theMap.window.thePlayer.linkCo[1] + 1;
                    }
                    if (this.theMap.window.thePlayer.direction.equals("r")) {
                        this.theMap.window.thePlayer.linkCo[0] = this.theMap.window.thePlayer.linkCo[0] + 1;
                    } else if (this.theMap.window.thePlayer.direction.equals("l")) {
                        this.theMap.window.thePlayer.linkCo[0] = this.theMap.window.thePlayer.linkCo[0] - 1;
                    }
                }
                switch (this.theMap.window.thePlayer.direction) {
                    case "u":
                        if (this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[0] && this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[1]) {
                            this.theMap.window.thePlayer.image = this.theMap.window.thePlayer.linkPic[0];
                            //If Link's costume isn't the correct direction, change it so it is
                        }
                        break;
                    case "d":
                        if (this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[4] && this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[5]) {
                            this.theMap.window.thePlayer.image = this.theMap.window.thePlayer.linkPic[4];
                        }
                        break;
                    case "r":
                        if (this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[2] && this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[3]) {
                            this.theMap.window.thePlayer.image = this.theMap.window.thePlayer.linkPic[2];
                        }
                        break;
                    case "l":
                        if (this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[6] && this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[7]) {
                            this.theMap.window.thePlayer.image = this.theMap.window.thePlayer.linkPic[6];
                        }
                        break;
                }
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
            this.theMap.repaint();
            //Refreshes the Map
        }

    }
}
