public class Main {

	public Main() {
		
	}

	public static void main(String[] args) {
		//����4����ͨ���ڡ�
		for(int i=1;i<5;i++){
			ServiceWindow commonWindow=new CommonServiceWindow();
			commonWindow.setWindowNumber(i);
			commonWindow.start();
		}
		//����1�����ٴ��ڡ�
		ServiceWindow quickWindow=new QuickServiceWindow();
		quickWindow.setWindowNumber(5);
		quickWindow.start();
		//����1��VIP���ڡ�
		ServiceWindow vipWindow=new VipServiceWindow();
		vipWindow.setWindowNumber(6);
		vipWindow.start();
		//������ͨ�ͻ��ú��̡߳�
		NumberMachine.getNumberMechine().getCommonNumberManager().start();
		//�������ٿͻ��ú��̡߳�
		NumberMachine.getNumberMechine().getQuickNumberManager().start();
		//����VIP�ͻ��ú��̡߳�
		NumberMachine.getNumberMechine().getVipNumberManager().start();
	}

}
