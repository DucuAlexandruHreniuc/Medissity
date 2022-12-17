package ro.siit.medissity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ro.siit.medissity.model.Policy;
import ro.siit.medissity.repository.PolicyRepositoryJpa;
import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("policies")
public class PolicyController {
    @Autowired
    private PolicyRepositoryJpa policyRepositoryJpa;

//    @PostConstruct
//    private void postConstructPolicy(){
//        Policy p1 = new Policy(UUID.randomUUID(),"Exemplu","http://s29.q4cdn.com/175625835/files/doc_downloads/test.pdf");
//        policyRepositoryJpa.saveAndFlush(p1);
//    }

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
    public String addPolicy(Model model,
                                            @RequestParam("policy_name") String policyNameInput, @RequestParam("policy_link") String link) {
        String policyName = policyNameInput.substring(0, 1).toUpperCase() + policyNameInput.substring(1);
        Optional <Policy> preExistingPolicy = policyRepositoryJpa.findByName(policyName);
        if (preExistingPolicy.isPresent()){
            model.addAttribute("error", "Polița \""+ policyName + "\" există deja în listă");

        }else{
        Policy addedPolicy = new Policy(UUID.randomUUID(), policyName, link);
        policyRepositoryJpa.saveAndFlush(addedPolicy);
        model.addAttribute("success", "Polița \"" + policyName + "\" a fost adăugată cu succes" );}

        return "diagnostic/policy/addForm";
    }

    @GetMapping("/delete/{id}")
    public RedirectView deletePolicy(Model model, @PathVariable("id") UUID policyId) {
        policyRepositoryJpa.deleteById(policyId);
        return new RedirectView("/policies/");
    }
}


