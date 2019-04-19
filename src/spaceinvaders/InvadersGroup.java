package spaceinvaders;


import biuoop.DrawSurface;
import core.Sprite;
import core.Velocity;
import geometry.Point;

import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * create group of invaders.
 */
public class InvadersGroup implements Sprite {
    private List<Invader> invaderList;
    private int startX, startY;
    private Image image;
    private int rightBorder, leftBorder;
    private Random random;
    private ArrayList<LinkedList<Invader>> arrOfLinkedList;
    private List<Invader> copyList;
    private Velocity velocity;

    /**
     * constructor.
     *
     * @param startX      start x position of the first invader
     * @param startY      start y position of the first invader
     * @param image       image of the invaders
     * @param leftBorder  left border of the game
     * @param rightBorder right border of the game
     * @param v           start velocity of the group
     */
    public InvadersGroup(int startX, int startY, Image image, int rightBorder, int leftBorder, Velocity v) {
        this.invaderList = new ArrayList<Invader>();
        this.startX = startX;
        this.startY = startY;
        this.image = image;
        this.rightBorder = rightBorder;
        this.velocity = v;
        this.leftBorder = leftBorder;
        this.random = new Random();
        this.arrOfLinkedList = new ArrayList<>();

        createInvaders();
    }

    /**
     * default constructor.
     *
     * @param group copy group
     */
    public InvadersGroup(InvadersGroup group) {
        this.invaderList = group.getInvaderList();
        this.startX = group.getStartX();
        this.startY = group.getStartY();
        this.image = group.getImage();
        this.velocity = group.getVelocity();
        this.rightBorder = group.getRightBorder();
        this.leftBorder = group.getLeftBorder();
        this.random = new Random();
        this.arrOfLinkedList = new ArrayList<>();
    }

    /**
     * @return arrOfLinkedList.
     */
    public ArrayList<LinkedList<Invader>> getArrOfLinkedList() {
        return arrOfLinkedList;
    }

    /**
     * set arrOfLinkedList.
     *
     * @param arrOfLinkedLists array of LinkedList
     */
    public void setArrOfLinkedList(ArrayList<LinkedList<Invader>> arrOfLinkedLists) {
        this.arrOfLinkedList = arrOfLinkedLists;
    }

    /**
     * list of all the invader in the group.
     *
     * @return list of all the invader in the group
     */
    public List<Invader> getInvaderList() {
        return invaderList;
    }

    /**
     * create 50 invaders in columns and rows.
     */
    private void createInvaders() {
        int x = startX;
        int y;
        int id = 1;
        for (int i = 0; i < 10; i++) {
            LinkedList<Invader> linkedList = new LinkedList<>();
            y = startY;
            for (int j = 0; j < 5; j++) {
                Invader invader = new Invader(id, x, y, velocity, image, rightBorder, leftBorder);
                id++;
                invaderList.add(invader);
                linkedList.add(invader);
                y += 40;
            }
            arrOfLinkedList.add(linkedList);
            x += 50;
        }
        copyList = new ArrayList<>(invaderList);
    }

    /**
     * check if there is need to swap position of the invaders and if it's
     * needed is move the invader down and change direction and speed.
     *
     * @return true if they reached the shields else return false
     */
    public boolean checkAndSwapPosition() {
        //List<Invader> copyInvaders = new ArrayList<>(invaderList);
        boolean reachedShields = false;
        for (Invader invader : invaderList) {
            if (invader.isNeedPositionChange()) {
                // move everybody down and swap direction
                for (Invader enemy : invaderList) {
                    enemy.setNeedPositionChange(false);
                    //change direction
                    enemy.setToMoveRight(!enemy.isToMoveRight());
                    //move down
                    enemy.setStartY(enemy.getStartY() + 20);
                    if (enemy.getStartY() >= 470) {
                        reachedShields = true;
                    }
                    //increase velocity
                    Velocity oldVel = enemy.getVelocity();
                    enemy.setVelocity(new Velocity(oldVel.getDx() * 1.1, 0));
                }
                break;
            }
        }
        //invaderList = copyList;
        return reachedShields;
    }

    @Override
    public void drawOn(DrawSurface d) {
        checkAndSwapPosition();
        for (Invader invader : invaderList) {
            invader.drawOn(d);
        }
    }

    @Override
    public void timePassed(double dt) {
        for (Invader invader : invaderList) {
            invader.timePassed(dt);
        }
    }

    /**
     * add the invaders to sprite and collidable collection of the game.
     *
     * @param g spaceinvaders.GameLevel
     */
    public void addToGame(GameLevel g) {
        for (Invader invader : invaderList) {
            g.addSprite(invader);
            g.addCollidable(invader);
        }
    }

    /**
     * @return return random point witch from the bullet will shoot
     */
    public Point getShoot() {
        //updateLinkedList(invaderList);
        int columIndex = random.nextInt(arrOfLinkedList.size());
        LinkedList<Invader> link = arrOfLinkedList.get(columIndex);
        if (!link.isEmpty()) {
            Invader last = link.getLast();
            return new Point(last.getRectangle().getUpperLeft().getX() + 10,
                    last.getRectangle().getUpperLeft().getY() + 40);
        } else {
            return null;
        }


    }

    /**
     * reset the invader to the original speed and location.
     *
     * @param invaders invader to reset their position
     */
    public void resetGroup(List<Invader> invaders) {
        for (Invader newInv : invaders) {
            for (Invader oldInv : copyList) {
                newInv.setStartY(oldInv.getStartY());
                newInv.setStartX(oldInv.getStartX());
                newInv.setVelocity(oldInv.getVelocity());
            }
        }
    }

    /**
     * delete Invader from the linked list.
     *
     * @param deletedId  the invader to delete
     * @param linkedList the invader linked list
     */
    public void deleteFromLinkedList(int deletedId, ArrayList<LinkedList<Invader>> linkedList) {

        for (LinkedList<Invader> linked : linkedList) {
            for (Invader inv : linked) {
                if (inv.getId() == deletedId) {
                    linked.remove(inv);
                    return;
                }
            }
        }
    }

    /**
     * @return startX
     */
    private int getStartX() {
        return this.startX;
    }

    /**
     * @return startY
     */
    private int getStartY() {
        return this.startY;
    }

    /**
     * @return image
     */
    private Image getImage() {
        return this.image;
    }

    /**
     * @return rightBorder
     */
    private int getRightBorder() {
        return this.rightBorder;
    }

    /**
     * @return leftBorder
     */
    private int getLeftBorder() {
        return this.leftBorder;
    }

    /**
     * @return velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }
}
