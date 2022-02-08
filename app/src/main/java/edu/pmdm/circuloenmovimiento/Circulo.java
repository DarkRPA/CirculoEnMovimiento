package edu.pmdm.circuloenmovimiento;

import android.graphics.Color;
import android.graphics.Paint;

import java.time.Instant;
import java.util.Random;

public class Circulo {
    public static int RADIO = 30;
    private int posX;
    private int posY;
    private int velX;
    private int velY;

    private int maxWidth;
    private int maxHeight;

    private Random random;

    private Paint pintura;

    public Circulo(int velX, int velY, int posX, int posY){
        this.velX = velX;
        this.velY = velY;

        this.random = new Random(Instant.now().toEpochMilli() + (int)(Math.random()*100));

        this.posX = posX + this.random.nextInt(200);
        this.posY = posY + this.random.nextInt(200);

        this.pintura = new Paint();
        this.pintura.setColor(Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255)));

    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void checkAndModifyCourseBorder(int maxBorderX, int maxBorderY){
        if(this.posX >= maxBorderX){
            this.posX = maxBorderX;
            this.velX *= -1;
        }

        if(this.posX <= Circulo.RADIO){
            this.posX = Circulo.RADIO;
            this.velX *= -1;
        }

        if(this.posY >= maxBorderY){
            this.posY = maxBorderY;
            this.velY *= -1;
        }

        if(this.posY <= Circulo.RADIO){
            this.posY = Circulo.RADIO;
            this.velY *= -1;
        }
    }

    public boolean checkAndMofidyCourseIfCircle(Circulo circulo){
        int diferenciaX = this.posX - circulo.posX;
        int diferenciaY = this.posY - circulo.posY;

        if(Math.abs(diferenciaY) <= Circulo.RADIO && Math.abs(diferenciaX) <= Circulo.RADIO) {
            if (Math.abs(diferenciaX) <= Circulo.RADIO) {

                //this.posX += diferenciaX;
                this.velX *= -1;
                this.checkAndModifyCourseBorder(this.maxWidth, this.maxHeight);
                return true;
            }

            if (Math.abs(diferenciaY) <= Circulo.RADIO) {

                //this.posY += diferenciaY;
                this.velY *= -1;
                this.checkAndModifyCourseBorder(this.maxWidth, this.maxHeight);
                return true;
            }
        }

        return false;
    }

    public void mover(){
        this.posX += this.velX;
        this.posY += this.velY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public Paint getPintura(){
        return this.pintura;
    }
}
