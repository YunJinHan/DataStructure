//자료구조 실습 과제 
//HashTable
//학번 : 2012036901
//이름 : 윤진한 

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable table = new Hashtable();
		table.setValue("Jonh","111-1111"); 
		table.setValue("No","123-4567");
		table.setValue("Sam","222-2222");
		table.setValue("Jee","456-7890");
		table.setValue("Sandra","333-3333");
		table.setValue("Jaewon","000- 0000");
		table.setValue("Lisa" ,"444-4444"); 
		table.setValue("Chris" ,"555-5555");
		System.out.println(table.getValue("Lisa"));
		System.out.println(table.getValue("Chris"));
		System.out.println(table.getValue("Kim"));
		table.showAll();
	}

}
