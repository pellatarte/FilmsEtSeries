package com.basespringboot.repository;
import com.basespringboot.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

/*
GenreRepository advantages :
- provides pre-made methods to access the data (extending JpaRepository)
- enables Srping to generate SQL requests instead of writing them in the code !!
*/

public interface GenreRepository extends JpaRepository<Genre, Long> {
    // "Home-made" ethods may be added later 
}

