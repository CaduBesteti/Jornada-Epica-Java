import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Exploracao {
    private static final String[] ILHAS = {
        "East Blue", "Alabasta", "Skypiea", "Water 7",
        "Thriller Bark", "Marineford", "Dressrosa", "Wano"
    };

    private static final String[][] INIMIGOS_POR_ILHA = {
        {"Kuro", "Krieg", "Arlong"},
        {"Mr.2", "Mr. 1", "Crocodile"},
        {"Satori", "Ohm", "Enel"},
        {"Blueno", "Kaku", "Lucci"},
        {"Hogback", "Absalom", "Moria"},
        {"Aokiji", "Kizaru", "Akainu"},
        {"Diamante", "Pica", "Doflamingo"},
        {"Queen", "King", "Kaido"}
    };

    public static void explorar(Personagem personagem, Aliado aliado, Bag bag, Scanner scanner) {
        System.out.println("\n=== Grande Jornada pelo Mundo de One Piece ===");

        for (int i = 0; i < ILHAS.length; i++) {
            String ilha = ILHAS[i];
            System.out.println("\n=== Chegando em " + ilha + " ===");
            System.out.println(getDescricaoIlha(ilha));
            
            for (String nomeInimigo : INIMIGOS_POR_ILHA[i]) {
                Inimigo inimigo = new Inimigo(ilha, nomeInimigo, i+1);
                System.out.println("\nUm novo desafio aparece: " + nomeInimigo + "!");
                Batalha.batalhar(personagem, aliado, inimigo, bag, scanner);
                
                if (personagem.getVida() <= 0) {
                    System.out.println("Game Over! Você não conseguiu completar a jornada.");
                    return;
                }
            }
            
            if (i < ILHAS.length - 1) {
                acessarLoja(personagem, bag, scanner, i);
            }
        }
        
        System.out.println("\nParabéns! Você se tornou o Rei dos Piratas!");
        bag.adicionarItem("One Piece", 1);
        System.out.println("Você encontrou o tesouro lendário!");
    }

private static void acessarLoja(Personagem personagem, Bag bag, Scanner scanner, int ilhaAtual) {
        System.out.println("\n=== Loja do Rayleigh - Nível " + (ilhaAtual + 1) + " ===");
        System.out.println("Itens disponíveis:");

        Map<String, Object[]> itens = new LinkedHashMap<>();
        itens.put("1", new Object[]{50000, 5, "Chapéu de Palha"});
        itens.put("2", new Object[]{120000, 15, "Wado Ichimonji"});
        itens.put("3", new Object[]{80000, 10, "Clima-Tact"});
        itens.put("4", new Object[]{75000, 12, "Tonfas de Sanji"});
        itens.put("5", new Object[]{60000, 8, "Violino de Brook"});
        itens.put("6", new Object[]{300000, 25, "Espada Shusui"});
        itens.put("7", new Object[]{250000, 20, "Armadura de Germa 66"});
        itens.put("8", new Object[]{150000, 20, "Mapa do Novo Mundo"});
        itens.put("9", new Object[]{500000, 40, "Titanic Captain's Cannon"});
        itens.put("10", new Object[]{1000, 20, "Poção de Cura Pequena"});
        itens.put("11", new Object[]{2500, 50, "Poção de Cura Média"});
        itens.put("12", new Object[]{5000, 100, "Poção de Cura Grande"});

        itens.forEach((key, valores) -> {
            int preco = (int) valores[0] * (ilhaAtual + 1);
            int bonus = (int) valores[1];
            String tipo = ((String) valores[2]).contains("Armadura") ? "Defesa" :
                          ((String) valores[2]).contains("Espada") || 
                          ((String) valores[2]).contains("Cannon") ? "Dano" :
                          ((String) valores[2]).contains("Poção") ? "Cura" : "Sorte";

            System.out.printf("%s. %s - %,d Berries (%s +%d)\n", 
                            key, valores[2], preco, tipo, bonus);
        });

        System.out.println("Seus Berries: " + bag.getBerries());

        while (true) {
            System.out.print("\nEscolha um item (1-12) ou 0 para sair: ");
            String escolha = scanner.next();

            if (escolha.equals("0")) break;

            if (itens.containsKey(escolha)) {
                Object[] item = itens.get(escolha);
                comprarItem(personagem, bag, scanner, 
                          (String) item[2], 
                          (int) item[0] * (ilhaAtual + 1), 
                          (int) item[1]);
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    private static void comprarItem(Personagem p, Bag bag, Scanner scanner, 
                                  String item, int preco, int bonus) {
        System.out.print("Quantidade de " + item + ": ");
        int quantidade = scanner.nextInt();
        int custoTotal = quantidade * preco;

        if (bag.getBerries() >= custoTotal) {
            bag.adicionarItem(item, quantidade);
            bag.adicionarBerries(-custoTotal);
            p.equiparItem(item, bonus * quantidade);
        } else {
            System.out.println("Berries insuficientes!");
        }
    }

    private static String getDescricaoIlha(String ilha) {
        switch(ilha) {
            case "East Blue": return "O mar inicial onde tudo começa!";
            case "Alabasta": return "O deserto escaldante esconde segredos antigos!";
            case "Skypiea": return "Ilha flutuante nas nuvens com tecnologia ancestral!";
            case "Water 7": return "Cidade dos estaleiros e CP9!";
            case "Thriller Bark": return "Ilha fantasma assombrada por zumbis!";
            case "Marineford": return "Quartel general da Marinha - prepare-se para guerra!";
            case "Dressrosa": return "Reino de brinquedo sob controle de Doflamingo!";
            case "Wano": return "Terra isolada governada por Kaido!";
            default: return "";
        }
    }
}