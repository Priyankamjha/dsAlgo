package dsAlgo_Utilities;


public class DsAlgoUtil {

	public static void snooze(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
