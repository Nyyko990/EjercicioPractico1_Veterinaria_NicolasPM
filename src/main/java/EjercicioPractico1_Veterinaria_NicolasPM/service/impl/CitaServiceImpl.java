package EjercicioPractico1_Veterinaria_NicolasPM.service.impl;

import EjercicioPractico1_Veterinaria_NicolasPM.domain.Cita;
import EjercicioPractico1_Veterinaria_NicolasPM.repository.CitaRepository;
import EjercicioPractico1_Veterinaria_NicolasPM.service.CitaService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CitaServiceImpl implements CitaService {

    // El repositorio es final para asegurar la inmutabilidad
    private final CitaRepository citaRepository;

    // Inyección por constructor (No requiere @Autowired en Spring moderno)
    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> getCitas() {
        return citaRepository.findAll();
    }
}
