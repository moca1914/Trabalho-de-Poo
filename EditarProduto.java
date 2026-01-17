package front;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Produto;
import modelo.RestricaoAlimentar;

public class EditarProduto extends JFrame {

    private JTextField txtNome;
    private JTextField txtPreco;
    private JSpinner spinnerTempo;
    private JCheckBox chkSemGluten, chkSemLactose, chkVegano;

    private Produto produto;

    public EditarProduto(Produto produto) {
        this.produto = produto;
        initialize();
        carregarDados();
    }

    private void initialize() {
        setTitle("Editar Produto");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblNome = new JLabel("NOME:");
        lblNome.setBounds(10, 10, 80, 20);
        getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 10, 250, 20);
        getContentPane().add(txtNome);

        JLabel lblPreco = new JLabel("PREÇO:");
        lblPreco.setBounds(10, 40, 80, 20);
        getContentPane().add(lblPreco);

        txtPreco = new JTextField();
        txtPreco.setBounds(100, 40, 100, 20);
        getContentPane().add(txtPreco);

        JLabel lblTempo = new JLabel("TEMPO:");
        lblTempo.setBounds(10, 70, 80, 20);
        getContentPane().add(lblTempo);

        spinnerTempo = new JSpinner();
        spinnerTempo.setBounds(100, 70, 60, 20);
        getContentPane().add(spinnerTempo);

        chkSemGluten = new JCheckBox("Sem Glúten");
        chkSemGluten.setBounds(10, 110, 120, 20);
        getContentPane().add(chkSemGluten);

        chkSemLactose = new JCheckBox("Sem Lactose");
        chkSemLactose.setBounds(140, 110, 120, 20);
        getContentPane().add(chkSemLactose);

        chkVegano = new JCheckBox("Vegano");
        chkVegano.setBounds(270, 110, 100, 20);
        getContentPane().add(chkVegano);

        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.setBounds(250, 200, 100, 30);
        btnSalvar.addActionListener(e -> salvar());
        getContentPane().add(btnSalvar);
    }

    private void carregarDados() {
        txtNome.setText(produto.getNome());
        txtPreco.setText(String.valueOf(produto.getPreco()));
        spinnerTempo.setValue(produto.getTempoPreparo());

        chkSemGluten.setSelected(produto.getRestricoes().contains(RestricaoAlimentar.SEM_GLUTEN));
        chkSemLactose.setSelected(produto.getRestricoes().contains(RestricaoAlimentar.SEM_LACTOSE));
        chkVegano.setSelected(produto.getRestricoes().contains(RestricaoAlimentar.VEGANO));
    }

    private void salvar() {
        try {
            produto.setNome(txtNome.getText());
            produto.setPreco(Double.parseDouble(txtPreco.getText()));
            produto.setTempoPreparo((int) spinnerTempo.getValue());

            List<RestricaoAlimentar> restricoes = new ArrayList<>();

            if (chkSemGluten.isSelected())
                restricoes.add(RestricaoAlimentar.SEM_GLUTEN);
            if (chkSemLactose.isSelected())
                restricoes.add(RestricaoAlimentar.SEM_LACTOSE);
            if (chkVegano.isSelected())
                restricoes.add(RestricaoAlimentar.VEGANO);

            produto.setRestricoes(restricoes);

            JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar alterações.");
        }
    }
}
