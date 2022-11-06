package com.example.lab1AUI.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
}
