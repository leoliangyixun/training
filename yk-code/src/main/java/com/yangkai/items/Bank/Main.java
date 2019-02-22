public class Main {

	public Main() {
		
	}

	public static void main(String[] args) {
		//开启4个普通窗口。
		for(int i=1;i<5;i++){
			ServiceWindow commonWindow=new CommonServiceWindow();
			commonWindow.setWindowNumber(i);
			commonWindow.start();
		}
		//开启1个快速窗口。
		ServiceWindow quickWindow=new QuickServiceWindow();
		quickWindow.setWindowNumber(5);
		quickWindow.start();
		//开启1个VIP窗口。
		ServiceWindow vipWindow=new VipServiceWindow();
		vipWindow.setWindowNumber(6);
		vipWindow.start();
		//开启普通客户拿号线程。
		NumberMachine.getNumberMechine().getCommonNumberManager().start();
		//开启快速客户拿号线程。
		NumberMachine.getNumberMechine().getQuickNumberManager().start();
		//开启VIP客户拿号线程。
		NumberMachine.getNumberMechine().getVipNumberManager().start();
	}

}
