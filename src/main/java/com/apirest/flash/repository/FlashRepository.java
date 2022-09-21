package com.apirest.flash.repository;

import com.apirest.flash.models.Flash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashRepository extends JpaRepository<Flash,Long> {
    Flash findById(long id);
}
