package cz.vsb.pocasi.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.vsb.pocasi.City;
import cz.vsb.pocasi.connector.WeatherstackConnector;
import cz.vsb.pocasi.dto.WeatherDto;
import cz.vsb.pocasi.service.WeatherService;
@RestController
public class WeatherController {
	WeatherService service;
	@RequestMapping("/weather")
	public Collection<WeatherDto> getWeather() {
		ArrayList<WeatherDto> wdtos  = new ArrayList<WeatherDto>();
		service = new WeatherService();
		for (City city : City.values()) {
			WeatherDto weatherDto = service.getWeatherForCity(city);
			wdtos.add(weatherDto);
		}
		return wdtos;
	}
	@RequestMapping("/weather/{city}")
	public WeatherDto getWeatherForCity(@PathVariable String city) {
		City cityEnum = City.valueOf(city.toUpperCase());
		service = new WeatherService();
		return service.getWeatherForCity(cityEnum);
	}
}
