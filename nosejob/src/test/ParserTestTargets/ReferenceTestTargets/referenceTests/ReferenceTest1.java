package referenceTests;

public class ReferenceTest1{
	
	public int field = 1;
	
	public int methodToCall() {}
	
	public void internalMethodCall() {
		int a = 0;
		methodToCall();
		int i = field;
		i = a;
		int t = methodToCall();
	}
	
}