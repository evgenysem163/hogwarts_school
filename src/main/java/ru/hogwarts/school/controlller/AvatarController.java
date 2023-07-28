package ru.hogwarts.school.controlller;

import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.AvatarDto;
import ru.hogwarts.school.service.AvatarService;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/avatars")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping("/{id}/rom-db")
    public ResponseEntity<byte[]> getFromDb(@PathVariable long id){
        return build(avatarService.getFromDb(id));
    }
    @GetMapping
    public List<AvatarDto> getPage (@RequestParam (value = "page", required = false, defaultValue = "0") int page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return avatarService.getPage(Math.abs(page), Math.abs(size));
    }
    @GetMapping("/{id}/from-fs")
    public ResponseEntity<byte[]> getFromFs(@PathVariable long id){
        return build(avatarService.getFromFs(id));
    }

    private ResponseEntity<byte[]> build(Pair<byte[],String> stringPair){
        byte[] data = stringPair.getFirst();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(stringPair.getSecond()))
                .contentLength(data.length)
                .body(data);
    }

}
