package cl.dgac.empresasproveedoras.controller;

import cl.dgac.empresasproveedoras.dto.EmpresaProveedoraRequestDTO;
import cl.dgac.empresasproveedoras.dto.EmpresaProveedoraResponseDTO;
import cl.dgac.empresasproveedoras.service.EmpresaProveedoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas-proveedoras")
@Tag(name = "Empresas Proveedoras", description = "Operaciones para la gestión de las empresas operadoras y dueñas de drones que proveen servicios de vuelo en el sistema DGAC")
public class EmpresaProveedoraController {

    private final EmpresaProveedoraService empresaService;

    public EmpresaProveedoraController(EmpresaProveedoraService empresaService) {
        this.empresaService = empresaService;
    }

    @Operation(summary = "Listar todas las empresas proveedoras", description = "Obtiene un registro completo de todas las empresas operadoras de drones inscritas en la plataforma.")
    @ApiResponse(responseCode = "200", description = "Lista de empresas obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<EmpresaProveedoraResponseDTO>> listarEmpresas() {
        return ResponseEntity.ok(empresaService.listarEmpresas());
    }

    @Operation(summary = "Buscar empresa por ID", description = "Obtiene los detalles de una empresa proveedora específica mediante su identificador único interno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaProveedoraResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }

    @Operation(
            summary = "Registrar nueva empresa proveedora", 
            description = "Ingresa una nueva entidad operadora de drones a la base de datos de la DGAC.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Estructura de datos para registrar una nueva empresa proveedora",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de Registro de Empresa",
                                    summary = "JSON de prueba para empresa de inspección",
                                    value = "{\n  \"rut\": \"76.543.210-K\",\n  \"razonSocial\": \"AeroInspecciones SpA\",\n  \"rubro\": \"INSPECCION_INDUSTRIAL\",\n  \"direccion\": \"Av. Providencia 1234, Santiago\",\n  \"telefono\": \"+56987654321\",\n  \"email\": \"contacto@aeroinspecciones.cl\",\n  \"estado\": \"ACTIVA\"\n}"
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (ej. RUT duplicado)")
    })
    @PostMapping
    public ResponseEntity<EmpresaProveedoraResponseDTO> crearEmpresa(
            @Valid @RequestBody EmpresaProveedoraRequestDTO dto) {

        EmpresaProveedoraResponseDTO empresaCreada = empresaService.crearEmpresa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaCreada);
    }

    @Operation(
            summary = "Actualizar información de la empresa", 
            description = "Modifica los datos comerciales, de contacto o el estado de una empresa proveedora existente.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Estructura de datos para actualizar la empresa",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de Actualización de Empresa",
                                    summary = "JSON de prueba para cambio de estado o datos",
                                    value = "{\n  \"rut\": \"76.543.210-K\",\n  \"razonSocial\": \"AeroInspecciones SpA\",\n  \"rubro\": \"INSPECCION_INDUSTRIAL\",\n  \"direccion\": \"Av. Providencia 1234, Oficina 501, Santiago\",\n  \"telefono\": \"+56911223344\",\n  \"email\": \"gerencia@aeroinspecciones.cl\",\n  \"estado\": \"SUSPENDIDA\"\n}"
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaProveedoraResponseDTO> actualizarEmpresa(
            @PathVariable Long id,
            @Valid @RequestBody EmpresaProveedoraRequestDTO dto) {

        return ResponseEntity.ok(empresaService.actualizarEmpresa(id, dto));
    }

    @Operation(summary = "Eliminar empresa proveedora", description = "Elimina el registro de una empresa operadora del sistema mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empresa eliminada exitosamente (Sin contenido)"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        empresaService.eliminarEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar empresa por RUT", description = "Busca el registro exacto de una empresa operadora utilizando su Rol Único Tributario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada"),
            @ApiResponse(responseCode = "404", description = "RUT no registrado en el sistema")
    })
    @GetMapping("/buscar-rut")
    public ResponseEntity<EmpresaProveedoraResponseDTO> buscarPorRut(
            @RequestParam String rut) {

        return ResponseEntity.ok(empresaService.buscarPorRut(rut));
    }

    @Operation(summary = "Filtrar empresas por estado", description = "Obtiene una lista de empresas según su estado operativo y de certificación (ej. ACTIVA, SUSPENDIDA).")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/estado")
    public ResponseEntity<List<EmpresaProveedoraResponseDTO>> listarPorEstado(
            @RequestParam String estado) {

        return ResponseEntity.ok(empresaService.listarPorEstado(estado));
    }

    @Operation(summary = "Filtrar por rubro o especialidad", description = "Obtiene una lista de empresas especializadas en un rubro específico (ej. FOTOGRAFIA, TOPOGRAFIA, INSPECCION_INDUSTRIAL).")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/rubro")
    public ResponseEntity<List<EmpresaProveedoraResponseDTO>> buscarPorRubro(
            @RequestParam String rubro) {

        return ResponseEntity.ok(empresaService.buscarPorRubro(rubro));
    }

    @Operation(summary = "Buscar por Razón Social", description = "Busca coincidencias parciales o totales en la razón social de las empresas operadoras.")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/razon-social")
    public ResponseEntity<List<EmpresaProveedoraResponseDTO>> buscarPorRazonSocial(
            @RequestParam String razonSocial) {

        return ResponseEntity.ok(empresaService.buscarPorRazonSocial(razonSocial));
    }

    @Operation(summary = "Consultar estado del servicio de Seguros", description = "Endpoint de integración para verificar la disponibilidad del microservicio de Seguros (validación de pólizas de drones).")
    @ApiResponse(responseCode = "200", description = "Comunicación exitosa con el microservicio de Seguros")
    @GetMapping("/seguros")
    public ResponseEntity<String> consultarSeguros() {
        return ResponseEntity.ok(empresaService.consultarMicroservicioSeguros());
    }
}