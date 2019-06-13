package com.act.kafka.weather;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @RequestMapping(value = "/getWeather", method = RequestMethod.GET)
    public void getWeaher() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://www.weather.com.cn/data/sk/101110101.html";
        JSONObject body = restTemplate.getForEntity(url, JSONObject.class).getBody();
        System.out.println(body);
    }


}
