package br.com.project.animals.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cat_photos", schema = "animal")
public class CatPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.PERSIST})
    private Cat cat;

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST})
    private AnimalPhoto photo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}