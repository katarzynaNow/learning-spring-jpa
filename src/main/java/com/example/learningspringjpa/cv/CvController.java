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

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepsoitory jobRepsoitory;


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

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id){
        model.addAttribute("cv", repository.findById(id).get());

        Skill newSkill = new Skill();
        model.addAttribute("newSkill", newSkill);
        model.addAttribute("types", Type.values());

        Job newJob = new Job();
        model.addAttribute("newJob", newJob);

        return "cv/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPost(@PathVariable Integer id, CV cv,@RequestParam("file") MultipartFile file) throws IOException {
        CV existing = repository.findById(id).get();
        existing.setBirthDate(cv.getBirthDate());
        existing.setFirstName(cv.getFirstName());
        existing.setLastName(cv.getLastName());

        if(!file.isEmpty()) {
            existing.setPicture(file.getBytes());
        }

        repository.save(existing);
        return "redirect:/cv/view/" + id;
    }

    @PostMapping("/edit/{cvId}/skill")
    public String addSkill (Skill skill, @PathVariable Integer cvId) {
        CV existing = repository.findById(cvId).get();
        skill.setCv(existing);
        skillRepository.save(skill);
        return "redirect:/cv/edit/" + cvId;
    }

    @GetMapping("/edit/{cvId}/skill/{id}/delete")
    public String deleteSkill(@PathVariable Integer cvId, @PathVariable Integer id) {
        skillRepository.deleteById(id);
        return "redirect:/cv/edit/" + cvId;
    }

    @PostMapping("/edit/{cvId}/job")
    public String addJob (Job job, @PathVariable Integer cvId) {
        CV existing = repository.findById(cvId).get();
        job.setCv(existing);
        jobRepsoitory.save(job);
        return "redirect:/cv/edit/" + cvId;
    }

    @GetMapping("/edit/{cvId}/job/{id}/delete")
    public String deleteJob(@PathVariable Integer cvId, @PathVariable Integer id) {
        jobRepsoitory.deleteById(id);
        return "redirect:/cv/edit/" + cvId;
    }
}
