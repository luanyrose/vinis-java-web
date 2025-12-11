package lrz.ifpe.vinisweb.controller;

import lrz.ifpe.vinisweb.model.Cliente;
import lrz.ifpe.vinisweb.model.Compra;
import lrz.ifpe.vinisweb.model.Vinil;
import lrz.ifpe.vinisweb.repository.ClienteRepository;
import lrz.ifpe.vinisweb.repository.CompraRepository;
import lrz.ifpe.vinisweb.repository.VinilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VinilRepository vinilRepository;

    @GetMapping
    public String listarCompras(@RequestParam(value = "busca", required = false) String busca, Model model) {
        List<Compra> compras;
        
        if (busca != null && !busca.isEmpty()) {
            compras = compraRepository.findByClienteCpf(busca);
        } else {
            compras = compraRepository.findAll();
        }
        
        model.addAttribute("compras", compras);
        model.addAttribute("busca", busca);
        return "compras/lista";
    }

    @GetMapping("/novo")
    public String novaCompraForm(Model model) {
        model.addAttribute("compra", new Compra());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("vinis", vinilRepository.findAll());
        return "compras/form";
    }

    @PostMapping
    public String salvarCompra(@RequestParam Long clienteId,
                               @RequestParam Long vinilId,
                               @RequestParam int quantidade) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Vinil vinil = vinilRepository.findById(vinilId)
                .orElseThrow(() -> new RuntimeException("Vinil não encontrado"));

        Compra compra = new Compra(new Date(), cliente);
        compra.adicionarItem(vinil, quantidade);
        compraRepository.save(compra);
        vinilRepository.save(vinil);

        return "redirect:/compras";
    }

    @GetMapping("/{id}")
    public String detalharCompra(@PathVariable Long id, Model model) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
        model.addAttribute("compra", compra);
        return "compras/detalhes";
    }

    @GetMapping("/{id}/excluir")
    public String excluirCompra(@PathVariable Long id) {
        compraRepository.deleteById(id);
        return "redirect:/compras";
    }
}

