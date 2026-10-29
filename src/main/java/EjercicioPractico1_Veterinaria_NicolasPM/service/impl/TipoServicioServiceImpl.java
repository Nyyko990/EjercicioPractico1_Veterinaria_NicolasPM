package EjercicioPractico1_Veterinaria_NicolasPM.service.impl;

import EjercicioPractico1_Veterinaria_NicolasPM.domain.TipoServicio;
import EjercicioPractico1_Veterinaria_NicolasPM.repository.TipoServicioRepository;
import EjercicioPractico1_Veterinaria_NicolasPM.service.TipoServicioService;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoServicioServiceImpl implements TipoServicioService {

    // El repositorio es final para asegurar la inmutabilidad
    private final TipoServicioRepository tipoServicioRepository;

    // Inyección por constructor (No requiere @Autowired en Spring moderno)
    public TipoServicioServiceImpl(TipoServicioRepository tipoServicioRepository) {
        this.tipoServicioRepository = tipoServicioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicio> getTiposServicio() {
        return tipoServicioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoServicio> getTipoServicio(Integer id) {
        return tipoServicioRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(TipoServicio tipoServicio) {
        tipoServicioRepository.save(tipoServicio);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // Verifica si el tipo de servicio existe antes de intentar eliminarlo
        if (!tipoServicioRepository.existsById(id)) {
            throw new IllegalArgumentException("El tipo de servicio con ID " + id + " no existe.");
        }
        try {
            tipoServicioRepository.deleteById(id);
            // El flush fuerza el DELETE aquí para poder capturar el error de integridad
            tipoServicioRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar el tipo de servicio. Tiene datos asociados.", e);
        }
    }
}
