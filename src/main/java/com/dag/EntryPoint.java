package com.dag;

import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.writers.FileWriter;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;

class EntryPoint extends JFrame {

    private static final int WIDTH = 544;
    private static final int HEIGHT = 400;
    private static final String TITLE = "The Legend of Zelda";

    public static void main(String[] args) {
        new EntryPoint();
    }

    private EntryPoint() {
        super(TITLE);
        setSize(WIDTH, HEIGHT);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Sets window commands
        setLayout(new BorderLayout());
        pack();

        setupLogger();
        new ZeldaPlayWindow(this);
    }

    private void setupLogger() {
        Configurator.defaultConfig()
                .writer(new FileWriter("log.txt"))
                .level(Level.TRACE).activate();
}
}
