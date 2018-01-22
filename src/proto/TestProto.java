package proto;

import io.netty.util.internal.ObjectUtil;

import com.google.protobuf.InvalidProtocolBufferException;

import proto.PersonEntity.Person;

public class TestProto {

	/**
	 * @param args
	 * @throws InvalidProtocolBufferException 
	 */
	public static void main(String[] args) throws InvalidProtocolBufferException {
        //模拟将对象转成byte[]，方便传输
        PersonEntity.Person.Builder builder = PersonEntity.Person.newBuilder();
        builder.setId(1);
        builder.setName("ant");
        builder.setEmail("ghb@soecode.com");
        builder.addFood("0");
        builder.addFood("1");
        builder.addFood("2");
        PersonEntity.Person person = builder.build();
        System.out.println("before :"+ person.toString());
        
        builder.clear();

        System.out.println("===========Clear==========");
        System.out.println("after :"+ person.toString());

/*        System.out.println("===========Person Byte==========");
        
        builder.setId(2);
        builder.setName("dsd");
        PersonEntity.Person person2 = builder.build();
        System.out.println("before :"+ person2.toString());

        System.out.println("===========Person2 Byte==========");
        
        builder.clear();
        
        System.out.println(ObjectShallowSize.sizeOf(builder));
*/        
/*        for(byte b : person.toByteArray()){
            System.out.print(b);
        }
        System.out.println();
        System.out.println(person.toByteString());
        System.out.println("================================");

        //模拟接收Byte[]，反序列化成Person类
        byte[] byteArray =person.toByteArray();
        Person p2 = Person.parseFrom(byteArray);
        System.out.println("after :" +p2.toString());*/
	}

}
