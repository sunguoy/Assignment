import java.io.*;

public class UI {

	public static void main(String[] args) throws DictionaryException, MultimediaException, FileNotFoundException,IOException {
		/***store the record from file ***/
		 SoundPlayer soundPlayer = new SoundPlayer();
		 PictureViewer pictureViewer = new PictureViewer();
		 OrderedDictionary orderedDictionary = new OrderedDictionary();
		 
		 try {
			    BufferedReader br = new BufferedReader(new FileReader(new File(args[0]))); 
	            do{
	            	String word = br.readLine();
	            	if(word==null)break;
	            	int type=1;
	            	String data = br.readLine();
	            	if(data.endsWith(".gif") || data.endsWith("jpg"))
	            		type=3;
	            	else if(data.endsWith("wav") || data.endsWith("mid"))
	            		type=2;
	            	
	            	Key key = new Key(word, type);
	            	Record record = new Record(key, data);
	            	orderedDictionary.insert(record);
	            }while (true);
	            br.close();
	        }
	        catch(IOException e) {
	            System.out.println("IO Problem");
	        }
		 
		 
		 /***deal with the input command of user ***/
		 while(true)
		 {
			 StringReader keyboard = new StringReader();
			 String line = keyboard.read("Enter next command: ");
			 line = line.toLowerCase();
			 
			 if(line.equals("end"))
				 break;
			 if(line.startsWith("search"))
			 {
				 boolean flag = false;
				 String[] commands = line.split(" ");
				 Key key1=new Key(commands[1],1), key2=new Key(commands[1],2), key3=new Key(commands[1],3);
				 if(orderedDictionary.find(key1)!=null)
				 {
					 System.out.println(orderedDictionary.find(key1).getData());
					 flag = true;
				 }
				 if(orderedDictionary.find(key2)!=null)
				 {		
					 soundPlayer.play(orderedDictionary.find(key2).getData());
					 flag = true;
				 }	 
				 if(orderedDictionary.find(key3)!=null)
				 {
					 pictureViewer.show(orderedDictionary.find(key3).getData());
					 flag = true;
				 }
				 if(flag==false)
					 System.out.println("There is no such record!");
				 continue;
			 }
			 if(line.startsWith("remove"))
			 {
				 String[] commands = line.split(" ");
				 String word = commands[1];
				 int type = Integer.parseInt(commands[2]);
				 Key k  = new Key(word,type);
				 
				 orderedDictionary.remove(k);
				 continue;
			 }
			 if(line.startsWith("insert"))
			 {
				 String[] commands = line.split(" ");
				 String word = commands[1];
				 int type = Integer.parseInt(commands[2]);
				 String data = "";
				 for(int i=3;i<commands.length;i++)
				 {
					 if(i==3)
						 data = commands[i];
					 else
						data = data+" "+commands[i];
				 }
				 
				 Key k  = new Key(word,type);
				 Record r = new Record(k,data);
				 
				 orderedDictionary.insert(r);
				 continue;
			 }
			 if(line.startsWith("next"))
			 {
				 String[] commands = line.split(" ");
				 String word = commands[1];
				 int type = Integer.parseInt(commands[2]);
				 
				 Key k  = new Key(word,type);
				 
				 Record next = orderedDictionary.successor(k);
				 if(next==null)
				 {
					 System.out.println("This record has already been the last!!!");
					 continue;
				 }
				 System.out.println("("+next.getKey().getWord()+","+next.getKey().getType()+")");
				 continue;
			 }
			 if(line.startsWith("prev"))
			 {
				 String[] commands = line.split(" ");
				 String word = commands[1];
				 int type = Integer.parseInt(commands[2]);
				 
				 Key k  = new Key(word,type);
				 
				 Record prev = orderedDictionary.predecessor(k);
				 if(prev==null)
				 {
					 System.out.println("This record has already been the first!!!");
					 continue;
				 }
				 System.out.println("("+prev.getKey().getWord()+","+prev.getKey().getType()+")");
				 continue;
			 }
			 if(line.startsWith("first"))
			 {
				 Record first = orderedDictionary.smallest();
				 System.out.println("("+first.getKey().getWord()+","+first.getKey().getType()+")");
				 continue;
			 }
			 if(line.startsWith("last"))
			 {
				 Record last = orderedDictionary.largest();
				 System.out.println("("+last.getKey().getWord()+","+last.getKey().getType()+")");
				 continue;
			 }
			 System.out.println("Bad command!!!");
		 }
		 
	}

}
