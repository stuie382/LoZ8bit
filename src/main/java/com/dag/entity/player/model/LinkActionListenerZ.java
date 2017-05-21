package com.dag.entity.player.model;

import com.dag.entity.player.Link;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class LinkActionListenerZ extends AbstractAction {

    private final Link link;

    public LinkActionListenerZ(Link theLink) {
        super();
        this.link = theLink; //Attaches this listener to link
    }

    //@Override
    public void actionPerformed(ActionEvent e) {

        if (!this.link.hurt) {
            if (this.link.canSlash) {
                this.link.linkSlash = true;
            }
            this.link.canSlash = false;
        }

    }
}
