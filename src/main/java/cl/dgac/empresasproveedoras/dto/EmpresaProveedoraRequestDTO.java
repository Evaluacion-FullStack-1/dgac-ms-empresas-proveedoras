package cl.dgac.empresasproveedoras.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmpresaProveedoraRequestDTO {

    @NotBlank(message = "La razón social es obligatoria")
    private String razonSocial;

    @NotBlank(message = "El RUT es obligatorio")
    private String rut;

    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    private String telefono;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "El rubro es obligatorio")
    private String rubro;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;
}