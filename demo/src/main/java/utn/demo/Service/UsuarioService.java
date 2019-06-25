package utn.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import utn.demo.model.UsuarioDto;
import utn.demo.repositories.UsuarioDtoRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UsuarioService {
    @Autowired
    UsuarioDtoRepository usuarioDtoRepository;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<UsuarioDto>> methodOne() {
        List<UsuarioDto> usuarios = usuarioDtoRepository.getUsuarios();
        return CompletableFuture.completedFuture(usuarios);
    }
}
