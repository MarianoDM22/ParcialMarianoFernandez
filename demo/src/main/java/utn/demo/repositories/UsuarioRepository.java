package utn.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.demo.model.Usuario;

import javax.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario,Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Comentarios where ((SELECT minute now()) - (SELECT (MINUTE time))) > 1;", nativeQuery = true) //select *, (timestampdiff(minute,time, now())) from comentarios
    void deleteComentarioss();
}
