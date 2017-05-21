package com.dag.entity.monster;

import com.dag.InvincibleActionListener;
import com.dag.InvincibleFlash;
import com.dag.ZeldaPlayWindow;
import com.dag.projectile.Projectile;

import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Monster implements Runnable {

    int straightWalking; // Higher number means they change directions more often
    int speed; // Higher speed means they walk slower, not faster.
    public int HP;
    int strength;
    public int x, y;
    int hurtCount;
    public boolean invincible;
    boolean damaging;
    boolean knockBack;
    boolean switchImage;
    public boolean visible;
    boolean hurt = false;
    public boolean dead = false;
    public boolean flash = false;
    public String direction;
    public final ZeldaPlayWindow window;
    public BufferedImage image;
    public final BufferedImage[] monPic = new BufferedImage[8];
    private final Random r = new Random();
    public Projectile projectile;

    int shootRate; // Higher number means they shoot more often

    private final int num;
    private final BufferedImage[] diePic = new BufferedImage[2];
    private final Timer timer;

    Monster(int theX, int theY, ZeldaPlayWindow theWindow, int theNum) {

        x = theX * 34;
        y = theY * 34;
        num = theNum;
        this.window = theWindow;

        try {
            this.diePic[0] = ImageIO.read(new File("src/main/resources/sprites/monster/dieSprite1.gif"));
            this.diePic[1] = ImageIO.read(new File("src/main/resources/sprites/monster/dieSprite2.gif"));
        } catch (IOException e) {
        }

        InvincibleActionListener theInvincibleActionListener = new InvincibleActionListener(this);
        timer = new Timer(500, theInvincibleActionListener);
        timer.setRepeats(false);
        timer.start();

        (new Thread(new InvincibleFlash(this))).start();

    }

    public void run() {
        // Intentionally empty
    }

    void move() {
        switch (direction) {
            case "u":
                y = y - 1;
                break;
            case "r":
                x = x + 1;
                break;
            case "d":
                y = y + 1;
                break;
            case "l":
                x = x - 1;
                break;
        }
    }

    void appear() {
        this.direction = "u";
    }

    void die() {
        this.image = diePic[0];
        this.knockBack = false;
        this.invincible = true;
        this.damaging = false;
        dead = true;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        this.image = diePic[1];
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        this.image = null;
        this.window.getTheMonster()[num] = null;
        while (true) {
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
            }
        }

    }

    void checkChange() {
        if (x % 34 == 0 && y % 34 == 0) {
            if (r.nextInt(10) < straightWalking) {
                changeDirection();
            }
            while (checkBlock()) {
                changeDirection();
            }
        }
    }

    private boolean checkBlock() {

        switch (this.direction) {
            case "u":
                if (y / 34 > 0) {
                    if (this.window.getTheMap().walkable[((y / 34) - 1) * 16 + (x / 34)] != 1) {
                        return true;
                    }
                } else {
                    return true;
                }
                break;
            case "r":
                if (x / 34 < 15) {
                    if (this.window.getTheMap().walkable[(y / 34) * 16 + ((x / 34) + 1)] != 1) {
                        return true;
                    }
                } else {
                    return true;
                }
                break;
            case "d":
                if (y / 34 < 10) {
                    if (this.window.getTheMap().walkable[((y / 34) + 1) * 16 + (x / 34)] != 1) {
                        return true;
                    }
                } else {
                    return true;
                }
                break;
            case "l":
                if (x / 34 > 0) {
                    if (this.window.getTheMap().walkable[(y / 34) * 16 + ((x / 34) - 1)] != 1) {
                        return true;
                    }
                } else {
                    return true;
                }
                break;
        }
        return false;

    }

    private void changeDirection() {

        int temp = r.nextInt(4);

        if (temp == 0) {
            this.direction = "u";
            if (switchImage) {
                this.image = this.monPic[0];
            }
        } else if (temp == 1) {
            this.direction = "r";
            if (switchImage) {
                this.image = this.monPic[2];
            }
        } else if (temp == 2) {
            this.direction = "d";
            if (switchImage) {
                this.image = this.monPic[4];
            }
        } else if (temp == 3) {
            this.direction = "l";
            if (switchImage) {
                this.image = this.monPic[6];
            }
        }
        checkChange();

    }

    void linkHit() {
        if (damaging && !this.window.thePlayer.invincible && !this.window.thePlayer.hurt) {
            if ((this.window.thePlayer.linkCo[0] + 17) >= x && (this.window.thePlayer.linkCo[0] + 17) <= (x + 34) && (this.window.thePlayer.linkCo[1] + 17) >= y && (this.window.thePlayer.linkCo[1] + 17) <= (y + 34)) {
                this.window.thePlayer.hurt = true;
                this.window.thePlayer.invincible = true;
                this.window.thePlayer.timer.restart();
                this.window.thePlayer.HP = this.window.thePlayer.HP - strength;
            }
        }
    }

    void monHit() {
        if (this.window.thePlayer.linkSlash && !this.invincible && !this.hurt) {
            if (this.window.thePlayer.sDirection.equals("u")) {
                if (x + 17 >= this.window.thePlayer.linkCo[0] && x + 17 <= this.window.thePlayer.linkCo[0] + 34 && y + 34 >= this.window.thePlayer.linkCo[1] - 22 && y + 34 <= this.window.thePlayer.linkCo[1] + 14) {
                    this.hurt = true;
                    this.invincible = true;
                    timer.restart();
                    this.flash = true;
                    this.HP = this.HP - 1;
                    if (this.direction.equals("u") || this.direction.equals("d")) {
                        this.direction = "d";
                        this.knockBack = true;
                    } else {
                        this.knockBack = false;
                    }
                }
            }
            if (this.window.thePlayer.sDirection.equals("r")) {
                if (x + 17 >= this.window.thePlayer.linkCo[0] + 34 && x + 17 <= this.window.thePlayer.linkCo[0] + 68 && y + 17 >= this.window.thePlayer.linkCo[1] && y + 17 <= this.window.thePlayer.linkCo[1] + 34) {
                    this.hurt = true;
                    this.invincible = true;
                    timer.restart();
                    this.flash = true;
                    this.HP = this.HP - 1;
                    if (this.direction.equals("r") || this.direction.equals("l")) {
                        this.direction = "l";
                        this.knockBack = true;
                    } else {
                        this.knockBack = false;
                    }
                }
            }
            if (this.window.thePlayer.sDirection.equals("d")) {
                if (x + 17 >= this.window.thePlayer.linkCo[0] && x + 17 <= this.window.thePlayer.linkCo[0] + 34 && y + 17 >= this.window.thePlayer.linkCo[1] + 34 && y + 17 <= this.window.thePlayer.linkCo[1] + 68) {
                    this.hurt = true;
                    this.invincible = true;
                    timer.restart();
                    this.flash = true;
                    this.HP = this.HP - 1;
                    if (this.direction.equals("u") || this.direction.equals("d")) {
                        this.direction = "u";
                        this.knockBack = true;
                    } else {
                        this.knockBack = false;
                    }
                }
            }
            if (this.window.thePlayer.sDirection.equals("l")) {
                if (x + 17 >= this.window.thePlayer.linkCo[0] - 34 && x + 17 <= this.window.thePlayer.linkCo[0] && y + 17 >= this.window.thePlayer.linkCo[1] && y + 17 <= this.window.thePlayer.linkCo[1] + 34) {
                    this.hurt = true;
                    this.invincible = true;
                    timer.restart();
                    this.flash = true;
                    this.HP = this.HP - 1;
                    if (this.direction.equals("l") || this.direction.equals("r")) {
                        this.direction = "r";
                        this.knockBack = true;
                    } else {
                        this.knockBack = false;
                    }
                }
            }
            hurtCount = 0;
        }
    }

    void monockback() {
        if (this.knockBack) {
            if (hurtCount <= 68 && !checkBlockHit()) {
                switch (this.direction) {
                    case "u":  //If Link is walking upwards...
                        y = y + 1;
                        break;
                    case "d":
                        y = y - 1;
                        break;
                    case "r":
                        x = x - 1;
                        break;
                    case "l":
                        x = x + 1;
                        break;
                }
            }
        }
    }

    private boolean checkBlockHit() {

        switch (this.direction) {
            case "u":
                if (y / 34 < 9) {
                    if (this.window.getTheMap().walkable[((y / 34) + 1) * 16 + (x / 34)] != 1) {
                        return true;
                    }
                } else {
                    return true;
                }
                break;
            case "r":
                if (x / 34 > 0) {
                    if (this.window.getTheMap().walkable[(y / 34) * 16 + ((x / 34))] != 1) {
                        return true;
                    }
                } else {
                    return true;
                }
                break;
            case "d":
                if (y / 34 > 0) {
                    if (this.window.getTheMap().walkable[((y / 34)) * 16 + (x / 34)] != 1) {
                        return true;
                    }
                } else {
                    return true;
                }
                break;
            case "l":
                if (x / 34 < 14) {
                    if (this.window.getTheMap().walkable[(y / 34) * 16 + ((x / 34) + 1)] != 1) {
                        return true;
                    }
                } else {
                    return true;
                }
                break;
        }
        return false;

    }

    void shoot() {

        if (projectile != null) {
            //System.out.println("shoot");
            this.projectile.x = x;
            this.projectile.y = y;
            this.projectile.direction = direction;
            switch (this.projectile.direction) {
                case "u":
                    this.projectile.image = this.projectile.cache[0];
                    break;
                case "r":
                    this.projectile.image = this.projectile.cache[1];
                    break;
                case "d":
                    this.projectile.image = this.projectile.cache[2];
                    break;
                case "l":
                    this.projectile.image = this.projectile.cache[3];
                    break;
            }
            this.projectile.visible = true;
        }

    }

    boolean checkShoot() {

        if (x % 34 == 0 && y % 34 == 0) {
            if (this.projectile != null && this.projectile.visible) {
                return false;
            }
            if (r.nextInt(10) < shootRate) {
                return true;
            }
        }
        return false;
    }
}