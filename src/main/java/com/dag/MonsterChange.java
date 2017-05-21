package com.dag;

import com.dag.entity.monster.Monster;

public class MonsterChange implements Runnable {

    private final Monster monster;

    public MonsterChange(Monster theMonster) {
        this.monster = theMonster;
    }

    //@Override
    public void run() {
        while (true) {
            if (this.monster.dead) {
                break;
            }
            switch (this.monster.direction) {
                case "u":  //If Link is walking upwards...
                    if (this.monster.image == this.monster.monPic[0]) { //If he has one icon for walking upwards...
                        this.monster.image = this.monster.monPic[1]; //Switch to the other
                    } else { //Repeat for all other directions and images
                        this.monster.image = this.monster.monPic[0];
                    }
                    break;
                case "d":
                    if (this.monster.image == this.monster.monPic[4]) {
                        this.monster.image = this.monster.monPic[5];
                    } else {
                        this.monster.image = this.monster.monPic[4];
                    }
                    break;
                case "r":
                    if (this.monster.image == this.monster.monPic[2]) {
                        this.monster.image = this.monster.monPic[3];
                    } else {
                        this.monster.image = this.monster.monPic[2];
                    }
                    break;
                case "l":
                    if (this.monster.image == this.monster.monPic[6]) {
                        this.monster.image = this.monster.monPic[7];
                    } else {
                        this.monster.image = this.monster.monPic[6];
                    }

                    break;
            }
            try {
                Thread.sleep(100); //Delay his next image change
            } catch (InterruptedException e) {

            }
        }
    }
}
