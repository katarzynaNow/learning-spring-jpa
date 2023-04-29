package com.example.learningspringjpa.cv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/cv")
public class CvController {

    @Autowired
    private CvRepository repository;

    @GetMapping
    public String list(Model model){
        model.addAttribute("cvs", repository.findAll());
        return "cv/list";
    }

    @GetMapping("/add")
    public String add(Model model){
        CV newCV = new CV();
        model.addAttribute("newCV", newCV);
        return "cv/add";
    }

    @PostMapping("/add")
    public String addPost(CV cv,@RequestParam("file") MultipartFile file) throws IOException {
        cv.setPicture(file.getBytes());
        repository.save(cv);
        return "redirect:/cv";
    }

    @GetMapping(value = "/file/{id}", produces = "image/*")
    @ResponseBody
    public byte[] picture(@PathVariable Integer id){
        return repository.findById(id)
                .get()
                .getPicture();
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable Integer id){
        model.addAttribute("cv", repository.findById(id).get());
        return "cv/view";
    }
}
