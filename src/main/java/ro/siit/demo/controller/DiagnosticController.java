package ro.siit.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ro.siit.demo.model.*;
import ro.siit.demo.repository.*;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("diagnostic")
public class DiagnosticController {

        @Autowired
    private DiagnosticRepositoryJpa diagnosticRepositoryJpa;

    /**
     * Hardcoded objects in repository
     */
    @PostConstruct
    private void postConstructDiagnostic() {
        String [] diagnosticNames = {"Trombofilia",
                "Gastrita cronică",
                "Gastrita acută",
                "Gastroduodenita",
                "Maculopatie optică/Degenerescența maculară (DMLV)",
                "Hiperprolactinemia",
                "Maculopatia exudativa OD",
                "Astigmatism hipermetropic OD",
                "Status post toxoplasmoză",
                "Hipertensiune arterială(HTA)",
                "Tulburări paroxistice",
                "Polipi endometriali",
                "Tiroidită subacută",
                        "Tiroidită acută",
                        "Distiroidie",
                        "Gușă toxică",
                        "Gusa netoxică",
                        "Boala Plummer",
                        "Steatoză hepatică",
                        "Keratoză pilară",
                        "Prurigo cronic",
                        "Otita externă stângă/dreaptă",
                        "Laringita acută",
                        "Rinita acută sau cronică alergică",
                        "Hemipareza facială",
                        "Prostatita acută sau cronică",
                        "Mixedemul primar",
                        "Psoriazis",
                        "Dislipidemia mixtă",
                        "Hipoacuzia stângă/dreaptă",
                        "Diareea si gastro-enterita posibil infectioasa",
                        "Dermita",
                        "Sindromul ovarelor polichistice",
                        "Bacteria E.Coli în funcție de localizare",
                        "Sindromul de citoliză hepatică",
                        "Discopatia lombară",
                        "Vulvovaginita",
                        "Exocervicita",
                        "Nevralgia intercostală ",
                        "Tulburările alimentare",
                        "Metroragia",
                        "Hipotrofia ponderală" ,
                        "Prolapsul rectal" ,
                        "Hipotiroidismul" ,
                        "Hipertiroidismul",
                        "Tiroidita autoimună cronică"
        };
        for (String diagnosticName : diagnosticNames) {
            Diagnostic d0 = new Diagnostic(UUID.randomUUID(), diagnosticName);
            diagnosticRepositoryJpa.save(d0);
        }
    }
    @Autowired
    private MedicalTestRepositoryJpa medicalTestRepositoryJpa;

    @PostConstruct
    private void postConstructMedicalTest() {
        String [] medicalTestNames = {"Hemoleucograma" ,
                "Fibrinogen" ,
                "VSH" ,
                "PRC" ,
                "Biochimie" ,
                "Coagulograma" ,
                "Antigenul carcino-embrionar" ,
                "TGP" ,
                "Creatinina" ,
                "LDL colesterol" ,
                "Sumar urina" ,
                "Uree I" ,
                "Ionograma " ,
                "INR" ,
                "Urocultura" ,
                "Factor reumatoid" ,
                "DHEA-S " ,
                "Prolactina" ,
                "LH" ,
                "Biopsie din pielea scalpului" ,
                "Magneziemie   " ,
                "Examen hematologic" ,
                "Examen micologic direct Insamantarea pe mediu de cultura selectiv (m. Sabouraud)  " ,
                "Glicemia " ,
                "Antifungigrama (pentru formele cronice rezistente)" ,
                "Dozari hormonale" ,
                "Test sarcina " ,
                "Testul HIV" ,
                "IDR – PPD" ,
                "Teste imunologice" ,
                "VDRL" ,
                "TPHA cantitative"
        };
        for (String medicalTestName : medicalTestNames) {
            MedicalTest m0 = new MedicalTest(UUID.randomUUID(), medicalTestName);
            medicalTestRepositoryJpa.save(m0);
        }
    }


   @Autowired
   private MedicalImagingRepositoryJpa medicalImagingRepositoryJpa;

