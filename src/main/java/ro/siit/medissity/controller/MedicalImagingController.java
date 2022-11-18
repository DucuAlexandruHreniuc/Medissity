package ro.siit.medissity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ro.siit.medissity.model.MedicalImaging;
import ro.siit.medissity.repository.MedicalImagingRepositoryJpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("imaging")
public class MedicalImagingController {
    @Autowired
    private MedicalImagingRepositoryJpa medicalImagingRepositoryJpa;
    @GetMapping(value = "/")
    public String getImaging(Model model){
        model.addAttribute("imaging", medicalImagingRepositoryJpa.findAll((Sort.by(Sort.Direction.ASC,"name"))));
        return "diagnostic/medicalImaging/list";

    }
    @GetMapping("/add")
    public String addMedicalImagingForm(Model model) {
        return "diagnostic/medicalImaging/addForm";
    }

    @PostMapping("/add")
    public String addMedicalImaging(Model model,
                                          @RequestParam("medicalImaging_name") String medicalImagingNameInput) {
        String medicalImagingName = medicalImagingNameInput.substring(0, 1).toUpperCase() + medicalImagingNameInput.substring(1);
        Optional<MedicalImaging> preExistingImaging = medicalImagingRepositoryJpa.findByName(medicalImagingName);
        if(preExistingImaging.isPresent()){
            model.addAttribute("error", "Investigația de imagistică \""+ medicalImagingName + "\" există deja în listă");
        }
        else{
        MedicalImaging addedMedicalImaging = new MedicalImaging(UUID.randomUUID(), medicalImagingName);
        medicalImagingRepositoryJpa.saveAndFlush(addedMedicalImaging);
        model.addAttribute("success", "Investigația de imagistică \"" + medicalImagingName + "\" a fost adăugată cu succes" );}
        return "diagnostic/medicalImaging/addForm";

    }
    @GetMapping("/edit/{id}")
    public String editMedicalImaging(Model model, @PathVariable("id") UUID medicalImagingId) {
        Optional<MedicalImaging> optionalMedicalImaging = medicalImagingRepositoryJpa.findById(medicalImagingId);
        MedicalImaging medicalImaging = optionalMedicalImaging.get();
        model.addAttribute("medicalImaging", medicalImaging);
        List<MedicalImaging> allMedicalImaging = medicalImagingRepositoryJpa.findAll();
        model.addAttribute("allMedicalImaging", allMedicalImaging);
        return "diagnostic/medicalImaging/editForm";
    }
    @PostMapping("/edit")
    public String addMedicalImaging(Model model,
                                          @RequestParam("medicalImaging_id") UUID medicalImagingId,
                                          @RequestParam("medicalImaging_name") String updatedName)
    {
        String updatedNameCapitalized = updatedName.substring(0, 1).toUpperCase() + updatedName.substring(1);
        Optional<MedicalImaging> preExistingMedicalImaging = medicalImagingRepositoryJpa.findByName(updatedNameCapitalized);
        if(preExistingMedicalImaging.isPresent()){
            model.addAttribute("error", "Numele \""+ updatedNameCapitalized + "\" există deja în listă");
            return "diagnostic/error";
        }
        else{
        Optional<MedicalImaging> medicalImaging = medicalImagingRepositoryJpa.findById(medicalImagingId);
        medicalImaging.get().setName(updatedNameCapitalized);
        medicalImagingRepositoryJpa.save(medicalImaging.get());}
        return "redirect:/imaging/";
    }
    @GetMapping("/delete/{id}")
    public RedirectView deleteMedicalImaging(Model model, @PathVariable("id") UUID medicalImagingId) {
        medicalImagingRepositoryJpa.deleteById(medicalImagingId);
        return new RedirectView("/imaging/");
    }
}
