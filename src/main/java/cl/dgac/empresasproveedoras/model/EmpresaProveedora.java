package cl.dgac.empresasproveedoras.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "empresas_proveedoras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaProveedora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String razonSocial;

    @Column(nullable = false, unique = true)
    private String rut;

    @Column(nullable = false, unique = true)
    private String email;

    private String telefono;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String rubro;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private LocalDate fechaRegistro;
}