    @PostConstruct
    private void postConstructMedicalImaging() {
        String [] medicalImagingNames = {"Rezonanta magnetica nucleara (RMN)" ,
                "Tomografie Computerizata (CT)" ,
                "Scintigrafie" ,
                "Ecografie abdominala" ,
                "Elastografie hepatica" ,
                "EEG epilepsie" ,
                "Angiografie" ,
                "Urografie" ,
                "Histerosalpingografie" ,
                "Radiologie Conventionala si Digitala" ,
                "Mamografie digitala" ,
                "Ecografie transvaginala" ,
                "Osteodensitometrie",
                "Endoscopie digestiva superioara si inferioara " ,
                "Rectosigmoidoscopie" ,
                "Ecografie musculo-scheletala" ,
                "Bronhoscopie " ,
                "Ecografie Doppler " ,
                "Bariu pasaj " ,
                "Radioiodocaptare " ,
                "Videocapsula endoscopica " ,
                "Manometrie" ,
                "Ecografie mamara"
        };
        for (String medicalImagingName : medicalImagingNames) {
            MedicalImaging i0 = new MedicalImaging(UUID.randomUUID(), medicalImagingName);
            medicalImagingRepositoryJpa.save(i0);
        }
    }

  @Autowired
   private SymptomRepositoryJpa symptomRepositoryJpa;

    @PostConstruct
    private void postConstructSymptom() {
        String [] symptomNames = {"Tahicardie, nespecificata" ,
                "Bradicardie, nespecificata" ,
                "Palpitatii" ,
                "Alte anomalii de ritm cardiac si nespecificate" ,
                "Murmur cardiac benign si anodin" ,
                "Murmur cardiac, nespecificat" ,
                "Alte zgomote cardiace" ,
                "Gangrena, neclasificata altundeva" ,
                "Citirea tensiunii arteriale crescute, fara diagnostic de hipertensiune" ,
                "Citirea tensiunii arteriale scazute nespecifice" ,
                "Epistaxis" ,
                "Hemoragie provenita din gat" ,
                "Hemoptizie" ,
                "Hemoragie provenind din alte localizari ale cailor respiratorii" ,
                "Hemoragia provenind din caile respiratorii, nespecificata" ,
                "Tuse" ,
                "Dispnee" ,
                "Stridor" ,
                "Respiratia suieratoare" ,
                "Respiratia periodica" ,
                "Hiperventilatie" ,
                "Respiratie pe gura" ,
                "Sughit" ,
                "Stranut" ,
                "Alte anomaliile de respiratie si nespecificate" ,
                "Durere in faringo-laringeana" ,
                "Durerea in piept la respiratie" ,
                "Durerea precordiala" ,
                "Alta durere in piept" ,
                "Durere in piept,nespecificata" ,
                "Asfixie" ,
                "Pleurezie" ,
                "Stop respirator" ,
                "Sputa anormala"
        };
        for (String symptomName : symptomNames) {
            Symptom s0 = new Symptom(UUID.randomUUID(), symptomName);
            symptomRepositoryJpa.save(s0);
        }
    }

    @GetMapping(value = "/")

    public String getDiagnostic(Model model){
        model.addAttribute("diagnostics", diagnosticRepositoryJpa.findAll(Sort.by(Sort.Direction.ASC,"name")));
        return "diagnostic/list";
    }
    @GetMapping("/add")
    public String addDiagnosticForm(Model model) {
        return "diagnostic/addForm";
    }
    @PostMapping("/add")
    public RedirectView addDiagnostics(Model model,

                                @RequestParam("diagnostic_name") String diagnosticName) {
        Diagnostic addedDiagnostic = new Diagnostic(UUID.randomUUID(), diagnosticName);


        diagnosticRepositoryJpa.saveAndFlush(addedDiagnostic);
        return new RedirectView("/diagnostic/");
    }
    @GetMapping("/edit/{id}")
    public String editDiangosticFormTests(Model model, @PathVariable("id") UUID diangosticId) {
        Optional<Diagnostic> optionalDiagnostic = diagnosticRepositoryJpa.findById(diangosticId);
        Diagnostic diagnostic = optionalDiagnostic.get();
        model.addAttribute("diagnostic", diagnostic);

        List<MedicalTest> allMedicalTests = medicalTestRepositoryJpa.findAll(Sort.by(Sort.Direction.ASC,"name"));
        List<MedicalTest> unassignedMedicalTest = allMedicalTests.stream()
                .filter(medicalTest -> !diagnostic.getMedicalTestList().contains(medicalTest))
                .collect(Collectors.toList());
        model.addAttribute("unassignedMedicalTests", unassignedMedicalTest);
        List<MedicalTest> assignedMedicalTest = allMedicalTests.stream()
                .filter(medicalTest -> diagnostic.getMedicalTestList().contains(medicalTest))
                .collect(Collectors.toList());
        model.addAttribute("assignedMedicalTests", assignedMedicalTest);

        List<MedicalImaging> allMedicalImaging = medicalImagingRepositoryJpa.findAll(Sort.by(Sort.Direction.ASC,"name"));
        List<MedicalImaging> unassignedMedicalImaging = allMedicalImaging.stream()
                .filter(medicalImaging -> !diagnostic.getMedicalImagingList().contains(medicalImaging))
                .collect(Collectors.toList());
        model.addAttribute("unassignedMedicalImaging", unassignedMedicalImaging);

        List<MedicalImaging> assignedMedicalImaging = allMedicalImaging.stream()
                .filter(medicalImaging -> diagnostic.getMedicalImagingList().contains(medicalImaging))
                .collect(Collectors.toList());
        model.addAttribute("assignedMedicalImaging", assignedMedicalImaging);

        List<Symptom> allSymptoms = symptomRepositoryJpa.findAll(Sort.by(Sort.Direction.ASC,"name"));
        List<Symptom> unassignedSymptoms = allSymptoms.stream()
                .filter(symptom -> !diagnostic.getSymptomList().contains(symptom))
                .collect(Collectors.toList());
        model.addAttribute("unassignedSymptoms", unassignedSymptoms);

        List<Symptom> assignedSymptoms = allSymptoms.stream()
                .filter(symptom -> diagnostic.getSymptomList().contains(symptom))
                .collect(Collectors.toList());
        model.addAttribute("assignedSymptoms", assignedSymptoms);
        return "diagnostic/editForm";

    }

