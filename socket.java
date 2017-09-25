/*
现分别有一个通过socket发送文件的客户端以及一个通过socket接收文件的服务端：
a) 客户端从/tmp/src.data文件中读取文件内容，通过网络socket将文件内容发给服务端
b) 服务端监听10000端口，当10000端口接收到客户端连接请求时，从连接读取文件内容，并写入/tmp/dst.data中
*/


public class MyClient {

    public static void main(String[] args) {
        MyClientmyClient = new MyClient();
        myClient.sendFile();
    }

    public void sendFile() {
        Socket socket = new Socket();
        SocketAddresssocketAddress = new InetSocketAddress("127.0.0.1", 10000);
        FileInputStreamfileInputStream = null;
        OutputStreamoutputStream = null;
        // 定义缓冲区
        byte[] buffer = new byte[1024];
        try {
            socket.connect(socketAddress);
            fileInputStream = new FileInputStream("/tmp/src.data");
            outputStream = socket.getOutputStream();
            int length;
            while ((length = fileInputStream.read(buffer, 0, buffer.length)) > 0) {
                outputStream.write(buffer, 0, length);
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null && socket.isConnected()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class MyServer {

    public static void main(String[] args) {
        MyServermyServer = new MyServer();
        myServer.receiveFile();
    }

    public void receiveFile() {
        Socket clientSocket = null;
        InputStreaminputStream = null;
        FileOutputStreamfileOutputStream = null;
        // 定义缓冲区
        byte[] buffer = new byte[1024];
        try {
            ServerSocketserverSocket = new ServerSocket(10000);
            clientSocket = serverSocket.accept();
            inputStream = clientSocket.getInputStream();
            fileOutputStream = new FileOutputStream("/tmp/dst.data");
            int length;
            while ((length = inputStream.read(buffer, 0, buffer.length)) > 0) {
                fileOutputStream.write(buffer, 0, length);
                fileOutputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (clientSocket != null && clientSocket.isConnected()) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
