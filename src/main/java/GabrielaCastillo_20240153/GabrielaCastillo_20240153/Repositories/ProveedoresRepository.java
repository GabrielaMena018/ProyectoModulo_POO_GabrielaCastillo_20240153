package GabrielaCastillo_20240153.GabrielaCastillo_20240153.Repositories;

import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Entities.ProveedoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Esta etiqueta hace que spring sepa que esta clase estara trabajando como repository
@Repository
//Y esta clase hereda el jpaRepository que tiene distintos metodos para comunicarse con la base
public interface ProveedoresRepository extends JpaRepository<ProveedoresEntity, Long> {
}
