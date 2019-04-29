/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package referenceTests;

public class ReferenceTest2{
	
	public void externalMethodCall() {
		ReferenceTest1 t = new ReferenceTest1();
		int i = t.field;
		t.methodToCall();
	}
	
}