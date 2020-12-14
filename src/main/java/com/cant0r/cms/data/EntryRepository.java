package com.cant0r.cms.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EntryRepository extends JpaRepository<Entry, Integer> {

    @Query("SELECT e FROM Entry e JOIN e.medium m WHERE m.id = :id")
    Page<Entry> findAllByMediumId(@Param("id")int id, Pageable pageable);

    @Query(value = "SELECT c FROM Entry c JOIN c.medium m WHERE c.name LIKE :name or c.size <= :size or m.name LIKE :discName")
    Page<Entry> findAllByParams(@Param("size")int size,@Param("name")String name, @Param("discName")String diskName, Pageable pageable);

}
