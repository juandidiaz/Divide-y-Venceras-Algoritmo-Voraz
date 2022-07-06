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
public class Trio {

    private Punto p1, p2, p3, puntoComun;
    private double dmin;

    public Trio(Punto p1, Punto p2, Punto p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        PuntoComun(p1, p2, p3);
    }

    public Punto getP1() {
        return p1;
    }

    public Punto getP2() {
        return p2;
    }

    public Punto getP3() {
        return p3;
    }

    public void PuntoComun(Punto a, Punto b, Punto c) {
        double aabc, abbc, abcc;

        aabc = a.distancia(b) + a.distancia(c);//Punto A

        abbc = b.distancia(a) + b.distancia(c);//Punto B

        abcc = c.distancia(a) + c.distancia(b);//Punto C

        if (aabc <= abbc && aabc <= abcc) {
            dmin = aabc;
            puntoComun = a;
        } else if (abbc <= aabc && abbc <= abcc) {
            this.dmin = abbc;
            this.puntoComun = b;
        } else if (abcc <= aabc && abcc <= abbc) {
            this.dmin = abcc;
            this.puntoComun = c;
        }
    }

    public Punto getPuntoComun()
    {
        return puntoComun;
    }
    
    public double getDistancia() {
        return this.dmin;
    }

}
