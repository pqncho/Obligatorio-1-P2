/* Trabajo realizado por Marcos Coszion (332945) y Francisco Lino (347691)*/
package MedioTateti;

import java.util.*;

public class Sistema {
    public Sistema(){

    }
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
            if(this.getJugadores().get(i).getNombre().equalsIgnoreCase(unNombre)){
                existe = true;
            }
        }
        return existe;
    }
    
    
    
    public void ordenarJugadoresPorNombre(){
        Collections.sort(jugadores);
    }
}

class CriterioVictorias implements Comparator<Jugador>{    
    @Override
    public int compare( Jugador j1,Jugador j2){
        return  j2.getPartidasGanadas() - j1.getPartidasGanadas();
    }
}

