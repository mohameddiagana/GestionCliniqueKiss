package sn.seck.GestionCliniqueKissi.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.seck.GestionCliniqueKissi.Model.Consultation;
import sn.seck.GestionCliniqueKissi.Repository.PatientRepository;
import sn.seck.GestionCliniqueKissi.Service.ConsultationService;

import java.util.List;

@RestController
@RequestMapping(path = "http://localhost:8190/api/v1/auth/consultations")
@CrossOrigin(origins = "http://localhost:4200")

/**
 * Recuperaton URL de front
 * */
public class ConsultationController {

//    @Autowired
    private ConsultationService consultationService;
    private  final PatientRepository patientRepository;

    public ConsultationController(ConsultationService consultationService, PatientRepository patientRepository) {
        this.consultationService = consultationService;
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public String getAll (Model model) {
        List<Consultation> getconsultationList = consultationService.getAllConsultations( );
        model.addAttribute("consultation", new Consultation());
        model.addAttribute("patients", patientRepository.findAll());
        return "consultation_form";
    }

    @GetMapping("/{id}")
    public Consultation getConsultationById(@PathVariable Long id) {
        return consultationService.getConsultationById(id);
    }

    @PostMapping
    @ResponseBody
    public Consultation saveConsultation(@RequestBody Consultation consultation) {
        return consultationService.saveConsultation(consultation);
    }

    @DeleteMapping("/{id}")
    public void deleteConsultation(@PathVariable Long id) {
        consultationService.deleteConsultation(id);
    }
}
