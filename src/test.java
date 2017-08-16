
public class test {
	
	public static void main(String[] args) {
		System.out.println(
	//	"5501 O'Donnell St. Cutoff".
		
		"Denny's-Sheridan-Hollywood-Super-Tours".replaceAll(" |//+|///|//?|%|#|&|=|'", "-")
		.replaceAll("-+", "-")
		+ "-");
		/*+ station.getCompanyName().trim()
				.replaceAll(" |//+|///|//?|%|#|&|='", "-")
				.replaceAll("-+", "-")*/
	}
}
