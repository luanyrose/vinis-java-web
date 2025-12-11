package lrz.ifpe.vinisweb.controller;

import lrz.ifpe.vinisweb.model.Cliente;
import lrz.ifpe.vinisweb.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public String listarClientes(@RequestParam(value = "busca", required = false) String busca, 
                                  @RequestParam(value = "tipo", required = false) String tipo,
                                  Model model) {
        List<Cliente> clientes;
        
        if (busca != null && !busca.isEmpty()) {
            clientes = clienteRepository.findByNomeContainingIgnoreCase(busca);
        } else if (tipo != null && !tipo.isEmpty()) {
            clientes = clienteRepository.findByTipoCliente(tipo);
        } else {
            clientes = clienteRepository.findAll();
        }
        
        model.addAttribute("clientes", clientes);
        model.addAttribute("busca", busca);
        model.addAttribute("tipo", tipo);
        return "clientes/lista";
    }

    @GetMapping("/novo")
    public String novoClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }

    @PostMapping
    public String salvarCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/{id}/editar")
    public String editarClienteForm(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        model.addAttribute("cliente", cliente);
        return "clientes/form";
    }

    @PostMapping("/{id}")
    public String atualizarCliente(@PathVariable Long id, @ModelAttribute Cliente cliente) {
        cliente.setId(id);
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/{id}/excluir")
    public String excluirCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }
}

