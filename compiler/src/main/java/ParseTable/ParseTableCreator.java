package ParseTable;

import java.io.File;
import java.io.IOException;
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

    List<Info> informations;
    public ParseTableCreator() throws IOException, URISyntaxException {
        ClassLoader loader = ParseTableCreator.super.getClass().getClassLoader();
        terminalsURL = loader.getResource("ParseTable/Terminals.txt");
        ntsURL = loader.getResource("ParseTable/NTS.txt");
        mmURL = loader.getResource("ParseTable/MM.txt");

        terminals = Files.readString(Path.of(terminalsURL.toURI()));
        nts = Files.readString(Path.of(ntsURL.toURI()));
        mm = Files.readString(Path.of(mmURL.toURI()));

        rowIndex = nts.split("\n");
        columnIndex = terminals.split("\n");

        rowLookup = createTableIndex(rowIndex);
        columnLookup = createTableIndex(columnIndex);

        for(Map.Entry<String, Integer> i : rowLookup.entrySet()){
            System.out.println(i.getKey());
        }

        for(Map.Entry<String, Integer> i : columnLookup.entrySet()){
            System.out.println(i.getKey());
        }

        informations = splitInfo();
        createTable();
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
        String[][] table = new String[columnIndex.length][rowIndex.length];
        for (String[] strings : table) {
            Arrays.fill(strings, "");
        }

        int x = 0;
        int y = 0;

        for(Info i : informations){
            System.out.println(i);
            y = rowLookup.get(i.nts);
            x = columnLookup.get(i.terminal);
            table[y][x] = i.info;
        }

        for(String[] t : table){
            System.out.println(Arrays.toString(t));
        }

        return null;
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
