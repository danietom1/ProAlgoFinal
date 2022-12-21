package com.example.profinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ProAlgo {

    public static Map<String, Integer> Nombre_Numero = new HashMap<>();
    public static Map<Integer, String> Numero_Nombre = new HashMap<>();
    public static Stack<Number> pilaEstaciones = new Stack<Number>();
    public static Stack<Number> pilaRutas = new Stack<Number>();
    public static Stack<Number> pilaDeltas = new Stack<Number>();
    public static int[] d;
    public static int[] pi;
    public static String[] state;
    public static int[] Rutas;

    public static void main(String[] args) {
        String FilePath = "src/Test.txt";
        boolean testMode = false;
        ArrayList[] grafo;

        if (testMode){
            grafo = GrafoTest(true);
        } else {
            grafo = LeerArchivo(FilePath);
        }

        System.out.println("Hello world!");
        System.out.println();

        Dijsktra(grafo,"A");

        reconstruirRuta("F");
    }



    public static ArrayList[] LeerArchivo(String FilePath) {


        //Hallar cantidad de vertices del
        int cantVertices = cantidadVertices(FilePath);

        ArrayList[] grafo = new ArrayList[cantVertices];

        ArrayList<int[]> aristas;

        int arista[];

        try (Scanner scanner = new Scanner(new File(FilePath))) {
            while (scanner.hasNext()){
                int VerticeOrigen = 0;
                int VerticeDestino = 0;
                int Distancia = 0;
                int Ruta = 0;
                int countLecturas = 1;
                boolean hallarOrigen = true;
                String[] inputText = scanner.nextLine().split(" ");
                if(inputText.length != 1){

                    int count=0;
                    for (int i = 0; i < inputText.length; i++){
                        //System.out.print(inputText[i].toString()+" ");
                        if (i == 0){
                            Ruta = Integer.parseInt(inputText[i]);
                            continue;
                        }

                        switch (countLecturas){
                            case 1:
                                if (/*VerticeOrigen == 0*/hallarOrigen){
                                    if (Nombre_Numero.containsKey(inputText[i].toString())){
                                        VerticeOrigen = Nombre_Numero.get(inputText[i].toString());
                                    }else {
                                        Nombre_Numero.put(inputText[i].toString(),count);
                                        Numero_Nombre.put(count,inputText[i].toString());
                                        VerticeOrigen = Nombre_Numero.get(inputText[i].toString());
                                        count++;
                                    }
                                } else {
                                    i--;
                                }
                                break;
                            case 2:
                                Distancia = Integer.parseInt(inputText[i].toString());
                                break;
                            case 3:
                                if (Nombre_Numero.containsKey(inputText[i].toString())){
                                    VerticeDestino = Nombre_Numero.get(inputText[i].toString());
                                }else {
                                    Nombre_Numero.put(inputText[i].toString(),count);
                                    Numero_Nombre.put(count,inputText[i].toString());
                                    VerticeDestino = Nombre_Numero.get(inputText[i].toString());
                                    count++;
                                }
                                arista = new int[] {VerticeDestino,Distancia,Ruta};

                                aristas = grafo[VerticeOrigen];

                                if (aristas == null){
                                    aristas = new ArrayList<>();
                                    aristas.add(arista);
                                }else {
                                    aristas.add(arista);
                                }

                                grafo[VerticeOrigen] = aristas;

                                VerticeOrigen = VerticeDestino;
                                VerticeDestino = 0;
                                Distancia = 0;
                                countLecturas = 0;
                                hallarOrigen = false;
                                break;

                        }

                        countLecturas++;

                    }

                }

            }
            MostrarGrafo(grafo);
            return grafo;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static int cantidadVertices(String FilePath){
        int count = 0;
        try (Scanner scanner = new Scanner(new File(FilePath))) {
            while (scanner.hasNext()){
                String[] inputText = scanner.nextLine().split(" ");
                for (int i = 1; i < inputText.length; i++){
                    if (!isParsable(inputText[i].toString())){
                        if (!Nombre_Numero.containsKey(inputText[i].toString())){
                            Nombre_Numero.put(inputText[i].toString(),count);
                            Numero_Nombre.put(count,inputText[i].toString());
                            count++;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public static ArrayList[] GrafoTest(boolean showgrafo){

        ArrayList[] grafo = new ArrayList[9];
        ArrayList<int[]> vertices = new ArrayList<int[]>();

        //Aristas nodo A/0
        int[][] aristas = {{1, 60,3},{6, 150,1}};
        
        vertices = new ArrayList<int[]>();
        for (int[] arista:aristas
             ) {            
            vertices.add(arista);
            
        }
        grafo[0] = vertices;

        //Aristas nodo B/1
        aristas = new int[][] {{2, 90, 3},{0,180,4}};

        vertices = new ArrayList<int[]>();
        for (int[] arista:aristas
        ) {
            vertices.add(arista);
        }
        grafo[1] = vertices;
        
        //Aristas nodo C/2
        aristas = new int[][] {{3, 140, 3},{1,220,4}};

        vertices = new ArrayList<int[]>();
        for (int[] arista:aristas
        ) {
            vertices.add(arista);
        }
        grafo[2] = vertices;

        //Aristas nodo D/3
        aristas = new int[][] {{4, 100, 3},{2,130,4}};

        vertices = new ArrayList<int[]>();
        for (int[] arista:aristas
        ) {
            vertices.add(arista);
        }
        grafo[3] = vertices;

        //Aristas nodo E/4
        aristas = new int[][] {{5, 180, 2},{3,110,4}};

        vertices = new ArrayList<int[]>();
        for (int[] arista:aristas
        ) {
            vertices.add(arista);
        }
        grafo[4] = vertices;
        
        //Aristas nodo F/5
        aristas = new int[][] {{}};

        vertices = new ArrayList<int[]>();
        for (int[] arista:aristas
        ) {
            vertices.add(arista);
        }
        grafo[5] = vertices;

        //Aristas nodo Z/6
        aristas = new int[][] {{7,150,2},{7,120,1}};

        vertices = new ArrayList<int[]>();
        for (int[] arista:aristas
        ) {
            vertices.add(arista);
        }
        grafo[6] = vertices;

        //Aristas nodo X/7
        aristas = new int[][] {{8,130,2},{8,95,1}};

        vertices = new ArrayList<int[]>();
        for (int[] arista:aristas
        ) {
            vertices.add(arista);
        }
        grafo[7] = vertices;

        //Aristas nodo Z/8
        aristas = new int[][] {{4,220,2}};

        vertices = new ArrayList<int[]>();
        for (int[] arista:aristas
        ) {
            vertices.add(arista);
        }
        grafo[8] = vertices;
        
        if (showgrafo){
            System.out.print("[");
            for (ArrayList<int[]> verticesTemp: grafo
                 ) {
                System.out.print("[");
                for (int[] verticeTemp: verticesTemp
                     ) {
                    System.out.print("(");
                    for (int temp:verticeTemp
                         ) {
                        System.out.print(temp+" ");
                    }
                    System.out.print(") ");
                }
                System.out.println("]");
            }
        }

        return grafo;
    }

    private static void MostrarGrafo(ArrayList[] grafo){
        System.out.println();
        System.out.print("[");
        for (ArrayList<int[]> verticesTemp: grafo
        ) {
            if(verticesTemp != null){
                System.out.print("[");
                for (int[] verticeTemp: verticesTemp
                ) {
                    System.out.print("(");
                    for (int temp:verticeTemp
                    ) {
                        System.out.print(temp+" ");
                    }
                    System.out.print(") ");
                }
                System.out.println("]");
            }else{
                System.out.println("[()]");
            }
        }
    }

    private static void Dijsktra(ArrayList[] grafo, String origenText){
        int origen = Nombre_Numero.get(origenText);
        int n = grafo.length;

        //Arreglo Delta
        d = new int[n];
        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.MAX_VALUE;
        }
        d[origen] = 0;

        //Arreglo Pi
        pi = new int[n];

        //Arreglo estado
        state = new String[n];
        for (int i = 0; i < state.length; i++) {
            state[i] = "N";
        }
        state[origen] = "D";

        //Arreglo rutas
        Rutas = new int[n];


        //Cola de prioridad
        PriorityQueue<Map.Entry<Integer, Integer>> Q = new PriorityQueue<>(Map.Entry.comparingByValue());

        Q.offer(new AbstractMap.SimpleEntry<>(origen, 0));



        while (!Q.isEmpty()){
            Map.Entry<Integer, Integer> verticeTemp = Q.remove();
            int DeltaTemp = verticeTemp.getValue();
            if (state[verticeTemp.getKey()] == "E") continue;
            ArrayList<int[]> vertice = grafo[verticeTemp.getKey()];
            if (vertice != null){
                for (int[] vert:vertice
                ) {
                    int TransTime = 0;
                    if(state[vert[0]] == "E") continue;
                    if((origen != verticeTemp.getKey()) && (vert[2] != Rutas[verticeTemp.getKey()])){
                        TransTime = 180;

                    }else {
                        TransTime = 0;
                    }
                    if (d[vert[0]] > d[verticeTemp.getKey()]+vert[1]+TransTime){
                        d[vert[0]] = d[verticeTemp.getKey()]+vert[1]+TransTime;
                        pi[vert[0]] = verticeTemp.getKey();
                        state[vert[0]] = "D";
                        Rutas[vert[0]] = vert[2];
                        Q.offer(new AbstractMap.SimpleEntry<>(vert[0], d[vert[0]]));
                    }
                }
                state[verticeTemp.getKey()] = "E";
            }

        }

        System.out.print("        ");
        for (int i = 0; i < pi.length; i++) {
            System.out.print(Numero_Nombre.get(i)+"   ");
        }
        System.out.println();
        System.out.print("Delta:   ");
        for (Object obj:d
             ) {
            System.out.print(obj+" ");
        }
        System.out.println();
        System.out.print("Pi:     ");
        for (Object obj:pi
             ) {
            System.out.print(Numero_Nombre.get(obj)+"   ");
        }
        System.out.println();
        System.out.print("Estado: ");
        for (Object obj:state
             ) {
            System.out.print(obj+"   ");
        }

        System.out.println();
        System.out.print("Rutas:  ");
        for (Object obj:Rutas
        ) {
            System.out.print(obj+"   ");
        }
    }

    private static void reconstruirRuta(String dest){
        int destino = Nombre_Numero.get(dest);

        try{
            while (true){
                pilaEstaciones.add(destino);
                pilaRutas.add(Rutas[destino]);
                if(d[destino]==Integer.MAX_VALUE) throw new Exception("Exception message");;
                destino = pi[destino];
                if (destino == 0) {
                    pilaEstaciones.add(destino);
                    break;
                }
            }
            System.out.println();
            System.out.println();
            System.out.println("Ruta Final");
            while(!pilaEstaciones.isEmpty()){
                System.out.print(Numero_Nombre.get(pilaEstaciones.pop())+" ");
            }
            System.out.println();
            System.out.print("  ");
            while (!pilaRutas.isEmpty()){
                System.out.print(pilaRutas.pop()+" ");
            }

        } catch (Exception e) {
            System.out.println();
            System.out.println();
            System.out.println("Ruta Final");
            System.out.println("No hay ruta disponible");
            //throw new RuntimeException(e);

        }

    }
}

