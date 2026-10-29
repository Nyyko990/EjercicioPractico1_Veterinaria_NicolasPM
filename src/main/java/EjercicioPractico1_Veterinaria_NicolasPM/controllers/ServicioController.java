package EjercicioPractico1_Veterinaria_NicolasPM.controllers;

import EjercicioPractico1_Veterinaria_NicolasPM.domain.Servicio;
import EjercicioPractico1_Veterinaria_NicolasPM.service.ServicioService;
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
@RequestMapping("/servicio")
public class ServicioController {

    // Los servicios son final para asegurar la inmutabilidad
    private final ServicioService servicioService;
    private final TipoServicioService tipoServicioService;
    private final MessageSource messageSource;

    // Inyección por constructor (No requiere @Autowired en Spring moderno)
    public ServicioController(ServicioService servicioService, TipoServicioService tipoServicioService, MessageSource messageSource) {
        this.servicioService = servicioService;
        this.tipoServicioService = tipoServicioService;
        this.messageSource = messageSource;
    }

    @GetMapping("/listado")
    public String inicio(Model model) {
        var servicios = servicioService.getServicios();
        model.addAttribute("servicios", servicios);
        model.addAttribute("totalServicios", servicios.size());
        model.addAttribute("tipos", tipoServicioService.getTiposServicio());
        model.addAttribute("servicio", new Servicio());
        return "/servicio/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Servicio servicio, RedirectAttributes redirectAttributes) {
        servicioService.save(servicio);
        redirectAttributes.addFlashAttribute("todoOk", messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        return "redirect:/servicio/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";
        try {
            servicioService.delete(id);
        } catch (IllegalArgumentException e) {
            titulo = "error"; // El servicio no existe
            detalle = "servicio.error01";
        } catch (IllegalStateException e) {
            titulo = "error"; // El servicio tiene datos asociados
            detalle = "servicio.error02";
        } catch (Exception e) {
            titulo = "error"; // Cualquier otro error inesperado
            detalle = "servicio.error03";
        }
        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/servicio/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Servicio> servicioOpt = servicioService.getServicio(id);
        if (servicioOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("servicio.error01", null, Locale.getDefault()));
            return "redirect:/servicio/listado";
        }
        model.addAttribute("servicio", servicioOpt.get());
        model.addAttribute("tipos", tipoServicioService.getTiposServicio());
        return "/servicio/modifica";
    }
}
