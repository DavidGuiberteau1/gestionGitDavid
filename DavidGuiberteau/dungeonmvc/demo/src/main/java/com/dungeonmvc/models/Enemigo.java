package com.dungeonmvc.models;
import java.util.ArrayList;
import java.util.Random;

import com.dungeonmvc.GameManager;
import com.dungeonmvc.enums.Habilidades;
import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.models.Board.Direction;
import com.dungeonmvc.utils.Vector2;

public class Enemigo extends Persona{
    private ArrayList<Observer> observers;

    String GradoMaldad;
    int Percepcion;
    int trackingRange;
    ArrayList <Habilidades> habilidades_enemigo;

    private Board board;
    
    public void añadirhabilidad(Habilidades habilidad){
       habilidades_enemigo.add(habilidad);
    }


    public Enemigo(String portrait, String image, Vector2 start, String nombre,
            int PuntosVida, int Velocidad, int Resistencias, int Percepcion, int Fuerza, String GradoMaldad,int trackingRange) {
        super(portrait, start, nombre, PuntosVida, Velocidad, image, Resistencias, Fuerza);
        this.GradoMaldad = GradoMaldad;
         habilidades_enemigo= new ArrayList<>();
    }

    public void setGradoMaldad(String gradoMaldad) {
        this.GradoMaldad = gradoMaldad;
    }
    public String getGradoMaldad() {
        return GradoMaldad;
    }
    public void suscribe(Observer observer){
        observers.add(observer);
    }

    public void unsuscribe(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        observers.forEach(x -> x.onChange());
    }
    public void setPercepcion(int Percepcion){
        this.Percepcion= Percepcion;
    }
    public int getPercepcion() {
        return Percepcion;
    }

    public int getTrackingRange() {
        return trackingRange;
    }
    public void setTrackingRange(int trackingRange) {
        this.trackingRange = trackingRange;
    }
    public void setBoard(Board board){
        this.board=board;
    }

    public void moveEnemy() {
        Vector2 enemyPos = this.position;
        Vector2 playerPos = GameManager.getInstance().getPlayer().getPosition();
        double distancia = enemyPos.distance(playerPos);

       

        if (distancia <= Percepcion) {
            System.out.println("Persiguiendo al jugador...");
            // Movimiento para seguir al jugador
            Direction direction = getDirectionTowardsPlayer(enemyPos, playerPos);
            moveInDirection(direction);
        } else {
            System.out.println("Movimiento aleatorio...");
            // Movimiento Aleatorio
            moveRandomly();
        }
    }

    private Direction getDirectionTowardsPlayer(Vector2 enemyPos, Vector2 playerPos) {
        int dx = playerPos.getX() - enemyPos.getX();
        int dy = playerPos.getY() - enemyPos.getY();

        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? Direction.RIGHT : Direction.LEFT;
        } else {
            return dy > 0 ? Direction.DOWN : Direction.UP;
        }
    }

    private void moveInDirection(Direction direction) {
        Board board = GameManager.getInstance().getBoard();
        Vector2 destino = board.getDestination(this.position, direction);

      
        if (destino.getX() >= 0 && destino.getX() < board.getSize() &&
            destino.getY() >= 0 && destino.getY() < board.getSize() &&
            board.isFloor(destino)) {
            setPosition(destino);
            
        } else {
    
            moveRandomly(); // Intentar un movimiento aleatorio si no puede moverse en la dirección deseada
        }
    }

    private void moveRandomly() {
        Direction[] directions = Direction.values();
        Random random = new Random();
        Board board = GameManager.getInstance().getBoard();

        for (int i = 0; i < directions.length; i++) {
            Direction randomDirection = directions[random.nextInt(directions.length)];
            Vector2 destino = board.getDestination(this.position, randomDirection);

            if (destino.getX() >= 0 && destino.getX() < board.getSize() &&
                destino.getY() >= 0 && destino.getY() < board.getSize() &&
                board.isFloor(destino)) {
                setPosition(destino);
                
                return;
            }
        }
        
    }
}
