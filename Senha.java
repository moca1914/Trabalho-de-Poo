package servico;
import excecao.SenhaIncorretaException;

public class SenhaAdm {

    private static final String SENHA_ADMIN = "admin30";

    public boolean autenticarAdministrador(String senhaDigitada) throws SenhaIncorretaException {

        if (!SENHA_ADMIN.equals(senhaDigitada)) {
            throw new SenhaIncorretaException();
        } else {
            return SENHA_ADMIN.equals(senhaDigitada);
        }
    }
}
