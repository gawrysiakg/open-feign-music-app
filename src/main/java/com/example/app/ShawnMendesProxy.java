package com.example.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "shawnmendes-client", url = "https://itunes.apple.com")

public interface ShawnMendesProxy {

//	https://itunes.apple.com/search?term=shawnmendes&limit=1
    @GetMapping("/search")
    public String makeSearchRequest(@RequestParam String term, @RequestParam int limit);
}
