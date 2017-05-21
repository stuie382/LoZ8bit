package com.dag.entity.player.model;

import com.dag.entity.player.Link;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class LinkActionListenerRightR extends AbstractAction {

    private final Link link;

    public LinkActionListenerRightR(Link theLink) {
        super();
        this.link = theLink; //Attaches this listener to link
    }

    //@Override
    public void actionPerformed(ActionEvent e) {

        if (Objects.equals(this.link.direction, "r")) {//If Link is still moving in this direction, he will stop moving
            this.link.direction = "n";
        }

    }
}
