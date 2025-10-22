/* Trabajo realizado por Marcos Coszion (332945) y Francisco Lino (347691)*/
package MedioTateti;


public class Pieza {
    private char color;
    private char orientacion;
    
    public Pieza(char unColor, char unaOrientacion){
        this.setColor(unColor);
        this.setOrientacion(unaOrientacion);
    }
    
    public char getColor(){
        return color;
    }
    public void setColor(char unColor){
        char aux = Character.toUpperCase(unColor);
        if(aux != 'B'){
            if(aux != 'N'){
                aux = 'B';
            }
        }
        color = aux;
    }
    
    public char getOrientacion(){
        return orientacion;
    }
    public void setOrientacion(char unaOrientacion){
        char aux = Character.toUpperCase(unaOrientacion);
        if(aux != 'C'){
            if(aux != 'D'){
                aux = 'C';
            }
        }
        orientacion = aux;
    }
    
    
    public void invertir(){
        if(orientacion == 'C' ){
            orientacion = 'D';
        }else{
            orientacion = 'C';
        }
    }
    
    private String simboloDeColor(){
        String simbolo = "○";
        if(color == 'N'){
            simbolo = "●";
        }
        return simbolo;
    }
    
    
   //hay que crear el metodo que muestra graficamente la pieza, que luego la vas a mostrar en el tablero
}