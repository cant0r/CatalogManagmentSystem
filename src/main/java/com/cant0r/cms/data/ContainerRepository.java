package com.cant0r.cms.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContainerRepository extends JpaRepository<Container, Integer> {


    @Query(value = "SELECT c FROM Container c JOIN c.type t WHERE t.name = :type or c.name LIKE :name or c.location LIKE :location or c.capacity <= :cap")
    Page<Container> findAllByParams(@Param("type")String type, @Param("name")String name, @Param("location")String location, @Param("cap")int capacity, Pageable pageable);

}
