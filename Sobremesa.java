package modelo;

import java.util.List;

public class Sobremesa extends Produto {

    public Sobremesa(String nome, double preco, int tempoPreparo, List<RestricaoAlimentar> restricoes) {
        super(nome, preco, tempoPreparo, restricoes);
    }

    @Override
    public String getCategoria() {
        return "Sobremesa";
    }
}
