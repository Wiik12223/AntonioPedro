class Venda {
    private Cliente cliente;
    private Bicicleta bicicleta;
    private int quantidade;

    public Venda(Cliente cliente, Bicicleta bicicleta, int quantidade) {
        this.cliente = cliente;
        this.bicicleta = bicicleta;
        this.quantidade = quantidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public int getQuantidade() {
        return quantidade;
    }
}