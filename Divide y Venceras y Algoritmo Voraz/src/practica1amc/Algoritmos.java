/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1amc;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author Usuario
 */
public class Algoritmos {

    public static Trio exhaustivo(Punto[] p, int inicio, int fin) {

        Trio sol = new Trio(p[0], p[1], p[2]);

        for (int i = inicio; i <= fin; i++) {

            for (int j = i + 1; j <= fin; j++) {

                for (int k = j + 1; k <= fin; k++) {

                    Trio aux = new Trio(p[i], p[j], p[k]);
                    if (aux.getDistancia() < sol.getDistancia()) {
                        sol = aux;
                    }
                }
            }
        }

        return sol;
    }

    public static Trio DyV(Punto[] T, int izq, int der) {

        Trio tmin;
        double distancia;

        if (der - izq + 1 < 6) {
            return exhaustivo(T, izq, der);
        }

        int mitad = (der + izq) / 2;
        Trio ti = DyV(T, izq, mitad);
        Trio td = DyV(T, mitad + 1, der);

        if ((ti.getDistancia() < td.getDistancia())) {
            distancia = ti.getDistancia();
            tmin = ti;
        } else {
            distancia = td.getDistancia();
            tmin = td;
        }

        int i, j;

        for (i = mitad; i >= izq; i--) {
            if ((T[mitad + 1].getx() - T[i].getx()) > distancia) {
                break;
            }
        }

        for (j = mitad + 1; j <= der; j++) {
            if (T[j].getx() - T[mitad].getx() > distancia) {
                break;
            }
        }

        for (int k = i + 1; k <= mitad; k++) {
            for (int h = mitad + 1; h < j; h++) {
                for (int m = h + 1; m < j; m++) {
                    Trio aux = new Trio(T[k], T[h], T[m]);
                    if (aux.getDistancia() < tmin.getDistancia()) {
                        tmin = aux;
                    }
                }
            }
        }

        for (int k = i + 1; k <= mitad; k++) {
            for (int h = k + 1; h <= mitad; h++) {
                for (int m = mitad + 1; m < j; m++) {
                    Trio aux = new Trio(T[k], T[h], T[m]);
                    if (aux.getDistancia() < tmin.getDistancia()) {
                        tmin = aux;
                    }
                }
            }
        }

        return tmin;
    }

    public static void quicksort(Punto[] T) {
        quicksort(T, 0, T.length - 1);
    }

    private static void quicksort(Punto[] T, int izq, int der) {

        if (izq < der) {
            int q = partition(T, izq, der);
            quicksort(T, izq, q);
            quicksort(T, q + 1, der);
        }

    }

    @SuppressWarnings("empty-statement")
    private static int partition(Punto[] T, int izq, int der) {
        Punto p = T[izq];
        int i = izq - 1;
        int j = der + 1;

        for (;;) {
            while (p.getx() < T[--j].getx());
            while (T[++i].getx() < p.getx());
            if (i >= j) {
                return j;
            }
            Punto aux = new Punto(T[i].getx(), T[i].gety());
            T[i] = T[j];
            T[j] = aux;

        }

    }

    public static Punto[] generarAleatorio(int n) {
        Punto[] p = new Punto[n];
        Random random1, random2;
        double num1, num2;
        for (int i = 0; i < n; i++) {
            random1 = new Random();
            random2 = new Random();
            num1 = 1 + 255 * random1.nextDouble();
            num2 = 1 + (255) * random2.nextDouble();
            p[i] = new Punto(num1, num2);
        }
        return p;

    }

    public static int[] Dijkstra(Punto[] p, int punto,String []caminosnodos) {
        int[] distancias = new int[p.length];
        boolean[] procesado = new boolean[p.length];
        String[] camino = new String[p.length];
        int u = 0;

        //Generacion de Matriz de Adyacencia  
        Matriz l = new Matriz(p.length);

        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p.length; j++) {
                l.agregar(Integer.MAX_VALUE / 2, i, j);
            }
        }

        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                double distancia = p[i].distancia(p[j]);
                int peso = (int) ((distancia * 100) % 100) + 1;
                l.agregar(peso, i, j);
            }
        }

        //Rellenamos el array distancia con la primera fila de la matriz de adyacencia
        //y marcamos como no procesados todos los vertices
        for (int i = 0; i < p.length; i++) {
            distancias[i] = l.valor(0, i);
            procesado[i] = false;
            camino[i] = punto + "";
        }

        //La distancia desde el origen a s mismo siempre sera 0
        distancias[punto] = 0;

        //Buscamos el camino mas corto hacia todos los vertices
        for (int j = 0; j < p.length-1; j++) {

            //Buscamos el nodo minimo en el array distancias
            int minimo = Integer.MAX_VALUE;

            for (int i = 0; i < p.length; i++) {
                if ((!procesado[i]) && (distancias[i] < minimo)) {
                    minimo = distancias[i];
                    u = i;
                }
            }

            //Marcamos como procesado el nodo minimo elegido del vector distancias
            procesado[u] = true;

            //Actualizamos distancias fijandonos en los adyacentes al vertice u
            for (int v = 0; v < p.length; v++) {

                if ((!procesado[v]) && (distancias[v] >= distancias[u] + l.valor(u, v))) {

                    camino[v] = camino[u] + "," + v;

                    distancias[v] = distancias[u] + l.valor(u, v);

                }

            }
        }

        for (int i = 0; i < p.length; i++) {

            
            System.out.println("Nodo "+i+" "+camino[i]);

        }
        
        for(int i=0;i<p.length;i++)
        caminosnodos[i]=camino[i];

        return distancias;
    }

}
