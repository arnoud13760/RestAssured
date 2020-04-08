import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(payload.CoursePrice());
		// size can be applied only on arrays, courses is an array because of square brackets
		int count = js.getInt("courses.size()");
		System.out.println(count);
		int TotalAmount = js.getInt("dashboard.purchaseAmount");
		//System.out.println(TotalAmount);
		String TitleFirstCourse = js.get("courses[0].title");
		//System.out.println(TitleFirstCourse);
		String TitleSecondCourse = js.get("courses[1].title");
		//System.out.println(TitleSecondCourse);
		// print all course titles and their respective prices
		for (int i=0;i<count;i++) 
		{
			String courseTitles = js.get("courses["+i+"].title");
			int coursePrices = js.get("courses["+i+"].price".toString());
			System.out.println(courseTitles);
			System.out.println(coursePrices);
		}
		
		//print number of copies sold for RPA course
		System.out.println("Number of copies sold for RPA course are :");
		for (int i=0;i<count;i++) 
		{
			String courseTitles = js.get("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA"))
			{
				//copies sold
				int copies = js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		
		}
		
	}}
