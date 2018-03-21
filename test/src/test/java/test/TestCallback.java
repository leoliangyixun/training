package test;

import org.junit.Test;

public class TestCallback {

    @Test
    public void test() {

    }

    /**
     * @author kerwin yang
     * @since 2018-01-24 14:58:00 回调模式-回调接口类
     */
    public interface CSCallBack {
        public void process(String status);
    }

    /**
     * @author kerwin yang
     * @since 2018-01-24 14:58:00 回调模式-模拟客户端类
     */
    public class Client implements CSCallBack {

        private Server server;

        public Client(Server server) {
            this.server = server;
        }

        public void sendMsg(final String msg) {
            System.out.println("客户端：发送的消息为：" + msg);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 内部类不能直接用this，因为this并不表示client对象而是Runnable对象
                    server.getClientMsg(Client.this, msg);
                }
            }).start();
            System.out.println("客户端：异步发送成功");
        }

        @Override
        public void process(String status) {
            System.out.println("客户端：服务端回调状态为：" + status);
        }
    }

    /**
     * @author kerwin yang
     * @since 2018-01-24 14:58:00 回调模式-模拟服务端类
     */
    public class Server {

        public void getClientMsg(CSCallBack csCallBack, String msg) {
            System.out.println("服务端：服务端接收到客户端发送的消息为:" + msg);

            // 模拟服务端需要对数据处理
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("服务端:数据处理成功，返回成功状态 200");
            String status = "200";
            csCallBack.process(status);
        }
    }
}
