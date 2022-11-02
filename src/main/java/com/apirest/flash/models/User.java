package com.apirest.flash.models;

import com.sun.istack.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal balance;

    @ManyToMany
    @JoinTable(
            name = "user_partners",
            joinColumns = @JoinColumn(name = "partners_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Partners> partners;

    public List<Partners> getPartners() {
        return partners;
    }

    public void setPartners(List<Partners> partners) {
        this.partners = partners;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
