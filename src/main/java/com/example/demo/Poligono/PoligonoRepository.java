package com.example.demo.Poligono;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Forma.Forma;

@Repository
public interface PoligonoRepository extends JpaRepository<Poligono, Integer>{

    List<Poligono> findByOrderByIdAsc();

    List<Poligono> findByOrderByLadosAsc();

    List<Poligono> findPoligonosByForma(Forma forma);

    List<Poligono> findByForma(Forma forma);

    @Query(value = "select lados,tamanho,count(*) from poligono group by (lados,tamanho,forma_id) having forma_id is null order by lados asc;", 
    nativeQuery = true)
    List<Object[]> estoquePoligonos();

}
