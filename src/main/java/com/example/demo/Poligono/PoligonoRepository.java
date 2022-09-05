package com.example.demo.Poligono;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Forma.Forma;

@Repository
public interface PoligonoRepository extends JpaRepository<Poligono, Integer>{

    List<Poligono> findByOrderByIdAsc();

    List<Poligono> findByOrderByLadosAsc();

    List<Poligono> findPoligonosByForma(Forma forma);

}
