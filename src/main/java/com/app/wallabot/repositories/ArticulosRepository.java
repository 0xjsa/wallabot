package com.app.wallabot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.wallabot.entities.Articulos;
import com.app.wallabot.entities.Busquedas;

@Repository
public interface ArticulosRepository extends JpaRepository<Articulos, String>{

	List<Articulos> findByBusqueda(Busquedas busqueda);

}
