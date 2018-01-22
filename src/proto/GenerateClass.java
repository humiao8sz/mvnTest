package proto;

import java.io.IOException;

public class GenerateClass {
    public static void main(String[] args) {
        String protoFile = "VO_Esse_Detail2.proto";//  D:\WorkSpace\TT\src\proto
        String strCmd = "D:/lib/proto/protobuf-master/src/protoc.exe -I=D:/workspace2/mvnTest/src/proto --java_out=D:/workspace2/mvnTest/src/proto D:/workspace2/mvnTest/src/proto/"+ protoFile;
        
       //String strCmd = "D:/lib/proto/protoc.exe D:/workspace2/mvnTest/src/proto/"+ protoFile;
        try {
            Runtime.getRuntime().exec(strCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
}
