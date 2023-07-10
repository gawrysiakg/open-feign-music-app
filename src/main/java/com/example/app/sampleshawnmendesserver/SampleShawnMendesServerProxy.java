package com.example.app.sampleshawnmendesserver;

import com.example.app.itunes.ItunesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//@FeignClient(value = "itunes-client", url = "https://itunes.apple.com") //moved to properties
@FeignClient(value = "sample-server-shawn-mendes-client")
public interface SampleShawnMendesServerProxy {


    @GetMapping("/shawn/songs")
    SampleServerShawnMendesResponse fetchAllSongs (@RequestHeader String requestId);

    @PostMapping("/shawn/songs")
    SampleServerShawnMendesResponse addSong (@RequestBody SampleShawnMendesRequest request);
}
