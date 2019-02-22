public class NumberMachine {
	private static NumberMachine numberMachine;
	
	private NumberManager commonNumberManager=new NumberManager();
	private NumberManager quickNumberManager=new NumberManager();
	private NumberManager vipNumberManager=new NumberManager();

	private NumberMachine() {
	
	}
	public static NumberMachine getNumberMechine(){
		if(numberMachine==null)	{
			synchronized(NumberMachine.class){
				if(numberMachine==null){
					numberMachine=new NumberMachine();
				}
			}
		}
		return numberMachine;
	}
	
	public NumberManager getCommonNumberManager(){
		commonNumberManager.setCustomerType(CustomerType.COMMON);
		return commonNumberManager;
	}
	
	public NumberManager getQuickNumberManager(){
		quickNumberManager.setCustomerType(CustomerType.QUICK);
		return quickNumberManager;
	}
	
	public NumberManager getVipNumberManager(){
		vipNumberManager.setCustomerType(CustomerType.VIP);
		return vipNumberManager;
	}

}
