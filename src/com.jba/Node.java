package com.jba;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Node {

    public int i, j;
    public float f, g, h;
    public Node previous;
    public List<Node> neighbors;
    public boolean isWall;

    public Node(int i, int j){
        this.i = i;
        this.j = j;
        f = 0;
        g = 0;
        h = 0;
        previous = null;
        neighbors = new ArrayList<>();
        isWall = false;
    }

    public void addNeighbors(Node[][] grid, int cols, int rows){
        if (this.i < cols - 1) {
            this.neighbors.add(grid[this.i + 1][this.j]);
        }
        if (this.i > 0) {
            this.neighbors.add(grid[this.i - 1][this.j]);
        }
        if (this.j < rows - 1) {
            this.neighbors.add(grid[this.i][this.j + 1]);
        }
        if (this.j > 0) {
            this.neighbors.add(grid[this.i][this.j - 1]);
        }
        if (this.i > 0 && this.j > 0){
            this.neighbors.add(grid[this.i - 1][this.j - 1]);
        }
        if (this.i < cols - 1 && this.j < rows - 1){
            this.neighbors.add(grid[this.i + 1][this.j + 1]);
        }
        if (this.i > 0 && this.j < rows - 1){
            this.neighbors.add(grid[this.i - 1][this.j + 1]);
        }
        if (this.j > 0 && this.i < cols - 1){
            this.neighbors.add(grid[this.i + 1][this.j - 1]);
        }
    }

    public void show (Graphics g, Color color){
        if (this.isWall){
            g.setColor(Color.black);
            g.fillRect(this.i*Game.RES, this.j*Game.RES, Game.RES, Game.RES);
        } else {
            g.setColor(color);
            g.fillRect(this.i*Game.RES, this.j*Game.RES, Game.RES, Game.RES);
        }
    }


}
