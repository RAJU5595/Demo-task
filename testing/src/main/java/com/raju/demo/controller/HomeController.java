package com.raju.demo.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raju.demo.pojo.Sample;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/first")
    public String testOne(){
        return "Hello World!";
    }

    @GetMapping("/second")
    public Integer testTwo(){
        return 2022;
    }

    @GetMapping("/third")
    public Sample testThree(){
        return new Sample("raju","chraj541@gmail.com","PlanSource");
    }

    @PostMapping("/fourth")
    public ResponseEntity<String> testFour(){
        return new ResponseEntity<>("Source created successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/fifth")
    public ResponseEntity<String> testFive(){
        return new ResponseEntity<>("Source deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/sixth")
    public ResponseEntity<String> testSix(@RequestHeader Map<String, String> headers){
        String customHeader = headers.get("custom");
        if(customHeader==null){
            return  new ResponseEntity<>("You don't have authorization :( ",HttpStatus.FORBIDDEN);
        }
        return  new ResponseEntity<>("Source retrieved successfully",HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<?> getDate() throws URISyntaxException, IOException, InterruptedException {
            String url = "http://date.jsontest.com/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> map = objectMapper.readValue(response.body(), Map.class);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @GetMapping("/weather")
    public ResponseEntity<?> getWeather() throws URISyntaxException, IOException, InterruptedException {
        String url = "http://api.weatherstack.com/current?access_key=1efaeb389c49f8dfb8003ebf3d954291&query=New%20York";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> map = objectMapper.readValue(response.body(), Map.class);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

}
