package com.cant0r.cms.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class ContainerType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;
    private String description;

    @OneToMany
    private List<Container> containers;


    public ContainerType() {

    }
}
