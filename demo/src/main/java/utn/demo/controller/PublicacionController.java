package utn.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import utn.demo.model.*;
import utn.demo.repositories.ComentariosRepository;
import utn.demo.repositories.PublicacionesRepository;

import java.util.List;

@RequestMapping("/publicacion")
@RestController
@EnableScheduling
public class PublicacionController {
    private static final String PERSON_NOT_FOUND = "Publicacion id not found: %s";

    @Autowired
    PublicacionesRepository publicacionRepository;

    @Autowired
    ComentariosRepository comentarioRepository;

    // ADD PUBLICACION
    @PostMapping("")
    public void addPublicacion(@RequestBody final Publicaciones p) {//averiguar q hace esto
        publicacionRepository.save(p);
    }

    // ADD COMENTARIO
    @PostMapping("/{idComentario}/{idPublicacion}")
    public void addComentario(@PathVariable final Integer idPublicacion, @PathVariable final Integer idComentario){
        Comentarios p = comentarioRepository.findById(idPublicacion)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        Publicaciones publicacion = getById(idPublicacion);
        p.setPublicaciones(publicacion);
        publicacion.getComentarios().add(p);
        comentarioRepository.save(p);
        publicacionRepository.save(publicacion);
    }

    // GET
    @GetMapping("")
    public List<Publicaciones> getAll() {
        return publicacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Publicaciones getById(@PathVariable final Integer id){
        return publicacionRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(PERSON_NOT_FOUND,id)));
    }

    // SCHEDULE
    @Scheduled(cron = ("${nValue}"))
    private void deleteComentarios(){
        comentarioRepository.deleteComentarios();
    }
}
