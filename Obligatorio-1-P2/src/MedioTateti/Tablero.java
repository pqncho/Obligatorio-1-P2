/* Trabajo realizado por Marcos Coszion (332945) y Francisco Lino (347691)*/
package MedioTateti;

public class Tablero {
    private Pieza[][] celdas;
    
    public Tablero(){
        celdas = new Pieza[3][6];
        inicializar();
    }
    
    public void inicializar(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                celdas[i][j] = null;
            }
        }
    }
    
    public boolean estaLibre(int fila, int columna){
        boolean libre = false;
        if(enRango(fila, columna)){
            if(celdas[fila][columna]== null){
                libre = true;
            }
        }
        return libre;
    }
    
    private boolean enRango(int fila, int columna){
        boolean enElRango = false;
        if(fila >= 0 && fila<3){
            if(columna >= 0 && columna < 6){
                enElRango = true;
            }
        }
        return enElRango;
    }
    
    //despues de definir la ficha hay que ver si esto cambia
    //aca se podria recibir color y orientacion y adentro definir new Pieza
    public boolean colocar(int fila, int columna, char unColor, char unaOrientacion){
        boolean pude = false;
        if(enRango(fila, columna)){
            if(celdas[fila][columna] == null){
                celdas[fila][columna] = new Pieza(unColor, unaOrientacion);
                pude = true;
            }
        }
        return pude;
    }
    
    public boolean estaLleno(){
        boolean lleno = true;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 6; j++) {
                if(celdas[i][j] == null){
                    lleno = false;
                }                
            }           
        }
        return lleno;
    }
    // hayLInea y tresIguales va a ser distinto, hay que cambiarlo cuando validemos las piezas X o O
    public boolean hayLinea(){
        boolean hay = false;
        
        for (int i = 0; i < 3; i++) {
            if(tresIguales(celdas[i][0], celdas[i][1], celdas[i][2])){
                hay = true;
            }
        }
        
        for (int j = 0; j < 6; j++) {
            
            
        }
        
        return hay;
    }
    private boolean tresIguales(Pieza a, Pieza b, Pieza c){
        boolean iguales = false;
        if(a != null){
            if(a == b){
                if(b == c){
                    iguales = true;
                }
            }
        }
        return iguales;
    }
    
    public Pieza[][] getCeldas(){
        return celdas;
    }
    
    public String[][] matrizPieza(Tablero unTablero){
        String[][] valores = new String[3][6];
        Pieza[][] celdas = unTablero.getCeldas();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                valores[i][j] = celdas[i][j].getColor() + celdas[i][j].getOrientacion() + "";
            }            
        }
        return valores;
    }
    
    
}
