package cl.dgac.empresasproveedoras.controller;

import cl.dgac.empresasproveedoras.dto.EmpresaProveedoraRequestDTO;
import cl.dgac.empresasproveedoras.dto.EmpresaProveedoraResponseDTO;
import cl.dgac.empresasproveedoras.service.EmpresaProveedoraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas-proveedoras")
public class EmpresaProveedoraController {

    private final EmpresaProveedoraService empresaService;

    public EmpresaProveedoraController(EmpresaProveedoraService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<List<EmpresaProveedoraResponseDTO>> listarEmpresas() {
        return ResponseEntity.ok(empresaService.listarEmpresas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaProveedoraResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EmpresaProveedoraResponseDTO> crearEmpresa(
            @Valid @RequestBody EmpresaProveedoraRequestDTO dto) {

        EmpresaProveedoraResponseDTO empresaCreada = empresaService.crearEmpresa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaProveedoraResponseDTO> actualizarEmpresa(
            @PathVariable Long id,
            @Valid @RequestBody EmpresaProveedoraRequestDTO dto) {

        return ResponseEntity.ok(empresaService.actualizarEmpresa(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        empresaService.eliminarEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-rut")
    public ResponseEntity<EmpresaProveedoraResponseDTO> buscarPorRut(
            @RequestParam String rut) {

        return ResponseEntity.ok(empresaService.buscarPorRut(rut));
    }

    @GetMapping("/estado")
    public ResponseEntity<List<EmpresaProveedoraResponseDTO>> listarPorEstado(
            @RequestParam String estado) {

        return ResponseEntity.ok(empresaService.listarPorEstado(estado));
    }

    @GetMapping("/rubro")
    public ResponseEntity<List<EmpresaProveedoraResponseDTO>> buscarPorRubro(
            @RequestParam String rubro) {

        return ResponseEntity.ok(empresaService.buscarPorRubro(rubro));
    }

    @GetMapping("/razon-social")
    public ResponseEntity<List<EmpresaProveedoraResponseDTO>> buscarPorRazonSocial(
            @RequestParam String razonSocial) {

        return ResponseEntity.ok(empresaService.buscarPorRazonSocial(razonSocial));
    }

    @GetMapping("/seguros")
    public ResponseEntity<String> consultarSeguros() {
        return ResponseEntity.ok(empresaService.consultarMicroservicioSeguros());
    }
}