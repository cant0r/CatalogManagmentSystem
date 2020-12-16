package com.cant0r.cms.data;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Medium", schema = "data")
public class Medium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @Column
    public String name;
    @Column
    public int capacity;

    @ManyToOne
    public Container container;
    @OneToMany
    public List<Entry> entries;
}
