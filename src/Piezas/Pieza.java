package Piezas;

import Main.Tablero;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pieza {
    public int col, fil;
    public int posX, posY;

    public boolean esBlanca;
    public String nombre;
    public int valor;

    BufferedImage sheet;{
        try {
            sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("540px-Chess_Pieces_Sprite.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    protected int sheetScale = sheet.getWidth()/6;
    Image sprite;
    Tablero tablero;

    public Pieza(Tablero tablero) {
        this.tablero = tablero;
    }

    public boolean isValidMovement(int col, int fil){return true;} //Ver si el movimiento es válido

    public boolean moveColisionaConPieza(int col, int fil) {return false;} //Comprobar si hay otra pieza en el camino

    public void colorear(Graphics2D g2d){
        g2d.drawImage(sprite, posX , posY, null);

    }

}
