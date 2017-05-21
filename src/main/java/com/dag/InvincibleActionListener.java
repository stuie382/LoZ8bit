package com.dag;

import com.dag.entity.monster.Monster;
import com.dag.entity.player.Link;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvincibleActionListener implements ActionListener {

    private Link link;
    private Monster monster;
    private final boolean forLink;

    public InvincibleActionListener(Link theLink) {
        this.link = theLink; //Attaches this listener to link
        forLink = true;
    }

    public InvincibleActionListener(Monster theMonster) {
        this.monster = theMonster; //Attaches this listener to link
        forLink = false;
    }

    //@Override
    public void actionPerformed(ActionEvent e) {

        if (this.forLink) {//If Link is still moving in this direction, he will stop moving
            this.link.invincible = false;
            //System.out.println(this.link.invincible);
        } else {
            this.monster.invincible = false;
            //System.out.println(this.monster.invincible);
        }

    }
}
