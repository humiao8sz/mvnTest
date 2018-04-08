package proto;

import java.io.IOException;

public class GenerateClass {
    public static void main(String[] args) {
    	//protoc --java_out=./java/ ./proto/helloworld.proto
    	// -I=D:/workspace2/mvnTest/src
    	// -I=D:/workspace2/mvnTest/src --java_out=D:/workspace2/mvnTest/src D:/workspace2/mvnTest/src/BbjxMsg.proto
        String protoFile = "BbjxMsg.proto";//  D:\WorkSpace\TT\src\proto
        String strCmd = "D:/lib/proto/protobuf-master/src/protoc.exe -I=D:/workspace2/mvnTest/src --java_out=D:/workspace2/mvnTest/src D:/workspace2/mvnTest/src/"+ protoFile;
        
       //String strCmd = "D:/lib/proto/protoc.exe D:/workspace2/mvnTest/src/proto/"+ protoFile;
        try {
            Runtime.getRuntime().exec(strCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
}
