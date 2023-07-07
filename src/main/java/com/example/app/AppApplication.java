package com.example.app;

import feign.FeignException;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
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
    public void makeRequestToShawnMendesEndpoint() {
        try {

            ShawnMendesResponse response = shawnMendesClient.makeSearchRequest("shawnmendes", 3);
            System.out.println(response);
            response.results().stream().map(s -> s.trackName()).forEach(System.out::println);

        } catch (FeignException.FeignClientException exception) {
            System.out.println("Feign client exception "  + exception.status()); //getMessage print body message
        } catch (FeignException.FeignServerException serverException) {
            System.out.println("Feign server exception " + serverException.getMessage() + " " + serverException.status());
        } catch (RetryableException retryableException) {
            System.out.println("Retryable exception " + retryableException.getMessage() + " " + retryableException.status());
        } catch (FeignException feignException) {
            System.out.println("Feign exception " + feignException.getMessage() + " " + feignException.status());
        }
    }


}
