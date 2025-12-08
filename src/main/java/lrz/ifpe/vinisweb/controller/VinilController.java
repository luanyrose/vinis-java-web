package lrz.ifpe.vinisweb.controller;

import lrz.ifpe.vinisweb.model.Vinil;
import lrz.ifpe.vinisweb.repository.VinilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vinis")
public class VinilController {

    @Autowired
    private VinilRepository vinilRepository;

    @GetMapping
    public String listarVinis(Model model) {
        List<Vinil> vinis = vinilRepository.findAll();
        model.addAttribute("vinis", vinis);
        return "vinis/lista";
    }

    @GetMapping("/novo")
    public String novoVinilForm(Model model) {
        model.addAttribute("vinil", new Vinil());
        return "vinis/form";
    }

    @PostMapping
    public String salvarVinil(@ModelAttribute Vinil vinil) {
        vinilRepository.save(vinil);
        return "redirect:/vinis";
    }

    @GetMapping("/{id}/editar")
    public String editarVinilForm(@PathVariable Long id, Model model) {
        Vinil vinil = vinilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vinil não encontrado"));
        model.addAttribute("vinil", vinil);
        return "vinis/form";
    }

    @PostMapping("/{id}")
    public String atualizarVinil(@PathVariable Long id, @ModelAttribute Vinil vinil) {
        vinil.setId(id);
        vinilRepository.save(vinil);
        return "redirect:/vinis";
    }

    @GetMapping("/{id}/excluir")
    public String excluirVinil(@PathVariable Long id) {
        vinilRepository.deleteById(id);
        return "redirect:/vinis";
    }
}

