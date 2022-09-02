package com.example.demo.Poligono;

import java.util.ArrayList;
import java.util.List;

public class LadosPoligono {
    private List<Float> tamanhos;
    
    public LadosPoligono(){
        tamanhos = new ArrayList<Float>();
    }

    public void adicionarTamanho(float tamanho){
        tamanhos.add(tamanho);
    }

    public List<Float> getTamanhos(){
        return tamanhos;
    }
}
