import java.util.List;

public class Semantic {
    private final SymbolTable symbols = new SymbolTable();
    private double revenue = 0.0;
    private int executedInstructions = 0;

    public void analyzeAndRun(List<Ast.Stmt> statements) {

        for (Ast.Stmt stmt : statements) {
            executedInstructions++;

            if (stmt instanceof Ast.AddItem) {
                Ast.AddItem add = (Ast.AddItem) stmt;

                if (add.price < 0)
                    throw new RuntimeException("Preço não pode ser negativo: " + add.name);

                if (add.stock < 0)
                    throw new RuntimeException("Estoque não pode ser negativo: " + add.name);

                symbols.addItem(add.name, add.price, add.stock);
                System.out.println("Item adicionado: " + add.name);
            }

            else if (stmt instanceof Ast.SellItem) {
                Ast.SellItem sell = (Ast.SellItem) stmt;

                if (!symbols.exists(sell.name))
                    throw new RuntimeException("Item não existe: " + sell.name);

                SymbolTable.Item item = symbols.get(sell.name);

                if (sell.quantity <= 0)
                    throw new RuntimeException("Quantidade inválida para venda");

                if (item.quantity < sell.quantity)
                    throw new RuntimeException("Estoque insuficiente para venda de " + sell.name);

                item.quantity -= sell.quantity;
                double total = item.price * sell.quantity;
                revenue += total;

                System.out.println("Venda: " + sell.quantity + "x " + sell.name + " → R$" + total);
            }

            else if (stmt instanceof Ast.ShowStock) {
                System.out.println("\n=== ESTOQUE ATUAL ===");

                if (symbols.getAll().isEmpty()) {
                    System.out.println("(nenhum item no estoque)");
                } else {
                    for (SymbolTable.Item item : symbols.getAll().values()) {
                        System.out.println(item);
                    }
                }
            }

            else if (stmt instanceof Ast.ShowRevenue) {
                System.out.printf("\n=== FATURAMENTO TOTAL: R$%.2f ===\n", revenue);
            }

            else if (stmt instanceof Ast.UpdatePrice) {
                Ast.UpdatePrice up = (Ast.UpdatePrice) stmt;

                if (!symbols.exists(up.name))
                    throw new RuntimeException("Item não existe para atualizar: " + up.name);

                if (up.newPrice <= 0)
                    throw new RuntimeException("Preço inválido");

                symbols.get(up.name).price = up.newPrice;
                System.out.println("Preço atualizado: " + up.name + " → R$" + up.newPrice);
            }

            else if (stmt instanceof Ast.RemoveItem) {
                Ast.RemoveItem rm = (Ast.RemoveItem) stmt;

                if (!symbols.exists(rm.name))
                    throw new RuntimeException("Item não existe para remover: " + rm.name);

                symbols.getAll().remove(rm.name.toLowerCase());
                System.out.println("Item removido: " + rm.name);
            }

            else if (stmt instanceof Ast.ListItems) {
                System.out.println("\n=== ITENS CADASTRADOS ===");

                if (symbols.getAll().isEmpty()) {
                    System.out.println("(nenhum item cadastrado)");
                } else {
                    for (SymbolTable.Item item : symbols.getAll().values()) {
                        System.out.println(item);
                    }
                }
            }
        }

        System.out.println("\n=== RELATÓRIO DE COMPILAÇÃO ===");
        System.out.println("Instruções executadas: " + executedInstructions);
        System.out.println("Itens cadastrados: " + symbols.getAll().size());
        System.out.printf("Faturamento total: R$%.2f%n", revenue);
        System.out.println("Compilação concluída sem erros.");
    }
}
