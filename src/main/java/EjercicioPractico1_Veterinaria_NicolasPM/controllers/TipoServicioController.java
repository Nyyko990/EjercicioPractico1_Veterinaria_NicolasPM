package EjercicioPractico1_Veterinaria_NicolasPM.controllers;

import EjercicioPractico1_Veterinaria_NicolasPM.domain.TipoServicio;
import EjercicioPractico1_Veterinaria_NicolasPM.service.TipoServicioService;
import jakarta.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tiposervicio")
public class TipoServicioController {

    // Los servicios son final para asegurar la inmutabilidad
    private final TipoServicioService tipoServicioService;
    private final MessageSource messageSource;

    // Inyección por constructor (No requiere @Autowired en Spring moderno)
    public TipoServicioController(TipoServicioService tipoServicioService, MessageSource messageSource) {
        this.tipoServicioService = tipoServicioService;
        this.messageSource = messageSource;
    }

    @GetMapping("/listado")
    public String inicio(Model model) {
        var tipos = tipoServicioService.getTiposServicio();
        model.addAttribute("tipos", tipos);
        model.addAttribute("totalTipos", tipos.size());
        model.addAttribute("tipoServicio", new TipoServicio());
        return "/tiposervicio/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid TipoServicio tipoServicio, RedirectAttributes redirectAttributes) {
        tipoServicioService.save(tipoServicio);
        redirectAttributes.addFlashAttribute("todoOk", messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        return "redirect:/tiposervicio/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";
        try {
            tipoServicioService.delete(id);
        } catch (IllegalArgumentException e) {
            titulo = "error"; // El tipo de servicio no existe
            detalle = "tiposervicio.error01";
        } catch (IllegalStateException e) {
            titulo = "error"; // El tipo de servicio tiene datos asociados
            detalle = "tiposervicio.error02";
        } catch (Exception e) {
            titulo = "error"; // Cualquier otro error inesperado
            detalle = "tiposervicio.error03";
        }
        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/tiposervicio/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<TipoServicio> tipoOpt = tipoServicioService.getTipoServicio(id);
        if (tipoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("tiposervicio.error01", null, Locale.getDefault()));
            return "redirect:/tiposervicio/listado";
        }
        model.addAttribute("tipoServicio", tipoOpt.get());
        return "/tiposervicio/modifica";
    }
}
