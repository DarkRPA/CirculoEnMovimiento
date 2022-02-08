package edu.pmdm.circuloenmovimiento;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class CirculoView extends View {
    private ArrayList<Circulo> arrayCirculos = new ArrayList<>();

    private int centroPrincipalX;
    private int centroPrincipalY;
    private Random random;

    public CirculoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.random = new Random();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.centroPrincipalX = w / 2;
        this.centroPrincipalY = h / 2;

        for(int i = 0; i < 2; i++){
            this.arrayCirculos.add(new Circulo(this.random.nextInt(10), this.random.nextInt(10), this.centroPrincipalX, this.centroPrincipalY));
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int tamanioX = canvas.getWidth() - Circulo.RADIO;
        int tamanioY = canvas.getHeight() - Circulo.RADIO;

        for(Circulo circulo : this.arrayCirculos){
            circulo.setMaxWidth(tamanioX);
            circulo.setMaxHeight(tamanioY);
        }

        for(Circulo circulo : this.arrayCirculos){
            //Primero debemos de comprobar que no colisionan las bolas
            boolean haColisionado = false;
            for(Circulo comprobarCirculo : this.arrayCirculos){
                if(circulo.equals(comprobarCirculo)) continue;
                if(circulo.checkAndMofidyCourseIfCircle(comprobarCirculo) && comprobarCirculo.checkAndMofidyCourseIfCircle(circulo)){
                    haColisionado = true;
                }
            }

            if(!haColisionado){
                circulo.checkAndModifyCourseBorder(tamanioX, tamanioY);
            }

            circulo.mover();

            canvas.drawCircle(circulo.getPosX(), circulo.getPosY(), Circulo.RADIO, circulo.getPintura());
        }
        this.postInvalidateDelayed(10);
        super.onDraw(canvas);
    }
}
