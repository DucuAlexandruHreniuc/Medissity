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
    public RedirectView addMedicalTest(Model model,
                                       @RequestParam("medicalTest_name") String medicalTestName) {
        MedicalTest addedMedicalTest = new MedicalTest(UUID.randomUUID(), medicalTestName);

        medicalTestRepositoryJpa.saveAndFlush(addedMedicalTest);
        return new RedirectView("/medical-tests/");


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
    public RedirectView addMedicalTests(Model model,
                                        @RequestParam("medicalTest_id") UUID medicalTestId,
                                        @RequestParam("medicalTest_name") String updatedName)
    {

        Optional<MedicalTest> medicalTest = medicalTestRepositoryJpa.findById(medicalTestId);
        medicalTest.get().setName(updatedName);
        medicalTestRepositoryJpa.save(medicalTest.get());
        return new RedirectView("/medical-tests/");
    }
    @GetMapping("/delete/{id}")
    public RedirectView deleteMedicalTest(Model model, @PathVariable("id") UUID medicalTestId) {
        medicalTestRepositoryJpa.deleteById(medicalTestId);
        return new RedirectView("/medical-tests/");
    }

}

