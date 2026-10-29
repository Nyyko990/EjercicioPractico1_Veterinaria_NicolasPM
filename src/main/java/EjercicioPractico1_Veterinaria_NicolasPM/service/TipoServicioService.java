package EjercicioPractico1_Veterinaria_NicolasPM.service;

import EjercicioPractico1_Veterinaria_NicolasPM.domain.TipoServicio;
import java.util.List;
import java.util.Optional;

public interface TipoServicioService {

    public List<TipoServicio> getTiposServicio();

    public Optional<TipoServicio> getTipoServicio(Integer id);

    public void save(TipoServicio tipoServicio);

    public void delete(Integer id);
}
