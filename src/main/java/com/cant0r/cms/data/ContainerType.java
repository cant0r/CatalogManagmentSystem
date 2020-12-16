package com.cant0r.cms.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "ContainerType", schema = "data")
public class ContainerType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String name;
    @Column
    public String description;

    @OneToMany
    public List<Container> containers;


    public ContainerType() {

    }
}
