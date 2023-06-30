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

    public void setAro(int aro) {
        this.aro = aro;
    }

    public void setPreco(int preco){
        this.preco = preco;
    }

    public void setMarca(String marca){
        this.marca = marca;
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

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void reduzirEstoque(int quantidade) {
        quantidadeEstoque -= quantidade;
    }

    @Override
    public String toString() {
        return  "Marca: " + marca +
                ", Aro: " + aro +
                ", Preço: " + preco +
                ", Quantidade em estoque: " + quantidadeEstoque;
    }
}