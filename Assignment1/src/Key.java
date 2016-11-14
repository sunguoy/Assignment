public class Key {
	private String word;
	private int type;
	public Key(String word, int type){
		this.word=word;
		this.type=type;
	};
	public String getWord(){
		return word;
	};
	public int getType(){
		return type;
	};
	public int compareTo(Key k){
		if(this.type==k.type)
		{
			if(this.word.compareTo(k.word)==0)
				return 0;
			else if(this.word.compareTo(k.word)<0)
				return -1;
			else 
				return 1;
		}
		else if(this.type<k.type)
			return -1;
		else 
			return 1;
	};
}
