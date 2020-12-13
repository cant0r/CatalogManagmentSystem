package com.cant0r.cms.data;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String location;
    private int capacity;
    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "name")
    private ContainerType type;
}
