package Util;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by mikhail on 2/6/15.
 */
public class Union {
    private List<String> keywordArray = new ArrayList<String>();
    private List<String> text = new ArrayList<String>();

    public List<String> updateUnion(String string) {
        List<String> keywordArray = Splitting.splitByBackslash(string);

        if(keywordArray.size() == 0) {
            return new ArrayList<String>();
        }

        String keywordAndText = keywordArray.get(keywordArray.size() - 1);
        text.add(StringWork.string2Space(keywordAndText));
        keywordArray.set(keywordArray.size() - 1, StringWork.string1Space(keywordAndText));
        addKeywords(keywordArray);

        return keywordArray;
    }

    public List<String> getKeywordArray() {
        return keywordArray;
    }

    public String getLastText() {
        if(text.size() == 0) {
            return "";
        }

        return text.get(text.size() - 1);
    }


    private void addKeywords(List<String> keywordArray){
        for(int i = 0; i < keywordArray.size(); i++){
            keywordArray.set(i, keywordArray.get(i).trim());
        }

        this.keywordArray.addAll(keywordArray);
    }

    @Override
    public String toString() {
        return "Util.Union{" +
                "keywordarray=" + keywordArray +
                ", text='" + text + '\'' +
                '}';
    }
}