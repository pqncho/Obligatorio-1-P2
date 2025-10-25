/* Trabajo realizado por Marcos Coszion (332945) y Francisco Lino (347691)*/
package MedioTateti;

public class Jugador implements Comparable<Jugador>{
    private int partidasGanadas;
    private int partidasPerdidas;
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
   
    public int getPartidasGanadas() {
    return partidasGanadas; 
    }
    public int getPartidasPerdidas() {
    return partidasPerdidas; 
    }

    public void sumarVictoria() {
    partidasGanadas++;
    
    }

    public void sumarDerrota() {
    partidasPerdidas++;
    
    }

    public boolean esInvicto() {
    return partidasPerdidas == 0;
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
        return "nombre: "+getNombre()+", edad: "+getEdad() +", ganadas: " + partidasGanadas + ", perdidas: "+ partidasPerdidas;
    }
    
   public Jugador(String unNombre, int unaEdad){
       nombre = unNombre;
       edad = unaEdad;
       
       partidasGanadas=0;
       partidasPerdidas=0;
   }
   
   
   
}
