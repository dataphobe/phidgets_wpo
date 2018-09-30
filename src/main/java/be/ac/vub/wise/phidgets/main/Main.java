/**
 * Main class responsible for running the game.
 * An instance of the Tetris game is created.
 * Phidget controllers are attached to to the game so
 * that the game is controlled both by the keyboard and
 * the phidgets.
 * <p>
 * To play press left and right arrow key to move,
 * up and down for rotation and space for going down
 * fast.
 * <p>
 * To play with the controller press up/down on the mini-joystick
 * to rotate and left/right for moving left and right.
 * The slider Phidgeet changes the speed of the game.
 */

package be.ac.vub.wise.phidgets.main;

import be.ac.vub.wise.phidgets.controller.*;
//import be.ac.vub.wise.phidgets.interfaces.*;
//import be.ac.vub.wise.phidgets.main.*;
//import be.ac.vub.wise.phidgets.controller.*;
import be.ac.vub.wise.phidgets.interfaces.HorizontalMoveChangeListener;
import be.ac.vub.wise.phidgets.interfaces.RFIDChangedListener;
import be.ac.vub.wise.phidgets.interfaces.SpeedChangeListener;
import be.ac.vub.wise.phidgets.interfaces.VerticalMoveChangeListener;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {


        JFrame f = new JFrame("Tetris");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(12 * 26 + 10, 26 * 23 + 25);
        f.setVisible(true);

        final Tetris game = new Tetris();
        game.init();
        f.add(game);
        /* Initialise the controller object */
        Controller c = new Controller();
        c.init();
        c.addVerticalChangeListener(new VerticalMoveChangeListener() {
            @Override
            public void onVerticalChanged(VerticalMoveChangeEvent v) {
                game.rotate(v.getDirection());
            }
        });

        c.addHorizontalChangeListener(new HorizontalMoveChangeListener() {
            @Override
            public void onHorizontalChanged(HorizontalMoveChangeEvent h) {
                game.move(h.getDirection());
            }
        });

        c.addSpeedChangeListener(new SpeedChangeListener() {
            @Override
            public void onSpeedChanged(SpeedChangedEvent s) {
                //game.dropDown(s.getspeed());
                System.out.println(s.getspeed());
                game.setSpeed(s.getspeed());
            }
        });

        c.addRFIDChangedListener(new RFIDChangedListener() {

            @Override
            public void onRFIDChanged(RFIDChangedEvent r) {
                //Excercise 3


            }
        });
        // Keyboard controls
        f.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        game.rotate(-1);
                        break;
                    case KeyEvent.VK_DOWN:
                        game.rotate(+1);
                        break;
                    case KeyEvent.VK_LEFT:
                        game.move(-1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.move(+1);
                        break;
                    case KeyEvent.VK_SPACE:
                        game.dropDown();
                        game.increaseScore();
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });

        // Make the falling piece drop every second
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        game.dropDown();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();


    }
}
