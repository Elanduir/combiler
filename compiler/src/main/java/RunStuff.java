import ParseTable.ParseTableCreator;
import Scanner.Base;
import Scanner.LexicalAnalysisException;
import Scanner.LexicalAnalysisScanner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class RunStuff {
    public static void main(String[] args) throws LexicalAnalysisException, IOException, URISyntaxException {
        FileHandler fileHandler = new FileHandler();
        LexicalAnalysisScanner scanner = new LexicalAnalysisScanner();
        List<Base> tokenList = scanner.analyze(fileHandler.getFilePath("testCode.txt"));
        System.out.println(tokenList);

        ParseTableCreator parseTableCreator = new ParseTableCreator();








    }
}
