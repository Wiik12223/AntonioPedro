import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class GestorVendas {
    private int estoqueMinimo;
    private List<Venda> vendas;

    public GestorVendas(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
        vendas = new ArrayList<>();
    }

    public void realizarVenda(Cliente cliente, Bicicleta bicicleta, int quantidade) {
        if (bicicleta.getQuantidadeEstoque() >= quantidade) {
            bicicleta.reduzirEstoque(quantidade);
            Venda venda = new Venda(cliente, bicicleta, quantidade);
            vendas.add(venda);

            if (bicicleta.getQuantidadeEstoque() < estoqueMinimo) {
                JOptionPane.showMessageDialog(null, "Estoque baixo para o produto: " + bicicleta.getMarca());
            }

            JOptionPane.showMessageDialog(null, "Venda realizada:\nCliente: " + cliente.getNome()
                    + "\nBicicleta: " + bicicleta.getMarca() + "\nQuantidade: " + quantidade);
        } else {
            JOptionPane.showMessageDialog(null, "Estoque insuficiente para a venda:\nBicicleta: "
                    + bicicleta.getMarca() + "\nQuantidade disponível: " + bicicleta.getQuantidadeEstoque());
        }
    }
}