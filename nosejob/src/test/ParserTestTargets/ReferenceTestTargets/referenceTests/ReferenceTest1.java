package referenceTests;

public class ReferenceTest1{
	
	public int field = 1;
	
	public int methodToCall() {
		return 0;
	}
	
	public void internalMethodCall() {
		int a = 0;
		methodToCall();
		int i = field;
		i = a;
		int t = methodToCall();
	}
	
	public static int staticMethodToCall() {
		return 0;
	}
	
}