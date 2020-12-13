package com.cant0r.cms.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MediumRepository extends JpaRepository<Medium,Integer> {
    Page<Medium> findAll(Pageable p);

    @Query("SELECT m FROM Medium m WHERE m.id = :id")
    Page<Medium> findByEntry(@Param("id")int id);
}
