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
    
    public Tablero getTablero(){
        return tablero;
    }
    
    public void actualizarEstado(){
        char estado = tablero.estadoGanador();
        if(estado != '-'){
            finalizado = true;
            if(estado == 'B'){
                if(turnoDeO){
                    ganador = jugadorO;
                    jugadorO.sumarVictoria();
                    jugadorX.sumarDerrota();
                }else{
                    ganador = jugadorX;
                    jugadorX.sumarVictoria();
                    jugadorO.sumarDerrota();
                }
            }else{
                if(estado == 'O'){
                    ganador = jugadorO;
                    jugadorO.sumarVictoria();
                    jugadorX.sumarDerrota();
                }else{
                    ganador = jugadorX;
                    jugadorX.sumarVictoria();
                    jugadorO.sumarDerrota();
                }    
            }
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
            jugadorX.sumarVictoria();
            jugadorO.sumarDerrota();
        }else{
            ganador =jugadorO;
            jugadorO.sumarVictoria();
            jugadorX.sumarDerrota();
        }
    }
    
    public Jugador getGanador(){
        return ganador;
    }
}
