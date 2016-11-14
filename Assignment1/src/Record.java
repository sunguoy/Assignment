
public class Record {
	private Key key;
	private String data;
	public Record(Key k, String data){
		this.key = k;
		this.data = data;
	};
	public Key getKey(){
		return key;
	};
	public String getData(){
		return this.data;
	};
}
