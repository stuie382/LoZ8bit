package com.dag.entity.player.model;

import com.dag.entity.player.Link;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class LinkActionListenerX extends AbstractAction {

    private final Link link;

    public LinkActionListenerX(Link theLink) {
        super();
        this.link = theLink; //Attaches this listener to link
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
