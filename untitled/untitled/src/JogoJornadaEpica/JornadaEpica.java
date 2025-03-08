import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Random;

class Aliado {
    private String nome;
    private int vida;
    private int vidaMaxima;
    private int dano;

    public Aliado(String nome, int vida, int dano) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.dano = dano;
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0) this.vida = 0;
    }

    public void restaurarVida() {
        this.vida = this.vidaMaxima;
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }
    public int getDano() { return dano; }
    public void setDano(int dano) { this.dano = dano; }
}

class Bag {
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

    public Map<String, Integer> getItens() { return itens; }
    public int getBerries() { return berries; }
    
    public void mostrarItens() {
        System.out.println("\nItens na Bag:");
        itens.forEach((item, qtd) -> System.out.println(qtd + "x " + item));
    }
}

class Caracteristicas {
    private int bonusVida;
    private int bonusAtaque;
    private int bonusSorte;

    public Caracteristicas(String raca) {
        switch (raca.toLowerCase()) {
            case "humano":
                bonusVida = 10;
                bonusAtaque = 10;
                bonusSorte = 10;
                break;
            case "homem peixe":
                bonusVida = 10;
                bonusAtaque = 15;
                bonusSorte = 0;
                break;
            case "mink":
                bonusVida = 10;
                bonusAtaque = 0;
                bonusSorte = 15;
                break;
            case "gigante":
                bonusVida = 15;
                bonusAtaque = 20;
                bonusSorte = -5;
                break;
            default:
                bonusVida = 0;
                bonusAtaque = 0;
                bonusSorte = 0;
        }
    }

    public int getBonusVida() { return bonusVida; }
    public int getBonusAtaque() { return bonusAtaque; }
    public int getBonusSorte() { return bonusSorte; }
}

class Personagem {
    private String nome;
    private String raca;
    private String classe;
    private int vida;
    private int vidaMaxima;
    private int dano;
    private int experiencia;
    private int level;
    private int sorte;
    private Map<String, Integer> equipamentos = new HashMap<>();

    public Personagem(String nome, String raca, String classe, int vida, int dano) {
        this.nome = nome;
        this.raca = raca;
        this.classe = classe;

        Caracteristicas caracteristicas = new Caracteristicas(raca);
        this.vida = vida + caracteristicas.getBonusVida();
        this.vidaMaxima = this.vida;
        this.dano = dano + caracteristicas.getBonusAtaque();
        this.sorte = caracteristicas.getBonusSorte();

        this.experiencia = 0;
        this.level = 1;
    }

    public void equiparItem(String item, int bonus) {
        equipamentos.put(item, bonus);
        System.out.println(item + " equipado! Bônus: +" + bonus);
    }

    public int calcularBerriesGanhos(int baseBerries) {
        return (int)(baseBerries * (1.0 + (sorte + getBonusEquipamentos())/100.0));
    }

    private int getBonusEquipamentos() {
        return equipamentos.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0) this.vida = 0;
    }

    public void restaurarVida() {
        this.vida = vidaMaxima;
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }
    public int getDano() { 
        int danoBase = dano 
            + equipamentos.getOrDefault("Wado Ichimonji", 0) 
            + equipamentos.getOrDefault("Tonfas de Sanji", 0)
            + equipamentos.getOrDefault("Espada Shusui", 0)
            + equipamentos.getOrDefault("Punhos de Marimheiro", 0)
            + equipamentos.getOrDefault("Titanic Captain's Cannon", 0);

        switch (classe) {
            case "Filho do Mar": 
                danoBase += 20; 
                break;
            case "Caçador de Piratas": 
                danoBase += 2 * level; 
                break;
        }
        return danoBase;
    }

    public void setDano(int dano) { this.dano = dano; }
    public int getExperiencia() { return experiencia; }
    public int getSorte() { 
        int sorteTotal = sorte 
            + equipamentos.getOrDefault("Chapéu de Palha", 0)
            + equipamentos.getOrDefault("Violino de Brook", 0)
            + equipamentos.getOrDefault("Mapa do Novo Mundo", 0);

        if ("Sortudo".equals(classe)) {
            sorteTotal += 20;
        }
        return sorteTotal;
    }
    public int getDefesa() {
        int defesa = equipamentos.getOrDefault("Clima-Tact", 0)
            + equipamentos.getOrDefault("Roupa de Batalha de Franky", 0)
            + equipamentos.getOrDefault("Armadura de Germa 66", 0);

        if ("Fã do Luffy".equals(classe)) {
            defesa += 20;
        }
        return defesa;
    }

    public void ganharExperiencia(int xp) {
        this.experiencia += xp;
        while (experiencia >= 100 && level < 10) {
            experiencia -= 100;
            level++;
            int aumentoVida = 20 + (sorte / 2);
            vidaMaxima += aumentoVida;
            vida = vidaMaxima; // Restaura a vida ao máximo ao subir de nível
            dano += 10 + (sorte / 2);
            System.out.println(nome + " subiu para o nível " + level + "! Vida +" + aumentoVida + ", Dano +" + (10 + (sorte / 2)) + "!");
        }
    }

    // Método adicionado para corrigir o erro
    public int getVidaMaxima() {
        return vidaMaxima;
    }
}

