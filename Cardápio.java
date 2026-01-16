package servico;

import modelo.Produto;
import modelo.RestricaoAlimentar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cardapio implements Filtravel{

    private static List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }
    public void removerProduto(Produto produto){
        produtos.remove(produto);
    }

    public List<Produto> listarDisponiveis(){
        List<Produto> disponiveis = new ArrayList<>();

        for (Produto produto : produtos){
            if (produto.isDisponivel()){
                disponiveis.add(produto);
            }
        }
        return disponiveis;
    }

    public List<Produto> filtrarPorRestricao(RestricaoAlimentar restricao){
        List<Produto> filtrados = new ArrayList<>();

        for (Produto produto : produtos){
            if (produto.getRestricoes().contains(restricao)){
                filtrados.add(produto);
            }
        }
        return filtrados;
    }

    public List<Produto> getTodos() {
        return produtos;
    }
}
