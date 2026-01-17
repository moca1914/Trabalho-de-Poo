package modelo;

import excecao.PedidoFinalizadoException;
import excecao.PedidoVazioException;
import excecao.ProdutoIndisponivelException;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private static int contador = 1;
    private int numero;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private boolean finalizado;

    public Pedido(Cliente cliente) {
        this.numero = contador++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.finalizado = false;
    }

    public ItemPedido adicionarItem(Produto produto, String comentario) {

        if (finalizado) {
            throw new PedidoFinalizadoException();
        }

        if (!produto.isDisponivel()) {
            throw new ProdutoIndisponivelException();
        }

        ItemPedido item = new ItemPedido(produto, comentario);
        itens.add(item);
        return item;
    }


    public void removerItem(ItemPedido item) {

        itens.remove(item);
    }

    public double calcularTotal() {
        double total = 0;

        for(ItemPedido item : itens){
            total += item.getProduto().getPreco();
        }
        return total;
    }

    public int calcularTempoTotal() {
        int tempo = 0;

        for(ItemPedido item : itens){
            tempo += item.getProduto().getTempoPreparo();
        }
        return tempo;
    }

    public void finalizar() {
        if (itens.isEmpty()) {
            throw new PedidoVazioException();
        }
        if(finalizado){
            throw new PedidoFinalizadoException();
        }
        finalizado = true;
    }
    
    public void verificarSePodeAlterar() throws PedidoFinalizadoException {
        if (finalizado) {
            throw new PedidoFinalizadoException();
        }
    }


    public int getNumero() {
        return numero;
    }
}
