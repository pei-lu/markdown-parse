// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String[] markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        for (String line: markdown){
            if(line.indexOf("[")==-1||
                line.indexOf("]")==-1||
                line.indexOf("(")==-1||
                line.indexOf(")")==-1){
                    continue;
            }
            
            int nextOpenBracket = line.indexOf("[", currentIndex);
            int nextCloseBracket = line.indexOf("]", nextOpenBracket);
            int openParen = line.indexOf("(", nextCloseBracket);
            int closeParen = line.indexOf(")", openParen);
            toReturn.add(line.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
            
        }
    return toReturn;
        // while(currentIndex < markdown.length()) {
        //     int nextOpenBracket = markdown.indexOf("[", currentIndex);
        //     int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
        //     int openParen = markdown.indexOf("(", nextCloseBracket);
        //     int closeParen = markdown.indexOf(")", openParen);
        //     toReturn.add(markdown.substring(openParen + 1, closeParen));
        //     currentIndex = closeParen + 1;
        // }
        // return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        String[] parseContents =  contents.split("\n");
        ArrayList<String> links = getLinks(parseContents);
        System.out.println(links);
    }
} 