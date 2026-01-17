package front;

import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import servico.Cardapio;
import modelo.Produto;

public class EditarCardapio {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EditarCardapio window = new EditarCardapio();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public EditarCardapio() {
        initialize();
        carregarProdutos();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setBounds(10, 10, 84, 20);
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnVoltar.addActionListener(e ->
                ((JFrame) SwingUtilities.getWindowAncestor(btnVoltar)).dispose()
        );
        frame.getContentPane().add(btnVoltar);

        JButton btnAdicionar = new JButton("ADICIONAR PRODUTO");
        btnAdicionar.setForeground(new Color(255, 255, 255));
        btnAdicionar.setBounds(262, 10, 144, 20);
        btnAdicionar.setBackground(new Color(0, 128, 255));
        btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnAdicionar.addActionListener(e -> new AdicionarProduto().setVisible(true));
        frame.getContentPane().add(btnAdicionar);

        JButton btnRemover = new JButton("EXCLUIR");
        btnRemover.setBounds(197, 225, 94, 28);
        btnRemover.setForeground(Color.WHITE);
        btnRemover.setBackground(Color.RED);
        frame.getContentPane().add(btnRemover);

        JButton btnEditar = new JButton("EDITAR");
        btnEditar.setBounds(312, 225, 94, 28);
        btnEditar.setBackground(new Color(0, 255, 128));
        frame.getContentPane().add(btnEditar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 40, 396, 178);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Nome", "Categoria", "Preço(R$)", "Tempo(min)", "Disponível"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    String.class, String.class, Double.class, Integer.class, Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        scrollPane.setViewportView(table);

        model = (DefaultTableModel) table.getModel();
        
        btnEditar.addActionListener(e -> {

            int linhaSelecionada = table.getSelectedRow();

            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Selecione um produto para editar.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            Cardapio cardapio = new Cardapio();
            Produto produtoSelecionado = cardapio.getTodos().get(linhaSelecionada);

            EditarProduto telaEditar = new EditarProduto(produtoSelecionado);
            telaEditar.setVisible(true);
        });
        
        btnRemover.addActionListener(e -> {

            int linhaSelecionada = table.getSelectedRow();

            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Selecione um produto para excluir.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int confirmacao = JOptionPane.showConfirmDialog(
                    frame,
                    "Tem certeza que deseja excluir este produto?",
                    "Confirmar exclusão",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacao != JOptionPane.YES_OPTION) {
                return;
            }

            Cardapio cardapio = new Cardapio();
            Produto produto = cardapio.getTodos().get(linhaSelecionada);

            cardapio.removerProduto(produto);

            carregarProdutos();

            JOptionPane.showMessageDialog(
                    frame,
                    "Produto excluído com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                carregarProdutos();
            }
        });
    }

    private void carregarProdutos() {
        model.setRowCount(0);

        Cardapio cardapio = new Cardapio();

        for (Produto p : cardapio.getTodos()) {
            model.addRow(new Object[]{
                    p.getNome(),
                    p.getCategoria(),
                    p.getPreco(),
                    p.getTempoPreparo(),
                    p.isDisponivel()
            });
        }
    }
}
