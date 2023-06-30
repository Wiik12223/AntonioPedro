import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class LojaBicicletas {
    private List<Bicicleta> bicicletas;

    public LojaBicicletas() {
        bicicletas = new ArrayList<>();
    }

    public void adicionarBicicleta(Bicicleta bicicleta) {
        bicicletas.add(bicicleta);
    }

    public void carregarBicicletasDeArquivo(String diretorioAtual, String nomeArquivo) {
        String caminhoArquivo = diretorioAtual + "/" + nomeArquivo;
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 4) {
                    String marca = partes[0].trim();
                    int aro = Integer.parseInt(partes[1].trim());
                    int preco = Integer.parseInt(partes[2].trim());
                    int quantidadeEstoque = Integer.parseInt(partes[3].trim());
                    Bicicleta bicicleta = new Bicicleta(marca, aro, preco, quantidadeEstoque);
                    adicionarBicicleta(bicicleta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Bicicleta> getBicicletas() {
        return bicicletas;
    }
}