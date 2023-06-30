import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class TelaLojaBicicletas extends JFrame {
    private JList<String> bicicletasList;
    private DefaultListModel<String> bicicletasListModel;
    private JLabel imagemLabel;
    private JScrollPane scrollPane;
    private JButton botaoVender;
    private JTextField textFieldNomeCliente;
    private JSpinner spinnerQuantidadeVenda;

    private List<Bicicleta> bicicletas;
    private List<String> marcasBicicletas;
    private List<String> caminhosImagens;

    public TelaLojaBicicletas() {
        // Configurações da janela
        setTitle("Loja de Bicicletas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Componentes da tela
        bicicletasListModel = new DefaultListModel<>();
        bicicletasList = new JList<>(bicicletasListModel);
        scrollPane = new JScrollPane(bicicletasList);

        imagemLabel = new JLabel();
        imagemLabel.setHorizontalAlignment(JLabel.CENTER);

        botaoVender = new JButton("Vender");
        textFieldNomeCliente = new JTextField();
        spinnerQuantidadeVenda = new JSpinner();

        // Layout da tela
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.WEST);
        add(imagemLabel, BorderLayout.CENTER);

        JPanel vendaPanel = new JPanel(new BorderLayout());
        vendaPanel.add(new JLabel("Nome do Cliente: "), BorderLayout.WEST);
        vendaPanel.add(textFieldNomeCliente, BorderLayout.CENTER);
        vendaPanel.add(new JLabel("Quantidade: "), BorderLayout.SOUTH);
        vendaPanel.add(spinnerQuantidadeVenda, BorderLayout.EAST);
        vendaPanel.add(botaoVender, BorderLayout.SOUTH);

        add(vendaPanel, BorderLayout.SOUTH);

        // Evento de clique na lista de bicicletas
        bicicletasList.addListSelectionListener(e -> {
            int selectedIndex = bicicletasList.getSelectedIndex();
            if (selectedIndex >= 0) {
                exibirImagem(selectedIndex);
            }
        });

        // Inicializa as listas
        bicicletas = new ArrayList<>();
        marcasBicicletas = new ArrayList<>();
        caminhosImagens = new ArrayList<>();
    }

    public void exibirBicicletas(List<Bicicleta> bicicletas) {
        this.bicicletas = bicicletas;
        bicicletasListModel.clear();
        marcasBicicletas.clear();
        for (Bicicleta bicicleta : bicicletas) {
            String marcaBicicleta = bicicleta.getMarca();
            bicicletasListModel.addElement(marcaBicicleta);
            marcasBicicletas.add(marcaBicicleta);
        }
    }

    public void exibirImagem(int selectedIndex) {
        String marcaBicicleta = marcasBicicletas.get(selectedIndex);
        String caminhoImagem = caminhosImagens.get(selectedIndex);
        ImageIcon imagemIcon = new ImageIcon(caminhoImagem);
        Image imagem = imagemIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        imagemLabel.setIcon(new ImageIcon(imagem));

        // Obtém a bicicleta correspondente ao índice selecionado
        Bicicleta bicicleta = bicicletas.get(selectedIndex);
        int aro = bicicleta.getAro();
        int preco = bicicleta.getPreco();
        int quantidadeEstoque = bicicleta.getQuantidadeEstoque();

        // Exibe a mensagem com a marca, aro e preço
        JOptionPane.showMessageDialog(this, "Marca: " + marcaBicicleta + "\nAro: " + aro + "\nPreço: R$" + preco + "\nEstoque: " + quantidadeEstoque);
    }

    public void adicionarImagemBicicleta(String marcaBicicleta, String caminhoImagem) {
        caminhosImagens.add(caminhoImagem);
    }

    public JButton getBotaoVender() {
        return botaoVender;
    }

    public String getNomeCliente() {
        return textFieldNomeCliente.getText();
    }

    public int getQuantidadeVenda() {
        return (int) spinnerQuantidadeVenda.getValue();
    }

    public JList<String> getBicicletasList() {
        return bicicletasList;
    }

    public void adicionarBotaoVenderListener(ActionListener listener) {
        botaoVender.addActionListener(listener);
    }
}