package com.dungeonmvc.models;

import java.util.ArrayList;

import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.models.Board.Direction;
import com.dungeonmvc.utils.Vector2;

public class Persona {
    ArrayList<Observer> observers;
    Vector2 position;
    String nombre;
    int Velocidad;
    String image;
    int Resistencias;
    String Percepcion;
    int Fuerza;
    int PuntosVida;
   String portrait;


    public Persona( String portrait,Vector2 start,String nombre,int PuntosVida, int Velocidad, String image, int Resistencias, int Fuerza){
        this.position = start;
        this.nombre = nombre;
        this.Velocidad = Velocidad;
        this.image = image;
        this.Resistencias = Resistencias;
        this.portrait = portrait;
        this.Fuerza = Fuerza;
        this.PuntosVida = PuntosVida;
    }

    
    public int getVelocidad() {
        return Velocidad;
    }
    public void setVelocidad(int Velocidad) {
        this.Velocidad = Velocidad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getResistencias() {
        return Resistencias;
    }
    public void setResistencias(int Resistencias) {
        this.Resistencias = Resistencias;
    }
    
    public int getFuerza() {
        return Fuerza;
    }
    public void setFuerza(int Fuerza) {
        this.Fuerza = Fuerza;
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

    public void setPosition(Vector2 position){
        this.position= position;
    }

    public Vector2 getPosition() {
        return position;
    }
    public int getPuntosVida(){
        return PuntosVida;
    }
    public void setPuntosVida(int PuntosVida){
        this.PuntosVida= PuntosVida;
    }

    public void move( Direction direction) {
        
    }
}
