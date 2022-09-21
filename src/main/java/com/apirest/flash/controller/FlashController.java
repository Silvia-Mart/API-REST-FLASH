package com.apirest.flash.controller;

import com.apirest.flash.models.Flash;
import com.apirest.flash.repository.FlashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/flash")
public class FlashController {
    @Autowired
    FlashRepository flashRepository;

    @GetMapping("/{id}")
    public Flash listarUsuarioId (@PathVariable(value = "id")long id)
    {
        return flashRepository.findById(id);
    }

    @PostMapping("/")
    public Flash salvarUsuario (@RequestBody @Validated Flash flash)
    {
        return flashRepository.save(flash);
    }

    @PutMapping("/add/{id}/{saldo}")
    public Flash atualizarSaldoSomar (@PathVariable(value = "id")long id,@PathVariable(value = "saldo") BigDecimal saldo)
    {
        Flash flash = flashRepository.findById(id);
        BigDecimal saldoAdd = flash.getSaldo();
        saldoAdd = saldoAdd.add(saldo);
        flash.setSaldo(saldoAdd);
        return flashRepository.save(flash);

    }

    @PutMapping("/subtract/{id}/{saldo}")
    public String atualizarSaldoSubtracao (@PathVariable(value = "id")long id,@PathVariable(value = "saldo") BigDecimal saldo)
    {
        Flash flash = flashRepository.findById(id);
        BigDecimal saldoSubt = flash.getSaldo();
        if (saldo.compareTo(saldoSubt) == 1  )
        {
            return " Não é possível efetuar a compra por falta de saldo!"+ saldoSubt;
        }else {
            saldoSubt = saldoSubt.subtract(saldo);
            flash.setSaldo(saldoSubt);
            flashRepository.save(flash);
            return " Compra realizada com sucesso, seu saldo de carteira agora é: " + saldoSubt;
        }


    }

}
