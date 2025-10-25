/* Trabajo realizado por Marcos Coszion (332945) y Francisco Lino (347691)*/
package MedioTateti;
import java.util.*;

public class CriterioVictorias implements Comparator<Jugador>{
    

    @Override
    public int compare( Jugador j1,Jugador j2){
    return j1.getPartidasGanadas() - j2.getPartidasGanadas();
    }
}
