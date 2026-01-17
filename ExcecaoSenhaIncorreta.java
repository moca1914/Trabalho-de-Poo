package excecao;

public class SenhaIncorretaException extends Exception {

    public SenhaIncorretaException() {
        super("Senha do administrador incorreta.");
    }
}
