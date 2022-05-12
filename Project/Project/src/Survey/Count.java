package Survey;

public class Count {
	private String rel;
	private int count;
	
	public Count(String rel, int count) {
		super();
		this.rel = rel;
		this.count = count;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return rel+"\t=\t" + count + "Έν";
	}
}
