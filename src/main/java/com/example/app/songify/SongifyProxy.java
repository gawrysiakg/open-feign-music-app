package com.example.app.songify;

import com.example.app.songify.dto.request.CreateSongRequestDto;
import com.example.app.songify.dto.request.PartiallyUpdateSongRequestDto;
import com.example.app.songify.dto.request.UpdateSongRequestDto;
import com.example.app.songify.dto.response.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "songify-client")
public interface SongifyProxy {


    @GetMapping("/songs")
    GetAllSongsResponseDto fetchAllSongs (@RequestParam Integer limit);

    @GetMapping("/songs/{id}")
    GetSongResponseDto getSongById(@PathVariable String id);

    @PostMapping("/songs")
    CreateSongResponseDto addSong (@RequestBody CreateSongRequestDto request);

    @PutMapping("/songs/{id}")
    UpdateSongResponseDto updateSongById(@PathVariable String id, @RequestBody UpdateSongRequestDto request);

    @PatchMapping("/songs/{id}")
    PartiallyUpdateSongResponseDto partiallyUpdateSongById(@PathVariable String id, @RequestBody PartiallyUpdateSongRequestDto request);

    @DeleteMapping("/songs/{id}")
    DeleteSongResponseDto deleteByPathVariableId (@PathVariable String id);

//    @DeleteMapping("/songs")
//    DeleteSongResponseDto deleteByIdUsingQueryParam (@RequestParam(name = "id") String id); //or String id

}
