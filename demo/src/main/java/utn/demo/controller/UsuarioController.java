package utn.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import utn.demo.model.Comentarios;
import utn.demo.model.Publicaciones;
import utn.demo.model.Usuario;
import utn.demo.repositories.ComentariosRepository;
import utn.demo.repositories.PublicacionesRepository;
import utn.demo.repositories.UsuarioRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequestMapping("/persona")
@RestController
@EnableScheduling
public class UsuarioController {

    private static final String USER_NOT_FOUND = "No existe el usuario con el id: %s";

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PublicacionesRepository publicacionesRepository;

    @Autowired
    ComentariosRepository comentariosRepository;

    //Adds
    @PostMapping("")
    public void addUsuario(@RequestBody final Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @PostMapping("")
    public void addPublicacion(@RequestBody final Publicaciones publicacion) { publicacionesRepository.save(publicacion);}

    @PostMapping("")
    public void addComentario(@RequestBody final Comentarios comentario) { comentariosRepository.save(comentario);}

    //Update
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
        Usuario currentUser = usuarioRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(USER_NOT_FOUND,id)));
        if (currentUser == null) {
            return new ResponseEntity<>("No se puede modificar. El Usuario con el Id " + id + " no existe.",
                    HttpStatus.NOT_FOUND);
        }

        currentUser.setNombre(usuario.getNombre());
        currentUser.setApellido(usuario.getApellido());
        usuarioRepository.save(currentUser);
        return new ResponseEntity<Usuario>(currentUser, HttpStatus.NO_CONTENT);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable final Integer id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(USER_NOT_FOUND,id)));
        if (usuario == null) {
            return new ResponseEntity<>("No se puede eliminar. El Usuario con el Id " + id + " no existe.",
                    HttpStatus.NOT_FOUND);
        }
        usuarioRepository.deleteById(id);
        return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
    }

    //Get usuarios
    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    //Get usuario especifico
    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable("id") Integer id) {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(USER_NOT_FOUND,id)));
            return usuario;
    }


    //Get publicaciones
    @GetMapping("/usuarios/{id}/publicaciones")
    public List<Publicaciones> getPublicacionesByUsuario(@PathVariable("id") Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(USER_NOT_FOUND,id)));
        List<Publicaciones> publicaciones = publicacionesRepository.findAllById(Collections.singleton(id));
        return publicaciones;
    }

    //
    @Scheduled(fixedRate = 50000)
    public void deleteComentarios(){
        usuarioRepository.deleteComentarioss();
    }


}
