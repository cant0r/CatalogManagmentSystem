package com.cant0r.cms.data;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Container", schema = "data")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @Column
    public String name;
    @Column
    public String location;
    @Column
    public int capacity;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "name")
    public ContainerType type;

    @OneToMany
    public List<Medium> mediums;
}
