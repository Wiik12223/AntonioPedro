import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class LojaBicicletas {
    private List<Bicicleta> bicicletas;

    public LojaBicicletas() {
        bicicletas = new ArrayList<>();
    }

    public void adicionarBicicleta(Bicicleta bicicleta) {
        bicicletas.add(bicicleta);
        mostrarBicicletas();
    }

    public void atualizarBicicleta(Bicicleta bicicleta) {
        // Encontre a bicicleta existente com base na marca, por exemplo
        for (int i = 0; i < bicicletas.size(); i++) {
            Bicicleta b = bicicletas.get(i);
            if (b.getMarca().equals(bicicleta.getMarca())) {
                bicicletas.set(i, bicicleta);
                break;
            }
        }
    }

     public Bicicleta buscarBicicleta(String marca) {
        for (Bicicleta bicicleta : bicicletas) {
            if (bicicleta.getMarca().equals(marca)) {
                return bicicleta;
            }
        }
        return null;
    }

    public void removerBicicleta(Bicicleta bicicleta) {
        bicicletas.remove(bicicleta);
    }
    public void carregarBicicletasDeArquivo(String diretorioAtual, String nomeArquivo) {
        String caminhoArquivo = diretorioAtual + File.separator + nomeArquivo;
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            // Lógica para lidar com o arquivo inexistente
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 4) {
                    String marca = partes[0].trim();
                    int aro;
                    int preco;
                    int quantidadeEstoque;
                    try {
                        aro = Integer.parseInt(partes[1].trim());
                        preco = Integer.parseInt(partes[2].trim());
                        quantidadeEstoque = Integer.parseInt(partes[3].trim());
                    } catch (NumberFormatException e) {
                        // Lógica para lidar com a conversão de número inválido
                        continue;
                    }
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

    public void salvarBicicletas(String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
        for (Bicicleta bicicleta : bicicletas) {
            String linha = bicicleta.getMarca() + ", " + bicicleta.getAro() + ", " + bicicleta.getPreco() + ", " + bicicleta.getQuantidadeEstoque();
            writer.println(linha);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarBicicletas() {
        JFrame frame = new JFrame("Bicicletas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        for (Bicicleta bicicleta : bicicletas) {
            textArea.append(bicicleta.toString() + "\n");
        }

        frame.add(scrollPane);
        frame.setVisible(true);
    }
    
        
}