package ro.siit.medissity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ro.siit.medissity.model.Policy;
import ro.siit.medissity.repository.PolicyRepositoryJpa;
import javax.annotation.PostConstruct;
import java.util.UUID;

@Controller
@RequestMapping("policies")
public class PolicyController {
    @Autowired
    private PolicyRepositoryJpa policyRepositoryJpa;

    @PostConstruct
    private void postConstructPolicy(){
        Policy p1 = new Policy(UUID.randomUUID(),"Exemplu","http://s29.q4cdn.com/175625835/files/doc_downloads/test.pdf");
        policyRepositoryJpa.saveAndFlush(p1);
    }

    @GetMapping(value = "/")
    public String getPolicies(Model model){
        model.addAttribute("policies" , policyRepositoryJpa.findAll());
        return "diagnostic/policy/list";
    }
    @GetMapping("/add")
    public String postConstructPolicy(Model model) {
        return "diagnostic/policy/addForm";
    }

    @PostMapping("/add")
    public RedirectView addPolicy(Model model,
                                            @RequestParam("policy_name") String policyName, @RequestParam("policy_link") String link) {
        Policy addedPolicy = new Policy(UUID.randomUUID(), policyName, link);
        policyRepositoryJpa.saveAndFlush(addedPolicy);
        return new RedirectView("/policies/");
    }

//    @GetMapping("/edit/{id}")
//    public String editMedicalImaging(Model model, @PathVariable("id") UUID medicalImagingId) {
//        Optional<MedicalImaging> optionalMedicalImaging = medicalImagingRepositoryJpa.findById(medicalImagingId);
//        MedicalImaging medicalImaging = optionalMedicalImaging.get();
//        model.addAttribute("medicalImaging", medicalImaging);
//        List<MedicalImaging> allMedicalImaging = medicalImagingRepositoryJpa.findAll();
//        model.addAttribute("allMedicalImaging", allMedicalImaging);
//        return "diagnostic/medicalImaging/editForm";
//    }
//    @PostMapping("/edit")
//    public RedirectView addMedicalImaging(Model model,
//                                          @RequestParam("medicalImaging_id") UUID medicalImagingId,
//                                          @RequestParam("medicalImaging_name") String updatedName)
//    {
//
//        Optional<MedicalImaging> medicalImaging = medicalImagingRepositoryJpa.findById(medicalImagingId);
//        medicalImaging.get().setName(updatedName);
//        medicalImagingRepositoryJpa.save(medicalImaging.get());
//        return new RedirectView("/imagistica/");
//    }
    @GetMapping("/delete/{id}")
    public RedirectView deletePolicy(Model model, @PathVariable("id") UUID policyId) {
        policyRepositoryJpa.deleteById(policyId);
        return new RedirectView("/policies/");
    }


}


