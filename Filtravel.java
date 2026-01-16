package servico;

import modelo.Produto;
import modelo.RestricaoAlimentar;
import java.util.List;

public interface Filtravel {

    List<Produto> filtrarPorRestricao(RestricaoAlimentar restricao);

}
