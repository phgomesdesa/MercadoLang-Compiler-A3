import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String caminho = "D:\\Projects\\untitled\\src\\mercado.txt";

        try {
            String codigo = new String(Files.readAllBytes(Paths.get(caminho)));

            Lexer lexer = new Lexer(codigo);
            Parser parser = new Parser(lexer);

            List<Ast.Stmt> comandos = parser.parse();

            Semantic semantic = new Semantic();
            semantic.analyzeAndRun(comandos);

        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
