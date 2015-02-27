package Parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by mikhail on 2/27/15.
 */
public class HtmlTags {
    private Map<String,Tag> map = new HashMap<String,Tag>();
    private static HtmlTags instance = new HtmlTags();

    {
        map.put("b",new Tag("b"));
        map.put("ul",new Tag("u"));
        map.put("i",new Tag("i"));
        map.put("par",new Tag("p"));
    }

    private HtmlTags() { }

    public static HtmlTags getInstance() {
        return instance;
    }

    public String getStartTag(String key) {
        return map.get(key).getOpeningTag();
    }

    public String getEndTag(String key) {
        return map.get(key).getClosingTag();
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }
}

class Tag {
    private String open;
    private String close;

    private static final Set<String> uncloseable = new HashSet<String>();

    static {
        uncloseable.add("p");
    }

    public Tag(String body) {
        this.open = "<" + body + ">";
        this.close = (uncloseable.contains(body)) ? "" : "</" + body + ">";
    }

    public String getOpeningTag() {
        return open;
    }

    public String getClosingTag() {
        return close;
    }

}
