package com.jba;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Astar astar;

    public MouseInput(Astar astar){
        this.astar = astar;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            int i = (int) Math.floor(e.getX()/Game.RES);
            int j = (int) Math.floor(e.getY()/Game.RES);

            astar.start = astar.grid[i][j];
            System.out.println(astar.start);
        } else if (e.getButton() == MouseEvent.BUTTON3){
            int i = (int) Math.floor(e.getX()/Game.RES);
            int j = (int) Math.floor(e.getY()/Game.RES);

            astar.end = astar.grid[i][j];
        } else if (e.getButton() == MouseEvent.BUTTON2){
            int i = (int) Math.floor(e.getX()/Game.RES);
            int j = (int) Math.floor(e.getY()/Game.RES);

            astar.grid[i][j].isWall = !astar.grid[i][j].isWall;
        }
    }
}
