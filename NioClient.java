package com.fang;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

public class NioClient {
	
	public static void main(String[] args) throws Exception {
		System.out.println("客户端已经被启动...");
		//1.创建socket通道
		SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8080));
		//2.切换为异步非阻塞
		channel.configureBlocking(false);
		//3.指定缓冲区大小
		ByteBuffer buffer = ByteBuffer.allocate(2048);
		buffer.put(new Date().toString().getBytes());
		//4.切换到读取模式
		buffer.flip();
		channel.write(buffer);
		buffer.clear();
		channel.close();
	}
	
}

class NioServer{
	
	public static void main(String[] args) throws Exception {
		System.out.println("服务器端已经启动...");
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.configureBlocking(false);
		channel.bind(new InetSocketAddress(8080));
		Selector selector = Selector.open();
		channel.register(selector, SelectionKey.OP_ACCEPT);
		while(selector.select()>0) {
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()) {
				SelectionKey next = iterator.next();
				if (next.isAcceptable()) {
					SocketChannel accept = channel.accept();
					//11.设置为阻塞事件
					accept.configureBlocking(false);
					channel.register(selector,SelectionKey.OP_READ);
				} else if (next.isReadable()) {
					SocketChannel channel2 = (SocketChannel) next.channel();
					int len = 0;
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					while((len=channel2.read(buffer))>0) {
						buffer.flip();
						System.out.println(new String(buffer.array(),0,len));
						buffer.clear();
					}
				}
			}
		}
	}
}