class Inimigo {
    private static final Map<String, Integer> DIFICULDADE = new HashMap<>();
    static {
        DIFICULDADE.put("East Blue", 1);
        DIFICULDADE.put("Alabasta", 2);
        DIFICULDADE.put("Skypiea", 3);
        DIFICULDADE.put("Water 7", 4);
        DIFICULDADE.put("Thriller Bark", 5);
        DIFICULDADE.put("Marineford", 6);
        DIFICULDADE.put("Dressrosa", 7);
        DIFICULDADE.put("Wano", 8);
    }

    private String nome;
    private int vida;
    private int dano;
    private int experiencia;
    private String habilidade;
    private String regiao;

    public Inimigo(String regiao, String nome, int nivel) {
        this.regiao = regiao;
        this.nome = nome;
        int multiplicador = DIFICULDADE.get(regiao);
        
        this.vida = 50 * multiplicador + (nivel * 20);
        this.dano = 10 * multiplicador + (nivel * 5);
        this.experiencia = 20 * multiplicador + (nivel * 10);
        
        this.habilidade = definirHabilidade(nome);
    }

    private String definirHabilidade(String nome) {
        switch(nome) {
            case "Arlong": return "Dente de Tubarão";
            case "Crocodile": return "Areia Assassina";
            case "Enel": return "Raio de 200 Milhões de Volts";
            case "Lucci": return "Rokuougan";
            case "Moria": return "Cortador de Sombras";
            case "Doflamingo": return "Linha de Corte";
            case "Big Mom": return "Raio de Napoleon";
            case "Kaido": return "Bafo do Dragão";
            default: return "Ataque Básico";
        }
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getDano() { return dano; }
    public int getExperiencia() { return experiencia; }
    public String getHabilidade() { return habilidade; }

    public void receberDano(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0;
    }
}

class Batalha {
    public static void batalhar(Personagem personagem, Aliado aliado, Inimigo inimigo, Bag bag, Scanner scanner) {
        while (inimigo.getVida() > 0 && (personagem.getVida() > 0 || (aliado != null && aliado.getVida() > 0))) {
            System.out.println("\n=== Turno do Jogador ===");
            System.out.println("Escolha uma ação:");
            System.out.println("1 - Atacar");
            System.out.println("2 - Ver Itens");
            System.out.println("3 - Usar Item");
            System.out.print("Opção: ");
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                // Ataque normal
                inimigo.receberDano(personagem.getDano());
                System.out.println(personagem.getNome() + " causou " + personagem.getDano() + " de dano!");
            } else if (escolha == 2) {
                // Mostrar itens na mochila
                bag.mostrarItens();
            } else if (escolha == 3) {
                // Usar item
                usarItem(personagem, aliado, bag, scanner);
            } else {
                System.out.println("Opção inválida!");
            }

            // Turno do aliado (se existir)
            if (aliado != null && aliado.getVida() > 0) {
                inimigo.receberDano(aliado.getDano());
                System.out.println(aliado.getNome() + " causou " + aliado.getDano() + " de dano!");
            }

            // Turno do inimigo
            if (inimigo.getVida() > 0) {
                int danoFinal = Math.max(inimigo.getDano() - personagem.getDefesa(), 1);
                personagem.receberDano(danoFinal);
                System.out.println(inimigo.getNome() + " usou " + inimigo.getHabilidade() + " e causou " + danoFinal + " de dano!");

                if (aliado != null && aliado.getVida() > 0) {
                    aliado.receberDano(danoFinal);
                    System.out.println(inimigo.getNome() + " atacou " + aliado.getNome() + " e causou " + danoFinal + " de dano!");
                }
            }

            // Exibir status
            System.out.println("\n=== Status ===");
            System.out.println(personagem.getNome() + ": " + personagem.getVida() + " HP");
            if (aliado != null) {
                System.out.println(aliado.getNome() + ": " + aliado.getVida() + " HP");
            }
            System.out.println(inimigo.getNome() + ": " + inimigo.getVida() + " HP");
        }

