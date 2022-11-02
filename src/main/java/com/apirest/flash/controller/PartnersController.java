package com.apirest.flash.controller;

import com.apirest.flash.models.Partners;
import com.apirest.flash.models.User;
import com.apirest.flash.repository.PartnersRepository;
import com.apirest.flash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
public class PartnersController {
    @Autowired
    PartnersRepository partnersRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public List<Partners> listPartners ()
    {
     return partnersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Partners listPartnersId (@PathVariable(value = "id")long id)
    {
        return partnersRepository.findById(id);
    }

    @PostMapping("/")
    public Partners savePartners (@RequestBody @Validated Partners partners)
    {
        return partnersRepository.save(partners);
    }

    @PutMapping("/update/{id}")
    public Partners updatePartners (@PathVariable(value = "id")long id)
    {
        Partners partners = partnersRepository.findById(id);
        return partnersRepository.save(partners);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePartners (@PathVariable(value = "id")long id)
    {
        Partners partners = partnersRepository.findById(id);
        partnersRepository.delete(partners);
    }
}
