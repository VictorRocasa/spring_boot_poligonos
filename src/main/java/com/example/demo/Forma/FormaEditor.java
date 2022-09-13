package com.example.demo.Forma;

import java.util.List;

public class FormaEditor {
    int id;
    List<Integer> novosPoligonos;
    List<Integer> novasFormas;
    List<Integer> poligonosRemover;
    List<Integer> formasRemover;


    public FormaEditor() {
    }

    public FormaEditor(int id, List<Integer> novosPoligonos, List<Integer> novasFormas, List<Integer> poligonosRemover, List<Integer> formasRemover) {
        this.id = id;
        this.novosPoligonos = novosPoligonos;
        this.novasFormas = novasFormas;
        this.poligonosRemover = poligonosRemover;
        this.formasRemover = formasRemover;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getNovosPoligonos() {
        return this.novosPoligonos;
    }

    public void setNovosPoligonos(List<Integer> novosPoligonos) {
        this.novosPoligonos = novosPoligonos;
    }

    public List<Integer> getNovasFormas() {
        return this.novasFormas;
    }

    public void setNovasFormas(List<Integer> novasFormas) {
        this.novasFormas = novasFormas;
    }

    public List<Integer> getPoligonosRemover() {
        return this.poligonosRemover;
    }

    public void setPoligonosRemover(List<Integer> poligonosRemover) {
        this.poligonosRemover = poligonosRemover;
    }

    public List<Integer> getFormasRemover() {
        return this.formasRemover;
    }

    public void setFormasRemover(List<Integer> formasRemover) {
        this.formasRemover = formasRemover;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", novosPoligonos='" + getNovosPoligonos() + "'" +
            ", novasFormas='" + getNovasFormas() + "'" +
            ", poligonosRemover='" + getPoligonosRemover() + "'" +
            ", formasRemover='" + getFormasRemover() + "'" +
            "}";
    }
    
}
