package com.cant0r.cms.data;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int size;
    @ManyToOne
    private Medium medium;
}
