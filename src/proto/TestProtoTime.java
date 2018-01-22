/*package proto;

import java.util.Map;
import java.util.Map.Entry;

import proto.VO_Esse_Detail2Entity;
import com.agileeagle.core.util.RandomUtil;
import com.agileeagle.dao.webgame.cache.EsseNumProperty;
import com.agileeagle.dao.webgame.cache.EsseSkillVO;
import com.agileeagle.webgame.game.handler.protobuf.EsseMsg.S_ESSE_SEARCH_OUT;
import com.agileeagle.webgame.game.handler.protobuf.EsseMsg.VO_Esse_Skill_Detail;
import com.agileeagle.webgame.game.handler.protobuf.EsseMsg.S_ESSE_SEARCH_OUT.VO_Esse_Detail;
import com.agileeagle.webgame.game.handler.protobuf.EsseMsg.S_ESSE_SEARCH_OUT.VO_Esse_Detail.VO_Esse_Prop_Detail;
import com.agileeagle.webgame.game.handler.protobuf.Message.MESSAGE;
import com.agileeagle.webgame.game.typeenum.PropertyType;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public class TestProtoTime {

	public static void main(String[] args) throws InvalidProtocolBufferException {
		byte[] bytes = new byte[500];
		for(int i = 0 ;i<500;i++){
			bytes[i] = (byte)RandomUtil.getRandom(1, 127);
		}
		MESSAGE.Builder m = MESSAGE.newBuilder();
		m.setBody(ByteString.copyFrom(bytes));
		m.setReceipt(1).setErrorcode(1).setHeader(1);
		byte[] msg = m.build().toByteArray();
		
		System.out.println(msg.length);
		
		MESSAGE headMessage = MESSAGE.parseFrom(msg);
		long totaltime2 = 0;
       long time = System.currentTimeMillis();
         for(int i = 0;i<100000;i++){
        	 VO_Esse_Detail.parseFrom(detail.build().toByteArray());
         }
         System.out.println(System.currentTimeMillis()-time);
         
         for(int i = 0;i<100000;i++){
             VO_Esse_Detail.Builder detail = VO_Esse_Detail.newBuilder()
             		.setRid(RandomUtil.getRandom(1, 128))
             		.setLv(RandomUtil.getRandom(1, 128))
             		.setExp(RandomUtil.getRandom(1, 128))
             		.setQuality(RandomUtil.getRandom(1, 128))
             		.setThrounghLv(RandomUtil.getRandom(1, 128))
             		.setSlot(RandomUtil.getRandom(1, 128))
             		.setFromation(RandomUtil.getRandom(1, 128));
             VO_Esse_Skill_Detail.Builder b = VO_Esse_Skill_Detail.newBuilder()
     		.setSkillId(RandomUtil.getRandom(1, 128))
     		.setSkillLevel(RandomUtil.getRandom(1, 128))
     		.setSkillState(RandomUtil.getRandom(1, 128));
             
             detail.addSkills(b);
             
             long time2 = System.currentTimeMillis();
        	 VO_Esse_Detail.parseFrom(detail.build().toByteArray());
        	 VO_Esse_Skill_Detail.parseFrom(b.build().toByteArray());
        	 totaltime2+=System.currentTimeMillis()-time2;
         }
         System.out.println(totaltime2);
	}

	
	
	public static void test1(){
		
	}
	
	public static void test2(){
		
	}
}
*/