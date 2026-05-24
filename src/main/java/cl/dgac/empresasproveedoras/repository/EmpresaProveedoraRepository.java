package cl.dgac.empresasproveedoras.repository;

import cl.dgac.empresasproveedoras.model.EmpresaProveedora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaProveedoraRepository extends JpaRepository<EmpresaProveedora, Long> {

    Optional<EmpresaProveedora> findByRut(String rut);

    List<EmpresaProveedora> findByEstado(String estado);

    @Query("SELECT e FROM EmpresaProveedora e WHERE LOWER(e.rubro) LIKE LOWER(CONCAT('%', :rubro, '%'))")
    List<EmpresaProveedora> buscarPorRubro(String rubro);

    @Query("SELECT e FROM EmpresaProveedora e WHERE LOWER(e.razonSocial) LIKE LOWER(CONCAT('%', :razonSocial, '%'))")
    List<EmpresaProveedora> buscarPorRazonSocial(String razonSocial);
}