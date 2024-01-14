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

        if(!move.pieza.isValidMovement(move.newCol,move.newFil)) {
            return false;
        }

        if(move.pieza.moveColisionaConPieza(move.newCol,move.newFil)){
            return false;
        }

        return true;
    }

    public void makeMove(Move move){
        move.pieza.col = move.newCol;
        move.pieza.fil = move.newFil;
        move.pieza.posX = move.newCol*tamCasilla;
        move.pieza.posY = move.newFil*tamCasilla;

        captura(move);

    }

    public void captura(Move move){
        listaPiezas.remove(move.captura);
    }

    public boolean mismoBando(Pieza p1, Pieza p2){
        if(p1==null || p2==null){
            return false;
        }
        return p1.esBlanca == p2.esBlanca;
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

    }




}
