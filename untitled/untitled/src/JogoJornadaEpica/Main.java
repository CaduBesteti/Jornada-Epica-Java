import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Criação de Personagem ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.println("Escolha sua raça:");
        System.out.println("1 - Humano");
        System.out.println("2 - Homem-Peixe");
        System.out.println("3 - Mink");
        System.out.println("4 - Gigante");
        System.out.print("Opção: ");
        int racaOpcao = scanner.nextInt();
        scanner.nextLine();
        
        String raca = switch (racaOpcao) {
            case 1 -> "humano";
            case 2 -> "homem peixe";
            case 3 -> "mink";
            case 4 -> "gigante";
            default -> "humano";
        };
        
        System.out.println("\nEscolha sua classe:");
        System.out.println("1 - Filho do Mar (Mera Mera No Mi)");
        System.out.println("2 - Caçador de Piratas");
        System.out.println("3 - Fã do Luffy (Bari Bari No Mi)");
        System.out.println("4 - Sortudo (Raki Raki No Mi)");
        System.out.print("Opção: ");
        int classeOpcao = scanner.nextInt();
        scanner.nextLine();
        
        String classe = switch (classeOpcao) {
            case 1 -> "Filho do Mar";
            case 2 -> "Caçador de Piratas";
            case 3 -> "Fã do Luffy";
            case 4 -> "Sortudo";
            default -> "Filho do Mar";
        };
        
        // Correção 1: Usar parênteses () em vez de chaves {}
        // Correção 2: Usar valores numéricos corretos (100 e 15)
        Personagem personagem = new Personagem(
            nome, 
            raca, 
            classe, 
            100,  // Vida base 
            15    // Dano base
        );

        Bag bag = new Bag(100);
        
        Exploracao.explorar(personagem, null, bag, scanner);
        scanner.close();
    }
}