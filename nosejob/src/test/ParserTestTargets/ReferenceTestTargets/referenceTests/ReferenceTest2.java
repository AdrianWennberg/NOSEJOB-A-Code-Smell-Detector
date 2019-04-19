package referenceTests;

public class ReferenceTest2{
	
	public void externalMethodCall() {
		ReferenceTest1 t = new ReferenceTest1();
		int i = t.field;
		t.methodToCall();
	}
	
}