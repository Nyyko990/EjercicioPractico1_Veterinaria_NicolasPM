package EjercicioPractico1_Veterinaria_NicolasPM.repository;

import EjercicioPractico1_Veterinaria_NicolasPM.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
}
