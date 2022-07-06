/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1amc;

/**
 *
 * @author Usuario
 */
public class Punto {

    private double x, y;

    public Punto(double p1, double p2) {
        this.x = p1;
        this.y = p2;
    }

    public double getx() {
        return x;
    }

    public double gety() {
        return y;
    }

    public double distancia(Punto p1) {
        double distancia;
        distancia = Math.sqrt(Math.pow(x - p1.x, 2) + Math.pow(y - p1.y, 2));
        return distancia;
    }

}
