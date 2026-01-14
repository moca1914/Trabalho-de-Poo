package excecao;

public class ProdutoIndisponivelException extends RuntimeException {
    public ProdutoIndisponivelException() {
        super("Produto indisponível no momento.");
    }
}
