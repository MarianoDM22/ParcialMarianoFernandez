package utn.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.demo.model.PublicacionDto;
import utn.demo.model.UsuarioDto;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UsuarioDtoRepository extends JpaRepository<UsuarioDto, Integer> {
    @Modifying
    @Transactional
    @Query(value = "select usuario.nombre, usuario.apellido from usuario;", nativeQuery = true)
    List<UsuarioDto> getUsuarios();
}
