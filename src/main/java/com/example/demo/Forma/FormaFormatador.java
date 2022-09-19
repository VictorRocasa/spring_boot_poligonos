package com.example.demo.Forma;

import java.util.List;

import com.example.demo.Poligono.Poligono;

public class FormaFormatador {
    int id;
    List<Poligono> poligonos;
    List<Forma> formas;

    public FormaFormatador() {
    }

    public FormaFormatador(int id, List<Poligono> poligonos, List<Forma> formas) {
        this.id = id;
        this.poligonos = poligonos;
        this.formas = formas;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Poligono> getPoligonos() {
        return this.poligonos;
    }

    public void setPoligonos(List<Poligono> poligonos) {
        this.poligonos = poligonos;
    }

    public List<Forma> getFormas() {
        return this.formas;
    }

    public void setFormas(List<Forma> formas) {
        this.formas = formas;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", poligonos='" + getPoligonos() + "'" +
            ", formas='" + getFormas() + "'" +
            "}";
    }

}

