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
                case 1: //registrar jugador
                    
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
        System.out.println("Ingrese nombre del jugador");
        String nombreJugador = scanner.nextLine();
        System.out.println("Ingrese la edad del jugador");
        int edadJugador = scanner.nextInt();
        scanner.nextLine();
       
        for (int i=0; i < sistema.jugadores.size(); i++) {
            while(sistema.jugadores.get(i).getNombre() == nombreJugador){
                System.out.println("Nombre existente, reingrese");
                nombreJugador = scanner.nextLine();
            }
            Jugador jugador = new Jugador(nombreJugador, edadJugador);
            sistema.jugadores.add(jugador);
        }
        
    }
    
}
