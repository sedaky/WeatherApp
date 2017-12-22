package com.havaDurumu.havaDurumu;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class Service {

    RestTemplate restTemplate;
    Weather weather;


    public Weather getWeather(String city){

        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        weather = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=337d3e96225dc70ca4e20a8799403630", Weather.class);

        return weather;
    }

    public Weather getWeather(){
        return this.weather;
    }
}