package front;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import modelo.RestricaoAlimentar;
import modelo.Produto;
import modelo.Prato;
import modelo.Sobremesa;
import modelo.Bebida;
import servico.Cardapio;

public class AdicionarProduto extends JFrame {

    private JTextField textField;
    private JTextField textField_1;

    private JCheckBox chkSemGluten;
    private JCheckBox chkSemLactose;
    private JCheckBox chkVegano;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdicionarProduto frame = new AdicionarProduto();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AdicionarProduto() {
        setTitle("Adicionar Produto");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("NOME :");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(10, 13, 58, 20);
        getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(72, 12, 354, 21);
        getContentPane().add(textField);

        JLabel lblNewLabel_1 = new JLabel("PREÇO :");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1.setBounds(10, 43, 65, 20);
        getContentPane().add(lblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.setBounds(82, 43, 106, 23);
        getContentPane().add(textField_1);

        JLabel lblNewLabel_2 = new JLabel("TEMPO DE PREPARO :");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2.setBounds(10, 77, 175, 20);
        getContentPane().add(lblNewLabel_2);

        JSpinner spinner = new JSpinner();
        spinner.setBounds(195, 80, 57, 20);
        getContentPane().add(spinner);

        JLabel lblNewLabel_3 = new JLabel("MIN");
        lblNewLabel_3.setBounds(262, 83, 44, 12);
        getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("CATEGORIA :");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_4.setBounds(10, 107, 106, 20);
        getContentPane().add(lblNewLabel_4);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(
                new DefaultComboBoxModel<>(new String[]{"PRATO", "SOBREMESA", "BEBIDA"}));
        comboBox.setBounds(126, 109, 120, 20);
        getContentPane().add(comboBox);

        chkSemGluten = new JCheckBox("Sem Glúten");
        chkSemGluten.setBounds(10, 133, 120, 20);
        getContentPane().add(chkSemGluten);

        chkSemLactose = new JCheckBox("Sem Lactose");
        chkSemLactose.setBounds(136, 133, 120, 20);
        getContentPane().add(chkSemLactose);

        chkVegano = new JCheckBox("Vegano");
        chkVegano.setBounds(262, 133, 120, 20);
        getContentPane().add(chkVegano);

        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.setForeground(new Color(255, 255, 255));
        btnSalvar.setBackground(new Color(0, 128, 255));
        btnSalvar.setBounds(326, 237, 100, 25);
        getContentPane().add(btnSalvar);

        JButton btnCancelar = new JButton("CANCELAR");
        btnCancelar.setForeground(new Color(255, 255, 255));
        btnCancelar.setBackground(Color.RED);
        btnCancelar.setBounds(10, 237, 100, 25);
        btnCancelar.addActionListener(e ->
                ((JFrame) SwingUtilities.getWindowAncestor(btnCancelar)).dispose()
        );
        getContentPane().add(btnCancelar);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String nome = textField.getText();
                    double preco = Double.parseDouble(textField_1.getText());
                    int tempo = (int) spinner.getValue();
                    String categoria = comboBox.getSelectedItem().toString();

                    List<RestricaoAlimentar> restricoes = obterRestricoesSelecionadas();

                    Produto produto;

                    switch (categoria) {
                        case "PRATO":
                            produto = new Prato(nome, preco, tempo, restricoes);
                            break;
                        case "SOBREMESA":
                            produto = new Sobremesa(nome, preco, tempo, restricoes);
                            break;
                        case "BEBIDA":
                            produto = new Bebida(nome, preco, tempo, restricoes);
                            break;
                        default:
                            throw new IllegalArgumentException("Categoria inválida");
                    }

                    Cardapio cardapio = new Cardapio();
                    cardapio.adicionarProduto(produto);

                    JOptionPane.showMessageDialog(
                            AdicionarProduto.this,
                            "Produto adicionado com sucesso!"
                    );

                    dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            AdicionarProduto.this,
                            "Erro ao salvar produto: " + ex.getMessage(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    private List<RestricaoAlimentar> obterRestricoesSelecionadas() {
        List<RestricaoAlimentar> restricoes = new ArrayList<>();

        if (chkSemGluten.isSelected()) {
            restricoes.add(RestricaoAlimentar.SEM_GLUTEN);
        }
        if (chkSemLactose.isSelected()) {
            restricoes.add(RestricaoAlimentar.SEM_LACTOSE);
        }
        if (chkVegano.isSelected()) {
            restricoes.add(RestricaoAlimentar.VEGANO);
        }
        return restricoes;
    }
}
