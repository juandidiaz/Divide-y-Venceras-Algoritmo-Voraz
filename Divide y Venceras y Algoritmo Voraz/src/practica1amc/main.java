/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1amc;

import java.io.IOException;
import java.util.Scanner;
import static practica1amc.Algoritmos.*;
import static practica1amc.Fichero.escribirFichero;

/**
 *
 * @author Usuario
 */
public class main {

    public static void main(String[] args) throws IOException {
        Trio T;
        String[] caminosnodos;
        Punto[] p = null, nodos = null;
        int[] solD;
        Punto p1, p2, p3;
        Matriz m = null;
        Scanner sc = new Scanner(System.in), n = new Scanner(System.in);
        int opcion, forma = 0, numal, partepractica = 0, coste = 0, origen;

        do {
            System.out.println("¿Quiere ejecutar la primera parte de la practica(1) o la segunda(2)?");
            partepractica = sc.nextInt();
            switch (partepractica) {
                case 1:
                    System.out.println("Elija si quiere leer un archivo(1) o generar puntos aleatorios(2)");
                    do {

                        forma = n.nextInt();

                        switch (forma) {

                            case 1:
                                System.out.println("Escriba el nombre del fichero");
                                String nombre,
                                 ruta,
                                 archivo;
                                sc.nextLine();
                                nombre = sc.nextLine();
                                ruta = "./src/dataset/" + nombre + ".tsp/";
                                archivo = ruta + nombre + ".tsp";
                                p = Fichero.leerArchivo(archivo);
                                break;

                            case 2:
                                System.out.println("¿Cuantos puntos aleatorios quiere generar?");
                                numal = n.nextInt();
                                p = generarAleatorio(numal);
                                break;

                            default:
                                System.out.println("Escriba una de las opciones disponibles: 1 o 2");
                                break;

                        }
                    } while (forma != 1 && forma != 2);

                    do {
                        System.out.println("Elija DyV(1) o exhaustivo(2)");

                        opcion = sc.nextInt();
                        switch (opcion) {
                            case 1:
                                System.out.println("\n");
                                System.out.println("Por DyV");
                                quicksort(p);
                                T = DyV(p, 0, p.length - 1);
                                System.out.println("El mejor triangulo lo forman los siguientes puntos: ");
                                p1 = T.getP1();
                                p2 = T.getP2();
                                p3 = T.getP3();
                                System.out.println(p1.getx() + " " + p1.gety());
                                System.out.println(p2.getx() + " " + p2.gety());
                                System.out.println(p3.getx() + " " + p3.gety());
                                System.out.println("El punto más cercano a otros dos es " + T.getPuntoComun().getx() + " " + T.getPuntoComun().gety());

                                break;

                            case 2:
                                System.out.println("\n");
                                System.out.println("Por exhaustivo");
                                T = exhaustivo(p, 0, p.length - 1);
                                System.out.println("El mejor triangulo lo forman los siguientes puntos: ");
                                p1 = T.getP1();
                                p2 = T.getP2();
                                p3 = T.getP3();
                                System.out.println(p1.getx() + " " + p1.gety());
                                System.out.println(p2.getx() + " " + p2.gety());
                                System.out.println(p3.getx() + " " + p3.gety());
                                System.out.println("El punto más cercano a otros dos es " + T.getPuntoComun().getx() + " " + T.getPuntoComun().gety());

                                break;
                            default:
                                System.out.println("Escriba una de las opciones disponibles: 1 o 2");
                                break;

                        }
                    } while (opcion != 1 && opcion != 2);
                    break;

                case 2:

                    do {
                        System.out.println("Elija si quiere leer un archivo(1) o trabajar con datos aleatorios(2)");
                        forma = n.nextInt();
                        switch (forma) {
                            case 1:
                                origen = 0;
                                System.out.println("Escriba el nombre del fichero");
                                String nombre,
                                 ruta,
                                 archivo;
                                sc.nextLine();
                                nombre = sc.nextLine();
                                ruta = "./src/dataset/" + nombre + ".tsp/";
                                archivo = ruta + nombre + ".tsp";

                                nodos = Fichero.leerArchivo(archivo);
                                caminosnodos=new String[nodos.length];
                                solD = Dijkstra(nodos, 0,caminosnodos);

                                for (int i = 0; i < nodos.length; i++) {
                                    coste = coste + solD[i];
                                }

                                System.out.println("El vector distancias da como resultado");
                                for (int i = 0; i < nodos.length; i++) {
                                    System.out.print(solD[i] + " ");
                                }
                                System.out.println("");
                                System.out.println("Y la suma de los caminos minimos es " + coste);
                                
                             
                                int guardar = 0;

                                System.out.println("¿Desea guardar los resultados en un fichero? (1)Si, (2)No");
                                do {
                                    guardar = n.nextInt();
                                    switch (guardar) {
                                        case 1:

                                            String nomFichero = "";
                                            System.out.println("Introduzca el nombre del fichero que quiere guardar");
                                            nomFichero = sc.nextLine();
                                            nomFichero = nomFichero;
                                            escribirFichero(nomFichero, nodos, origen, solD, coste,caminosnodos);

                                            break;

                                        case 2:
                                            System.out.println("Fin del programa");
                                            break;

                                        default:
                                            System.out.println("Seleccione una de las opciones validas (1) o (2)");
                                    }
                                } while (guardar != 1 && guardar != 2);

                                break;

                            case 2:
                                System.out.println("¿Cuantos puntos aleatorios quiere generar?");
                                numal = n.nextInt();
                                nodos = generarAleatorio(numal);
                                caminosnodos=new String[nodos.length];
                                System.out.println("Seleccione el origen:");
                                origen = n.nextInt();
                                solD = Dijkstra(nodos, origen,caminosnodos);

                                System.out.println("El vector distancias da como resultado");
                                for (int i = 0; i < nodos.length; i++) {
                                    System.out.print(solD[i] + " ");
                                    coste = coste + solD[i];
                                }
                                System.out.println("");
                                System.out.println("Y la suma de los caminos minimos es " + coste);
                                

                                int guardar2 = 0;
                                System.out.println("¿Desea guardar los resultados en un fichero? (1)Si, (2)No");
                                do {
                                    guardar2 = n.nextInt();
                                    switch (guardar2) {
                                        case 1:
                                            String nomFichero = "";
                                            System.out.println("Introduzca el nombre del fichero que quiere guardar");
                                            sc.nextLine();
                                            nomFichero = sc.nextLine();
                                            escribirFichero(nomFichero, nodos, origen, solD, coste,caminosnodos);

                                            break;

                                        case 2:
                                            System.out.println("Fin del programa");
                                            break;

                                        default:
                                            System.out.println("Seleccione una de las opciones validas (1) o (2)");
                                    }
                                } while (guardar2 != 1 && guardar2 != 2);

                                break;

                            default:
                                System.out.println("Introduzca una de las opciones validas(1) o (2)");
                                break;
                        }
                    } while (forma != 1 && forma != 2);

                    break;

                default:
                    System.out.println("Introduzca una de las opciones correctas(1) o (2)");
                    break;
            }
        } while (partepractica != 1 && partepractica != 2);

        if (partepractica == 2) {

        }
    }

}
