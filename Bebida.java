package modelo;

import java.util.List;

public class Bebida extends Produto {

    public Bebida(String nome, double preco, int tempoPreparo, List<RestricaoAlimentar> restricoes) {
        super(nome, preco, tempoPreparo, restricoes);
    }

    @Override
    public String getCategoria() {
        return "Bebidas";
    }
}
