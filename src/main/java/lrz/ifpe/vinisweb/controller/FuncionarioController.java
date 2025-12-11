package lrz.ifpe.vinisweb.controller;

import lrz.ifpe.vinisweb.model.Funcionario;
import lrz.ifpe.vinisweb.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public String listarFuncionarios(@RequestParam(value = "busca", required = false) String busca, Model model) {
        List<Funcionario> funcionarios;
        if (busca != null && !busca.isEmpty()) {
            funcionarios = funcionarioRepository.findByCpf(busca) != null 
                ? List.of(funcionarioRepository.findByCpf(busca))
                : List.of();
        } else {
            funcionarios = funcionarioRepository.findAll();
        }
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("busca", busca);
        return "funcionarios/lista";
    }

    @GetMapping("/novo")
    public String novoFuncionarioForm(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "funcionarios/form";
    }

    @PostMapping
    public String salvarFuncionario(@ModelAttribute Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
        return "redirect:/funcionarios";
    }

    @GetMapping("/{id}/editar")
    public String editarFuncionarioForm(@PathVariable Long id, Model model) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        model.addAttribute("funcionario", funcionario);
        return "funcionarios/form";
    }

    @PostMapping("/{id}")
    public String atualizarFuncionario(@PathVariable Long id, @ModelAttribute Funcionario funcionario) {
        funcionario.setId(id);
        funcionarioRepository.save(funcionario);
        return "redirect:/funcionarios";
    }

    @GetMapping("/{id}/excluir")
    public String excluirFuncionario(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);
        return "redirect:/funcionarios";
    }
}
