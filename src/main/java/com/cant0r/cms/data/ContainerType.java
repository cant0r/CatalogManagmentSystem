package com.cant0r.cms.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ContainerType", schema = "data")
public class ContainerType {
    @Id
    private String name;
    @Column
    public String description;

    @OneToMany
    private List<Container> containers;
}
