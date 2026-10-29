package EjercicioPractico1_Veterinaria_NicolasPM.repository;

import EjercicioPractico1_Veterinaria_NicolasPM.domain.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
}
