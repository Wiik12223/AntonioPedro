import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TelaLojaBicicletas extends JFrame {
    //private JSpinner aroSpinner;
    //private JSpinner precoSpinner;
    //private JSpinner quantidadeEstoqueSpinner;
    private ActionListener listener;
    private JButton btnCadastrar;
    private JButton btnAtualizar;
    private JList<String> bicicletasList;
    private DefaultListModel<String> bicicletasListModel;
    private JLabel imagemLabel;
    private JScrollPane scrollPane;
    private JButton botaoVender;
    private JButton botaoExcluir;
    private JTextField textFieldNomeCliente;
    private JSpinner spinnerQuantidadeVenda;
    private JTextField textFieldMarca;
    private JTextField textFieldAro;
    private JTextField textFieldPreco;
    private JTextField textFieldQuantidadeEstoque;

    private List<Bicicleta> bicicletas;
    private List<String> marcasBicicletas;
    public char[] getListener;

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

        btnCadastrar = new JButton("Cadastrar");
        btnAtualizar = new JButton("Atualizar");
        botaoVender = new JButton("Vender");
        botaoExcluir = new JButton("Excluir");
        //aroSpinner = new JSpinner();
        //precoSpinner = new JSpinner();
        //quantidadeEstoqueSpinner = new JSpinner();

        textFieldNomeCliente = new JTextField();
        spinnerQuantidadeVenda = new JSpinner();

        textFieldMarca = new JTextField();
        textFieldAro = new JTextField();
        textFieldPreco = new JTextField();
        textFieldQuantidadeEstoque = new JTextField();

        // Layout da tela
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.WEST);
        add(imagemLabel, BorderLayout.CENTER);

        JPanel vendaPanel = new JPanel(new BorderLayout());
        vendaPanel.add(new JLabel("Nome do Cliente: "), BorderLayout.WEST);
        vendaPanel.add(textFieldNomeCliente, BorderLayout.CENTER);
        vendaPanel.add(new JLabel("Quantidade: "), BorderLayout.SOUTH);
        vendaPanel.add(spinnerQuantidadeVenda, BorderLayout.EAST);
        vendaPanel.add(botaoVender, BorderLayout.NORTH);
        vendaPanel.add(botaoExcluir, BorderLayout.SOUTH);

        add(vendaPanel, BorderLayout.SOUTH);

        JPanel cadastroPanel = new JPanel(new GridLayout(5, 2));
        cadastroPanel.add(new JLabel("Marca: "));
        cadastroPanel.add(textFieldMarca);
        cadastroPanel.add(new JLabel("Aro: "));
        cadastroPanel.add(textFieldAro);
        cadastroPanel.add(new JLabel("Preço: "));
        cadastroPanel.add(textFieldPreco);
        cadastroPanel.add(new JLabel("Quantidade em Estoque: "));
        cadastroPanel.add(textFieldQuantidadeEstoque);
        cadastroPanel.add(btnCadastrar);
        cadastroPanel.add(btnAtualizar);

        add(cadastroPanel, BorderLayout.CENTER);

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

        // Obtém a bicicleta correspondente ao índice selecionado
        Bicicleta bicicleta = bicicletas.get(selectedIndex);
        int aro = bicicleta.getAro();
        int preco = bicicleta.getPreco();
        int quantidadeEstoque = bicicleta.getQuantidadeEstoque();

        // Exibe a mensagem com a marca, aro e preço
        JOptionPane.showMessageDialog(this, "Marca: " + marcaBicicleta + "\nAro: " + aro + "\nPreço: R$" + preco + "\nEstoque: " + quantidadeEstoque);
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

    public void adicionarBotaoExcluirListener(ActionListener listener) {
        botaoExcluir.addActionListener(listener);
    }

    public JTextField getMarcaTextField() {
        return textFieldMarca;
    }

    public int getAroSpinner() {
        return Integer.parseInt(textFieldAro.getText());
    }

    public int getPrecoSpinner() {
        return Integer.parseInt(textFieldPreco.getText());
    }

    public int getQuantidadeEstoqueSpinner() {
        return Integer.parseInt(textFieldQuantidadeEstoque.getText());
    }

    public void limparCamposCadastro() {
        textFieldMarca.setText("");
        textFieldAro.setText("");
        textFieldPreco.setText("");
        textFieldQuantidadeEstoque.setText("");
    }

    public void adicionarBotaoCadastrarListener(ActionListener listener) {
        btnCadastrar.addActionListener(listener);
    }

    public void adicionarBotaoAtualizarListener(ActionListener listener) {
        btnAtualizar.addActionListener(listener);
    }

    public void exibirJanelaCadastro() {
        JFrame janelaCadastro = new JFrame("Cadastro de Bicicleta");
        janelaCadastro.setSize(400, 300);
        janelaCadastro.setLocationRelativeTo(this);

        JPanel painelCadastro = new JPanel(new BorderLayout());
        painelCadastro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        painelFormulario.add(new JLabel("Marca:"));
        painelFormulario.add(textFieldMarca);
        painelFormulario.add(new JLabel("Aro:"));
        painelFormulario.add(textFieldAro);
        painelFormulario.add(new JLabel("Preço:"));
        painelFormulario.add(textFieldPreco);
        painelFormulario.add(new JLabel("Quantidade em Estoque:"));
        painelFormulario.add(textFieldQuantidadeEstoque);

        painelCadastro.add(painelFormulario, BorderLayout.CENTER);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(this.listener);

        painelCadastro.add(botaoCadastrar, BorderLayout.SOUTH);

        janelaCadastro.setContentPane(painelCadastro);
        janelaCadastro.setVisible(true);
    }

    public void exibirJanelaAtualizacao(int selectedIndex) {
        Bicicleta bicicleta = bicicletas.get(selectedIndex);

        JFrame janelaAtualizacao = new JFrame("Atualização de Bicicleta");
        janelaAtualizacao.setSize(400, 300);
        janelaAtualizacao.setLocationRelativeTo(this);

        JPanel painelAtualizacao = new JPanel(new BorderLayout());
        painelAtualizacao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        painelFormulario.add(new JLabel("Marca:"));
        painelFormulario.add(textFieldMarca);
        painelFormulario.add(new JLabel("Aro:"));
        painelFormulario.add(textFieldAro);
        painelFormulario.add(new JLabel("Preço:"));
        painelFormulario.add(textFieldPreco);
        painelFormulario.add(new JLabel("Quantidade em Estoque:"));
        painelFormulario.add(textFieldQuantidadeEstoque);

        textFieldMarca.setText(bicicleta.getMarca());
        textFieldAro.setText(String.valueOf(bicicleta.getAro()));
        textFieldPreco.setText(String.valueOf(bicicleta.getPreco()));
        textFieldQuantidadeEstoque.setText(String.valueOf(bicicleta.getQuantidadeEstoque()));

        painelAtualizacao.add(painelFormulario, BorderLayout.CENTER);

        JButton botaoAtualizar = new JButton("Atualizar");
        botaoAtualizar.addActionListener(this.listener);

        painelAtualizacao.add(botaoAtualizar, BorderLayout.SOUTH);

        janelaAtualizacao.setContentPane(painelAtualizacao);
        janelaAtualizacao.setVisible(true);
    }

    private void adicionarBicicletaNoArquivo(Bicicleta bicicleta) {
        try (FileWriter writer = new FileWriter("bicicletas.txt", true)) {
            String linha = bicicleta.getMarca() + ";" + bicicleta.getAro() + ";" + bicicleta.getPreco() + ";" + bicicleta.getQuantidadeEstoque() + "\n";
            writer.write(linha);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarBicicleta(Bicicleta bicicleta) {
    bicicletas.add(bicicleta);
    adicionarBicicletaNoArquivo(bicicleta); // Chama o método para adicionar no arquivo
    exibirBicicletas(bicicletas); // Atualiza a exibição das bicicletas na interface
}
}
