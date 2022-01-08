package com.app.wallabot.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MonitorRest {

	@GetMapping("/html")
	public ResponseEntity<String> obtenerHtmlWallapop() throws IOException {

		List<String> idProducts = new ArrayList<>();
		Document doc = Jsoup.connect("https://es.wallapop.com/moviles-telefonos").get();
		Elements elements = doc.select(("div[class*=card js-masonry-item card-product product]"));
		elements.forEach((element) -> {
			if (elements.indexOf(element) < 5) {
				idProducts.add(element.attr("itemid"));
			}
		});

		return new ResponseEntity<>(idProducts.toString(), HttpStatus.OK);
	}

}