       if (inimigo.getVida() <= 0) {
    System.out.println("\nVocê derrotou " + inimigo.getNome() + "!");
    personagem.ganharExperiencia(inimigo.getExperiencia());

    String[] recompensas = {
        "Chapéu de Palha", "Wado Ichimonji", "Clima-Tact",
        "Tonfas de Sanji", "Violino de Brook", "Espada Shusui",
        "Armadura de Germa 66", "Mapa do Novo Mundo", "Titanic Captain's Cannon"
    };
    
    String itemRecompensa = recompensas[new Random().nextInt(recompensas.length)];
    bag.adicionarItem(itemRecompensa, 1);
    System.out.println("Você ganhou 1x " + itemRecompensa + "!");

    // Adicionar recompensas em Berries para todos os inimigos
    int baseBerries;
    switch(inimigo.getNome()) {
        case "Kuro": baseBerries = 50000; break;
        case "Krieg": baseBerries = 30000; break;
        case "Arlong": baseBerries = 40000; break;
        case "Mr. 2": baseBerries = 100000; break;
        case "Mr. 1": baseBerries = 60000; break;
        case "Crocodile": baseBerries = 70000; break;
        case "Enel": baseBerries = 150000; break;
        case "Ohm": baseBerries = 80000; break;
        case "Satori": baseBerries = 90000; break;
        case "Lucci": baseBerries = 200000; break;
        case "Kaku": baseBerries = 150000; break;
        case "Blueno": baseBerries = 120000; break;
        case "Moria": baseBerries = 180000; break;
        case "Absalom": baseBerries = 100000; break;
        case "Hogback": baseBerries = 90000; break;
        case "Akainu": baseBerries = 300000; break;
        case "Kizaru": baseBerries = 250000; break;
        case "Aokiji": baseBerries = 220000; break;
        case "Doflamingo": baseBerries = 400000; break;
        case "Pica": baseBerries = 200000; break;
        case "Diamante": baseBerries = 180000; break;
        case "Queen": baseBerries = 500000; break;
        case "King": baseBerries = 300000; break;
        case "Kaido": baseBerries = 250000; break;
        default: baseBerries = 50000; // Recompensa padrão para inimigos não listados
    }

    int berriesGanhos = personagem.calcularBerriesGanhos(baseBerries);
    bag.adicionarBerries(berriesGanhos);
    System.out.println("Você ganhou " + berriesGanhos + " Berries!");

    personagem.restaurarVida();
    if (aliado != null) aliado.restaurarVida();
    System.out.println("A vida do personagem e do aliado foi restaurada!");
} else {
    System.out.println("\nVocê foi derrotado...");
}
    }

   private static void usarItem(Personagem personagem, Aliado aliado, Bag bag, Scanner scanner) {
    System.out.println("\n=== Itens Disponíveis ===");
    Map<String, Integer> itens = bag.getItens();

    if (itens.isEmpty()) {
        System.out.println("Sua mochila está vazia!");
        return; // Corrigido: removido o "s" após o return
    }

    // Exibir itens numerados
    int index = 1;
    Map<Integer, String> itensNumerados = new HashMap<>();
    for (Map.Entry<String, Integer> entry : itens.entrySet()) {
        String item = entry.getKey();
        int quantidade = entry.getValue();
        System.out.println(index + ". " + item + " (" + quantidade + "x)");
        itensNumerados.put(index, item);
        index++;
    }

    System.out.print("Escolha o número do item que deseja usar (ou 0 para cancelar): ");
    int escolha = scanner.nextInt();

    if (escolha == 0) {
        System.out.println("Voltando ao menu de batalha...");
        return;
    }

    if (!itensNumerados.containsKey(escolha)) {
        System.out.println("Opção inválida!");
        return;
    }

    String itemEscolhido = itensNumerados.get(escolha);

    // Verificar o tipo de item e aplicar efeitos
    switch (itemEscolhido) {
        case "Chapéu de Palha":
            System.out.println("Você usou o Chapéu de Palha! Sorte aumentada.");
            personagem.equiparItem("Chapéu de Palha", 10);
            break;
        case "Clima-Tact":
            System.out.println("Você usou o Clima-Tact! Defesa aumentada.");
            personagem.equiparItem("Clima-Tact", 15);
            break;
        case "Tonfas de Sanji":
            System.out.println("Você usou as Tonfas de Sanji! Dano aumentado.");
            personagem.equiparItem("Tonfas de Sanji", 20);
            break;
        case "Violino de Brook":
            System.out.println("Você usou o Violino de Brook! Sorte aumentada.");
            personagem.equiparItem("Violino de Brook", 10);
            break;
        case "Poção de Cura Pequena":
            int curaPequena = 20;
            int novaVidaPequena = personagem.getVida() + curaPequena;
            personagem.setVida(Math.min(novaVidaPequena, personagem.getVidaMaxima()));
            System.out.println("Você usou uma Poção de Cura Pequena e recuperou " + curaPequena + " HP!");
            break;
        case "Poção de Cura Média":
            int curaMedia = 50;
            int novaVidaMedia = personagem.getVida() + curaMedia;
            personagem.setVida(Math.min(novaVidaMedia, personagem.getVidaMaxima()));
            System.out.println("Você usou uma Poção de Cura Média e recuperou " + curaMedia + " HP!");
            break;
        case "Poção de Cura Grande":
            int curaGrande = 100;
            int novaVidaGrande = personagem.getVida() + curaGrande;
            personagem.setVida(Math.min(novaVidaGrande, personagem.getVidaMaxima()));
            System.out.println("Você usou uma Poção de Cura Grande e recuperou " + curaGrande + " HP!");
            break;
        default:
            System.out.println("Este item não tem efeito especial.");
            break;
    }

    // Remover o item da mochila após o uso
    bag.removerItem(itemEscolhido, 1);
}
}
class Exploracao {
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
    itens.put("8", new Object[]{150000, 15, "Mapa do Novo Mundo"});
    itens.put("9", new Object[]{500000, 40, "Titanic Captain's Cannon"});
    itens.put("10", new Object[]{1000, 20, "Poção de Cura Pequena"});
    itens.put("11", new Object[]{2500, 50, "Poção de Cura Média"});
    itens.put("12", new Object[]{5000, 100, "Poção de Cura Grande"});

