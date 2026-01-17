package modelo;

import excecao.PrecoInvalidoException;
import java.util.List;

public abstract class Produto{

    protected String nome;
    protected double preco;
    protected boolean disponivel;
    protected int tempoPreparo;
    protected List<RestricaoAlimentar> restricoes;

    public Produto(String nome, double preco, int tempoPreparo, List<RestricaoAlimentar> restricoes) {
        this.nome = nome;
        setPreco(preco);
        this.tempoPreparo = tempoPreparo;
        this.restricoes = restricoes;
        this.disponivel = true;
    }

    public abstract String getCategoria();

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new PrecoInvalidoException();
        }
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getTempoPreparo() {
        return tempoPreparo;
    }

    public List<RestricaoAlimentar> getRestricoes() {
        return restricoes;
    }
    public String getNome(){
        return nome;
    }

}
package modelo;

import excecao.PrecoInvalidoException;
import java.util.List;

public abstract class Produto{

    protected String nome;
    protected double preco;
    protected boolean disponivel;
    protected int tempoPreparo;
    protected List<RestricaoAlimentar> restricoes;

    public Produto(String nome, double preco, int tempoPreparo, List<RestricaoAlimentar> restricoes) {
        this.nome = nome;
        setPreco(preco);
        this.tempoPreparo = tempoPreparo;
        this.restricoes = restricoes;
        this.disponivel = true;
    }

    public abstract String getCategoria();

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new PrecoInvalidoException();
        }
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getTempoPreparo() {
        return tempoPreparo;
    }

    public List<RestricaoAlimentar> getRestricoes() {
        return restricoes;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTempoPreparo(int tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public void setRestricoes(List<RestricaoAlimentar> restricoes) {
        this.restricoes = restricoes;
    }
}
