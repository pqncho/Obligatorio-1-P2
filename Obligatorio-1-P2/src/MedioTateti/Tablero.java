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
    public boolean colocar(int fila, int columna, String ficha){
        boolean pude = false;
        if(enRango(fila, columna)){
            if(celdas[fila][columna] == " "){
                celdas[fila][columna] = ficha;
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
    
    public boolean hayLinea(){
        return true;
    }
    
    private boolean tresIguales(char a, char b, char c){
        boolean iguales = false;
        if(a != ' '){
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
