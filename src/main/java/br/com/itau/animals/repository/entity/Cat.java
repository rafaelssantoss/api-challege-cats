package br.com.itau.animals.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cats", schema = "animal")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cat_id_integration")
    private String catId;

    @Column
    private String breed;

    @Column
    private String origin;

    @Column
    private String temperament;

    @Column
    private String description;

    @OneToMany(mappedBy = "cat", fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.PERSIST})
    private List<CatPhoto> photos;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}