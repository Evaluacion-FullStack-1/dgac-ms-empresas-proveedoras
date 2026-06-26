package cl.dgac.empresasproveedoras.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Modelo de petición para el registro o actualización de una empresa proveedora de servicios de drones")
public class EmpresaProveedoraRequestDTO {

    @Schema(description = "Nombre comercial o legal de la empresa operadora", example = "Drones Patagonia SpA")
    @NotBlank(message = "La razón social es obligatoria")
    private String razonSocial;

    @Schema(description = "Rol Único Tributario (RUT) de la empresa", example = "77.234.567-8")
    @NotBlank(message = "El RUT es obligatorio")
    private String rut;

    @Schema(description = "Correo electrónico oficial de la empresa", example = "contacto@dronespatagonia.cl")
    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Schema(description = "Número telefónico de contacto", example = "+56987654321")
    private String telefono;

    @Schema(description = "Dirección física de la casa matriz", example = "Av. Providencia 1234, Oficina 501, Providencia")
    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @Schema(description = "Especialidad principal de vuelo o servicio que provee (ej. TOPOGRAFIA, AUDIOVISUAL, INSPECCION)", example = "TOPOGRAFIA")
    @NotBlank(message = "El rubro es obligatorio")
    private String rubro;

    @Schema(description = "Estado actual de la certificación operativa (ej. ACTIVA, SUSPENDIDA)", example = "ACTIVA")
    @NotBlank(message = "El estado es obligatorio")
    private String estado;
}