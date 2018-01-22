package jedisLock;

import java.util.List;
import java.util.Set;

public final class JedisKeyRule {
    
    /**
     * 根据扩展字符串组成KEY
     * @param ext
     * @param keyRule
     * @return
     */
    public static String[] getExtKeys(short keyRule, Set<Object[]> ext) {
        String[] retExt = new String[ext.size()];
        int i = 0;
        for (Object[] extObj : ext) {
            retExt[i] = getExtKey(keyRule, extObj);
            i++;
        }
        return retExt;
    }
    
    /**
     * 根据扩展字符串组成KEY
     * @param ext
     * @param keyRule
     * @return
     */
    public static String getExtKey(short keyRule, Object[] ext) {
        StringBuilder keySb = new StringBuilder().append(keyRule);
        for (Object object : ext) {
            keySb.append(":").append(object);
        }
        return keySb.toString();
    }
    
    /**
     * 根据扩展字符串组成KEY
     * @param ext
     * @param keyRule
     * @return
     */
    public static String[] getExtKeys(short keyRule, List<Object[]> ext) {
        String[] retExt = new String[ext.size()];
        int i = 0;
        for (Object[] extObj : ext) {
            retExt[i] = getExtKey(keyRule, extObj);
            i++;
        }
        return retExt;
    }
}
