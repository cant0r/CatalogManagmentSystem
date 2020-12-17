package com.cant0r.cms.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MediumRepository extends JpaRepository<Medium,Integer> {

    @Query(value = "SELECT m FROM Medium m JOIN m.container c WHERE m.name like %:name% and m.capacity <= :cap and c.name LIKE %:containerName%")
    Page<Medium> findAllByParams(@Param("cap")int cap,@Param("name")String name, @Param("containerName")String containerName, Pageable pageable);

}
