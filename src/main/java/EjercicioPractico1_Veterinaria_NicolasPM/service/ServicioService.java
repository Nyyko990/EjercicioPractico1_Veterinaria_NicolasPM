package EjercicioPractico1_Veterinaria_NicolasPM.service;

import EjercicioPractico1_Veterinaria_NicolasPM.domain.Servicio;
import java.util.List;
import java.util.Optional;

public interface ServicioService {

    public List<Servicio> getServicios();

    public Optional<Servicio> getServicio(Integer id);

    public void save(Servicio servicio);

    public void delete(Integer id);
}
