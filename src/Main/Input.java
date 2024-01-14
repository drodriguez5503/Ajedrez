package Main;

import Piezas.Pieza;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input extends MouseAdapter {
Tablero tablero;
 public Input(Tablero tablero) {
     this.tablero = tablero;
 }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX()/tablero.tamCasilla;
        int fil = e.getY()/ tablero.tamCasilla;

        Pieza piezaXY = tablero.getPieza(col, fil);
        if(piezaXY != null){
            tablero.piezaSeleccionada = piezaXY;
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
     if(tablero.piezaSeleccionada != null){
         tablero.piezaSeleccionada.posX = e.getX() -tablero.tamCasilla/2;
         tablero.piezaSeleccionada.posY = e.getY() -tablero.tamCasilla/2;

         tablero.repaint();
     }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
     int col = e.getX()/tablero.tamCasilla;
     int fil = e.getY()/ tablero.tamCasilla;

     if(tablero.piezaSeleccionada != null){
         Move move = new Move(tablero, tablero.piezaSeleccionada,col, fil);

         if(tablero.isValidMove(move)){
             tablero.makeMove(move);
         } else {
             tablero.piezaSeleccionada.posX = tablero.piezaSeleccionada.col * tablero.tamCasilla;
             tablero.piezaSeleccionada.posY = tablero.piezaSeleccionada.fil * tablero.tamCasilla;
         }
     }

     tablero.piezaSeleccionada = null;
     tablero.repaint();

    }



}
