package com.itschool.springapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "piers")
public class Pier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String name;
    private Long tonnageCapacity;

    @OneToMany(mappedBy = "pier", cascade = CascadeType.ALL)
    private List<Ship> dockedShips;
}
