package com.example.app;

import com.example.app.itunes.ItunesProxy;
import com.example.app.itunes.ItunesResponse;
import feign.FeignException;

import feign.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;


@SpringBootApplication
@EnableFeignClients
@Log4j2
public class AppApplication {



    @Autowired
    ItunesProxy itunesClient;
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
    public void run() {
        try {

            ItunesResponse response = itunesClient.makeSearchRequest("shawnmendes", 3);
            System.out.println(response);
            response.results().stream().map(s -> s.trackName()).forEach(System.out::println);

        } catch (FeignException.FeignClientException exception) {
            log.error("Feign client exception "  + exception.status()); //getMessage print body message
        } catch (FeignException.FeignServerException serverException) {
            log.error("Feign server exception " + serverException.getMessage() + " " + serverException.status());
        } catch (RetryableException retryableException) {
            log.error("Retryable exception " + retryableException.getMessage() + " " + retryableException.status());
        } catch (FeignException feignException) {
            log.error("Feign exception " + feignException.getMessage() + " " + feignException.status());
        }
    }


}
