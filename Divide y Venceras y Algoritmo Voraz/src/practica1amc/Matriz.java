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
public class Matriz {

    private int numelementos;
    private int[][] matriz;

    public Matriz(int n) {
        this.numelementos = n;
        matriz = new int[this.numelementos][this.numelementos];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = 0;
            }

        }
    }

    public void agregar(int peso, int i, int j) {

        matriz[i][j] = matriz[j][i] = peso;
    }

    public int valor(int i, int j) {
        int valor = matriz[i][j];
        return valor;
    }
}
