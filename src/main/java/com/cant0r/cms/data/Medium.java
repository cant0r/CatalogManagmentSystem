package com.cant0r.cms.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Medium", schema = "data")
public class Medium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    @NonNull
    private String name;
    @Column
    @NonNull
    private int capacity;

    @ManyToOne
  //  @JoinColumn(name = "container_id")
    @NonNull
    private Container container;

    @OneToMany
    private List<Entry> entries;
}