    itens.forEach((key, valores) -> {
        int preco = (int) valores[0] * (ilhaAtual + 1);
        int bonus = (int) valores[1];
        String tipo = ((String) valores[2]).contains("Armadura") ? "Defesa" :
                      ((String) valores[2]).contains("Espada") || ((String) valores[2]).contains("Cannon") ? "Dano" :
                      ((String) valores[2]).contains("Poção") ? "Cura" : "Sorte";

        System.out.printf("%s. %s - %,d Berries (%s +%d)\n", key, valores[2], preco, tipo, bonus);
    });

    System.out.println("Seus Berries: " + bag.getBerries());

    while (true) {
        System.out.print("\nEscolha um item (1-12) ou 0 para sair: ");
        String escolha = scanner.next();

        if (escolha.equals("0")) break;

        if (itens.containsKey(escolha)) {
            Object[] item = itens.get(escolha);
            comprarItem(personagem, bag, scanner, (String) item[2], (int) item[0] * (ilhaAtual + 1), (int) item[1]);
        } else {
            System.out.println("Opção inválida!");
        }
    }
}

    private static void comprarItem(Personagem p, Bag bag, Scanner scanner, String item, int preco, int bonus) {
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

class JornadaEpica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Criação de Personagem ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        // Seleção de Raça (existente)
        System.out.println("Escolha sua raça:");
        System.out.println("1 - Humano");
        System.out.println("2 - Homem-Peixe");
        System.out.println("3 - Mink");
        System.out.println("4 - Gigante");
        System.out.print("Opção: ");
        int racaOpcao = scanner.nextInt();
        scanner.nextLine();
        
        String raca;
        switch (racaOpcao) {
            case 1: raca = "humano"; break;
            case 2: raca = "homem peixe"; break;
            case 3: raca = "mink"; break;
            case 4: raca = "gigante"; break;
            default: raca = "humano";
        }
        
        // NOVO: Seleção de Classe
        System.out.println("\nEscolha sua classe:");
        System.out.println("1 - Filho do Mar (Mera Mera No Mi)");
        System.out.println("2 - Caçador de Piratas");
        System.out.println("3 - Fã do Luffy (Bari Bari No Mi)");
        System.out.println("4 - Sortudo (Raki Raki No Mi)");
        System.out.print("Opção: ");
        int classeOpcao = scanner.nextInt();
        scanner.nextLine();
        
        String classe;
        switch (classeOpcao) {
            case 1: classe = "Filho do Mar"; break;
            case 2: classe = "Caçador de Piratas"; break;
            case 3: classe = "Fã do Luffy"; break;
            case 4: classe = "Sortudo"; break;
            default: classe = "Filho do Mar";
        }
        
        // Passar a classe para o construtor
        Personagem personagem = new Personagem(nome, raca, classe, 100, 15);
        Bag bag = new Bag(100);
        
        Exploracao.explorar(personagem, null, bag, scanner);
        scanner.close();
    }
}