import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private Map<String, String> tags = new HashMap<>();
    private String line;
    private String parsedLine = "";

    public Parser(String line) {
        this.line = line;
        tags.put("RESET", "\u001B[0m");
        tags.put("BLACK", "\u001B[30m");
        tags.put("RED", "\u001B[31m");
        tags.put("GREEN", "\u001B[32m");
        tags.put("YELLOW", "\u001B[33m");
        tags.put("BLUE", "\u001B[34m");
        tags.put("PURPLE", "\u001B[35m");
        tags.put("CYAN", "\u001B[36m");
        tags.put("WHITE", "\u001B[37m");
        tags.put("BOLD", "\u001B[1m");
    }

    public String getParsedLine() {
        parse();
        return parsedLine;
    }

    private int tagReader(int index, char[] arr) {
        int first = index + 2;
        int second = index + 2;
        while (arr[second] != '}') {
            second++;
        }

        String tag = new String(arr);
        tag = tag.substring(first, second);
        String[] separatedTags = tag.split("\\s+");
        ArrayList<String> tagList = new ArrayList<>(Arrays.asList(separatedTags));

        first = second + 2;
        second += 2;
        while (arr[second] != '{' || arr[second+1]!='}' ) {
            second++;
        }

        String stringToApply = new String(arr);
        stringToApply = stringToApply.substring(first, second);

        tagApplier(stringToApply, tagList);
        return second + 1;
    }

    private void tagApplier(String stringToApply, ArrayList<String> tagsToApply) {
        String applied = "";
        for (String tag : tagsToApply) {
            applied += tags.get(tag);
        }
        applied += stringToApply + tags.get("RESET");
        parsedLine += applied;
    }

    private void parse() {
        char[] arr = line.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            char el = arr[i];
            if (i < arr.length - 1) {
                if (el == '{' && arr[i + 1] == '{') {
                    i = tagReader(i, arr);
                } else {
                    parsedLine += el;
                }
            }
        }
    }


}
