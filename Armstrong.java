public class Armstrong {
	public static void main(String[] args) {
		int number=371,OriginalNumber,remainder,result=0;
		OriginalNumber=number;
		while(OriginalNumber!=0)
		{
			remainder=OriginalNumber%10;
			result+=Math.pow(remainder,3);
			OriginalNumber/=10;
		}
		if(result==number)
		System.out.println(number+"is an Armstrong number.");
		else
		System.out.println(number+"is not an Armstrong number.");
	}
}