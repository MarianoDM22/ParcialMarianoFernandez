package utn.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import utn.demo.model.Comentarios;
import utn.demo.repositories.ComentariosRepository;

import java.util.List;

@RequestMapping("/comentario")
@RestController
public class ComentarioController {
    private static final String PERSON_NOT_FOUND = "Comentario id not found: %s";

    @Autowired
    ComentariosRepository comentarioRepository;


    // ADD COMENTARIO
    @PostMapping("")
    public void addComentario(@RequestBody final Comentarios p) {
        comentarioRepository.save(p);
    }

    // GET COMENTARIO
    @GetMapping("")
    public List<Comentarios> getAll() {
        return comentarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comentarios getById(@PathVariable final Integer id){
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(PERSON_NOT_FOUND,id)));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable final Integer id){
        Comentarios comentario = comentarioRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(PERSON_NOT_FOUND,id)));
        if (comentario == null) {
            return new ResponseEntity<>("Unable to delete Comentario, id " + id + " not found.",
                    HttpStatus.NOT_FOUND);
        }
        comentarioRepository.deleteById(id);
        return new ResponseEntity<Comentarios>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "")
    public void deleteAllComentarios() {
        comentarioRepository.deleteAll();
    }
}
