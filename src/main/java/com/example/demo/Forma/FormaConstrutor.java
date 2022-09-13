package com.example.demo.Forma;

import java.util.List;

public class FormaConstrutor {
    int id;
    List<Integer> idsPoligonos;
    List<Integer> idsFormas;
  

    public FormaConstrutor() {
    }

    public FormaConstrutor(List<Integer> poligonos, List<Integer> formas) {
        this.idsPoligonos = poligonos;
        this.idsFormas = formas;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getPoligonos() {
        return this.idsPoligonos;
    }

    public void setPoligonos(List<Integer> poligonos) {
        this.idsPoligonos = poligonos;
    }

    public List<Integer> getFormas() {
        return this.idsFormas;
    }

    public void setFormas(List<Integer> formas) {
        this.idsFormas = formas;
    }


    @Override
    public String toString() {
        return "{" +
            " poligonos='" + getPoligonos() + "'" +
            ", formas='" + getFormas() + "'" +
            "}";
    }
    
}
