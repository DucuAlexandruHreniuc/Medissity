package ro.siit.medissity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ro.siit.medissity.model.Symptom;
import ro.siit.medissity.repository.SymptomRepositoryJpa;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("symptoms")
public class SymptomController {
    @Autowired
    private SymptomRepositoryJpa symptomRepositoryJpa;

    @GetMapping(value = "/")
    public String getSymptoms(Model model){
        model.addAttribute("symptoms", symptomRepositoryJpa.findAll((Sort.by(Sort.Direction.ASC,"name"))));
        return "diagnostic/symptom/list";

    }
    @GetMapping("/add")
    public String addSymptomForm(Model model) {
        return "diagnostic/symptom/addForm";
    }
    @PostMapping("/add")
    public String addSymptom(Model model,
                                   @RequestParam("symptom_name") String symptomNameInput) {
        String symptomName = symptomNameInput.substring(0, 1).toUpperCase() + symptomNameInput.substring(1);
        Optional <Symptom> preExistingSymptom = symptomRepositoryJpa.findByName(symptomName);
        if (preExistingSymptom.isPresent()){
            model.addAttribute("error", "Simptomul \""+ symptomName + "\" există deja în listă");

        }else{
        Symptom addedSymptom = new Symptom(UUID.randomUUID(), symptomName);
        symptomRepositoryJpa.saveAndFlush(addedSymptom);
        model.addAttribute("success", "Simptomul \"" + symptomName + "\" a fost adăugat cu succes" );}
        return "diagnostic/symptom/addForm";
    }

    @GetMapping("/edit/{id}")
    public String editSymptom(Model model, @PathVariable("id") UUID symptomId) {
        Optional<Symptom> optionalSymptom = symptomRepositoryJpa.findById(symptomId);
        Symptom symptom = optionalSymptom.get();
        model.addAttribute("symptom", symptom);
        List<Symptom> allSymptoms = symptomRepositoryJpa.findAll();
        model.addAttribute("allSymptoms", allSymptoms);
        return "diagnostic/symptom/editForm";
    }

    @PostMapping("/edit")
    public String addSymtoms(Model model,
                                   @RequestParam("symptom_id") UUID symptomId,
                                   @RequestParam("symptom_name") String updatedName)
    {
        String updatedNameCapitalized = updatedName.substring(0, 1).toUpperCase() + updatedName.substring(1);
        Optional<Symptom> preExistingSymptom = symptomRepositoryJpa.findByName(updatedNameCapitalized);
        if(preExistingSymptom.isPresent()){
            model.addAttribute("error", "Numele \""+ updatedNameCapitalized + "\" există deja în listă");
            return "diagnostic/error";
        }
        else{
        Optional<Symptom> symptom = symptomRepositoryJpa.findById(symptomId);
        symptom.get().setName(updatedNameCapitalized);
        symptomRepositoryJpa.save(symptom.get());}
        return "redirect:/symptoms/edit/" + symptomId;
    }
    @GetMapping("/delete/{id}")
    public RedirectView deleteSymptom(Model model, @PathVariable("id") UUID symptomId) {
        symptomRepositoryJpa.deleteById(symptomId);
        return new RedirectView("/symptoms/");
    }
}




