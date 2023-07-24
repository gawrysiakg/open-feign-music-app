package com.example.app;

import com.example.app.itunes.ItunesProxy;
import com.example.app.sampleshawnmendesserver.SampleShawnMendesRequest;
import com.example.app.sampleshawnmendesserver.SampleShawnMendesServerProxy;
import com.example.app.songify.SongifyProxy;
import com.example.app.songify.dto.request.CreateSongRequestDto;
import com.example.app.songify.dto.request.PartiallyUpdateSongRequestDto;
import com.example.app.songify.dto.request.UpdateSongRequestDto;
import feign.FeignException;
import feign.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;


@SpringBootApplication
@EnableFeignClients
@Log4j2
public class MusicApp {

    ItunesProxy itunesClient;
    SampleShawnMendesServerProxy sampleShawnMendesServerClient;
    SongifyProxy songifyClient;

    public MusicApp(ItunesProxy itunesClient, SampleShawnMendesServerProxy sampleShawnMendesServerClient, SongifyProxy songifyClient) {
        this.itunesClient = itunesClient;
        this.sampleShawnMendesServerClient = sampleShawnMendesServerClient;
        this.songifyClient = songifyClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(MusicApp.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void run() {
        try {
    //songify:
            log.info(songifyClient.fetchAllSongs(null)); // 4 default songs from db(limit is optional so its null)
            log.info("adding new song:");
            log.info(songifyClient.addSong(new CreateSongRequestDto("Java is my Life", "The Programmers")));
            log.info(songifyClient.fetchAllSongs(null)); // 5 songs
            log.info("deleting song with id 5 (last added):");
            log.info(songifyClient.deleteByPathVariableId("5"));
            log.info(songifyClient.fetchAllSongs(null)); // 4 songs
            log.info(songifyClient.updateSongById("1", new UpdateSongRequestDto("updated UMBRELLA SONG", "updated RIHANNA")));
            log.info("get song with id 1:");
            log.info(songifyClient.getSongById("1"));
            log.info(songifyClient.fetchAllSongs(null)); // 4 songs
            log.info("partially updated song - only song name changed");
            log.info(songifyClient.partiallyUpdateSongById("1", new PartiallyUpdateSongRequestDto("PaRtIaLlY uPdAtEd UmBrElLa", null)));
            log.info(songifyClient.fetchAllSongs(null)); // 4 songs

    //sampleshawnmendesserver:
            //songsBySampleShawnMendesServerClient(); // it's correct

    //itunes:
            //log.info(itunesClient.makeSearchRequest("shawnmendes", 3)); // it's correct

        } catch (FeignException.FeignClientException exception) {
            log.error("Feign client exception " + exception.status()); //getMessage print body message
        } catch (FeignException.FeignServerException serverException) {
            log.error("Feign server exception " + serverException.getMessage() + " " + serverException.status());
        } catch (RetryableException retryableException) {
            log.error("Retryable exception " + retryableException.getMessage() + " " + retryableException.status());
        } catch (FeignException feignException) {
            log.error("Feign exception " + feignException.getMessage() + " " + feignException.status());
        }
    }

    private void songsBySampleShawnMendesServerClient() {
        log.info(sampleShawnMendesServerClient.fetchAllSongs("01"));
        log.info(sampleShawnMendesServerClient.addSong(new SampleShawnMendesRequest("MySongByFeign")));
        log.info(sampleShawnMendesServerClient.fetchAllSongs("01"));
        sampleShawnMendesServerClient.deleteByIdUsingQueryParam("0");
        log.info(sampleShawnMendesServerClient.fetchAllSongs("01"));
    }
}
