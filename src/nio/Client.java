package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

/**  
 * TCP/IP的NIO非阻塞方式  
 * 客户端  
 * */
public class Client {

    public static void main(String[] args) throws IOException {
        final InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8099);
        final SocketChannel socket = SocketChannel.open();
        socket.configureBlocking(false); 
        //创建选择器   
        final Selector selector = SelectorProvider.provider().openSelector();
        //将选择器注册到通道上，返回一个选择键   
		socket.connect(address);
        //OP_CONNECT用于套接字接受操作的操作集位   
        socket.register(selector, SelectionKey.OP_CONNECT);
        boolean isFound = false;
        while (true) {
            try {
                //System.out.println("running ... ");
                //选择一组键，其相应的通道已为 I/O 操作准备就绪。   
                selector.select();
                //返回此选择器的已选择键集   
                //public abstract Set<SelectionKey> selectedKeys()   
                Iterator selectorKeys = selector.selectedKeys().iterator();
                while (selectorKeys.hasNext()) {
                    // System.out.println("running2 ... ");
                    //这里找到当前的选择键   
                    SelectionKey key = (SelectionKey) selectorKeys.next();
                    //然后将它从返回键队列中删除   
                    selectorKeys.remove();
                    if (!key.isValid()) { // 选择键无效
                        continue;
                    }
                    //1516276853492 
                    //1516276853492
                    if (key.isConnectable()) {
                    	System.out.println("++++++++++++++++"+System.currentTimeMillis());
                    	isFound = true;
                    	break;
                    }
                }
                if(isFound) break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}