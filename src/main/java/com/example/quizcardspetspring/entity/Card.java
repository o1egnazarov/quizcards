package com.example.quizcardspetspring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "t_card")
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_frontSide")
    @NotNull
    @Size(min = 1)
    private String frontSide;

    @Column(name = "c_backSide")
    private String backSide;

    @ManyToOne
    @JoinColumn(name = "с_module_id")
    @JsonBackReference
    private Module module;
}
