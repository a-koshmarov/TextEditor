import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


class Parser {

    //    Initialize hash map to store tags and their ANSI codes
    private Map<String, String> tags = new HashMap<>();
    private String line;
    private String parsedLine = "";

    Parser(String line) {
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

    String getParsedLine() {
        parse();
        return parsedLine;
    }

    //    Parse tags and string which they enclose
    private int tagReader(int index, char[] arr) {

//        Get indexes for the opening tag
        int first = index + 2;
        int second = index + 2;
        while (arr[second] != '}') {
            second++;
        }

//        Detach opening tag from the line
        String tag = new String(arr);
        tag = tag.substring(first, second);

//        Separate tags by whitespace
        String[] separatedTags = tag.split("\\s+");
        ArrayList<String> tagList = new ArrayList<>(Arrays.asList(separatedTags));

//        Get indexes for string inside the tags
        first = second + 2;
        second += 2;
        while (arr[second] != '{' || arr[second + 1] != '}') {
            second++;
        }

//        Detach the string from the line
        String stringToApply = new String(arr);
        stringToApply = stringToApply.substring(first, second);

//        Apply the tags to the string
        tagApplier(stringToApply, tagList);

//        Return the last index of the closing tag
        return second + 1;
    }

    //    Applies the list of tags to the string
    private void tagApplier(String stringToApply, ArrayList<String> tagsToApply) {
        String applied = "";

//        Concatenate the tags
        for (String tag : tagsToApply) {
            applied += tags.get(tag);
        }

//        Concatenate the string and RESET tag
        applied += stringToApply + tags.get("RESET");

//        Add formatted line to the final string
        parsedLine += applied;
    }

    private void parse() {
        char[] arr = line.toCharArray();

//        Read line by char to find opening tag braces
        for (int i = 0; i < arr.length; i++) {
            char el = arr[i];

//            Parse line only if there are 2 opening braces
//            Move index to the end of the closing tag or
//            Add char to final string if it is not enclosed in tags
            if (i < arr.length - 1 && el == '{' && arr[i + 1] == '{') {
                i = tagReader(i, arr);
            } else {
                parsedLine += el;
            }
        }
    }
}
