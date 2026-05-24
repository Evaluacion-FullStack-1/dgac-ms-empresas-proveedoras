package cl.dgac.empresasproveedoras.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpresaProveedoraResponseDTO {

    private Long id;
    private String razonSocial;
    private String rut;
    private String email;
    private String telefono;
    private String direccion;
    private String rubro;
    private String estado;
    private LocalDate fechaRegistro;
}