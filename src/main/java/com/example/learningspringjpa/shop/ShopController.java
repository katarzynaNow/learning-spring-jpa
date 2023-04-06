package com.example.learningspringjpa.shop;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public String list(Model model, @RequestParam(required = false) Category category){

        if(category == null){
            model.addAttribute("products", repository.findAll());
        } else {
            model.addAttribute("products", repository.findAllByCategory(category));
        }
        return "shop/list";
    }

    @GetMapping("/create")
    public String create (Model model){
        Product newProduct = new Product();
        Category[] categories = Category.values();

        model.addAttribute("newProduct", newProduct);
        model.addAttribute("categories", categories);
        return "shop/create";
    }

    @PostMapping("/create")
    public String createAction(Product newProduct, @RequestParam("file")MultipartFile file) throws IOException {
        newProduct.setId(null);
        newProduct.setPicture(file.getBytes());
        repository.save(newProduct);
        return "redirect:/shop";
    }

    @GetMapping("/product/image/{id}")
    public void showProductImage(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");

        Product product = repository.findById(id).get();

        InputStream is = new ByteArrayInputStream(product.getPicture());
        IOUtils.copy(is, response.getOutputStream());
    }

}
