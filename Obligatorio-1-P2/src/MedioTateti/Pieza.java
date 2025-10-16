/* Trabajo realizado por Marcos Coszion (332945) y Francisco Lino (347691)*/
package MedioTateti;


public class Pieza {
    private char color;
    private char orientacion;
    
    public Pieza(char color, char orientacion){
    this.color=Character.toUpperCase(color);
    this.orientacion=Character.toUpperCase(orientacion);
    }
    
    public char getColor(){
    return color;
    }
    
    public char getOrientacion(){
    return orientacion;
    }
    public void invertir(){
        if(orientacion == 'C' ){
            orientacion = 'D';
        }else{
                orientacion = 'C';
        }
    }
    
   //hay que crear el metodo que muestra graficamente la pieza, que luego la vas a mostrar en el tablero
}
