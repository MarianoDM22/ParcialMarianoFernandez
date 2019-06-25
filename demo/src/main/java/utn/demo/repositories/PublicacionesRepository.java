package utn.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.demo.model.Publicaciones;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PublicacionesRepository extends JpaRepository<Publicaciones,Integer> {

}
