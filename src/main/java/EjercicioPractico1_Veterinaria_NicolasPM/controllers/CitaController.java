package EjercicioPractico1_Veterinaria_NicolasPM.controllers;

import EjercicioPractico1_Veterinaria_NicolasPM.service.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cita")
public class CitaController {

    // El servicio es final para asegurar la inmutabilidad
    private final CitaService citaService;

    // Inyección por constructor (No requiere @Autowired en Spring moderno)
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping("/listado")
    public String inicio(Model model) {
        var citas = citaService.getCitas();
        model.addAttribute("citas", citas);
        model.addAttribute("totalCitas", citas.size());
        return "/cita/listado";
    }
}
