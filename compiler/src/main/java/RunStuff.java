import ParseTable.ParseTableCreator;
import Parser.Parser;
import Scanner.Base;
import Scanner.LexicalAnalysisException;
import Scanner.LexicalAnalysisScanner;
import Parser.GrammerException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class RunStuff {
    public static void main(String[] args) throws LexicalAnalysisException, IOException, URISyntaxException, GrammerException {
        FileHandler fileHandler = new FileHandler();
        LexicalAnalysisScanner scanner = new LexicalAnalysisScanner();
        List<Base> tokenList = scanner.analyze(fileHandler.getFilePath("testCode.txt"));
        System.out.println(tokenList);

        Parser parser = new Parser(tokenList);








    }
}
