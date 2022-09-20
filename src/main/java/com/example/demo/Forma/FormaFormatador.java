package com.example.demo.Forma;

import java.util.List;

import com.example.demo.Poligono.Poligono;

public class FormaFormatador {
    int id;
    List<Poligono> poligonos;
    List<Forma> formas;
    List<String> resumoFormas;

    public FormaFormatador() {
    }

    public FormaFormatador(int id, List<Poligono> poligonos, List<String> resumoFormas) {
        this.id = id;
        this.poligonos = poligonos;
        this.resumoFormas = resumoFormas;
    }

    public FormaFormatador(int id, List<Poligono> poligonos, List<Forma> formas, List<String> resumoFormas) {
        this.id = id;
        this.poligonos = poligonos;
        this.formas = formas;
        this.resumoFormas = resumoFormas;
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

    public List<String> getResumoFormas() {
        return this.resumoFormas;
    }

    public void setResumoFormas(List<String> resumoFormas) {
        this.resumoFormas = resumoFormas;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", poligonos='" + getPoligonos() + "'" +
            ", formas='" + getFormas() + "'" +
            ", resumoFormas='" + getResumoFormas() + "'" +
            "}";
    }
}