    @PostMapping("/edit")
    public RedirectView addDiangostics(Model model,
                                @RequestParam("diagnostic_id") UUID diagnosticId,
                                @RequestParam("diagnostic_name") String updatedName)
                                {

        Optional<Diagnostic> diangostic = diagnosticRepositoryJpa.findById(diagnosticId);
        diangostic.get().setName(updatedName);
        diagnosticRepositoryJpa.save(diangostic.get());
        return new RedirectView("/diagnostic/");
    }
    @GetMapping("/delete/{id}")
    public RedirectView deleteDiagnostic(Model model, @PathVariable("id") UUID diagnosticId) {
        diagnosticRepositoryJpa.deleteById(diagnosticId);
        return new RedirectView("/diagnostic/");
    }
    @GetMapping("/{id}/assignMedicalTest/{medicalTestId}")
    public RedirectView assignMedicalTest(Model model, @PathVariable("id") UUID diagnosticId, @PathVariable("medicalTestId") UUID medicalTestId) {
        Diagnostic diagnostic = diagnosticRepositoryJpa.findById(diagnosticId).get();
        MedicalTest medicalTestToAdd = medicalTestRepositoryJpa.findById(medicalTestId).get();
        diagnostic.getMedicalTestList().add(medicalTestToAdd);
        diagnosticRepositoryJpa.save(diagnostic);
        return new RedirectView("/diagnostic/");
    }
    @GetMapping("/{id}/assignMedicalImaging/{medicalImagingId}")
    public RedirectView assignMedicalImaging(Model model, @PathVariable("id") UUID diagnosticId, @PathVariable("medicalImagingId") UUID medicalImagingId) {
        Diagnostic diagnostic = diagnosticRepositoryJpa.findById(diagnosticId).get();
        MedicalImaging medicalImagingToAdd = medicalImagingRepositoryJpa.findById(medicalImagingId).get();
        diagnostic.getMedicalImagingList().add(medicalImagingToAdd);
        diagnosticRepositoryJpa.save(diagnostic);
        return new RedirectView("/diagnostic/");
    }

