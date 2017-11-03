import java.util.Random;

public class VipServiceWindow implements ServiceWindow{
	private Integer windowNumber;
	
	public VipServiceWindow() {
		
	}
	
	public void setWindowNumber(Integer windowNumber){
		this.windowNumber=windowNumber;
	}
	
	public void start() {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					System.out.println(windowNumber + "��VIP���ڿ�ʼ�к�");
					Integer cutomerNumber = NumberMachine.getNumberMechine()
							.getVipNumberManager().getNumber();
					if (cutomerNumber != null) {
						System.out.println(windowNumber + "��VIP��������Ϊ"
								+ cutomerNumber + "��VIP�ͻ�����......");
						int serviceTime=(new Random().nextInt(Constants.MAX_SERVICE_TIME) + 1);
						try {
							Thread.sleep(serviceTime * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(windowNumber + "��VIP����Ϊ"
								+ cutomerNumber + "��VIP�ͻ�������"+serviceTime+"s");
					} else {
						int waitTime = new Random().nextInt(Constants.MAX_WINDOW_WAIT_TIME) + 1;
						System.out.println(windowNumber + "��VIP����û�не��ţ��ȴ�"
								+ waitTime + "s");
						try {
							Thread.sleep(waitTime * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}

		}).start();

	}

}
