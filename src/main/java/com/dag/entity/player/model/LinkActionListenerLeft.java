package com.dag.entity.player.model;

import com.dag.entity.player.Link;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class LinkActionListenerLeft extends AbstractAction {

    private final Link link;

    public LinkActionListenerLeft(Link theLink) {
        super();
        this.link = theLink; //Attaches this listener to link
    }

    //@Override
    public void actionPerformed(ActionEvent e) {

        if (!this.link.hurt && !this.link.window.intro && this.link.linkExit.equals("n")) {
            this.link.direction = "l";//Changes Link's direction when the key bound to it is pressed
            this.link.sDirection = "l";
        }

    }
}
