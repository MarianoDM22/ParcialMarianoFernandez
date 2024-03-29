package utn.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.demo.model.Comentarios;
import utn.demo.model.Publicaciones;

import javax.transaction.Transactional;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios,Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Comentarios where (timestampdiff(minute,Fecha , now())) > 5;", nativeQuery = true)
    void deleteComentarios();
}
