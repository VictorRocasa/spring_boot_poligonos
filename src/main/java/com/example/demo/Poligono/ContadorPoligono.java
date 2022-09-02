package com.example.demo.Poligono;


public class ContadorPoligono {
    int lados;
    float tamanho;
    int ocorrencias;


    public ContadorPoligono(int lados, float tamanho) {
        this.lados = lados;
        this.tamanho = tamanho;
    }

    public int getLados() {
        return this.lados;
    }

    public void setLados(int lados) {
        this.lados = lados;
    }

    public float getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }
 
    public int getOcorrencias() {
        return this.ocorrencias;
    }

    public void setOcorrencias(int ocorrencias) {
        this.ocorrencias = ocorrencias;
    }


}

