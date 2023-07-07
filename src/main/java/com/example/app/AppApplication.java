package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableFeignClients
public class AppApplication {

    @Autowired
    ShawnMendesProxy shawnMendesClient;
    //Spring będzie automatycznie tworzył instancję klasy implementującej interfejs ShawnMendesProxy
    // i wstrzykiwał ją do pola shawnMendesClient
    // shawnMendesClient jest instancją klasy implementującej interfejs ShawnMendesProxy,
    // która została wstrzyknięta przez Spring Dependency Injection.
    // Jest to przykład wstrzykiwania zależności przez Spring, gdzie można zdefiniować interfejs jako typ zmiennej,
    // a Spring automatycznie dostarczy implementację tego interfejsu.

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }


    @EventListener(ApplicationStartedEvent.class)
    public void makeRequestToShawnMendesEndpoint(){
        ShawnMendesResponse response = shawnMendesClient.makeSearchRequest("shawnmendes", 3);
        System.out.println(response);


    }






}
