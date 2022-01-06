package builderPattern;

public class Book {
	private String name;
	private String contents;
	private String title;

	
	public static class Builder {
		private String name;
		private String contents;
		private String title;
		
		public Builder name(String name) {
			this.name=name;
			return this;
		}
		public Builder contents(String contents) {
			this.contents=contents;
			return this;
		}
		public Builder title(String title) {
			this.title=title;
			return this;
		}
		
		public Book build() {
			return new Book(this);
		}
	}
	
	public Book(Builder builder) {
		this.name = name;
		this.contents = contents;
		this.title = title;
	}
}
// 내부 클래스를 사용하는 이유?
// 1. 외부 클래스와 긴밀한 관계가 있는 경우
// 2. 내부 클래스에서 외부 클래스 멤버로 쉽게 접근하기 위한 경우
// 3. 서로 관련있는 코드를 묶서 캡슐화 증가
