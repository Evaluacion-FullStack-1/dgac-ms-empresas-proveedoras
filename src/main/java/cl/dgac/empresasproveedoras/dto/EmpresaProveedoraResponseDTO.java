package cl.dgac.empresasproveedoras.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Modelo de respuesta con la información detallada de una empresa proveedora de drones")
public class EmpresaProveedoraResponseDTO {

    @Schema(description = "Identificador único de la empresa en la base de datos", example = "1")
    private Long id;

    @Schema(description = "Nombre comercial o legal de la empresa operadora", example = "Drones Patagonia SpA")
    private String razonSocial;

    @Schema(description = "Rol Único Tributario (RUT) de la empresa", example = "77.234.567-8")
    private String rut;

    @Schema(description = "Correo electrónico oficial de la empresa", example = "contacto@dronespatagonia.cl")
    private String email;

    @Schema(description = "Número telefónico de contacto", example = "+56987654321")
    private String telefono;

    @Schema(description = "Dirección física de la casa matriz", example = "Av. Providencia 1234, Oficina 501, Providencia")
    private String direccion;

    @Schema(description = "Especialidad principal de vuelo o servicio que provee", example = "TOPOGRAFIA")
    private String rubro;

    @Schema(description = "Estado actual de la certificación operativa", example = "ACTIVA")
    private String estado;

    @Schema(description = "Fecha en la que la empresa fue registrada en el sistema", example = "2026-06-26")
    private LocalDate fechaRegistro;
}