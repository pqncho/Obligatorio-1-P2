/* Trabajo realizado por Marcos Coszion (332945) y Francisco Lino (347691)*/
package MedioTateti;

public class Jugador implements Comparable<Jugador>{
    private String nombre;
    private int edad;
    
    public String getNombre(){
        return nombre;
    }
    public int getEdad(){
        return edad;
    }
    
    public void setNombre( String unNombre){
        nombre = unNombre;
    }
    public void setEdad (int unaEdad){
        edad = unaEdad;
    }
    
    @Override
    public int compareTo(Jugador otroJugador){
        int resultado = 0;
        if(this.nombre != null && otroJugador != null){
            resultado = this.nombre.compareTo(otroJugador.getNombre());
        }
        return resultado;
    }
    
    @Override
    public String toString(){
        return "nombre: "+getNombre()+", edad: "+getEdad();
    }
    
   public Jugador(String unNombre, int unaEdad){
       nombre = unNombre;
       edad = unaEdad;
   }
}
