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
        });
    }
}