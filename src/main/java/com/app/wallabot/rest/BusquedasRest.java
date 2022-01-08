package com.app.wallabot.rest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.wallabot.dtos.ArticulosDTO;
import com.app.wallabot.dtos.BusquedasDTO;
import com.app.wallabot.entities.Busquedas;
import com.app.wallabot.repositories.BusquedasRepository;
import com.app.wallabot.servicios.ServicioArticulos;

@RestController
@RequestMapping("/api/v1/busquedas")
public class BusquedasRest {

	@Autowired
	BusquedasRepository busquedasRepo;

	@Autowired
	ServicioArticulos servicioArticulos;

	@GetMapping("")
	public ResponseEntity<List<BusquedasDTO>> getBusquedas() {
		return ResponseEntity
				.ok(busquedasRepo.findAll().parallelStream().map(Busquedas::toDTO).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<BusquedasDTO> getBusquedasId(@PathVariable(value = "id") Long id) {
		Busquedas busqueda = busquedasRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Busqueda no encontrada con el id: " + id));
		return ResponseEntity.ok().body(busqueda.toDTO());
	}

	@PostMapping("")
	public Busquedas createBusquedas(@RequestBody Busquedas busqueda) throws IOException, URISyntaxException {
		Busquedas nueva = busquedasRepo.saveAndFlush(busqueda);
		servicioArticulos.guardarArticulosBusqueda(nueva);
		return nueva;
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteBusquedas(@PathVariable(value = "id") Long busquedasId)
			throws ResourceNotFoundException {
		Busquedas busqueda = busquedasRepo.findById(busquedasId)
				.orElseThrow(() -> new ResourceNotFoundException("Busqueda no encontrada con id :: " + busquedasId));

		busquedasRepo.delete(busqueda);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@GetMapping("/{id}/articulosNuevos")
	public ResponseEntity<List<ArticulosDTO>> getArticulosNuevos(@PathVariable(value = "id") Long busquedasId) throws URISyntaxException {
		return ResponseEntity.ok(servicioArticulos.getNuevosArticulos(busquedasId));
	}

}
