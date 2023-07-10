package com.example.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(value = "itunes-client", url = "https://itunes.apple.com") //moved to properties
@FeignClient(value = "itunes-client")
public interface ItunesProxy {

//	https://itunes.apple.com/search?term=shawnmendes&limit=1
    @GetMapping("/search")
    ShawnMendesResponse makeSearchRequest(@RequestParam("term") String term, @RequestParam("limit") Integer limit);
}
