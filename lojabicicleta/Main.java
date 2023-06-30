import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        LojaBicicletas loja = new LojaBicicletas();
        String nomeArquivo = "bicicletas.txt";
        String diretorioAtual = System.getProperty("user.dir");
        loja.carregarBicicletasDeArquivo(diretorioAtual, nomeArquivo);

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
        });
    }
}