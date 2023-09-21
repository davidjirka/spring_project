package cz.vsb.pocasi.service;

import cz.vsb.pocasi.City;
import cz.vsb.pocasi.connector.WeatherstackConnector;
import cz.vsb.pocasi.dto.WeatherDto;
import cz.vsb.pocasi.dto.WeatherstackDto;

public class WeatherService {

	public WeatherDto getWeatherForCity(City cityEnum) {
		WeatherstackConnector connector = new WeatherstackConnector();
		WeatherstackDto weatherstackData = connector.getWeatherForCity(cityEnum);
		return transformDto(weatherstackData);
	}

	private WeatherDto transformDto(WeatherstackDto weatherstackData) {
		WeatherDto wdto = new WeatherDto();
		wdto.setLocation(weatherstackData.getLocation().getName());
		wdto.setRel_humidity(weatherstackData.getCurrent().getHumidity());
		wdto.setTemp_celsius(weatherstackData.getCurrent().getTemperature());
		wdto.setTimestamp(weatherstackData.getLocation().getLocaltime());
		wdto.setWeatherDescription(weatherstackData.getCurrent().getWeather_descriptions().get(0));
		wdto.setWindDirection(weatherstackData.getCurrent().getWind_dir());
		wdto.setWindSpeed_mps(weatherstackData.getCurrent().getWind_speed()/3.6);
		return wdto;
	}

}
