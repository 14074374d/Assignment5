package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		if (tuplesResult.isEmpty()) {
			ArrayList<Tuple> sort = new ArrayList<Tuple>();
			Tuple tuple = child.next();
			if(tuple==null){
				return null;
			}
			else{ 
				while (tuple != null) {
				sort.add(tuple);
				tuple = child.next();
				}
			}
			tuple = sort.get(0);
			for(int i = 0 ; i <tuple.getAttributeList().size() ; i++){
				if(tuple.getAttributeName(i).equals(orderPredicate)){
					while(sort.isEmpty()==false){
						int k = 0;
						for(int j = 0; j < sort.size();j++){
							if((sort.get(j).getAttributeValue(i)).toString().compareTo(sort.get(k).getAttributeValue(i).toString())<0)
								k = j;
						}
						tuplesResult.add(sort.get(k));
						sort.remove(k);
					}
				}
			}
			
		}
		Tuple tuple2 = tuplesResult.remove(0);
		return tuple2;
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}