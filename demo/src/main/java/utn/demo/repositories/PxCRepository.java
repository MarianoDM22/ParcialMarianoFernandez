package utn.demo.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.demo.model.PxC;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PxCRepository {
    @Modifying
    @Transactional
    @Query(value = "select publicaciones.titulo, usuario.nombre, count(comentarios.id) as cantidad from publicaciones inner join comentarios on comentarios.publicaionesId = publicaciones.id group by publicaciones.titulo", nativeQuery = true)
    List<PxC> getPxC();
}
