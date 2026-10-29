package EjercicioPractico1_Veterinaria_NicolasPM.service.impl;

import EjercicioPractico1_Veterinaria_NicolasPM.domain.Servicio;
import EjercicioPractico1_Veterinaria_NicolasPM.repository.ServicioRepository;
import EjercicioPractico1_Veterinaria_NicolasPM.service.ServicioService;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioServiceImpl implements ServicioService {

    // El repositorio es final para asegurar la inmutabilidad
    private final ServicioRepository servicioRepository;

    // Inyección por constructor (No requiere @Autowired en Spring moderno)
    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> getServicios() {
        return servicioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Servicio> getServicio(Integer id) {
        return servicioRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // Verifica si el servicio existe antes de intentar eliminarlo
        if (!servicioRepository.existsById(id)) {
            throw new IllegalArgumentException("El servicio con ID " + id + " no existe.");
        }
        try {
            servicioRepository.deleteById(id);
            // El flush fuerza el DELETE aquí para poder capturar el error de integridad
            servicioRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar el servicio. Tiene datos asociados.", e);
        }
    }
}
