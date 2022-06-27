package br.com.itau.animals.repository;

import br.com.itau.animals.repository.entity.Cat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Long> {

    Cat findByCatId(String catId);

    @Query("SELECT c FROM Cat c WHERE c.temperament LIKE %:text%")
    List<Cat> findByTemperament(@Param("text") String temperament);

    @Query("SELECT c FROM Cat c WHERE c.temperament LIKE %:text%")
    Page<Cat> findByTemperament(@Param("text") String temperament, Pageable pageable);

    List<Cat> findByOrigin(String origin);

    Page<Cat> findByOrigin(String origin, Pageable pageable);
}