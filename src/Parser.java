import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final Lexer lexer;
    private Lexer.Token current;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.current = lexer.nextToken();
    }

    private void advance() {
        current = lexer.nextToken();
    }

    private void expect(Lexer.TokenType type) {
        if (current.type != type) {
            throw new RuntimeException(
                    "Erro de sintaxe. Esperado: " + type +
                            " | Encontrado: " + current.type +
                            " na linha " + current.line
            );
        }
        advance();
    }

    public List<Ast.Stmt> parse() {
        List<Ast.Stmt> lista = new ArrayList<>();

        while (current.type != Lexer.TokenType.EOF) {
            lista.add(parseCommand());
        }

        return lista;
    }

    private Ast.Stmt parseCommand() {
        switch (current.type) {

            case ADICIONAR:
                return parseAdd();

            case VENDER:
                return parseSell();

            case MOSTRAR:
                return parseShow();

            // ⭐ NOVOS COMANDOS ⭐
            case ATUALIZAR:
                return parseUpdate();

            case REMOVER:
                return parseRemove();

            case LISTAR:
                return parseList();

            default:
                throw new RuntimeException(
                        "Comando inválido: " + current.lexeme +
                                " na linha " + current.line
                );
        }
    }

    private Ast.Stmt parseAdd() {
        advance(); // ADICIONAR

        if (current.type != Lexer.TokenType.STRING)
            throw new RuntimeException("Esperado nome do item (string)");

        String name = current.lexeme;
        advance();

        expect(Lexer.TokenType.PRECO);

        if (current.type != Lexer.TokenType.NUMBER)
            throw new RuntimeException("Esperado número (preço)");

        double price = Double.parseDouble(current.lexeme);
        advance();

        expect(Lexer.TokenType.ESTOQUE);

        if (current.type != Lexer.TokenType.NUMBER)
            throw new RuntimeException("Esperado número (estoque)");

        int stock = Integer.parseInt(current.lexeme);
        advance();

        return new Ast.AddItem(name, price, stock);
    }

    private Ast.Stmt parseSell() {
        advance(); // VENDER

        if (current.type != Lexer.TokenType.STRING)
            throw new RuntimeException("Esperado nome do item");

        String name = current.lexeme;
        advance();

        expect(Lexer.TokenType.QUANTIDADE);

        if (current.type != Lexer.TokenType.NUMBER)
            throw new RuntimeException("Esperado número (quantidade)");

        int qty = Integer.parseInt(current.lexeme);
        advance();

        return new Ast.SellItem(name, qty);
    }

    private Ast.Stmt parseShow() {
        advance(); // MOSTRAR

        if (current.type == Lexer.TokenType.ESTOQUE) {
            advance();
            return new Ast.ShowStock();
        }

        if (current.type == Lexer.TokenType.FATURAMENTO) {
            advance();
            return new Ast.ShowRevenue();
        }

        throw new RuntimeException(
                "MOSTRAR precisa ser seguido de ESTOQUE ou FATURAMENTO"
        );
    }

    private Ast.Stmt parseUpdate() {
        advance(); // ATUALIZAR
        expect(Lexer.TokenType.PRECO);

        if (current.type != Lexer.TokenType.STRING)
            throw new RuntimeException("Esperado nome do item");

        String name = current.lexeme;
        advance();

        expect(Lexer.TokenType.VALOR);

        if (current.type != Lexer.TokenType.NUMBER)
            throw new RuntimeException("Esperado número (novo preço)");

        double newPrice = Double.parseDouble(current.lexeme);
        advance();

        return new Ast.UpdatePrice(name, newPrice);
    }

    private Ast.Stmt parseRemove() {
        advance(); // REMOVER

        if (current.type != Lexer.TokenType.STRING)
            throw new RuntimeException("Esperado nome do item");

        String name = current.lexeme;
        advance();

        return new Ast.RemoveItem(name);
    }

    private Ast.Stmt parseList() {
        advance(); // LISTAR
        expect(Lexer.TokenType.ITENS);
        return new Ast.ListItems();
    }
}