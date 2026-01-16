package modelo;

import java.util.List;

public class Prato extends Produto {

    public Prato(String nome, double preco, int tempoPreparo, List<RestricaoAlimentar> restricoes) {
        super(nome, preco, tempoPreparo, restricoes);
    }

    @Override
    public String getCategoria() {
        return "Prato";
    }
}
