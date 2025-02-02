package com.barberia.taller1_poo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@SpringBootApplication
public class ControladorCitas {
    @GetMapping({"/", "/Menu"})
    public String mostrarMenuPrincipal() {
        return "menu_principal";
    }

    @GetMapping({"/agendar_cita"})
    public String mostrarFormularioCita(Model model) {
        // Generar las próximas 7 fechas disponibles
        List<String> fechas_disponibles = new ArrayList<>();
        LocalDate hoy = LocalDate.now();
        for (int i = 0; i < 7; i++) {
            fechas_disponibles.add(hoy.plusDays(i).toString());
        }

        model.addAttribute("fechas", fechas_disponibles);
        return "agendar_cita";
    }

    @GetMapping({"/buscar_citas"})
    public String mostrarCitas(Model model) {
        List<String> citas_disponibles = new ArrayList<>(ControladorRepoCitas.obtenerCitasDisponibles());
        model.addAttribute("fechas_disponibles", citas_disponibles);
        return "buscar_citas";
    }

    @PostMapping({"/procesar-formulario"})
    public String procesarFormulario(@ModelAttribute Cita cita, Model model) {
        // Prueba
        System.out.println("Cédula: " + cita.getCedula());
        System.out.println("Nombre: " + cita.getNombre());
        System.out.println("Edad: " + cita.getEdad());
        System.out.println("Fecha: " + cita.getFecha());
        System.out.println("Hora: " + cita.getHora());

        //Manejo Archivo JSON
        if (ControladorRepoCitas.crearCita(cita)) {
            model.addAttribute("mensaje", "Cita registrada correctamente.");
        } else {
            model.addAttribute("advertencia", "Ya existe una cita en esa fecha y hora");
        }



        // Enviar un nuevo objeto vacío para limpiar el formulario
        model.addAttribute("cita", new Cita());

        // Redirigir a la página de confirmación
        return "agendar_cita";
    }

    public static void main(String[] args) {
        SpringApplication.run(ControladorCitas.class, args);
    }

}
