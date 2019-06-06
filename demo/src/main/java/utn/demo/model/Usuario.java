package utn.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Integer id;

    private String nombre;
    private String apellido;
    private String browser;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Publicaciones> publicaciones;

    //
    @PrePersist
    public ResponseEntity<String> listAllHeaders(@RequestHeader Map<String, String> headers){
        headers.forEach((key, value)-> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });
        return new ResponseEntity<String> (String.format(" Listed %s headers", headers.size()), HttpStatus.OK);
    }
}
