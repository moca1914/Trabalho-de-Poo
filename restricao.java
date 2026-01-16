package modelo;

public enum RestricaoAlimentar {

    SEM_LACTOSE("Sem Lactose"),
    SEM_GLUTEN("Sem Glúten"),
    VEGANO("Vegano");

    private final String descricao;

    RestricaoAlimentar(String descricao) {
        this.descricao = descricao;
        }
        public String getDescricao() {
            return descricao;
        }
    }
