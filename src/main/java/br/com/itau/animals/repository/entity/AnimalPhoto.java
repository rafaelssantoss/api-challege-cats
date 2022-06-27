package br.com.itau.animals.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "photo_animals", schema = "animal")
public class AnimalPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_photo")
    private String idPhoto;

    @Column
    private Integer width;

    @Column
    private Integer height;

    @Column
    private String url;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}