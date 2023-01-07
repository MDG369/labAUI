package com.example.lab1AUI.prison.entity;

import com.example.lab1AUI.prisoner.entity.Prisoner;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name="prisons")
public class Prison implements Serializable {
    @Id
    public String name;
    public int size;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "prison")
    @ToString.Exclude
    private List<Prisoner> prisoners;
}
