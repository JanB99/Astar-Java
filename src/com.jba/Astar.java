package com.jba;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Astar {

    public Node[][] grid;
    public int rows, cols;
    public Node start, end;
    public List<Node> openSet, closedSet;
    public boolean isRunning;
    public List<Node> path;

    public Astar (int rows, int cols){
        this.cols = cols;
        this.rows = rows;
        this.grid = this.makeGrid();

//        this.start = null;//this.grid[0][0];
//        this.end = null;//this.grid[cols - 1][rows - 1];

        this.openSet = new ArrayList<>();
        this.closedSet = new ArrayList<>();
        this.path = new ArrayList<>();

        this.isRunning = false;
    }

    public Node[][] makeGrid(){
        Node[][] temp = new Node[cols][rows];
        for (int i = 0; i < cols; i++){
            for (int j = 0; j < rows; j++){
                temp[i][j] = new Node(i, j);
            }
        }
        return temp;
    }

    public void start(){
        if (start != null && end != null){
            this.openSet.add(start);
            this.isRunning = true;
            this.initNodes();
        }
    }

    public void initNodes(){
        for (int i = 0; i < cols; i++){
            for (int j = 0; j < rows; j++){
                grid[i][j].h = heuristic(grid[i][j], end);
                grid[i][j].addNeighbors(grid, cols, rows);
            }
        }
    }

    public float heuristic(Node a, Node b){
        return a != null && b != null? (float) Math.sqrt(Math.pow(a.i - b.i, 2) + Math.pow(a.j - b.j, 2)) : 0;
    }

    public Node findLowestFScore(){
        int lowestIndex = 0;
        for (int i = 0; i < openSet.size(); i++){
            if (openSet.get(i).f < openSet.get(lowestIndex).f){
                lowestIndex = i;
            }
        }
        return openSet.get(lowestIndex);
    }

    public void tick(){
        if (isRunning){
            Node current = findLowestFScore();

            if (openSet.size() > 0){
                if (current == end){
                    isRunning = false;
                }

                openSet.remove(current);
                closedSet.add(current);

                for (int i = 0; i < current.neighbors.size(); i++){

                    if (!closedSet.contains(current.neighbors.get(i)) && !current.neighbors.get(i).isWall){
                        float tempG = current.neighbors.get(i).g + heuristic(current.neighbors.get(i), current);

                        boolean newPath = false;
                        if (openSet.contains(current.neighbors.get(i))){
                            if (tempG < current.neighbors.get(i).g){
                                current.neighbors.get(i).g = tempG;
                                newPath = true;
                            }
                        } else {
                            current.neighbors.get(i).g = tempG;
                            newPath = true;
                            openSet.add(current.neighbors.get(i));
                        }

                        if (newPath){
                            current.neighbors.get(i).f = current.neighbors.get(i).g + current.neighbors.get(i).h;
                            current.neighbors.get(i).previous = current;
                        }
                    }
                }
            }

            path.clear();
            Node temp = current;
            path.add(temp);
            while (temp.previous != null) {
                path.add(temp.previous);
                temp = temp.previous;
            }
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                grid[i][j].show(g, Color.gray);

                if (start != null){
                    start.show(g, Color.YELLOW);
                }

                if (end != null){
                    end.show(g, Color.MAGENTA);
                }

                if (openSet.contains(grid[i][j])){
                    grid[i][j].show(g, Color.green);
                }


                if (closedSet.contains(grid[i][j])){
                    grid[i][j].show(g, Color.RED);
                }
            }
        }

        for (int i = 0; i < path.size(); i++){
            path.get(i).show(g, Color.BLUE);
        }
    }
}
