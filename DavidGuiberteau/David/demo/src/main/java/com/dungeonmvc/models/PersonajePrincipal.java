package com.dungeonmvc.models;

import java.util.ArrayList;

import com.dungeonmvc.GameManager;
import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.models.Board.Direction;
import com.dungeonmvc.utils.Vector2;

public class PersonajePrincipal extends Persona {
    ArrayList<Observer> observers;
    String portrait;
    String leftHand;
    String rightHand;
    Inventory inventory;

    public PersonajePrincipal(Vector2 start, Inventory inventory, String nombre, int Velocidad, String image,
            int Resistencias,
            int Fuerza, int PuntosVida, String portrait, String leftHand, String rightHand) {
        super(portrait, start, nombre, Fuerza, Velocidad, image, Resistencias, Fuerza);
        observers = new ArrayList<>();
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.portrait = portrait;
        this.inventory = inventory;

    }

    public String getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(String leftHand) {
        this.leftHand = leftHand;
    }

    public String getRightHand() {
        return rightHand;
    }

    public void setRightHand(String rightHand) {
        this.rightHand = rightHand;
    }

    public String getPortrait() {
        return portrait;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
    }

    public void suscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsuscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        observers.forEach(x -> x.onChange());
    }

    @Override
    public void move(Direction direction) {
        Board board  = GameManager.getInstance().getBoard();
        Vector2 destino = board.getDestination(position, direction);

        if (destino.getX() >= 0 && destino.getX() < board.getSize() && destino.getY() >= 0
                && destino.getY() < board.getSize()) {
            if (board.board()[destino.getX()][destino.getY()].getIsFloor()) {
                setPosition(destino);
            }
           
        }
         
        board.notifyObservers();
    }

}
