import java.util.Scanner;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class Batalha {
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
                inimigo.receberDano(personagem.getDano());
                System.out.println(personagem.getNome() + " causou " + personagem.getDano() + " de dano!");
            } else if (escolha == 2) {
                bag.mostrarItens();
            } else if (escolha == 3) {
                usarItem(personagem, aliado, bag, scanner);
            } else {
                System.out.println("Opção inválida!");
            }

            if (aliado != null && aliado.getVida() > 0) {
                inimigo.receberDano(aliado.getDano());
                System.out.println(aliado.getNome() + " causou " + aliado.getDano() + " de dano!");
            }

            if (inimigo.getVida() > 0) {
                int danoFinal = Math.max(inimigo.getDano() - personagem.getDefesa(), 1);
                personagem.receberDano(danoFinal);
                System.out.println(inimigo.getNome() + " usou " + inimigo.getHabilidade() + " e causou " + danoFinal + " de dano!");

                if (aliado != null && aliado.getVida() > 0) {
                    aliado.receberDano(danoFinal);
                    System.out.println(inimigo.getNome() + " atacou " + aliado.getNome() + " e causou " + danoFinal + " de dano!");
                }
            }

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
                default: baseBerries = 50000;
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
            return;
        }

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

        if (escolha == 0) return;
        if (!itensNumerados.containsKey(escolha)) {
            System.out.println("Opção inválida!");
            return;
        }

        String itemEscolhido = itensNumerados.get(escolha);

        switch (itemEscolhido) {
            case "Chapéu de Palha":
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

        bag.removerItem(itemEscolhido, 1);
    }
}