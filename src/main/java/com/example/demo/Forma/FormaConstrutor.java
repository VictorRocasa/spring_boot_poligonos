package com.example.demo.Forma;

import java.util.List;

public class FormaConstrutor {
  List<Integer> poligonos;
  List<Integer> formas;
  

    public FormaConstrutor() {
    }

    public FormaConstrutor(List<Integer> poligonos, List<Integer> formas) {
        this.poligonos = poligonos;
        this.formas = formas;
    }


    public List<Integer> getPoligonos() {
        return this.poligonos;
    }

    public void setPoligonos(List<Integer> poligonos) {
        this.poligonos = poligonos;
    }

    public List<Integer> getFormas() {
        return this.formas;
    }

    public void setFormas(List<Integer> formas) {
        this.formas = formas;
    }


    @Override
    public String toString() {
        return "{" +
            " poligonos='" + getPoligonos() + "'" +
            ", formas='" + getFormas() + "'" +
            "}";
    }
    
}
