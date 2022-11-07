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
    public RedirectView addMedicalImaging(Model model,
                                       @RequestParam("medicalImaging_name") String medicalImagingName) {
        MedicalImaging addedMedicalImaging = new MedicalImaging(UUID.randomUUID(), medicalImagingName);

        medicalImagingRepositoryJpa.saveAndFlush(addedMedicalImaging);
        return new RedirectView("/imaging/add");

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
    public RedirectView addMedicalImaging(Model model,
                                        @RequestParam("medicalImaging_id") UUID medicalImagingId,
                                        @RequestParam("medicalImaging_name") String updatedName)
    {

        Optional<MedicalImaging> medicalImaging = medicalImagingRepositoryJpa.findById(medicalImagingId);
        medicalImaging.get().setName(updatedName);
        medicalImagingRepositoryJpa.save(medicalImaging.get());
        return new RedirectView("/imaging/");
    }
    @GetMapping("/delete/{id}")
    public RedirectView deleteMedicalImaging(Model model, @PathVariable("id") UUID medicalImagingId) {
        medicalImagingRepositoryJpa.deleteById(medicalImagingId);
        return new RedirectView("/imaging/");
    }
}
