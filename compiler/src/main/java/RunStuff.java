import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class RunStuff {
    public static void main(String[] args){
        LexicalAnalysisScanner scanner = new LexicalAnalysisScanner();
        String stringPath = Objects.requireNonNull(RunStuff.class.getResource("testCode.txt")).getPath().replace("/", "\\").replace("%20", " ").substring(1);
        Path filePath = Path.of(stringPath);
        List<Base> tokenList = scanner.analyze(filePath);
        System.out.println(tokenList);
    }
}
