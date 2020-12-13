package com.cant0r.cms.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContainerTypeRepository extends JpaRepository<ContainerType, String> {

    List<ContainerType> findAll();
}
