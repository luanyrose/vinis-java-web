package lrz.ifpe.vinisweb.model;

import java.util.ArrayList;
import java.util.List;


// Agregação: o estoque conhece vários vinis, mas eles existem sem o estoque
public class Estoque {
    private List<Vinil> vinis;

    public Estoque() {
        vinis = new ArrayList<>();
    }


    // Adiciona um vinil na lista do estoque
    public void adicionarVinil(Vinil vinil) {
        vinis.add(vinil);
    }


    // Consulta quantidade disponível de um vinil específico
    public int quantidadeDisponivel(Vinil vinil) {
        return vinil.getQtdDisponivel();
    }

    public List<Vinil> getVinis() {
        return vinis;
    }
}
