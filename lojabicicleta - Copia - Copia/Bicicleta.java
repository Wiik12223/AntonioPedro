class Bicicleta {
    private String marca;
    private int aro;
    private int preco;
    private int quantidadeEstoque;

    public Bicicleta(String marca, int aro, int preco, int quantidadeEstoque) {
        this.marca = marca;
        this.aro = aro;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getMarca() {
        return marca;
    }

    public int getAro() {
        return aro;
    }

    public int getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void reduzirEstoque(int quantidade) {
        quantidadeEstoque -= quantidade;
    }
}