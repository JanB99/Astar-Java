package com.jba;

//import objects.BasicEnemy;
//import objects.ID;
//import objects.Player;
//import objects.Wall;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 600, HEIGHT = 600, COLS = 50, ROWS = 50, RES = WIDTH/COLS;
    private Thread thread;
    private boolean running;
    private Astar astar;

    private KeyInput keyInput;
    private MouseInput mouseInput;
    private MouseMotionInput mouseMotionInput;

    public Game(){

        astar = new Astar(ROWS, COLS);

        keyInput = new KeyInput(astar);
        this.addKeyListener(keyInput);

        mouseInput = new MouseInput(astar);
        this.addMouseListener(mouseInput);

        mouseMotionInput = new MouseMotionInput(astar);
        this.addMouseMotionListener(mouseMotionInput);

        new Window("A* Algorithm", HEIGHT, WIDTH, this);
    }

    public static void main(String[] args) {
	    new Game();
    }

    public synchronized void start() {
        this.thread = new Thread(this);
        this.thread.start();
        this.running = true;
    }

    public synchronized void stop() {
        try{
            this.thread.join();
            this.running = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running){
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        astar.render(g);

        g.dispose();
        bs.show();
    }

    private void tick() {
        astar.tick();
    }
}