    @GetMapping("/{id}/assignSymptom/{symptomId}")
    public RedirectView assignSymptom(Model model, @PathVariable("id") UUID diagnosticId, @PathVariable("symptomId") UUID symptomId) {
        Diagnostic diagnostic = diagnosticRepositoryJpa.findById(diagnosticId).get();
        Symptom symptomToAdd = symptomRepositoryJpa.findById(symptomId).get();
        diagnostic.getSymptomList().add(symptomToAdd);
        diagnosticRepositoryJpa.save(diagnostic);
        return new RedirectView("/diagnostic/edit/{id}");
    }
    @PostMapping("/{id}/assignSymptoms")
    public RedirectView processFormSymptoms(@PathVariable("id") UUID diagnosticId, @RequestParam UUID[] symptoms) {
        System.out.println(Arrays.toString(symptoms));
        Diagnostic diagnostic = diagnosticRepositoryJpa.findById(diagnosticId).get();
        for (UUID symptom : symptoms) {
            Symptom symptomToAdd = symptomRepositoryJpa.findById(symptom).get();
            diagnostic.getSymptomList().add(symptomToAdd);
        }
        diagnosticRepositoryJpa.save(diagnostic);
        return new RedirectView("/diagnostic/");
    }
    @PostMapping("/{id}/assignMedicalImaging")
    public RedirectView processFormImaging(@PathVariable("id") UUID diagnosticId, @RequestParam UUID[] medicalImagingList) {
        Diagnostic diagnostic = diagnosticRepositoryJpa.findById(diagnosticId).get();
        for (UUID medicalImaging : medicalImagingList) {
            MedicalImaging medicalImagingToAdd = medicalImagingRepositoryJpa.findById(medicalImaging).get();
            diagnostic.getMedicalImagingList().add(medicalImagingToAdd);
        }
        diagnosticRepositoryJpa.save(diagnostic);
        return new RedirectView("/diagnostic/");
    }
    @PostMapping("/{id}/assignMedicalTests")
    public RedirectView processFormTests(@PathVariable("id") UUID diagnosticId, @RequestParam UUID[] medicalTests) {
//        System.out.println(Arrays.toString(medicalTests));
        Diagnostic diagnostic = diagnosticRepositoryJpa.findById(diagnosticId).get();
        for (UUID medicalTest : medicalTests) {
            MedicalTest medicalTestToAdd = medicalTestRepositoryJpa.findById(medicalTest).get();
            diagnostic.getMedicalTestList().add(medicalTestToAdd);
        }
        diagnosticRepositoryJpa.save(diagnostic);
        return new RedirectView("/diagnostic/");
    }

    @PostMapping("/{id}/assignAll")
    public RedirectView processFormAll(@PathVariable("id") UUID diagnosticId, @RequestParam (required = false) UUID[] medicalTests, @RequestParam(required = false) UUID[] medicalImagingList, @RequestParam(required = false) UUID[]symptoms) {
//        System.out.println(Arrays.toString(medicalTests));
        Diagnostic diagnostic = diagnosticRepositoryJpa.findById(diagnosticId).get();
        if(medicalTests != null){
        for (UUID medicalTest : medicalTests) {
            MedicalTest medicalTestToAdd = medicalTestRepositoryJpa.findById(medicalTest).get();
            diagnostic.getMedicalTestList().add(medicalTestToAdd);
        }
        }
        if(medicalImagingList != null){
        for (UUID medicalImaging : medicalImagingList) {
            MedicalImaging medicalImagingToAdd = medicalImagingRepositoryJpa.findById(medicalImaging).get();
            diagnostic.getMedicalImagingList().add(medicalImagingToAdd);
        }
        }

        if(symptoms != null) {
            for (UUID symptom : symptoms) {
                Symptom symptomToAdd = symptomRepositoryJpa.findById(symptom).get();
                diagnostic.getSymptomList().add(symptomToAdd);
            }
            }else {


        }

        diagnosticRepositoryJpa.save(diagnostic);
        return new RedirectView("/diagnostic/");
    }

//    Unassign
    @PostMapping("/{id}/unassignAll")
    public RedirectView processFormUnassignAll(@PathVariable("id") UUID diagnosticId, @RequestParam (required = false) UUID[] medicalTests, @RequestParam(required = false) UUID[] medicalImagingList, @RequestParam(required = false) UUID[]symptoms) {
//        System.out.println(Arrays.toString(medicalTests));
        Diagnostic diagnostic = diagnosticRepositoryJpa.findById(diagnosticId).get();
        if(medicalTests != null){
        for (UUID medicalTest : medicalTests) {
            MedicalTest medicalTestToAdd = medicalTestRepositoryJpa.findById(medicalTest).get();
            diagnostic.getMedicalTestList().remove(medicalTestToAdd);
            }
        }
        if(medicalImagingList != null) {
            for (UUID medicalImaging : medicalImagingList) {
                MedicalImaging medicalImagingToAdd = medicalImagingRepositoryJpa.findById(medicalImaging).get();
                diagnostic.getMedicalImagingList().remove(medicalImagingToAdd);
            }
        }

        if(symptoms != null) {
            for (UUID symptom : symptoms) {
                Symptom symptomToAdd = symptomRepositoryJpa.findById(symptom).get();
                diagnostic.getSymptomList().remove(symptomToAdd);
            }
        }
        diagnosticRepositoryJpa.save(diagnostic);
        return new RedirectView("/diagnostic/");
    }
}



