import ParseTable.ParseTableCreator;
import Scanner.Base;
import Scanner.LexicalAnalysisException;
import Scanner.LexicalAnalysisScanner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class RunStuff {
    public static void main(String[] args) throws LexicalAnalysisException, IOException, URISyntaxException {

        Long x = 9223372036854775806L;
        Long y = x/2;
        System.out.println(y);
        /*
        LexicalAnalysisScanner scanner = new LexicalAnalysisScanner();
        ClassLoader classLoader = RunStuff.class.getClassLoader();
        Path onMac = Path.of("/Users/fabian/IdeaProjects/cpib-compiler-ph-fh/compiler/src/main/resources/testCode.txt");
        String stringPath = Objects.requireNonNull(classLoader.getResource("testCode.txt")).getPath().replace("/", "\\").replace("%20", " ").substring(1);
        Path filePath = Path.of(stringPath);
        List<Base> tokenList = scanner.analyze(onMac);
        System.out.println(tokenList);

         */

        ParseTableCreator parseTableCreator = new ParseTableCreator();


    }
}
