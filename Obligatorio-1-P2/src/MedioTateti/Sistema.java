/* Trabajo realizado por Marcos Coszion (332945) y Francisco Lino (347691)*/
package MedioTateti;

import java.util.ArrayList;
    
/*
 * Clase Sistema
 * Maneja la lista de jugadores, el ranking y crea las partidas (instancias de Juego).
 * Es el intermediario entre la interfaz (TatetiMain) y la lógica del juego.
 */
public class Sistema {
    private ArrayList<Jugador> jugadores= new ArrayList<Jugador>();
    
    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }
    public void setJugadores(ArrayList<Jugador> listaJugadores){
        jugadores = listaJugadores;
    }
    
    public void agregarJugador(Jugador unJugador){
        this.getJugadores().add(unJugador);
    }
    public boolean existeJugador(String unNombre){
        boolean existe = false;
        for (int i = 0; i < this.getJugadores().size(); i++) {
            if(this.getJugadores().get(i).getNombre().equals(unNombre)){
                existe = true;
            }
        }
        return existe;
    }
    public Jugador buscarJugador(String unNombre){
        Jugador unJugador = null;
        for (int i = 0; i < getJugadores().size(); i++) {
            Jugador aux = getJugadores().get(i);
            if(aux.getNombre().equals(unNombre)){
                unJugador = aux;
            }
        }
        return unJugador;
    }
}
