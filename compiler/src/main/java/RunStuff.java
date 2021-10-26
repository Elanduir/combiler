import Scanner.Base;
import Scanner.LexicalAnalysisException;
import Scanner.LexicalAnalysisScanner;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class RunStuff {
    public static void main(String[] args) throws LexicalAnalysisException {
        LexicalAnalysisScanner scanner = new LexicalAnalysisScanner();
        ClassLoader classLoader = RunStuff.class.getClassLoader();
        String stringPath = Objects.requireNonNull(classLoader.getResource("testCode.txt")).getPath().replace("/", "\\").replace("%20", " ").substring(1);
        Path filePath = Path.of(stringPath);
        List<Base> tokenList = scanner.analyze(filePath);
        System.out.println(tokenList);


    }
}
