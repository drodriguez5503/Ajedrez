package Main;

import Piezas.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tablero extends JPanel {
    public int tamCasilla = 85;
    int fila = 8;
    int columna = 8;

    ArrayList <Pieza> listaPiezas = new ArrayList<>();

    public Pieza piezaSeleccionada;

    Input input = new Input(this);

    public EscanerJaques escanerJaques = new EscanerJaques(this);

    public int alPasoCasilla = -1 ;

    private boolean turnoBlancas = true;

    public boolean ganador;

    public Tablero(){
        this.setPreferredSize(new Dimension(columna*tamCasilla,fila*tamCasilla));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        anadirPiezas();


    }

    public Pieza getPieza(int col, int fil) {

        for(Pieza pieza : listaPiezas){
            if(pieza.col == col && pieza.fil == fil){
                return pieza;
            }
        }

        return null;
    }

    public boolean isValidMove(Move move){

        if(mismoBando(move.pieza, move.captura)){
            return false;
        }

        if (move.pieza.esBlanca != turnoBlancas) {
            return false;
        }

        if(!move.pieza.isValidMovement(move.newCol,move.newFil)) {
            return false;
        }

        if(move.pieza.moveColisionaConPieza(move.newCol,move.newFil)){
            return false;
        }

        if(escanerJaques.hayJaque(move)) {
            return false;
        }

        return true;
    }

    public boolean hayMovimientosPosibles(boolean esBlanca) {
        for (Pieza pieza : listaPiezas) {
            // Verifica si la pieza pertenece al jugador actual y si tiene al menos un movimiento válido
            if (pieza.esBlanca == esBlanca) {
                for (int f = 0; f < fila; f++) {
                    for (int c = 0; c < columna; c++) {
                        Move move = new Move(this, pieza, c, f);
                        if (isValidMove(move)) {
                            // Hay al menos un movimiento posible
                            return true;
                        }
                    }
                }
            }
        }

        // No se encontraron movimientos posibles para el jugador actual
        return false;
    }

    public void makeMove(Move move){
        if(move.pieza.nombre.equals("Peon")){
            movePeon(move);
        } else if(move.pieza.nombre.equals(("Rey"))){
            moveRey((move));
        }

        move.pieza.col = move.newCol;
        move.pieza.fil = move.newFil;
        move.pieza.posX = move.newCol * tamCasilla;
        move.pieza.posY = move.newFil * tamCasilla;

        move.pieza.esPrimermove = false;

        captura(move.captura);

        turnoBlancas = !turnoBlancas;

    }

    public void moveRey(Move move){

        if(Math.abs(move.pieza.col - move.newCol) == 2){
            Pieza torre;
            if(move.pieza.col < move.newCol){
                torre = getPieza(7,move.pieza.fil);
                torre.col = 5;
            } else {
                torre = getPieza(0,move.pieza.fil);
                torre.col = 3;

            }
            torre.posX = torre.col * tamCasilla;
        }

    }

    public void movePeon(Move move){

        //Al paso
        int indiceColor = move.pieza.esBlanca ? 1 : -1 ;

        if(getNumCasilla(move.newCol, move.newFil) == alPasoCasilla){
            move.captura = getPieza(move.newCol,move.newFil + indiceColor);
        }

        if(Math.abs(move.pieza.fil-move.newFil)==2){
            alPasoCasilla = getNumCasilla(move.newCol, move.newFil+indiceColor);
        } else {
            alPasoCasilla = -1;
        }

        // Promoción
        indiceColor = move.pieza.esBlanca ? 0 : 7;

        if(move.newFil == indiceColor) {
            promoverPeon(move);
        }
    }

    private void promoverPeon(Move move) {
        listaPiezas.add(new Dama(this,move.newCol,move.newFil,move.pieza.esBlanca));

        captura(move.pieza);
    }

    public void captura(Pieza pieza){
        listaPiezas.remove(pieza);
    }



    public boolean mismoBando(Pieza p1, Pieza p2){
        if(p1==null || p2==null){
            return false;
        }
        return p1.esBlanca == p2.esBlanca;
    }

    public int getNumCasilla(int col,int fil){
        return fil*fila+col;
    }

    Pieza encuentraRey(boolean esBlanca){
        for(Pieza pieza : listaPiezas){
            if(esBlanca == pieza.esBlanca && pieza.nombre.equals("Rey")){
                repaint();
                return pieza;
            }
        }

        return null;
    }

    public boolean jaqueMate() {

        boolean blancasTienenMovimientos = hayMovimientosPosibles(true);
        boolean negrasTienenMovimientos = hayMovimientosPosibles(false);

        if (!blancasTienenMovimientos && !negrasTienenMovimientos) {
            if(!blancasTienenMovimientos) { ganador=false; }
            else if (!negrasTienenMovimientos) { ganador=true; }
            repaint();
            return true;
        }

        return false;
    }



    public void anadirPiezas(){

        //Blancas
        listaPiezas.add(new Peon(this,0,6,true));
        listaPiezas.add(new Peon(this,1,6,true));
        listaPiezas.add(new Peon(this,2,6,true));
        listaPiezas.add(new Peon(this,3,6,true));
        listaPiezas.add(new Peon(this,4,6,true));
        listaPiezas.add(new Peon(this,5,6,true));
        listaPiezas.add(new Peon(this,6,6,true));
        listaPiezas.add(new Peon(this,7,6,true));

        listaPiezas.add(new Caballo(this,1, 7, true ));
        listaPiezas.add(new Caballo(this,6, 7, true ));

        listaPiezas.add(new Alfil(this,5, 7, true ));
        listaPiezas.add(new Alfil(this,2, 7, true ));

        listaPiezas.add(new Torre(this,0, 7, true ));
        listaPiezas.add(new Torre(this,7, 7, true ));

        listaPiezas.add(new Dama(this,3, 7, true ));

        listaPiezas.add(new Rey(this,4, 7, true ));


        //Negras
        listaPiezas.add(new Peon(this,0,1,false));
        listaPiezas.add(new Peon(this,1,1,false));
        listaPiezas.add(new Peon(this,2,1,false));
        listaPiezas.add(new Peon(this,3,1,false));
        listaPiezas.add(new Peon(this,4,1,false));
        listaPiezas.add(new Peon(this,5,1,false));
        listaPiezas.add(new Peon(this,6,1,false));
        listaPiezas.add(new Peon(this,7,1,false));

        listaPiezas.add(new Caballo(this,1,0,false));
        listaPiezas.add(new Caballo(this,6,0,false));

        listaPiezas.add(new Alfil(this,2, 0, false ));
        listaPiezas.add(new Alfil(this,5, 0, false ));

        listaPiezas.add(new Torre(this,0, 0, false ));
        listaPiezas.add(new Torre(this,7, 0, false ));

        listaPiezas.add(new Dama(this,3, 0, false ));

        listaPiezas.add(new Rey(this,4, 0, false ));




    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        //Pintar tablero
        for (int f=0;f<fila;f++){
            for (int c=0;c<columna;c++){
                g2d.setColor((c+f) % 2 == 0 ? new Color(227, 198, 181) : new Color(157, 185, 53));
                g2d.fillRect(c*tamCasilla,f*tamCasilla,tamCasilla,tamCasilla);
            }
        }

        // Pintar movimientos posibles
        if(piezaSeleccionada != null)
            for(int f=0;f<fila;f++){
                for(int c=0; c<columna;c++){
                    if(isValidMove(new Move(this, piezaSeleccionada,c,f))){
                        g2d.setColor(new Color(105, 112, 98, 169));
                        g2d.fillRect(c*tamCasilla,f*tamCasilla,tamCasilla,tamCasilla);
                }
            }
        }

        //Pintar Piezas
        for(Pieza piezaActual : listaPiezas) {
            piezaActual.colorear(g2d);
        }

        //JaqueMate
        if(jaqueMate()){
            g2d.setFont(new Font("Arial",Font.PLAIN,60));
            g2d.setColor(new Color(0,0,0));
            g2d.drawString("GANAN LAS "+(ganador ? "BLANCAS":"NEGRAS"),350,450);

        }

    }




}
