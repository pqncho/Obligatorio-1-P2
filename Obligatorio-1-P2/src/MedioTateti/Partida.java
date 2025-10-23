/* Trabajo realizado por Marcos Coszion (332945) y Francisco Lino (347691)*/
package MedioTateti;

public class Partida {
    private Jugador jugadorX;
    private Jugador jugadorO;
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
    public Pieza darPiezaActual(){
        //hay que hacerlo despues de definir la clase jugada, la cual no va a existir, pero veremos
        return new Pieza('N', 'C');
    }
    
    public Tablero getTablero(){
        return tablero;
    }
    
    private void actualizarEstado(){
        boolean hayGanador = tablero.hayLinea();
        if(hayGanador){
            finalizado = true;
        }
    }
}
