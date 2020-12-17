package com.cant0r.cms.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ContainerTypeRepository extends JpaRepository<ContainerType, String> {
    @Query(value = "SELECT c FROM ContainerType c WHERE c.name LIKE %:name% and c.description LIKE %:desc%")
    public Page<ContainerType> findAllByParams(@Param("name")String name, @Param("desc")String desc, Pageable pageable);

}
