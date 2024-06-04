package com.dungeonmvc;

import java.util.ArrayList;



import com.dungeonmvc.models.*;
import com.dungeonmvc.models.Board.Direction;
import com.dungeonmvc.utils.Vector2;

public class GameManager {
    private static GameManager instance;
    PersonajePrincipal player;
    Inventory inventory;
    Board board;
    ArrayList<Persona> listapersonaje = new ArrayList<>();
    Enemigo enemigo;
    Enemigo enemigo2; // NUEVO

    private GameManager (){


    }
    public  ArrayList<Persona> getListapersonaje(){
        return listapersonaje;
        
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }


    public PersonajePrincipal getPlayer() {
        return this.player;
    }

    public Board getBoard() {
        return this.board;
    }

    public void newTurn(Direction direction){
        /* 
        for(Persona lista: listapersonaje){
            lista.move(direction);
        } */

        for(Persona entidad : listapersonaje) {
            if(entidad instanceof PersonajePrincipal) {
                entidad.move(direction);
            }
            else {
                ((Enemigo) entidad).moveEnemy();
            }
        }
    }

    public void testGame(){
        inventory = new Inventory();
        player = new PersonajePrincipal(new Vector2(0,0), inventory, "Personaje1", 350, "player", 100, 4, 0, "portrait", "item1", "item2");
        enemigo = new Enemigo("enemigo1", "enemigo1", new Vector2(6, 2), "enemigo1", 50, 270, 80, 5, 3,"Muy Malo",5);
        enemigo2 = new Enemigo("enemigo2", "enemigo2", new Vector2(13, 3), "enemigo2", 60, 300, 90, 6, 4, "Muy Malo",8);

        listapersonaje.add(player);
        listapersonaje.add(enemigo);
        listapersonaje.add(enemigo2);
        player.getInventory().addItem("item1");
        player.getInventory().addItem("item2");
        player.getInventory().addItem("item3");
        player.getInventory().addItem("item4");
        player.getInventory().addItem("item5");

        boolean[][] boardMatrix = {
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, false, false, false, false, false, false, false, false, false, true, false, false, false, true},
            {true, false, true, false, true, false, true, true, true, true, true, true, false, true, true},
            {true, false, true, true, true, false, true, false, false, false, false, false, false, false, true},
            {true, false, true, false, true, false, true, true, true, true, true, true, true, true, true},
            {true, false, false, false, true, false, false, false, false, true, false, false, false, false, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, false, false, false, true, false, false, false, false, false, false, false, false, false, true},
            {true, false, true, false, true,true, true, true, true, true, true, true, false, true, true},
            {true, false, true, false, true, false, false, false, false, true, false, false, false, false, true},
            {true, false, true, true, true, false, true, true, true, true, true, true, false, true, true},
            {true, false, false, false, false, false, true, false, false, false, false, false, false, false, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true}
        };
        
        board = new Board(boardMatrix.length,"floor","wall");
        for (int i = 0; i < boardMatrix.length; i++) {
            for (int j=0;j < boardMatrix[0].length;j++){
                board.newCell(new Vector2(i, j), boardMatrix[i][j]);
            }
        }
        
    }
}