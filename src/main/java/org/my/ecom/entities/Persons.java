package org.my.ecom.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "persons")
public class Persons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;
    private String gender;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "person_categories",
            joinColumns = { @JoinColumn(name = "person_id",
                    nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "cid",
                    nullable = false, updatable = false) })
    private Set<Categories> categories = new HashSet<>();
}
