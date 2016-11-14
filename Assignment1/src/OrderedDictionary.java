import java.util.ArrayList;
import java.util.List;

/** The structrue of the node of BST */  
class Node {  
    public Record record; 
    public Node leftChild;  
    public Node rightChild;  
    public Node(Record record) {  
        this(record,null,null);
    }  
    public Node(Record record, Node leftChild, Node rightChild)
    {
    	this.record = record;  
        this.leftChild=leftChild;
        this.rightChild=rightChild;
    }
    public Node()
    {
    	this.record=null;
    	this.leftChild=leftChild;
    	this.rightChild=rightChild;
    }
};

/** The implemention of BST */ 
public class OrderedDictionary {
	private Node root; 
	private List list;
	private int count=0;
	 
	/* Construct the OrderedDictionary */
	public OrderedDictionary(){
		root=null;
		list=new ArrayList();
	} 
	public OrderedDictionary(Record record){
		list=new ArrayList();
		root.record = record;
		root.leftChild=null;
		root.rightChild=null;
	}
	public OrderedDictionary(Record []records) throws DictionaryException{
		list=new ArrayList();
		for(Record record:records)
			this.insert(record);
	}
	
	/*** for test!!!! ***/
	public void show()
	{
		inOrder(root);
		System.out.println("root: "+root.record.getKey().getWord());
		for(int i=0;i<list.size();i++)
		{
			
			System.out.print(((Record) (list.get(i))).getKey().getWord()+"  ");
		}
		System.out.println();
	}
	/*** for test!!!! ***/
	
	private void inOrder(Node root)
	{
		if(root==null)
			return;
		if(root.leftChild!=null)
			inOrder(root.leftChild);
		list.add(root.record);
		if(root.rightChild!=null)
			inOrder(root.rightChild);
		return;
	}
	
	
	
    /* Returns the Record object with key k, or it returns null if such 
       a record is not in the dictionary. */
    public Record find (Key k){
    	if(root == null){  
            System.out.println("No match record!");  
            return null;  
        }  
        Node current = root;  
        Key currentKey = current.record.getKey();
        while(currentKey.compareTo(k) != 0){  
            if(currentKey.compareTo(k)==-1)  
                current = current.rightChild;  
            else  
                current = current.leftChild;  
            if(current == null) 
            {
                return null;
            }  
            currentKey = current.record.getKey();
        }  
        return current.record;  
    }

    /* Inserts r into the ordered dictionary. It throws a DictionaryException 
       if a record with the same key as r is already in the dictionary. */
    public void insert (Record r) throws DictionaryException{
    	if(root == null){  
    		root = new Node(r);  
            return;  
        }  
        if(this.find(r.getKey()) != null){  
        	DictionaryException e = new DictionaryException("Record has already existed!");
            throw e; 
        }  
        Node current = root;  
        Key currentKey = current.record.getKey();
        while(current != null){  
            if(currentKey.compareTo(r.getKey())==-1){  
                if(current.rightChild == null){  
                    current.rightChild = new Node(r);  
                    return;  
                }  
                current = current.rightChild;  
            }
            else if(currentKey.compareTo(r.getKey())==1){  
                if(current.leftChild == null){ 
                    current.leftChild = new Node(r);  
                    return;  
                }  
                current = current.leftChild;  
            } 
            else
            {
            	DictionaryException e = new DictionaryException("Record has already existed!");
                throw e;
            }
            currentKey = current.record.getKey();
        } 
    }

    /*  Removes the record with Key k from the dictionary. It throws a 
        DictionaryException if the record is not in the dictionary. */
    public void remove (Key k) throws DictionaryException
    {
    	if(find(k)==null)
    		throw new DictionaryException("The record is not in the dictionary");
    	root = remove(k,root); 
    }
    private Node remove(Key k,Node node)  
    {  
        if(node == null)  
            return node;
        int result = k.compareTo(node.record.getKey());  
        if(result>0)  
            node.rightChild = remove(k,node.rightChild);  
        else if(result<0)  
            node.leftChild = remove(k,node.leftChild);  
        else if(node.leftChild!=null&&node.rightChild!=null)  
        {  
            node.record = find(node.rightChild.record.getKey());  
            node.rightChild = remove(node.record.getKey(),node.rightChild);  
        }  
        else  
            node = (node.leftChild!=null)?node.leftChild:node.rightChild;  
        return node;  
              
    }  
    /* Returns the successor of k (the record from the ordered dictionary 
       with smallest key larger than k); it returns null if the given key has
       no successor. The given key DOES NOT need to be in the dictionary. */
    public Record successor (Key k){
    	list.clear();
    	inOrder(root);
    	Record r = find(k);
//    	System.out.println("size: "+list.size()+"    index:"+list.indexOf(r));
    	if(r==null || list.indexOf(r)==list.size()-1)
    		return null;
    	else
    		return (Record) list.get(list.indexOf(r)+1);
    }

    /* Returns the predecessor of k (the record from the ordered dictionary 
       with largest key smaller than k; it returns null if the given key has 
       no predecessor. The given key DOES NOT need to be in the dictionary.  */
    
    public Record predecessor (Key k){
    	list.clear();
    	inOrder(root);
    	Record r = find(k);
    	if(r==null || list.indexOf(r)==0)
    		return null;
    	else
    		return (Record) list.get(list.indexOf(r)-1); 
    }

    /* Returns the record with smallest key in the ordered dictionary. 
       Returns null if the dictionary is empty.  */
    public Record smallest (){
    	list.clear();
    	inOrder(root);
    	return (Record) list.get(0);
    }
    private Record getSmallest(Node root){
    	if(root==null)
    		return null;
    	if(root.leftChild== null) 
    	{
   		 return root.record;
   		 } 
    	else 
    	{
   		  return getLargest(root.leftChild);
   		 }
    }

    /* Returns the record with largest key in the ordered dictionary. 
       Returns null if the dictionary is empty.  */
    public Record largest (){
    	list.clear();
    	inOrder(root);
    	return (Record) list.get(list.size()-1);
    }
    private Record getLargest(Node root){
    	if(root==null)
    		return null;
    	if(root.rightChild == null) 
    	{
   		 return root.record;
   		 } 
    	else 
    	{
   		  return getLargest(root.rightChild);
   		 }
    }

}
