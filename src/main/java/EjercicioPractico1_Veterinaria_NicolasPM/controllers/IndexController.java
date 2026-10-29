package EjercicioPractico1_Veterinaria_NicolasPM.controllers;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    private final MessageSource messageSource;

    // Inyección por constructor (No requiere @Autowired en Spring moderno)
    public IndexController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    //recibe el formulario de consultas, no se guarda en base de datos
    @PostMapping("/consultas/enviar")
    public String enviarConsulta(@RequestParam String nombre, @RequestParam String correo,
            @RequestParam String nombreMascota, @RequestParam String mensaje,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("todoOk",
                messageSource.getMessage("consultas.gracias", new Object[]{nombre}, Locale.getDefault()));
        return "redirect:/#consultas";
    }
}
