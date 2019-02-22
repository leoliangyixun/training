import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberManager {
	private List<Integer> numberList = new ArrayList<Integer>();
	private Integer customerNumber = 0;
	private CustomerType customerType;
	public NumberManager() {

	}
	public void setCustomerType(CustomerType customerType){
		this.customerType=customerType;
	}
	public synchronized Integer getNumber() {
		if (numberList.size() > 0) {
			return numberList.remove(0);
		} else {
			return null;
		}
	}

	private Integer generateNumber() {
		return new Random().nextInt(10);
	}

	public void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					int generateNumber = generateNumber();
					switch(customerType){
						case COMMON:
							if (generateNumber == 4 || generateNumber == 5
								|| generateNumber == 6 || generateNumber == 7
								|| generateNumber == 8 || generateNumber == 9) {
								numberList.add(++customerNumber);
								try {
								int waitTime=(new Random().nextInt(Constants.MAX_INTERVAL_TIME) + 1) * 1000;
									Thread.sleep(waitTime);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							break;
						case QUICK:
							if (generateNumber == 1 || generateNumber == 2 || generateNumber == 3 ) {
								numberList.add(++customerNumber);
								try {
									int waitTime=(new Random().nextInt(Constants.MAX_INTERVAL_TIME) + 1) * 1000;
									
									Thread.sleep(waitTime);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							break;
						case VIP:
							if (generateNumber == 0 ) {
								numberList.add(++customerNumber);
								try {
									int waitTime=(new Random().nextInt(Constants.MAX_INTERVAL_TIME) + 1) * 1000;
									
									Thread.sleep(waitTime);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							break;
					}

				}
			}

		}).start();
		
	}
}
