package com.cant0r.cms.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Container", schema = "data")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private String location;
    @Column
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "name")
    private ContainerType type;


    @OneToMany
    private List<Medium> mediums;
}
