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
            if(celdas[fila][columna] == null){
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
   
    public boolean colocar(int fila, int columna, Pieza unaPieza){
        boolean pude = false;
        if(enRango(fila, columna)){
            if(celdas[fila][columna] == null){
                celdas[fila][columna] = unaPieza;
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
    
    public Pieza[][] getCeldas(){
        return celdas;
    }
    
    public boolean hayLinea(){
        boolean hay = false;
        char estado = estadoGanador();
        if(estado != '-'){
            hay = true;
        }
        return hay;
    }
    
    public char estadoGanador(){
        char estado = '-';
        boolean tieneO = false;
        boolean tieneX = false;
        boolean[][] OEnPos = new boolean[3][5];
        boolean[][] XEnPos = new boolean[3][5];
        
        for (int fila = 0; fila < 3; fila++) {
            for (int colPar = 0; colPar < 5; colPar++) {
               OEnPos[fila][colPar] = esLetraO(fila, colPar); 
               XEnPos[fila][colPar] = esLetraX(fila, colPar);
            }
            
        }
        
        //horizontales
        for (int fila = 0; fila < 3; fila++) {
            if(OEnPos[fila][0] && OEnPos[fila][2] && OEnPos[fila][4]){
                tieneO = true;
            }else{
                if(XEnPos[fila][0] && XEnPos[fila][2] && XEnPos[fila][4]){
                    tieneX = true;
                }
            }
        }
        
        //verticales
        for (int colPar = 0; colPar < 5; colPar += 2) {
            if(OEnPos[0][colPar] && OEnPos[1][colPar] && OEnPos[2][colPar]){
                tieneO = true;
            }else{
                if(XEnPos[0][colPar] && XEnPos[1][colPar] && XEnPos[2][colPar]){
                    tieneX = true;
                }
            }
        }

        //diagonales ->
        if(OEnPos[0][0] && OEnPos[1][1] && OEnPos[2][2]){
            tieneO = true;
        }else{
            if(XEnPos[0][0] && XEnPos[1][1] && XEnPos[2][2]){
                tieneX = true;
            }else{
                if(OEnPos[0][1] && OEnPos[1][2] && OEnPos[2][3]){
                    tieneO = true;
                }else{
                    if(XEnPos[0][1] && XEnPos[1][2] && XEnPos[2][3]){
                        tieneX = true;
                    }else{
                        if(OEnPos[0][2] && OEnPos[1][3] && OEnPos[2][4]){
                            tieneO = true;
                        }else{
                            if(XEnPos[0][2] && XEnPos[1][3] && XEnPos[2][4]){
                                tieneX = true;
                            }
                        }
                    }
                }
            }
        }
        
        //diagonales <-
        if(OEnPos[0][2] && OEnPos[1][1] && OEnPos[2][0]){
            tieneO = true;
        }else{
            if(XEnPos[0][2] && XEnPos[1][1] && XEnPos[2][0]){
                tieneX = true;
            }else{
                if(OEnPos[0][3] && OEnPos[1][2] && OEnPos[2][1]){
                    tieneO = true;
                }else{
                    if(XEnPos[0][3] && XEnPos[1][2] && XEnPos[2][1]){
                        tieneX = true;
                    }else{
                        if(OEnPos[0][4] && OEnPos[1][3] && OEnPos[2][2]){
                            tieneO = true;
                        }else{
                            if(XEnPos[0][4] && XEnPos[1][3] && XEnPos[2][2]){
                                tieneX = true;
                            }
                        }
                    }
                }
            }
        }
        
        if(tieneO){
            if(tieneX){
                estado = 'B';
            }else{
                estado = 'O';
            }
        }else{
            if(tieneX){
                estado = 'X';
            }else{
                estado = '-';
            }
        }
        
        return estado;
    }
    
    private boolean esLetraO(int fila, int colPar){
        boolean es = false;
        Pieza pIzq = celdas[fila][colPar];
        Pieza pDer = celdas[fila][colPar + 1];
        if(pIzq != null){
            if(pDer != null){
                if(pIzq.getOrientacion() == 'C' && pDer.getOrientacion() == 'D'){
                    es = true;
                }
            }
        }
        return es;
    }
    private boolean esLetraX(int fila, int colPar){
        boolean es = false;
        Pieza pIzq = celdas[fila][colPar];
        Pieza pDer = celdas[fila][colPar + 1];
        if(pIzq != null){
            if(pDer != null){
                if(pIzq.getOrientacion() == 'D' && pDer.getOrientacion() == 'C'){
                    es = true;
                }
            }
        }
        return es;
    }

    public boolean[][] coberturaGanadora(char letraMin) {
        boolean[][] cobertura = new boolean[3][6];
        boolean[][] OEnPos = new boolean[3][5];
        boolean[][] XEnPos = new boolean[3][5];
        char letra = Character.toUpperCase(letraMin);

        for (int fila = 0; fila < 3; fila++) {
            for (int colPar = 0; colPar < 5; colPar++) {
                OEnPos[fila][colPar] = esLetraO(fila, colPar);
                XEnPos[fila][colPar] = esLetraX(fila, colPar);
            }
        }

        for (int fila = 0; fila < 3; fila++) {
            if (OEnPos[fila][0] && OEnPos[fila][2] && OEnPos[fila][4]) {
                if (letra == 'O') {
                    marcarPar(cobertura, fila, 0);
                    marcarPar(cobertura, fila, 2);
                    marcarPar(cobertura, fila, 4);
                }
            } else {
                if (XEnPos[fila][0] && XEnPos[fila][2] && XEnPos[fila][4]) {
                    if (letra == 'X') {
                        marcarPar(cobertura, fila, 0);
                        marcarPar(cobertura, fila, 2);
                        marcarPar(cobertura, fila, 4);
                    }
                }
            }
        }

        for (int colPar = 0; colPar < 5; colPar += 2) {
            if (OEnPos[0][colPar] && OEnPos[1][colPar] && OEnPos[2][colPar]) {
                if (letra == 'O') {
                    marcarPar(cobertura, 0, colPar);
                    marcarPar(cobertura, 1, colPar);
                    marcarPar(cobertura, 2, colPar);
                }
            } else {
                if (XEnPos[0][colPar] && XEnPos[1][colPar] && XEnPos[2][colPar]) {
                    if (letra == 'X') {
                        marcarPar(cobertura, 0, colPar);
                        marcarPar(cobertura, 1, colPar);
                        marcarPar(cobertura, 2, colPar);
                    }
                }
            }
        }

        if (OEnPos[0][0] && OEnPos[1][1] && OEnPos[2][2]) {
            if (letra == 'O') {
                marcarPar(cobertura, 0, 0);
                marcarPar(cobertura, 1, 1);
                marcarPar(cobertura, 2, 2);
            }
        } else {
            if (XEnPos[0][0] && XEnPos[1][1] && XEnPos[2][2]) {
                if (letra == 'X') {
                    marcarPar(cobertura, 0, 0);
                    marcarPar(cobertura, 1, 1);
                    marcarPar(cobertura, 2, 2);
                }
            } else {
                if (OEnPos[0][1] && OEnPos[1][2] && OEnPos[2][3]) {
                    if (letra == 'O') {
                        marcarPar(cobertura, 0, 1);
                        marcarPar(cobertura, 1, 2);
                        marcarPar(cobertura, 2, 3);
                    }
                } else {
                    if (XEnPos[0][1] && XEnPos[1][2] && XEnPos[2][3]) {
                        if (letra == 'X') {
                            marcarPar(cobertura, 0, 1);
                            marcarPar(cobertura, 1, 2);
                            marcarPar(cobertura, 2, 3);
                        }
                    } else {
                        if (OEnPos[0][2] && OEnPos[1][3] && OEnPos[2][4]) {
                            if (letra == 'O') {
                                marcarPar(cobertura, 0, 2);
                                marcarPar(cobertura, 1, 3);
                                marcarPar(cobertura, 2, 4);
                            }
                        } else {
                            if (XEnPos[0][2] && XEnPos[1][3] && XEnPos[2][4]) {
                                if (letra == 'X') {
                                    marcarPar(cobertura, 0, 2);
                                    marcarPar(cobertura, 1, 3);
                                    marcarPar(cobertura, 2, 4);
                                }
                            }
                        }
                    }
                }
            }
        }

        
        if (OEnPos[0][2] && OEnPos[1][1] && OEnPos[2][0]) {
            if (letra == 'O') {
                marcarPar(cobertura, 0, 2);
                marcarPar(cobertura, 1, 1);
                marcarPar(cobertura, 2, 0);
            }
        } else {
            if (XEnPos[0][2] && XEnPos[1][1] && XEnPos[2][0]) {
                if (letra == 'X') {
                    marcarPar(cobertura, 0, 2);
                    marcarPar(cobertura, 1, 1);
                    marcarPar(cobertura, 2, 0);
                }
            } else {
                if (OEnPos[0][3] && OEnPos[1][2] && OEnPos[2][1]) {
                    if (letra == 'O') {
                        marcarPar(cobertura, 0, 3);
                        marcarPar(cobertura, 1, 2);
                        marcarPar(cobertura, 2, 1);
                    }
                } else {
                    if (XEnPos[0][3] && XEnPos[1][2] && XEnPos[2][1]) {
                        if (letra == 'X') {
                            marcarPar(cobertura, 0, 3);
                            marcarPar(cobertura, 1, 2);
                            marcarPar(cobertura, 2, 1);
                        }
                    } else {
                        if (OEnPos[0][4] && OEnPos[1][3] && OEnPos[2][2]) {
                            if (letra == 'O') {
                                marcarPar(cobertura, 0, 4);
                                marcarPar(cobertura, 1, 3);
                                marcarPar(cobertura, 2, 2);
                            }
                        } else {
                            if (XEnPos[0][4] && XEnPos[1][3] && XEnPos[2][2]) {
                                if (letra == 'X') {
                                    marcarPar(cobertura, 0, 4);
                                    marcarPar(cobertura, 1, 3);
                                    marcarPar(cobertura, 2, 2);
                                }
                            }
                        }
                    }
                }
            }
        }

        return cobertura;
    }
    private void marcarPar(boolean[][] cobertura, int fila, int colPar){
        if(fila >= 0 && fila < 3 && colPar >= 0 && colPar +1 <6){
            cobertura[fila][colPar] = true;
            cobertura[fila][colPar +1] = true;
        }
    }
        
}
