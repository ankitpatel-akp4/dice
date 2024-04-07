package com.dice.task2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/rapid")
public class RapidController {
	ObjectMapper mapper = new ObjectMapper();
	@GetMapping("/city-forecast-summary/{locationName}")
	public ResponseEntity<JsonNode> GetForecastSummaryByLocationName(@PathVariable String locationName) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://forecast9.p.rapidapi.com/rapidapi/forecast/"+locationName+"/summary/"))
				.header("X-RapidAPI-Key", "60f89336b5msh891510e233a1eb2p17b363jsn260de7b70398")
				.header("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		JsonNode result = mapper.readTree(response.body());
		return ResponseEntity.ok(result);
	}

	
	@GetMapping("/city-hourly-forecas/{locationName}")
	public ResponseEntity<JsonNode> GetHourlyForecastByLocationName(@PathVariable String locationName) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://forecast9.p.rapidapi.com/rapidapi/forecast/"+locationName+"/hourly/"))
				.header("X-RapidAPI-Key", "60f89336b5msh891510e233a1eb2p17b363jsn260de7b70398")
				.header("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		JsonNode result = mapper.readTree(response.body());
		return ResponseEntity.ok(result);
	}

}
