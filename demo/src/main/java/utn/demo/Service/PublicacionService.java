package utn.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import utn.demo.model.PublicacionDto;
import utn.demo.repositories.PublicacionDtoRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PublicacionService {
    @Autowired
    PublicacionDtoRepository publicacionDtoRepository;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<PublicacionDto>> methodOne() {
        List<PublicacionDto> publicaciones = publicacionDtoRepository.getPublicaciones();
        return CompletableFuture.completedFuture(publicaciones);
    }
}
