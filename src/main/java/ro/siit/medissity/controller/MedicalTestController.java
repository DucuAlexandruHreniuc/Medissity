package ro.siit.medissity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ro.siit.medissity.model.MedicalTest;
import ro.siit.medissity.repository.MedicalTestRepositoryJpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("medical-tests")
public class MedicalTestController {
    @Autowired
    private MedicalTestRepositoryJpa medicalTestRepositoryJpa;


    @GetMapping(value = "/")
    public String getMedicalTests(Model model){
        model.addAttribute("medicalTests",medicalTestRepositoryJpa.findAll((Sort.by(Sort.Direction.ASC,"name"))));
        return "diagnostic/medicalTest/list";
    }
    @GetMapping("/add")
    public String addMedicalTestForm(Model model) {
        return "diagnostic/medicalTest/addForm";
    }
    @PostMapping("/add")
    public String addMedicalTest(Model model,
                                       @RequestParam("medicalTest_name") String medicalTestNameInput) {
        String medicalTestName = medicalTestNameInput.substring(0, 1).toUpperCase() + medicalTestNameInput.substring(1);
        Optional<MedicalTest> preExistingMedicalTest = medicalTestRepositoryJpa.findByName(medicalTestName);
        if (preExistingMedicalTest.isPresent()){
            model.addAttribute("error", "Analiza \""+ medicalTestName + "\" există deja în listă");

        }
        else{
        MedicalTest addedMedicalTest = new MedicalTest(UUID.randomUUID(), medicalTestName);
            medicalTestRepositoryJpa.saveAndFlush(addedMedicalTest);
            model.addAttribute("success", "Analiza \"" + medicalTestName + "\" a fost adăugată cu succes" );}
        return "diagnostic/medicalTest/addForm";
    }
    @GetMapping("/edit/{id}")
    public String editMedicalTest(Model model, @PathVariable("id") UUID medicalTestId) {
        Optional<MedicalTest> optionalMedicalTest = medicalTestRepositoryJpa.findById(medicalTestId);
        MedicalTest medicalTest = optionalMedicalTest.get();
        model.addAttribute("medicalTest", medicalTest);
        List<MedicalTest> allMedicalTests = medicalTestRepositoryJpa.findAll();
        model.addAttribute("allMedicalTests", allMedicalTests);
        return "diagnostic/medicalTest/editForm";
    }
    @PostMapping("/edit")
    public String addMedicalTests(Model model,
                                        @RequestParam("medicalTest_id") UUID medicalTestId,
                                        @RequestParam("medicalTest_name") String updatedName)
    {
        String updatedNameCapitalized = updatedName.substring(0, 1).toUpperCase() + updatedName.substring(1);
        Optional<MedicalTest> preExistingMedicalTest = medicalTestRepositoryJpa.findByName(updatedNameCapitalized);
        if(preExistingMedicalTest.isPresent()){
            model.addAttribute("error", "Numele \""+ updatedNameCapitalized + "\" există deja în listă");
            return "diagnostic/error";
        }
        else{
        Optional<MedicalTest> medicalTest = medicalTestRepositoryJpa.findById(medicalTestId);
        medicalTest.get().setName(updatedNameCapitalized);
        medicalTestRepositoryJpa.save(medicalTest.get());}
        return "redirect:/medical-tests/edit/" + medicalTestId;
    }
    @GetMapping("/delete/{id}")
    public RedirectView deleteMedicalTest(Model model, @PathVariable("id") UUID medicalTestId) {
        medicalTestRepositoryJpa.deleteById(medicalTestId);
        return new RedirectView("/medical-tests/");
    }

}

