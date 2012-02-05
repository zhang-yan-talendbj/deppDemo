/*
 * Created on 13/12/2005
 */
package com.caribe.stone.ognl;

import java.util.Map;
import java.util.regex.Pattern;

import ognl.OgnlException;

public class WordbasedSubstitution extends Substitution {
    private Pattern keywordPattern;
    private String missingString;

    public WordbasedSubstitution(String keyword, String ognl) {
        super(ognl);
        this.keywordPattern = Pattern.compile(TOKEN_SURROUNDS + keyword + TOKEN_SURROUNDS);
        this.missingString = getMissingString(keyword);
    }
    @Override
    public String subsitute(String result, Map input) throws OgnlException {
        Object value = getValue(input);
        if (value != null) {
            String stringValue = value.toString();
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < stringValue.length(); ++i) {
                char c = stringValue.charAt(i);
                if ((c == '\\') || (c == '$')) {
                    buf.append('\\');
                }
                buf.append(c);
            }
            result = keywordPattern.matcher(result).replaceAll(escapeXML(buf.toString()));
        }
        return result;
    }
}
