package EjercicioPractico1_Veterinaria_NicolasPM.repository;

import EjercicioPractico1_Veterinaria_NicolasPM.domain.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoServicioRepository extends JpaRepository<TipoServicio, Integer> {
}
