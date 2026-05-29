package cl.dgac.empresasproveedoras.mapper;

import cl.dgac.empresasproveedoras.dto.EmpresaProveedoraRequestDTO;
import cl.dgac.empresasproveedoras.dto.EmpresaProveedoraResponseDTO;
import cl.dgac.empresasproveedoras.model.EmpresaProveedora;
import org.springframework.stereotype.Component;

@Component
public class EmpresaProveedoraMapper {

    public EmpresaProveedora toEntity(EmpresaProveedoraRequestDTO dto) {
        EmpresaProveedora empresa = new EmpresaProveedora();

        empresa.setRazonSocial(dto.getRazonSocial());
        empresa.setRut(dto.getRut());
        empresa.setEmail(dto.getEmail());
        empresa.setTelefono(dto.getTelefono());
        empresa.setDireccion(dto.getDireccion());
        empresa.setRubro(dto.getRubro());
        empresa.setEstado(dto.getEstado());

        return empresa;
    }

    public EmpresaProveedoraResponseDTO toDTO(EmpresaProveedora empresa) {
        EmpresaProveedoraResponseDTO dto = new EmpresaProveedoraResponseDTO();

        dto.setId(empresa.getId());
        dto.setRazonSocial(empresa.getRazonSocial());
        dto.setRut(empresa.getRut());
        dto.setEmail(empresa.getEmail());
        dto.setTelefono(empresa.getTelefono());
        dto.setDireccion(empresa.getDireccion());
        dto.setRubro(empresa.getRubro());
        dto.setEstado(empresa.getEstado());
        dto.setFechaRegistro(empresa.getFechaRegistro());

        return dto;
    }

    public void updateEntity(EmpresaProveedora empresa, EmpresaProveedoraRequestDTO dto) {
        empresa.setRazonSocial(dto.getRazonSocial());
        empresa.setRut(dto.getRut());
        empresa.setEmail(dto.getEmail());
        empresa.setTelefono(dto.getTelefono());
        empresa.setDireccion(dto.getDireccion());
        empresa.setRubro(dto.getRubro());
        empresa.setEstado(dto.getEstado());
    }
}