package builderPattern;

public class BuilderPatternTest {

	public static void main(String[] args) {
		Book book = new Book.Builder()
							.contents("contents")
							.title("title")
							.name("name")
							.build();
	}

}
