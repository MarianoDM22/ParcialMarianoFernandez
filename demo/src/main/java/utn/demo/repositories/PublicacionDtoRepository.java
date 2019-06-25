package utn.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.demo.model.PublicacionDto;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PublicacionDtoRepository extends JpaRepository<PublicacionDto, Integer> {
    @Modifying
    @Transactional
    @Query(value = "select publicaciones.titulo, publicaciones.descripcion, publicaciones.liked from publicaciones;", nativeQuery = true)
    List<PublicacionDto> getPublicaciones();
}

