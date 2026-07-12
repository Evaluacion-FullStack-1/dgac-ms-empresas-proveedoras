package cl.dgac.empresasproveedoras.service;

import cl.dgac.empresasproveedoras.dto.EmpresaProveedoraRequestDTO;
import cl.dgac.empresasproveedoras.dto.EmpresaProveedoraResponseDTO;
import cl.dgac.empresasproveedoras.exception.ResourceNotFoundException;
import cl.dgac.empresasproveedoras.mapper.EmpresaProveedoraMapper;
import cl.dgac.empresasproveedoras.model.EmpresaProveedora;
import cl.dgac.empresasproveedoras.repository.EmpresaProveedoraRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaProveedoraService {

    private final EmpresaProveedoraRepository empresaRepository;
    private final EmpresaProveedoraMapper empresaMapper;
    
    // Inyectamos RestTemplate en lugar de WebClient
    private final RestTemplate restTemplate;

    // Leemos la URL base desde el application.yml
    @Value("${seguros.base-url}")
    private String segurosBaseUrl;

    public EmpresaProveedoraService(EmpresaProveedoraRepository empresaRepository,
                                    EmpresaProveedoraMapper empresaMapper,
                                    RestTemplate restTemplate) {
        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;
        this.restTemplate = restTemplate;
    }

    public List<EmpresaProveedoraResponseDTO> listarEmpresas() {
        return empresaRepository.findAll()
                .stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EmpresaProveedoraResponseDTO buscarPorId(Long id) {
        EmpresaProveedora empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa proveedora no encontrada con ID: " + id));

        return empresaMapper.toDTO(empresa);
    }

    public EmpresaProveedoraResponseDTO crearEmpresa(EmpresaProveedoraRequestDTO dto) {
        EmpresaProveedora empresa = empresaMapper.toEntity(dto);

        empresa.setFechaRegistro(java.time.LocalDate.now());

        EmpresaProveedora empresaGuardada = empresaRepository.save(empresa);

        return empresaMapper.toDTO(empresaGuardada);
    }

    public EmpresaProveedoraResponseDTO actualizarEmpresa(Long id, EmpresaProveedoraRequestDTO dto) {
        EmpresaProveedora empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa proveedora no encontrada con ID: " + id));

        empresaMapper.updateEntity(empresa, dto);
        EmpresaProveedora empresaActualizada = empresaRepository.save(empresa);

        return empresaMapper.toDTO(empresaActualizada);
    }

    public void eliminarEmpresa(Long id) {
        EmpresaProveedora empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa proveedora no encontrada con ID: " + id));

        empresaRepository.delete(empresa);
    }

    public EmpresaProveedoraResponseDTO buscarPorRut(String rut) {
        EmpresaProveedora empresa = empresaRepository.findByRut(rut)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa proveedora no encontrada con RUT: " + rut));

        return empresaMapper.toDTO(empresa);
    }

    public List<EmpresaProveedoraResponseDTO> listarPorEstado(String estado) {
        return empresaRepository.findByEstado(estado)
                .stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<EmpresaProveedoraResponseDTO> buscarPorRubro(String rubro) {
        return empresaRepository.buscarPorRubro(rubro)
                .stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<EmpresaProveedoraResponseDTO> buscarPorRazonSocial(String razonSocial) {
        return empresaRepository.buscarPorRazonSocial(razonSocial)
                .stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // --- MÉTODO CORREGIDO ---
    public String consultarMicroservicioSeguros() {
        // Construimos la URL completa para llamar al otro servicio vía Eureka
        String urlFinal = segurosBaseUrl + "/api/seguros";
        
        // Hacemos la petición GET de forma síncrona
        return restTemplate.getForObject(urlFinal, String.class);
    }
}