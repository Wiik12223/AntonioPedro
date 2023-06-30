class Bicicleta {
    private String marca;
    private int aro, preco;

    public Bicicleta(String marca, int aro, int preco) {
        this.marca = marca;
        this.aro = aro;
        this.preco = preco;
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
}