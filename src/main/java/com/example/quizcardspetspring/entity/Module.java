package com.example.quizcardspetspring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "t_module")
@AllArgsConstructor
@NoArgsConstructor
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_title")
    @Size(min = 1)
    @NotBlank
    private String title;

    @Column(name = "c_description")
    private String description;

    @OneToMany(
            mappedBy = "module",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Column(name = "c_cards")
    @JsonManagedReference
    @Size(min = 2)
    private List<Card> cards;

    @ManyToOne
    @JoinColumn(name = "с_user_id")
    @JsonBackReference
    private User user;

}
