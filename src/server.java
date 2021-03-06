import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    private int port = 1234;
    private ServerSocket serverSocket;

    public server() throws Exception {
        serverSocket = new ServerSocket(port);
        System.out.println("服务器启动!");
    }

    public void service() {
        while (true) {
            Socket client = null;
            try {
                client = serverSocket.accept();
                System.out.println("New connection accepted " + client.getInetAddress() + ":" + client.getPort());
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                String jsonObjStr = (String) ois.readObject();
                JSONObject jsonObject = JSONObject.fromObject(jsonObjStr);
                if(jsonObject != null)
                    System.out.println(jsonObject.toString());
                else System.out.println("there is error!");

                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                person person = new person("dxr", "654321");
                JSONObject jsonObject1 = JSONObject.fromObject(person);
                String response = jsonObject1.toString();
                oos.writeObject(response);
                oos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        server server = new server();
//        Thread.sleep(60000 * 10);
        server.service();
    }
    /*
    //3、获取输入流，并读取客户端信息
     InputStream is = socket.getInputStream();
   InputStreamReader isr =newInputStreamReader(is);
   BufferedReader br =newBufferedReader(isr);
   String info =null;
   while((info=br.readLine())!=null){
     System.out.println("我是服务器，客户端说："+info)；
   }
 socket.shutdownInput();//关闭输入流
 //4、获取输出流，响应客户端的请求
 OutputStream os = socket.getOutputStream();
 PrintWriter pw = new PrintWriter(os);
 pw.write("欢迎您！");
 pw.flush();
     */

}
