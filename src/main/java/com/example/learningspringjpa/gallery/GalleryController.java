package com.example.learningspringjpa.gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

    @Autowired
    private PictureRepository pictureRepository;

    @GetMapping
    public String gallery(Model model, @RequestParam(required = false) String mimeType) {
        if(mimeType == null) {
            model.addAttribute("pictures", pictureRepository.findAll());
        } else {
            model.addAttribute("pictures", pictureRepository.findAllByMimeType(mimeType));
        }
        return "gallery";
    }

    @PostMapping
    public String submit(@RequestParam("picture")MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return "redirect:/gallery";
        }
        Picture newPicture = new Picture();
        newPicture.name = file.getOriginalFilename();
        newPicture.content = file.getBytes();
        newPicture.mimeType = file.getContentType();
        pictureRepository.save(newPicture);
        return "redirect:/gallery";
    }

    @GetMapping("/delete/{name}")
    public String delete(@PathVariable String name) {
        pictureRepository.deleteById(name);
        return "redirect:/gallery";
    }

    @GetMapping("/{name}")
    @ResponseBody
    public ResponseEntity<byte[]> content(@PathVariable String name){
        Picture picture = pictureRepository.findById(name).get();

        return ResponseEntity.status((HttpStatus.OK))
                .contentType(MediaType.parseMediaType(picture.mimeType))
                .body(picture.content);
    }
}
