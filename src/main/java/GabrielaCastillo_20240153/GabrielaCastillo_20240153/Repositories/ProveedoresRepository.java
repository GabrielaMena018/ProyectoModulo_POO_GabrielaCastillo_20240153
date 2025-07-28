package GabrielaCastillo_20240153.GabrielaCastillo_20240153.Repositories;

import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Entities.ProveedoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedoresRepository extends JpaRepository<ProveedoresEntity, Long> {
}
