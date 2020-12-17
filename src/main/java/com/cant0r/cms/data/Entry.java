package com.cant0r.cms.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Entry", schema = "data")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private int size;

    @ManyToOne
    @JoinColumn(name = "medium_id", referencedColumnName = "id")
    @NonNull
    private Medium medium;
}
