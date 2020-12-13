package com.cant0r.cms.data;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Medium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int capacity;

    @ManyToOne
    private Container container;
}
