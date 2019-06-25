package utn.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.demo.Service.ComentarioService;
import utn.demo.Service.PublicacionService;
import utn.demo.Service.UsuarioService;
import utn.demo.model.ComentarioDto;
import utn.demo.model.PublicacionDto;
import utn.demo.model.PxC;
import utn.demo.model.UsuarioDto;
import utn.demo.repositories.PxCRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/contenedora")
public class ContenedoraController {

    @Autowired
    ComentarioService comentarioService;

    @Autowired
    PublicacionService publicacionService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PxCRepository PxCRepository;

    //
    @GetMapping(value = "/cantComentarios")
    public List<PxC> getPxC(){
        return PxCRepository.getPxC();
    }

    // ASYNC
    @GetMapping("/allContent")
    public ResponseEntity<?> getAsync() {
        CompletableFuture<List<PublicacionDto>> resultMethodOne = publicacionService.methodOne();
        CompletableFuture<List<ComentarioDto>> resultMethodTwo = comentarioService.methodOne();
        CompletableFuture<List<UsuarioDto>> resultMethodThree = usuarioService.methodOne();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultMethodOne.join() + "" + resultMethodTwo.join() + "" + resultMethodThree);
    }
}