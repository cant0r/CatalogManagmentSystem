package com.cant0r.cms.data;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Entry", schema = "data")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @Column
    public String name;
    @Column
    public int size;
    @ManyToOne
    public Medium medium;
}
