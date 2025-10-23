/* Trabajo realizado por Marcos Coszion (332945) y Francisco Lino (347691)*/
package MedioTateti;

import java.util.*;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

//System.out.println("●");
//System.out.println("○");
public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    public static Sistema sistema = new Sistema();
    public static void main(String args[]){
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException ex) {
            System.getLogger(Menu.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        mostrarMenu();
        int opcion = scanner.nextInt();
        scanner.nextLine();
        while(opcion != 5){
            switch (opcion){
                case 1: registrarJugador();
                    break;
                case 2: //comienzo de partida comun
                    
                    break;
                case 3: //continuacion de partida
                    
                    break;
                case 4: //mostrar ranking e invictos
                    
                    break;
                default: 
                    System.out.println("Opción incorrecta, Reingrese");
            }
            opcion = scanner.nextInt();
            scanner.nextLine();
        }
    }
    
    public static void mostrarMenu(){
        System.out.println("Trabajo desarrollado por: Marcos Coszion (332945) y Fransisco Lino (347691)");
        System.out.println("-------------------------------");
        System.out.println(" MEDIO TATETI - MENU PRINCIPAL");
        System.out.println("-------------------------------");
        System.out.println("");
        System.out.println("1. Registrar jugador");
        System.out.println("2. Jugar partida normal");
        System.out.println("3. Continuación de partida");
        System.out.println("4. Mostrar ranking e invictos");
        System.out.println("5. Fin");
    }
    
    public static void registrarJugador(){
        String nombreJugador = "";
        int edadJugador = -1;
        boolean valido = false;
        
        while(!valido){
            System.out.println("Ingrese el nombre del jugador:");
            nombreJugador = scanner.nextLine();
            if(nombreJugador.length() == 0){
                System.out.println("Nombre vacio, Reingrese");
            }else{
                if(sistema.existeJugador(nombreJugador)){
                    System.out.println("Nombre existente, Reingrese");
                }else{
                    valido = true;
                }
            }
        }
        
        System.out.println("ingrese edad del jugador");
        edadJugador = scanner.nextInt();
        scanner.nextLine();
        while(edadJugador <= 0){
            System.out.println("edad invalida, Reingrese");
            edadJugador = scanner.nextInt();
            scanner.nextLine();
        }
        
        Jugador nuevoJugador = new Jugador(nombreJugador, edadJugador);
        sistema.agregarJugador(nuevoJugador);
        System.out.println("Jugador Registrado");
    }
    
    private static void jugarPartidaNormal(){
        if(sistema.getJugadores().size()<2){
            System.out.println("Debe haber al menos 2 jugadores registrados");
        }else{
            sistema.ordenarJugadoresPorNombre();
            ArrayList<Jugador> lista = sistema.getJugadores();
            mostrarJugadoresNumerados(lista);
            
            Jugador jugadorX = null;
            Jugador jugadorO = null;
            
            jugadorX = elegirJugadorPorNumero(lista, "JUGADOR X");
            boolean distinto = false;
            while(!distinto){
                jugadorO = elegirJugadorPorNumero(lista, "JUGADOR O");
                if(jugadorO == jugadorX){
                    System.out.println("Debe elegir jugadores diferentes. Reintente JUGADOR O");
                }else{
                    distinto = true;
                }
            }
            
            Partida partida = new Partida(jugadorX, jugadorO);
            Tablero tablero = new Tablero();
            System.out.println("Comieza jugando: X = "+jugadorX.getNombre()+" vs O = "+jugadorO.getNombre());
            imprimirTablero(tablero);
            while(!partida.estaFinalizado()){
                System.out.println("Juega "+jugadorX.getNombre());
                System.out.println("Ingrese la jugada: ");
                String jugada = scanner.nextLine();
                //aca van cosistas
                
                
                
            }
        }
    }
    
    private static void imprimirTablero(Tablero unTablero){
        String[] NC = {" ●","● "," ●"};
        String[] ND = {"● "," ●","● "};
        String[] BC = {" ○","○ "," ○"};
        String[] BD = {"○ "," ○","○ "};
        
        String[][] valorPiezas = unTablero.matrizPieza(unTablero);
        for (int i = 0; i < valorPiezas.length; i++) {
            System.out.println("+--+--+--+--+--+--+");
            for(int k = 0; k < 3; k++){
                String renglon = "|";
                for (int j = 0; j < valorPiezas[0].length; j++) {                
                    if(valorPiezas[i][j].charAt(0) == 'N' && valorPiezas[i][j].charAt(1) == 'C'){
                        renglon += NC[k];
                    }else{
                        if(valorPiezas[i][j].charAt(0) == 'N' && valorPiezas[i][j].charAt(1) == 'D'){
                            renglon += ND[k];
                        }else{
                            if(valorPiezas[i][j].charAt(0) == 'B' && valorPiezas[i][j].charAt(1) == 'C'){
                                renglon += BC[k];
                            }else{
                                if(valorPiezas[i][j].charAt(0) == 'B' && valorPiezas[i][j].charAt(1) == 'D'){
                                    renglon += BD[k];
                                }else{
                                    renglon += "  |";
                                }
                            }
                        }
                    }
                }
                System.out.println(renglon);
            }
        }
        System.out.println("+--+--+--+--+--+--+");
    }
    
    //debe haber otra forma de hacerlo
    private static void imprimirTableroConBordes(Tablero unTablero){
        String[] NC = {" ●","● "," ●"};
        String[] ND = {"● "," ●","● "};
        String[] BC = {" ○","○ "," ○"};
        String[] BD = {"○ "," ○","○ "};
        
        String[][] valorPiezas = unTablero.matrizPieza(unTablero);
        String[] letrasBordes = {"A","B","C"};
        for (int i = 0; i < valorPiezas.length; i++) {
            System.out.println("  1  2  3  4  5  6");
            System.out.println("+--+--+--+--+--+--+");
            for(int k = 0; k < 3; k++){
                String renglon = "|";
                if(k == 1){
                    renglon = letrasBordes[i]+"|";
                }
                for (int j = 0; j < valorPiezas[0].length; j++) {                
                    if(valorPiezas[i][j].charAt(0) == 'N' && valorPiezas[i][j].charAt(1) == 'C'){
                        renglon += NC[k];
                    }else{
                        if(valorPiezas[i][j].charAt(0) == 'N' && valorPiezas[i][j].charAt(1) == 'D'){
                            renglon += ND[k];
                        }else{
                            if(valorPiezas[i][j].charAt(0) == 'B' && valorPiezas[i][j].charAt(1) == 'C'){
                                renglon += BC[k];
                            }else{
                                if(valorPiezas[i][j].charAt(0) == 'B' && valorPiezas[i][j].charAt(1) == 'D'){
                                    renglon += BD[k];
                                }else{
                                    renglon += "  |";
                                }
                            }
                        }
                    }
                }
                System.out.println(renglon);
            }
        }
        System.out.println("+--+--+--+--+--+--+");
    }
    
    private static void mostrarJugadoresNumerados(ArrayList<Jugador> lista){
        System.out.println("JUGADORES:");
        for (int i = 0; i < lista.size(); i++) {
            int numero = i+1;
            System.out.println(numero + ") "+ lista.get(i).getNombre());
        }
    }
    
    private static int leerNumeroJugador(int minimo, int maximo){
        int numeroElegido = -1;
        boolean valido = false;
        while(!valido){
            System.out.println("Elija un jugador por el numero");
            if(scanner.hasNextInt()){
                numeroElegido = scanner.nextInt();
                scanner.nextLine();
                if(numeroElegido >= minimo){
                    if(numeroElegido <= maximo){
                        valido = true;
                    }else{
                        System.out.println("Fuera de rango. Reingresar");
                    }
                }else{
                    System.out.println("Fuera de rango. Reingresar");
                }
            }else{
                System.out.println("Se debe ingresar un número");
                scanner.nextLine();
            }
        }
        return numeroElegido;
    }
    
    private static Jugador elegirJugadorPorNumero(ArrayList<Jugador> lista, String etiqueta){
        Jugador elegido = null;
        int maximo = lista.size();
        System.out.println("Seleccion el "+etiqueta+" :");
        int numero = leerNumeroJugador(1, maximo);
        elegido = lista.get(numero -1);
        return elegido;
    }
    
}
