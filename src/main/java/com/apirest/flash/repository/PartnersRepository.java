package com.apirest.flash.repository;

import com.apirest.flash.models.Partners;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnersRepository extends JpaRepository<Partners,Long> {
    Partners findById(long id);
}
