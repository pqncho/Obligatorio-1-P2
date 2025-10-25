/* Trabajo realizado por Marcos Coszion (332945) y Francisco Lino (347691)*/
/*
de momento quedo hecha la opcion a, y la opcion b esta casi completa
de la opcion b queda: 
-Definir la jugada H, la ayuda(la hacemos al final igual)
-Hay que definir el metodo que verifica si hay una jugada ganadora en el tablero, osea si hay un ganador
    tablero.hayLinea


*/
package MedioTateti;

import java.util.*;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

//System.out.println("●");
//System.out.println("○");
public class Menu{
    
    public static Scanner scanner = new Scanner(System.in);
    public static Sistema sistema;
    public static boolean mostrarTitulo = true;
    private static final int ESTADO_OK = 0;
    private static final int ESTADO_FIN_RENUNCIA = 1;
    private static final int ESTADO_FIN_EMPATE = 2;
    private static final int ESTADO_REINGRESAR = 3;
    public static void main(String args[]){
        boolean valido=false;
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException ex) {
            System.getLogger(Menu.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        sistema = new Sistema();
        
        mostrarMenu();
        System.out.print("Ingrese su opcion (1-5) ");
        int opcion = 0;
        while(!valido){
            try{
                opcion=scanner.nextInt();
                scanner.nextLine();
                if(opcion<6 && opcion>0){
                    valido=true;
                }
            }catch(InputMismatchException e){
                System.out.println("Opcion incorrecta, reingrese");
                scanner.nextLine();
            }
        }
        while(opcion != 5){
            switch (opcion){
                case 1: 
                    registrarJugador();
                    mostrarMenu();
                    break;
                case 2:
                    jugarPartidaNormal();
                    mostrarMenu();
                    break;
                case 3: //continuacion de partida
                    
                    break;
                case 4:
                    mostrarRankingEInvictos();
                    mostrarMenu();
                    break;
                   
                default: 
                    System.out.println("Opción incorrecta, Reingrese");
            }
            opcion = scanner.nextInt();
            scanner.nextLine();
        }
       
    }
    
    private static void mostrarRankingEInvictos(){
        ArrayList<Jugador> lista = sistema.getJugadores();

        if (lista.isEmpty()) {
            System.out.println("No hay jugadores registrados.");
            return;
        }
        System.out.println("RANKING DE JUGADORES");
    
         Collections.sort(lista,new CriterioVictorias());
    
        for (int i = 0; i < lista.size(); i++) {
            Jugador j = lista.get(i);
            System.out.println((i + 1) + ") " + j.getNombre() + " | " + j.getPartidasGanadas());
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
           
        System.out.println("JUGADORES INVICTOS ");
        System.out.println("");
     
        ArrayList<Jugador> invictos = new ArrayList<Jugador>();

   
        for (int i = 0; i < lista.size(); i++) {
            Jugador j = lista.get(i);
            if (j.esInvicto()) {
            invictos.add(j);
            }
        }
        Collections.sort(invictos);
        
        if (invictos.isEmpty()) {
        System.out.println("No hay jugadores invictos.");
    } else {
        for (int i = 0; i < invictos.size(); i++) {
            System.out.println(invictos.get(i).getNombre());
        }
    }
        System.out.println("");
        System.out.println("");
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
    
    private static void imprimirTablero(Tablero unTablero, boolean mostrarTitulo){
        String[] NC = {" ●","● "," ●"};
        String[] ND = {"● "," ●","● "};
        String[] BC = {" ○","○ "," ○"};
        String[] BD = {"○ "," ○","○ "};
        
        Pieza[][] celdas = unTablero.getCeldas();
        String[] letrasTitulo = {"A","B","C"};
        if(mostrarTitulo){
            System.out.println("  1  2  3  4  5  6");
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(" +--+--+--+--+--+--+");
            for(int k = 0; k < 3; k++){
                String renglon = " |";
                if(mostrarTitulo){
                    if(k == 1){
                        renglon = letrasTitulo[i] + "|";
                    }
                }
                for (int j = 0; j < 6; j++) {    
                    String dosChars = "  ";
                    Pieza p = celdas[i][j];
                    if(p != null){
                        char col = p.getColor();
                        char ori = p.getOrientacion();
                        if(col == 'N'){
                            if(ori == 'C'){
                                dosChars = NC[k];
                            }else{
                                if(ori == 'D'){
                                    dosChars = ND[k];
                                }
                            }
                        }else{
                            if(col == 'B'){
                                if(ori == 'C'){
                                    dosChars = BC[k];
                                }else{
                                    if(ori == 'D'){
                                        dosChars = BD[k];
                                    }
                                }
                            }
                        }
                    }
                    renglon += dosChars + "|";
                }
                System.out.println(renglon);
            }
        }
        System.out.println(" +--+--+--+--+--+--+");
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
        System.out.println("Seleccione el "+etiqueta+" :");
        int numero = leerNumeroJugador(1, maximo);
        elegido = lista.get(numero -1);
        return elegido;
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
            Tablero tablero = partida.getTablero();
            mostrarTitulo = true;
            
            System.out.println("Comieza jugando: O = "+jugadorO.getNombre()+" vs X = "+jugadorX.getNombre());
    
            boolean continuar = true;
            while(!partida.estaFinalizado() && continuar){
                imprimirTablero(tablero, mostrarTitulo);
                
                Jugador jugadorTurno;
                char colorTurno;
                
                if(partida.esTurnoDeO()){
                    jugadorTurno = jugadorO;
                    colorTurno = 'B';
                }else{
                    jugadorTurno = jugadorX;
                    colorTurno = 'N';
                }
                
                System.out.println(jugadorTurno.getNombre()+" ingrese la jugada:");
                String linea = scanner.nextLine();
                int estado = procesarJugadaLinea(linea, tablero, colorTurno);
                
                if(estado == ESTADO_OK){
                    partida.actualizarEstado();
                    if(partida.estaFinalizado()){
                        imprimirTablero(tablero, mostrarTitulo);
                        Jugador ganador = partida.getGanador();
                        System.out.println("Hay ganador!");
                        if(ganador != null){
                            System.out.println("Ganó: "+ganador.getNombre());
                        }
                    }else{
                        partida.cambiarTurno();
                    }
                }else{
                    if(estado == ESTADO_FIN_RENUNCIA){
                        partida.renunciar();
                        imprimirTablero(tablero, mostrarTitulo);
                        Jugador ganador = partida.getGanador();
                        Jugador rendido;
                        if(partida.esTurnoDeO()){
                            rendido = jugadorO;
                        }else{
                            rendido = jugadorX;
                        }
                        System.out.println("Partida finalizada por rendición");
                        System.out.println("Se rindio "+rendido.getNombre());
                        System.out.println("Gano "+ganador.getNombre());
                        
                        continuar = false;
                    }else{
                        if(estado == ESTADO_FIN_EMPATE){
                            System.out.println("Partida finalizada por empate.");
                            continuar = false;
                        }else{
                            if(estado == ESTADO_REINGRESAR){
                                System.out.println("Jugada invalida. Reintentar");
                            }
                        }
                    }
                }
            }
        }
    }
    
    private static int indiceFila(char letra){
        int indice = -1;
        char aux = Character.toUpperCase(letra);
        if(aux == 'A'){
            indice = 0;
        }else{
            if(aux == 'B'){
                indice = 1;
            }else{
                if(aux == 'C'){
                    indice = 2;
                }
            }
        }
        return indice;
    }
    private static int indiceColumna(char digito){
        int indice = -1;
        if(digito >= '1'){
            if(digito <= '6'){
                indice = (digito - '1');
            }
        }
        return indice;
    }
    
    private static boolean esOrientacion(char c){
        boolean es = false;
        char aux = Character.toUpperCase(c);
        if(aux == 'C'){
            es = true;
        }else{
            if(aux == 'D'){
                es = true;
            }
        }
        return es;
    }
    private static boolean esInvertir(char c){
        boolean es = false;
        char aux = Character.toUpperCase(c);
        if(aux == 'I'){
            es = true;
        }
        return es;
    }
    
    
    private static int procesarJugadaLinea(String linea, Tablero tablero, char colorTurno){
        int estado = ESTADO_OK;
        String jugada = "";
        if(linea != null){
            jugada = linea.trim().toUpperCase();
        }
        
        if(jugada.length() == 0){
            estado = ESTADO_REINGRESAR;
        }else{
            if(jugada.equals("X")){
                System.out.println("Fin por renuncia");
                estado = ESTADO_FIN_RENUNCIA;
            }else{
                if(jugada.equals("B")){
                    mostrarTitulo = true;
                }else{
                    if(jugada.equals("N")){
                        mostrarTitulo = false;
                    }else{
                        if(jugada.equals("T")){
                            boolean confirmado = confirmarEmpate();
                            if(confirmado){
                                System.out.println("Empate por acuerdo mutuo.");
                                estado = ESTADO_FIN_EMPATE;
                            }
                        }else{
                            if(jugada.equals("H")){
                                System.out.println("Ayuda:  ");//no implementada aun
                            }else{
                                if(jugada.length() == 3){
                                    char letraFila = jugada.charAt(0);
                                    char digitoCol = jugada.charAt(1);
                                    char accion = jugada.charAt(2);
                                    
                                    int fila = indiceFila(letraFila);
                                    int col = indiceColumna(digitoCol);
                                    
                                    if(fila == -1 || col == -1){
                                        System.out.println("Cordenada invalida");
                                        estado = ESTADO_REINGRESAR;
                                    }else{
                                        if(esInvertir(accion)){
                                            boolean pude = intentarInvertir(fila, col, tablero, colorTurno);
                                            if(!pude){
                                                System.out.println("No se pude invertir esa pieza");
                                                estado = ESTADO_REINGRESAR;
                                            }
                                        }else{
                                            if(esOrientacion(accion)){
                                                boolean pude = intentarColocar(fila, col, Character.toUpperCase(accion), tablero, colorTurno);
                                                if(!pude){
                                                    System.out.println("No se puede colocar en esa celda");
                                                    estado = ESTADO_REINGRESAR;
                                                }                                               
                                            }else{
                                                System.out.println("Accion invalida");
                                                estado = ESTADO_REINGRESAR;
                                            }
                                        }
                                    }
                                }else{
                                    System.out.println("Formato invalido. Use A3C, B2D, A2I, o comandos X/B/N/T/H");
                                    estado = ESTADO_REINGRESAR;
                                }
                            }
                        }
                    }
                }
            }
        }
        return estado;
    }
    
    private static boolean confirmarEmpate(){
        boolean confirmado = false;
        System.out.println("Confirmar empate? (S/N): ");
        String respuesta = scanner.nextLine().trim().toUpperCase();
        if(respuesta.equals("S")){
            confirmado = true;
        }
        return confirmado;
    }
    
    private static boolean intentarColocar(int fila, int col, char orientacion, Tablero tablero, char colorTurno){
        boolean pude = false;
        if(tablero.estaLibre(fila, col)){
            Pieza pieza = new Pieza(colorTurno, orientacion);
            boolean ok = tablero.colocar(fila, col, pieza);
            if(ok){
                pude = true;
            }
        }
        return pude;
    }
    
    private static boolean intentarInvertir(int fila, int col, Tablero tablero, char colorTurno){
        boolean pude = false;
        Pieza[][] celdas = tablero.getCeldas();
        if(celdas[fila][col] != null){
            Pieza pieza = celdas[fila][col];
            if(pieza.getColor() == colorTurno){
                pieza.invertir();
                pude = true;
            }
        }
        return pude;
    }
    
}
