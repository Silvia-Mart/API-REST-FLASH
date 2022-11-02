package com.apirest.flash.controller;

import com.apirest.flash.models.Partners;
import com.apirest.flash.models.User;
import com.apirest.flash.repository.PartnersRepository;
import com.apirest.flash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PartnersRepository partnersRepository;

    @GetMapping("/")
    public List<User> listUsers()
    {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User listUsersId(@PathVariable(value = "id")long id)
    {
        return userRepository.findById(id);
    }

    @PostMapping("/")
    public User saveUser(@RequestBody @Validated User user)
    {
        return userRepository.save(user);
    }

    @PostMapping(value = "/partners/{id}")
    public void saveUser(@PathVariable(value = "id") long id, @RequestBody List<Long> idsPartners)
    {
        User user = userRepository.findById(id);
        List<Partners> partners =  partnersRepository.findAllById(idsPartners);

//        user.setPartners(partners);
         userRepository.save(user);
    }

    @PutMapping("/add/{id}/{balance}")
    public User updateBalanceSum(@PathVariable(value = "id")long id, @PathVariable(value = "saldo") BigDecimal saldo)
    {
        User user = userRepository.findById(id);
        BigDecimal balanceAdd = user.getBalance();
        balanceAdd = balanceAdd.add(saldo);
        user.setBalance(balanceAdd);
        return userRepository.save(user);

    }

    @PutMapping("/subtract/{id}/{balance}")
    public String updateSubtractionBalance(@PathVariable(value = "id")long id, @PathVariable(value = "balance") BigDecimal balance)
    {
        User user = userRepository.findById(id);
        BigDecimal balanceSubt = user.getBalance();
        if (balance.compareTo(balanceSubt) == 1  )
        {
            return " It is not possible to make the purchase due to lack of balance! "+ balanceSubt;
        }else {
            balanceSubt = balanceSubt.subtract(balance);
            user.setBalance(balanceSubt);
            userRepository.save(user);
            return " Successful purchase, your wallet balance is now: " + balanceSubt;
        }


    }

}
