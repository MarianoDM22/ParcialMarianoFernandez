package utn.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comentarios {

    @Id
    @GeneratedValue
    private Integer id;

    private String descripcion;
    private LocalDateTime fecha;
    //private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicaciones_id", referencedColumnName = "id")
    @JsonIgnore
    private Publicaciones publicaciones;

    @PrePersist
    public void setFecha() {
        if (isNull(this.getFecha())) {
            this.fecha = LocalDateTime.now();
        }
    }
}
