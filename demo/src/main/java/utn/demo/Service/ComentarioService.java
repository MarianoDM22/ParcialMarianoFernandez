package utn.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import utn.demo.model.ComentarioDto;
import utn.demo.repositories.ComentarioDtoRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ComentarioService {

    @Autowired
    ComentarioDtoRepository comentarioDtoRepository;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<ComentarioDto>> methodOne() {
        List<ComentarioDto> comentarios = comentarioDtoRepository.getComentarios();
        return CompletableFuture.completedFuture(comentarios);
    }
}
