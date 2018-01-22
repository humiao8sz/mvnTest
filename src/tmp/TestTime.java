/*package tmp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.google.protobuf.MessageLite;

public class TestTime {
	
	public static void main(String[] args) {
		System.out.println(95/100D);
		Map map = new HashMap();
		*//**map.put(10001, 2);
		map.put(10002, 1);
		map.put(10003, 1);
		map.put(10004, 5);
		List<Entry<Integer, Integer>> entryList = new ArrayList<Entry<Integer, Integer>>(map.entrySet());
		Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		for(int i=0;i<entryList.size();i++){
			System.out.print(entryList.get(i).getKey()+"      ");
			System.out.println(entryList.get(i).getValue());
		}*//*
		TestTime testTime = new TestTime();
		ArrayList<C_ESSE_UPGRADE> d1 = testTime.sendGameToMultiMsgSync(map, 1L);
		d1.add(C_ESSE_UPGRADE.newBuilder().setItemId(1).setRid(1).setType(1).build());
		//System.out.print(map);
		ArrayList<C_ESSE_SKILL_UPGRADE> d2 = testTime.sendGameToMultiMsgSync(map, 2L);
		d2.add(C_ESSE_SKILL_UPGRADE.newBuilder().setNum(1).setRid(1).setSkillId(1).build());
		//System.out.print(map);
		Map<Long,ArrayList<C_ESSE_UPGRADE>> aaa = (ConcurrentHashMap<Long,ArrayList<C_ESSE_UPGRADE>>)(map.get(SessionKey.SESSION_SYNC_MSG));
		for(Entry<Long,ArrayList<C_ESSE_UPGRADE>> entry : aaa.entrySet()){
			System.out.println(entry.getValue());
		}//32767 -32768
		System.out.println(Short.MIN_VALUE);
		AtomicLong atomicInteger = new AtomicLong(225000);
		for(int i = 0;i<5000;i++){
			long value = atomicInteger.incrementAndGet();
			System.out.println(value+" : "+(short)value);
		}
	}
	
	
    public <T extends MessageLite> ArrayList<T> sendGameToMultiMsgSync(Map map,long msgId) {
		 if(!map.containsKey(SessionKey.SESSION_SYNC_MSG)){
			 map.put(SessionKey.SESSION_SYNC_MSG, new ConcurrentHashMap<Long,ArrayList>());
		 }
        @SuppressWarnings("unchecked")
        ArrayList promise = ((ConcurrentHashMap<Long,ArrayList>)map.get(SessionKey.SESSION_SYNC_MSG)).put(msgId, new ArrayList());
        return promise;
        		//((ConcurrentHashMap<Long,ArrayList<T>>)map.get(SessionKey.SESSION_SYNC_MSG)).get(msgId);
    }
}
*/