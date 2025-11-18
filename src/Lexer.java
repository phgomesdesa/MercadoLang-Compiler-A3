import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public enum TokenType {
        ADICIONAR, VENDER, MOSTRAR, PRECO, ESTOQUE, QUANTIDADE, FATURAMENTO,
        ATUALIZAR, VALOR, REMOVER, LISTAR, ITENS,
        STRING, NUMBER, EOF, UNKNOWN
    }

    public static class Token {
        public TokenType type;
        public String lexeme;
        public int line;

        public Token(TokenType type, String lexeme, int line) {
            this.type = type;
            this.lexeme = lexeme;
            this.line = line;
        }

        @Override
        public String toString() {
            return type + "('" + lexeme + "') linha: " + line;
        }
    }

    private final String input;
    private int pos = 0;
    private int line = 1;

    public Lexer(String input) {
        this.input = input;
    }

    private void skipWhitespace() {
        while (pos < input.length()) {
            char c = input.charAt(pos);
            if (c == ' ' || c == '\r' || c == '\t') {
                pos++;
            } else if (c == '\n') {
                line++;
                pos++;
            } else {
                break;
            }
        }
    }

    public Token nextToken() {
        skipWhitespace();
        if (pos >= input.length()) {
            return new Token(TokenType.EOF, "", line);
        }

        char c = input.charAt(pos);

        if (c == '"') {
            pos++;
            StringBuilder sb = new StringBuilder();
            while (pos < input.length() && input.charAt(pos) != '"') {
                sb.append(input.charAt(pos));
                pos++;
            }
            pos++;
            return new Token(TokenType.STRING, sb.toString(), line);
        }

        if (Character.isDigit(c)) {
            int start = pos;
            while (pos < input.length() &&
                    (Character.isDigit(input.charAt(pos)) || input.charAt(pos) == '.')) {
                pos++;
            }
            String lexeme = input.substring(start, pos);
            return new Token(TokenType.NUMBER, lexeme, line);
        }

        if (Character.isLetter(c)) {
            int start = pos;
            while (pos < input.length() && Character.isLetter(input.charAt(pos))) {
                pos++;
            }
            String lexeme = input.substring(start, pos).toUpperCase();
            switch (lexeme) {
                    case "ADICIONAR":
                        return new Token(TokenType.ADICIONAR, lexeme, line);
                    case "VENDER":
                        return new Token(TokenType.VENDER, lexeme, line);
                    case "MOSTRAR":
                        return new Token(TokenType.MOSTRAR, lexeme, line);
                    case "PRECO":
                        return new Token(TokenType.PRECO, lexeme, line);
                    case "ESTOQUE":
                        return new Token(TokenType.ESTOQUE, lexeme, line);
                    case "QUANTIDADE":
                        return new Token(TokenType.QUANTIDADE, lexeme, line);
                    case "FATURAMENTO":
                        return new Token(TokenType.FATURAMENTO, lexeme, line);
                    case "ATUALIZAR":
                        return new Token(TokenType.ATUALIZAR, lexeme, line);
                    case "VALOR":
                        return new Token(TokenType.VALOR, lexeme, line);
                    case "REMOVER":
                        return new Token(TokenType.REMOVER, lexeme, line);
                    case "LISTAR":
                        return new Token(TokenType.LISTAR, lexeme, line);
                    case "ITENS":
                        return new Token(TokenType.ITENS, lexeme, line);
                    default:
                        return new Token(TokenType.UNKNOWN, lexeme, line);
                }
            }

            pos++;
            return new Token(TokenType.UNKNOWN, String.valueOf(c), line);
        }

        public List<Token> tokenizeAll () {
            List<Token> tokens = new ArrayList<>();
            Token t;
            do {
                t = nextToken();
                tokens.add(t);
            } while (t.type != TokenType.EOF);
            return tokens;
        }
    }

