import java.util.Random;

public class QuickServiceWindow implements ServiceWindow{
	private Integer windowNumber;
	public QuickServiceWindow() {
		
	}
	public void setWindowNumber(Integer windowNumber){
		this.windowNumber=windowNumber;
	}

	public void start() {
		new Thread(new Runnable() {

			
			public void run() {
				while (true) {
					System.out.println(windowNumber + "号快速窗口开始叫号");
					Integer cutomerNumber = NumberMachine.getNumberMechine()
							.getQuickNumberManager().getNumber();
					if (cutomerNumber != null) {
						System.out.println(windowNumber + "号快速窗口正在为"
								+ cutomerNumber + "号快速客户服务......");
						int serviceTime=(new Random().nextInt(Constants.MAX_SERVICE_TIME) + 1);
						try {
							Thread.sleep(serviceTime * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(windowNumber + "号快速窗口为"
								+ cutomerNumber + "号快速客户服务了"+serviceTime+"s");
					} else {
						int waitTime = new Random().nextInt(Constants.MAX_WINDOW_WAIT_TIME) + 1;
						System.out.println(windowNumber + "号快速窗口没有叫到号，等待"
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
