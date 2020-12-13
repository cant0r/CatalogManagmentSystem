package com.cant0r.cms.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EntryRepository extends JpaRepository<Entry, Integer> {
    Page<Entry> findAll(Pageable p);

    @Query("SELECT e FROM Entry e JOIN e.medium m WHERE m.id = :id")
    Page<Entry> findAllByMediumId(@Param("id")int id);
}
