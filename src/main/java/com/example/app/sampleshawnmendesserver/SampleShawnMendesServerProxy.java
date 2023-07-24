package com.example.app.sampleshawnmendesserver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//@FeignClient(value = "itunes-client", url = "https://itunes.apple.com") //moved to properties
@FeignClient(value = "sample-server-shawn-mendes-client")
public interface SampleShawnMendesServerProxy {


    @GetMapping("/shawn/songs")
    SampleServerShawnMendesResponse fetchAllSongs (@RequestHeader String requestId);
    @PostMapping("/shawn/songs")
    SampleServerShawnMendesResponse addSong (@RequestBody SampleShawnMendesRequest request);

    @DeleteMapping("/shawn/songs/{songId}")
    void deleteByPathVariableId (@PathVariable String songId);

    @DeleteMapping("/shawn/songs")
    void deleteByIdUsingQueryParam (@RequestParam(name = "id") String songId); //or String id
}
