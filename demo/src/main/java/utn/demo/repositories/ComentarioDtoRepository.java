package utn.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.demo.model.ComentarioDto;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ComentarioDtoRepository extends JpaRepository<ComentarioDto, Integer> {
    @Modifying
    @Transactional
    @Query(value = "select comentarios.id, comentarios.descripcion, comentarios.usuario from comentarios;", nativeQuery = true)
    List<ComentarioDto> getComentarios();
}
