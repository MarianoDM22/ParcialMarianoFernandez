package utn.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import utn.demo.model.Comentarios;
import utn.demo.model.Publicaciones;

import javax.transaction.Transactional;

public interface ComentariosRepository extends JpaRepository<Comentarios,Integer> {

}
