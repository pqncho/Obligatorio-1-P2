/* Trabajo realizado por Marcos Coszion (332945) y Francisco Lino (347691)*/
package MedioTateti;

public class Partida {
    private Jugador jugadorX;
    private Jugador jugadorO;
    private Jugador ganador;
    private Tablero tablero;
    private boolean turnoDeO;
    private boolean finalizado;
    
    public Partida(Jugador unJugadorX, Jugador unJugadorO){
        tablero = new Tablero();
        jugadorX = unJugadorX;
        jugadorO = unJugadorO;
        turnoDeO = true;
        finalizado = false;
    }
    
    public boolean estaFinalizado(){
        return finalizado;
    }
    
    //esto cambia despues de definir jugada, la cual no va a existir, pero veremos
    //creo que esto ni va ya
    public boolean jugar(int fila, int columna){
        boolean pudeJugar = false;
        if(!finalizado){
            Pieza piezaActual = darPiezaActual();
            if(tablero.estaLibre(fila, columna)){
                tablero.colocar(fila, columna, piezaActual);
                actualizarEstado();
                if(!finalizado){
                    turnoDeO = !turnoDeO;
                }
                pudeJugar = true;
            }
        }
        return pudeJugar;
    }
    
    // 'C' o 'D' segun el truno (y el color)
    //esto creo que tampoco va
    public Pieza darPiezaActual(){
        //creo que esto al final no
        //hay que hacerlo despues de definir la clase jugada, la cual no va a existir, pero veremos
        return new Pieza('N', 'C');
    }
    
    public Tablero getTablero(){
        return tablero;
    }
    
    //esto va a cambiar porque el jugadorO, cuando coloca o invierte una pieza, puede hacer ganar al jugadorX
    //aca si se detecta que en el tablero alguien ya gano, gana el ultimo que jugo (hay que hacer un metodo que evalue de que formas esta hecha la jugada ganadora)(despues igual)
    public void actualizarEstado(){
        boolean hayGanador = tablero.hayLinea();
        if(hayGanador){
            finalizado = true;
        }
        if(turnoDeO){
            ganador = jugadorO;
        }else{
            ganador = jugadorX;
        }
    }
    
    public boolean esTurnoDeO(){
        return turnoDeO;
    }
    public void cambiarTurno(){
        if(turnoDeO){
            turnoDeO = false;
        }else{
            turnoDeO = true;
        }
    }
    
    public void renunciar(){
        finalizado = true;
        if(turnoDeO){
            ganador = jugadorX;
        }else{
            ganador =jugadorO;
        }
    }
    
    public Jugador getGanador(){
        return ganador;
    }
}
