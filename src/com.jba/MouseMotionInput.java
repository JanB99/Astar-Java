package com.jba;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseMotionInput extends MouseMotionAdapter {

    private Astar astar;

    public MouseMotionInput(Astar astar){
        this.astar = astar;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int i = (int) Math.floor(e.getX()/Game.RES);
        int j = (int) Math.floor(e.getY()/Game.RES);

        astar.grid[i][j].isWall = true;
    }

}
