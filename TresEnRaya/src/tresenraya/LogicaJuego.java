package tresenraya;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class LogicaJuego {
    int turno, pX, pO; // Turno del jugador
    boolean habilitado; // Habilita y deshabilita el tablero

    /**
     * Inicializaremos el juego con las siguientes variables_
     * @param turno (Nos indicará el turno del jugador: 0 - X, 1 - O)
     * @param pX (Contiene el valor total de las victorias de este jugador)
     * @param pO (Contiene el valor total de las victorias de este jugador)
     */
    public LogicaJuego(int turno, int pX, int pO) {
        this.turno = turno;
        this.pX = pX;
        this.pO = pO;
    }

    /**
     * Obtener turno
     * @return 
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Insertar turno
     * @param turno 
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getpX() {
        return pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public int getpO() {
        return pO;
    }

    public void setpO(int pO) {
        this.pO = pO;
    }
    
    /**
     * Llamaremos a este método para cambiar de turno
     */
    public void cambioTurno(){
         turno++;
	    
	 if (turno==2){turno=0;}
        
    }
    
    /**
     * Comprobar si se ha conseguido un tres en raya, 
     * en caso que se haya conseguido devolverá 1, en caso contrario retorna 0 y continúa el juego
     * @param matriz (Tablero de juego)
     * @return 
     */
    public int comprobarJuego(int matriz[][]){
    /* se tendrá que comprobar que las posiciones correspondientes tengan el 
       mismo valor; es decir se tiene que ir comparando las casillas entre sí; 
       de forma horizontal, vertical y diagonal, obligatoriamente.  . 
       Se evalúa utilizar las estructuras correctas, para reducir el número de 
       iteraciones. Todo de forma organizada y optimizada recordemos 
       que estamos en un módulo del tercer semestre del ciclo.  Pero, 
       por si acaso posible pseudocódigo:   
        Comprobar si existe tres en raya:
            Comprobar horizontal			
            si no, Comprobar vertical        
            si no, Comprobar en diagonal
        Si no hay tres en raya:
    */   
         	//Comprobación horizontal
	    	
	    	if ((( matriz[0][0]==matriz[0][1])&&(matriz[0][1]==matriz[0][2]) )|| (( matriz[1][0]==matriz[1][1])&&(matriz[1][1]==matriz[1][2]) )||(( matriz[2][0]==matriz[2][1])&&(matriz[2][1]==matriz[2][2]) ) ){
	    		
                    return 1; }
	 
	    	//Comprobación vertical
	    	
                 else if ((( matriz[0][0]==matriz[1][0])&&(matriz[1][0]==matriz[2][0]) )|| (( matriz[0][1]==matriz[1][1])&&(matriz[1][1]==matriz[2][1]) )||(( matriz[0][2]==matriz[1][2])&&(matriz[1][2]==matriz[2][2]) )) { 
            	
                    return 1; }
	    	
	    	//Comprobación diagonal
	    	
	    	else if ((( matriz[0][0]==matriz[1][1])&&(matriz[1][1]==matriz[2][2]) )|| (( matriz[0][2]==matriz[1][1])&&(matriz[1][1]==matriz[2][0]) )){ 
	    		
                     return 1; }
		    				    		    
	    	//si se cumple alguna condicion antes devolvera el valor asignado, si no seguirá hasta devolver 0
                        	return 0;  
    
                       }         
     /**
     * En caso de Ganador, mostrará la ventana, dentro del tablero; con el 
     * mensaje de enhorabuena al gandador; y le preguntará si desea continuar 
     * jugando.
     * Se valorará el código limpio, comprensible, organizado y optimizado; 
     * se puede implementar en 3 o 4 líneas de código
     * Obligatorio utilizar el operador condicional ?
     * @param jp (panel donde está situado el tablero)
     * variable local String mensajeGanador
     */   
    public void mostrarVentanaGanador(javax.swing.JPanel jp){
        String ficha=(turno-1==0)?"X":"O";                       //-1 en el turno ya que en el metodo ganador ya se ha cambiado al turno siguiente 
        int result=JOptionPane.showConfirmDialog(jp,"Soy Ángel Mercadal López,\ndoy la enhorabuena a las "+ficha+"!!!\n¿Quieres continuar jugando?","Enhorabuena Ganador",YES_NO_OPTION,QUESTION_MESSAGE);
        if(result == JOptionPane.NO_OPTION) System.exit(0);
        
        
    }
    /**
     * Deshabilitará el botón para evitar que se vuelva a posicionar una ficha en ese hueco
     * @param bt (Botón seleccionado)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     * @return 
     */
    public int tiradaJugador(javax.swing.JButton bt, int x, int y, 
            int matriz[][], javax.swing.JPanel jp, 
            javax.swing.JLabel lX, javax.swing.JLabel lO){
        // Inserta código aquí...
        
        // Deshabilita el botón
        bt.setEnabled(false);
        // Insertar la ficha en el botón
        ponerFicha(matriz,x,y,bt);
        // Comprobar si se ha ganado la partida y mostrar la ventana con el
        // mensaje cuando corresponda
         if(comprobarJuego(matriz)==1){ 
         
        ganador(lX,lO);
        // Deshabilitar tablero
         habilitado=false;
         habilitarTablero(jp);
          //mostar ventana con mensaje ganador
         mostrarVentanaGanador(jp);
         }
        
         else{cambioTurno();}
         
        
         return turno;
    }
    
    /**
     * Actualizar la puntuación de cada jugador al ganar la partida
     * Al finalizar el incremento de puntuación es necesario cambiar de turno
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     */
    public void ganador(javax.swing.JLabel lX, javax.swing.JLabel lO){
        // Inserta código aquí...
        if(turno==0){
            
            int punt=Integer.parseInt(lX.getText());
            punt++;
            lX.setText(String.valueOf(punt));}
      
         else{
            
            int punt=Integer.parseInt(lO.getText());
            punt++;
            lO.setText(String.valueOf(punt));}
        
          // Incrementa jugador ganador e inserta resultado en jLabel  
          cambioTurno();
          //cambio de turno
    }
    
    /**
     * Habilitará o deshabilitará el tablero dependiendo del estado de la variable habilitado
     * @param jp  (Panel dónde se sitúa el tablero de juego)
     */
    public void habilitarTablero( javax.swing.JPanel jp){
        
        
    
        Component[] components = jp.getComponents();

    for (Component component : components) {
          component.setEnabled(habilitado);
        // Bloquea todos los elementos del JPanel
        
    }
    }
    
    /**
     * En ponerFicha, Insertaremos la ficha en la posición correspondiente de la matriz
     * Llamaremos al método pintarFicha
     * @param matriz (Tablero de juego)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param bt (Botón pulsado)
     * turno  (para saber que ficha poner)
     */
    public void ponerFicha(int matriz[][], int x, int y, javax.swing.JButton bt){
        // Inserta código aquí...  
        
        if (turno==0) { matriz[x][y]=0;}
                
              else{ matriz[x][y]=1;  }
           
        // Insertar ficha en la posición de la matriz
         pintarFicha(bt);
        // mostrar la ficha correspondiente
        
    }
    
    /**
     * Pintará la ficha en el tablero de juego visual, es decir, en el botón
     * @param bt (Botón pulsado)
     */
    private void pintarFicha(javax.swing.JButton bt){
        // Inserta código aquí...
        // Si el turno es de 0 mostrará una X en rojo
        	if (turno==0) {
	    		
	    		bt.setText("X"); 
                        bt.setForeground(Color.RED);
               
                
                
                }
                       

	    		
         // Si el turno es de 1, mostrará una O en azul 
         else{
                
                 bt.setText("O");
                 bt.setForeground(Color.BLUE);
                }
                 
                
                }
    
    /**
     * Inicializa una nueva partida, reinicia la matriz (Tablero de juego) y habilita el tablero
     * 
     * ¡¡¡¡No es necesario modificar este método!!!!.
     * 
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     */
    public void iniciarPartida(int matriz[][], javax.swing.JPanel jp){
        // Rellenamos la matriz por primera vez, evitando que se repitan los números
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                matriz[x][y]=(x+10)*(y+10);
            }
        }

        // Habilitar tablero
         habilitado = true;
         habilitarTablero(jp);
         
         
         
       
    }
}
