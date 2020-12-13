package com.cant0r.cms.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContainerRepository extends JpaRepository<Container, Integer> {

    Page<Container> findAll(Pageable p);

    @Query(value = "SELECT c FROM Container c JOIN c.type t WHERE t.name = :type")
    Page<Container> findAllByType(@Param("type")String type);

    @Query(value = "SELECT c FROM Container c WHERE c.id = :id")
    Page<Container> findByMediumId(@Param("id")int id);

}
