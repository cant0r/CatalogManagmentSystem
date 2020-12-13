package com.cant0r.cms.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class ContainerType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;
    private String description;

    public ContainerType() {

    }
}
