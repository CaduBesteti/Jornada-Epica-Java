import java.util.HashMap;
import java.util.Map;

public class Bag {
    private int capacidade;
    private int espacoOcupado;
    private int berries;
    private Map<String, Integer> itens;

    public Bag(int capacidade) {
        this.capacidade = capacidade;
        this.espacoOcupado = 0;
        this.berries = 0;
        this.itens = new HashMap<>();
    }

    public void adicionarItem(String item, int quantidade) {
        int espacoNecessario = quantidade * 1;
        if (espacoOcupado + espacoNecessario <= capacidade) {
            itens.put(item, itens.getOrDefault(item, 0) + quantidade);
            espacoOcupado += espacoNecessario;
            System.out.println(quantidade + " " + item + "(s) adicionado(s) à bag.");
        } else {
            System.out.println("Espaço insuficiente na bag!");
        }
    }

    public void removerItem(String item, int quantidade) {
        if (itens.containsKey(item) && itens.get(item) >= quantidade) {
            itens.put(item, itens.get(item) - quantidade);
            espacoOcupado -= quantidade * 1;
            if (itens.get(item) == 0) {
                itens.remove(item);
            }
        } else {
            System.out.println("Item não encontrado ou quantidade insuficiente!");
        }
    }

    public void adicionarBerries(int quantidade) {
        berries += quantidade;
        System.out.println(quantidade + " Berries adicionados à bag.");
    }

    public void mostrarItens() {
        System.out.println("\nItens na Bag:");
        itens.forEach((item, qtd) -> System.out.println(qtd + "x " + item));
    }

    // Getters e Setters
    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }
    public int getEspacoOcupado() { return espacoOcupado; }
    public void setEspacoOcupado(int espacoOcupado) { this.espacoOcupado = espacoOcupado; }
    public int getBerries() { return berries; }
    public void setBerries(int berries) { this.berries = berries; }
    public Map<String, Integer> getItens() { return itens; }
    public void setItens(Map<String, Integer> itens) { this.itens = itens; }
}