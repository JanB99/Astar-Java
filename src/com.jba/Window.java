package com.jba;

import com.jba.Game;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas{

    private JFrame frame;
    private String title;
    private Game game;
    private int width, height;

    public Window(String title, int height, int width, Game game){
        this.title = title;
        this.width = width;
        this.height = height;
        this.game = game;

        createWindow();
    }

    private void createWindow(){
        this.frame = new JFrame(this.title);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(this.width, this.height));
        this.frame.setMinimumSize(new Dimension(this.width, this.height));
        this.frame.setMaximumSize(new Dimension(this.width, this.height));
        this.frame.setLocationRelativeTo(null);
        this.frame.add(this.game);
        this.frame.setVisible(true);
        this.game.start();
    }
}
