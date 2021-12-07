package ParseTable;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ParseTableCreator {
    URL terminalsURL;
    URL ntsURL;
    URL mmURL;

    String terminals;
    String nts;
    String mm;

    String[] rowIndex;
    String[] columnIndex;

    Map<String, Integer> rowLookup;
    Map<String, Integer> columnLookup;

    String[][] tableNoHeader;
    String[][] tableToPrint;

    File outputFile;
    String outputPath;

    List<Info> informations;
    public ParseTableCreator() throws IOException, URISyntaxException {
        ClassLoader loader = ParseTableCreator.super.getClass().getClassLoader();
        terminalsURL = loader.getResource("ParseTable/Terminals.txt");
        ntsURL = loader.getResource("ParseTable/NTS.txt");
        mmURL = loader.getResource("ParseTable/MM.txt");
        outputFile = new File("ParseTable.csv");
        outputPath = System.getProperty("user.dir");
        outputPath += "/compiler/src/main/resources/ParseTable/";
        System.out.println(outputPath);

        terminals = Files.readString(Path.of(terminalsURL.toURI()));
        nts = Files.readString(Path.of(ntsURL.toURI()));
        mm = Files.readString(Path.of(mmURL.toURI()));

        rowIndex = nts.split("\n");
        columnIndex = terminals.split("\n");

        rowLookup = createTableIndex(rowIndex);
        columnLookup = createTableIndex(columnIndex);

        informations = splitInfo();

        tableNoHeader = createTable();

        tableToPrint = createHeaders();

        writeCSV();


    }

    private Map<String, Integer> createTableIndex(String[] items){
        Map<String, Integer> index = new HashMap<>();
        int count = 0;
        for(String str : items){
            index.put(str, count);
            count++;
        }

        return index;
    }

    private String[][] createTable(){
        String[][] table = new String[rowIndex.length][columnIndex.length];
        for (String[] strings : table) {
            Arrays.fill(strings, "");
        }

        int x;
        int y;

        for(Info i : informations){
            y = rowLookup.get(i.nts);
            x = columnLookup.get(i.terminal);
            table[y][x] = i.info;
        }

        return table;
    }

    private List<Info> splitInfo(){
        Scanner scan = new Scanner(mm);
        List<Info> infos = new ArrayList<>();
        String line;
        String nts = "";
        String terminal = "";
        while(scan.hasNext()){
            line = scan.nextLine();
            if(line.startsWith("<") && !line.contains(" ")){
                nts = line;

            }else if(line.startsWith("  terminal")){
                terminal = line;

            }else{
                if(line.equals("")){
                    line = "ɛ";
                }
                infos.add(new Info(nts.replace("<", "").replace(">", ""), terminal.trim().split(" ")[1], line.trim()));

            }
        }
        return infos;
    }

    private String[][] createHeaders(){
        String[][] tableWithHeaders = new String[tableNoHeader.length+ 1][tableNoHeader[0].length+1];
        tableWithHeaders[0][0] = "";
        for (int i = 1; i < tableWithHeaders[0].length - 1; i++) {
            tableWithHeaders[0][i] = columnIndex[i - 1];
        }

        for (int i = 1; i < tableWithHeaders.length; i++) {
            for (int j = 0; j < tableWithHeaders[i].length; j++) {
                tableWithHeaders[i][j] = (j == 0) ? rowIndex[i-1] : tableNoHeader[i - 1][j - 1];

            }

        }

        for(String[] a : tableWithHeaders){
            System.out.println(Arrays.toString(a));
        }


        return tableWithHeaders;
    }

    private void writeCSV() throws IOException {
        Files.deleteIfExists(Path.of(outputPath + outputFile));
        FileWriter fileWriter = new FileWriter(outputPath + outputFile, true);
        for(String[] a : tableToPrint){
            fileWriter.write(Arrays.toString(a).replace("[", "").replace("]", "").replace(",", ";"));
            fileWriter.write(System.getProperty("line.separator"));
        }


        fileWriter.close();
    }


}

class Info{
    String nts;
    String terminal;
    String info;
    public Info(String nts, String terminal, String info){
        this.nts = nts;
        this.terminal = terminal;
        this.info = info;
    }

    @Override
    public String toString(){
        return "NTS:" + nts + "Terminal:" + terminal + "Info:" + info;
    }
}
