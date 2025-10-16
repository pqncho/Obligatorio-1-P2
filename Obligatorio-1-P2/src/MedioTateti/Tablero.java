/* Trabajo realizado por Marcos Coszion (332945) y Francisco Lino (347691)*/
package MedioTateti;

public class Tablero {
    private String[][] celdas;
    
    public Tablero(){
        celdas = new String[3][6];
        inicializar();
    }
    
    public void inicializar(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                celdas[i][j] = " ";
            }
            
        }
    }
    
    public boolean estaLibre(int fila, int columna){
        boolean libre = false;
        if(enRango(fila, columna)){
            if(celdas[fila][columna]== " "){
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
    public boolean colocar(int fila, int columna, String pieza){
        boolean pude = false;
        if(enRango(fila, columna)){
            if(celdas[fila][columna] == " "){
                celdas[fila][columna] = pieza;
                pude = true;
            }
        }
        return pude;
    }
    
    public boolean estaLleno(){
        boolean lleno = true;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 6; j++) {
                if(celdas[i][j] == " "){
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
    private boolean tresIguales(String a, String b, String c){
        boolean iguales = false;
        if(a != " "){
            if(a == b){
                if(b == c){
                    iguales = true;
                }
            }
        }
        return iguales;
    }
    
    public String[][] getCeldas(){
        return celdas;
    }
    
    
}
