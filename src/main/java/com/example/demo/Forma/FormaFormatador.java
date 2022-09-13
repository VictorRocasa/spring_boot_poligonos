package com.example.demo.Forma;

import java.util.List;

public class FormaFormatador {
    int id;
    List<Integer> idsPoligonos;
    List<String> resumoPoligonos;
    List<Integer> idsFormas;
    List<String> resumoFormas;

    public FormaFormatador() {
    }

    public FormaFormatador(int id, List<Integer> idsPoligonos, List<String> resumoPoligonos, List<Integer> idsFormas, List<String> resumoFormas) {
        this.id = id;
        this.idsPoligonos = idsPoligonos;
        this.resumoPoligonos = resumoPoligonos;
        this.idsFormas = idsFormas;
        this.resumoFormas = resumoFormas;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getIdsPoligonos() {
        return this.idsPoligonos;
    }

    public void setIdsPoligonos(List<Integer> idsPoligonos) {
        this.idsPoligonos = idsPoligonos;
    }

    public List<String> getResumoPoligonos() {
        return this.resumoPoligonos;
    }

    public void setResumoPoligonos(List<String> resumoPoligonos) {
        this.resumoPoligonos = resumoPoligonos;
    }

    public List<Integer> getIdsFormas() {
        return this.idsFormas;
    }

    public void setIdsFormas(List<Integer> idsFormas) {
        this.idsFormas = idsFormas;
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
            ", idsPoligonos='" + getIdsPoligonos() + "'" +
            ", resumoPoligonos='" + getResumoPoligonos() + "'" +
            ", idsFormas='" + getIdsFormas() + "'" +
            ", resumoFormas='" + getResumoFormas() + "'" +
            "}";
    }

}

