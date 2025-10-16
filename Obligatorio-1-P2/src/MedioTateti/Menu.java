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
        System.out.println("Jugador X(nombre): ");
        String nombreX = scanner.nextLine();
        System.out.println("Jugador O(nombre): ");
        String nombreO = scanner.nextLine();
        
        if(!sistema.existeJugador(nombreX) || !sistema.existeJugador(nombreO)){
            System.out.println("Alguno de los jugadores no existe.");
        }else{
            Jugador jugadorX = sistema.buscarJugador(nombreX);
            Jugador jugadorO = sistema.buscarJugador(nombreO);
            Partida partida = new Partida(jugadorX, jugadorO);
            while(!partida.estaFinalizado()){
                imprimirTablero(partida.getTablero());
                System.out.println("Juega "+ partida.darJugadaActual());
                System.out.println("Ingrese la jugada");
                // Jugada jugada = scanner.nextLine;
            }
        }
    }
    
    private static void imprimirTablero(Tablero unTablero){
        String[][] mat = unTablero.getCeldas();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
    
}
