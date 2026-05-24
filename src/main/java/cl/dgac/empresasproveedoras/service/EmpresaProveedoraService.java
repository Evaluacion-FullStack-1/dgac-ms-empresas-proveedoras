package cl.dgac.empresasproveedoras.service;

import cl.dgac.empresasproveedoras.dto.EmpresaProveedoraRequestDTO;
import cl.dgac.empresasproveedoras.dto.EmpresaProveedoraResponseDTO;
import cl.dgac.empresasproveedoras.exception.ResourceNotFoundException;
import cl.dgac.empresasproveedoras.mapper.EmpresaProveedoraMapper;
import cl.dgac.empresasproveedoras.model.EmpresaProveedora;
import cl.dgac.empresasproveedoras.repository.EmpresaProveedoraRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaProveedoraService {

    private final EmpresaProveedoraRepository empresaRepository;
    private final EmpresaProveedoraMapper empresaMapper;
    private final WebClient.Builder webClientBuilder;

    public EmpresaProveedoraService(EmpresaProveedoraRepository empresaRepository,
                                    EmpresaProveedoraMapper empresaMapper,
                                    WebClient.Builder webClientBuilder) {
        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;
        this.webClientBuilder = webClientBuilder;
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

    public String consultarMicroservicioSeguros() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8084/api/seguros")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}