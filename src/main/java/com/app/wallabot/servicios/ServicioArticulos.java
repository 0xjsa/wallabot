package com.app.wallabot.servicios;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.wallabot.dtos.ArticulosDTO;
import com.app.wallabot.dtos.wallapop.WProductoDTO;
import com.app.wallabot.entities.Articulos;
import com.app.wallabot.entities.Busquedas;
import com.app.wallabot.repositories.ArticulosRepository;
import com.app.wallabot.repositories.BusquedasRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ServicioArticulos {

	@Autowired
	ArticulosRepository articulosRepo;

	@Autowired
	BusquedasRepository busquedasRepo;

	/**
	 * Metodo que obtiene los articulos de la url de la busqueda y los guarda en la
	 * tabla articulos
	 * 
	 * @param nueva
	 * @throws IOException
	 * @throws URISyntaxException
	 * @author j.angel
	 */
	public void guardarArticulosBusqueda(Busquedas nueva) throws IOException, URISyntaxException {

		getProductosFromUrl(nueva.getUrl()).parallelStream().forEach((item) -> {
			Articulos nuevo = item.toArticulos();
			nuevo.setBusqueda(nueva);
			articulosRepo.saveAndFlush(nuevo);
		});
	}

	public List<WProductoDTO> getProductosFromUrl(String url) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(url);

		Map<String, Object> result = restTemplate.getForObject(uri, Map.class);
		ArrayList<String> searchObject = (ArrayList<String>) result.get("search_objects");

		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(searchObject, new TypeReference<List<WProductoDTO>>() {
		});
	}

	public List<ArticulosDTO> getNuevosArticulosBusqueda(Long idBusqueda) throws URISyntaxException {
		Optional<Busquedas> busqueda = busquedasRepo.findById(idBusqueda);

		List<Articulos> existentes = articulosRepo.findByBusqueda(busqueda.get());

		List<Articulos> nuevos = getProductosFromUrl(busqueda.get().getUrl()).parallelStream()
				.map(WProductoDTO::toArticulos).collect(Collectors.toList());

		nuevos.removeAll(existentes);

		return (nuevos != null && !nuevos.isEmpty())
				? nuevos.parallelStream().map(Articulos::toDTO).collect(Collectors.toList())
				: null;
	}

	public List<ArticulosDTO> getArticulosNuevos() throws URISyntaxException {
		List<Busquedas> busquedas = busquedasRepo.findAll();

		List<ArticulosDTO> articulosNuevos = new ArrayList<ArticulosDTO>();
		busquedas.forEach((busqueda) -> {
			try {
				articulosNuevos.addAll(getNuevosArticulosBusqueda(busqueda.getId()));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		});

		return articulosNuevos;
	}

	public List<ArticulosDTO> getArticulosByBusqueda(Long idBusqueda) {
		Optional<Busquedas> busqueda = busquedasRepo.findById(idBusqueda);

		return articulosRepo.findByBusqueda(busqueda.get()).parallelStream().map(Articulos::toDTO)
				.collect(Collectors.toList());
	}

}
