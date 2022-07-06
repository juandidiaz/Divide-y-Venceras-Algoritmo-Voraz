/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1amc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static practica1amc.Algoritmos.Dijkstra;

/**
 *
 * @author Usuario
 */
public class Fichero {

    public static Punto[] leerArchivo(String archivo) {
        Punto[] puntos = null;
        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            boolean coordenadas = false;
            while ((linea = br.readLine()) != null) {
                if (!linea.equals("EOF") && !linea.equals("")) {
                    if (coordenadas) {
                        String[] parts = linea.split(" ");
                        puntos[i++] = new Punto(Double.parseDouble(parts[1].trim()), Double.parseDouble(parts[2].trim()));
                    } else {
                        if (linea.contains("DIMENSION")) {
                            String[] parts = linea.split(":");
                            puntos = new Punto[Integer.parseInt(parts[1].trim())];
                        } else if (linea.equals("NODE_COORD_SECTION")) {
                            coordenadas = true;

                        }
                    }
                }
            }
            br.close();
            return puntos;
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return puntos;
    }

    public static void escribirFichero(String nombre, Punto[] nodos,int origen,int[] sol,int coste,String[] caminosnodos) throws IOException {
        
        int cos=0;
        String ruta = "./src/dataset/";
        String archivo = ruta + nombre+".tsp";
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        bw.write("NAME : " + nombre + "\n");
        bw.write("TYPE : TOUR\n");
        bw.write("DIMENSION : " + nodos.length + "\n");
        bw.write("SOLUTION: "+coste+"\n");
        bw.write("TOUR_SECTION\n");
        for(int i=0;i<caminosnodos.length;i++)
        {
            
            bw.write(sol[i]+"-"+caminosnodos[i]+"\n");
        }
        bw.write("-1\n");
        bw.write("EOF\n");
        bw.close();
    }

     
     
}
