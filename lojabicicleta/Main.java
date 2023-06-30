import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
    LojaBicicletas loja = new LojaBicicletas();
    String nomeArquivo = "bicicletas.txt";
    String diretorioAtual = System.getProperty("user.dir");
    loja.carregarBicicletasDeArquivo(diretorioAtual, nomeArquivo);

    GestorVendas gestorVendas = new GestorVendas(5); // Defina o estoque mínimo desejado

    SwingUtilities.invokeLater(() -> {
        TelaLojaBicicletas tela = new TelaLojaBicicletas();
        tela.exibirBicicletas(loja.getBicicletas());

        for (Bicicleta bicicleta : loja.getBicicletas()) {
            String marcaBicicleta = bicicleta.getMarca();
            String nomeImagem = marcaBicicleta + ".jpg";
            String caminhoImagem = diretorioAtual + File.separator + "imagens" + File.separator + nomeImagem;
            tela.adicionarImagemBicicleta(marcaBicicleta, caminhoImagem);
        }

        tela.setVisible(true);

        tela.adicionarBotaoVenderListener(e -> {
            int selectedIndex = tela.getBicicletasList().getSelectedIndex();
            if (selectedIndex >= 0) {
                Bicicleta bicicletaSelecionada = loja.getBicicletas().get(selectedIndex);
                Cliente cliente = new Cliente(tela.getNomeCliente());

                gestorVendas.realizarVenda(cliente, bicicletaSelecionada, tela.getQuantidadeVenda());
            }
        });
        tela.adicionarBotaoCadastrarListener(e -> {
            String marca = tela.getMarcaTextField().getText();
            int aro = (int) tela.getAroSpinner();
            int preco = tela.getPrecoSpinner();
            int quantidadeEstoque = (int) tela.getQuantidadeEstoqueSpinner();

            Bicicleta bicicleta = new Bicicleta(marca, aro, preco, quantidadeEstoque);
            loja.adicionarBicicleta(bicicleta);
            loja.salvarBicicletas("bicicletas.txt");
            tela.exibirBicicletas(loja.getBicicletas());
            tela.limparCamposCadastro();
        });

        tela.adicionarBotaoAtualizarListener(e -> {
            int selectedIndex = tela.getBicicletasList().getSelectedIndex();
            if (selectedIndex >= 0) {
                Bicicleta bicicletaSelecionada = loja.getBicicletas().get(selectedIndex);

                String marca = tela.getMarcaTextField().getText();
                int aro = (int) tela.getAroSpinner();
                int preco = (int) tela.getPrecoSpinner();
                int quantidadeEstoque = (int) tela.getQuantidadeEstoqueSpinner();

                bicicletaSelecionada.setMarca(marca);
                bicicletaSelecionada.setAro(aro);
                bicicletaSelecionada.setPreco(preco);
                bicicletaSelecionada.setQuantidadeEstoque(quantidadeEstoque);

                tela.exibirBicicletas(loja.getBicicletas());
                tela.limparCamposCadastro();
            }
        });

        tela.adicionarBotaoExcluirListener(e -> {
            int selectedIndex = tela.getBicicletasList().getSelectedIndex();
            if (selectedIndex >= 0) {
                Bicicleta bicicletaSelecionada = loja.getBicicletas().get(selectedIndex);
                loja.removerBicicleta(bicicletaSelecionada);

                tela.exibirBicicletas(loja.getBicicletas());
                tela.limparCamposCadastro();
            }
        });
    });
}
}