package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		Tuple tupleA = leftChild.next() , tupleB= rightChild.next();
		while(tupleA != null){
			tuples1.add(tupleA);	
			tupleA = leftChild.next();
		}
		if(tupleB == null){
			return null;
		}
		else{
			for(int row=0;row<tuples1.size();row++){
				tupleA = tuples1.get(row);
				for(int i = 0;i < tupleA.getAttributeList().size();i++){
					for(int j = 0;j < tupleB.getAttributeList().size();j++){
						if(tupleB.getAttributeName(j).equals(tupleA.getAttributeName(i)) && tupleB.getAttributeValue(j).equals(tupleA.getAttributeValue(i))){
							newAttributeList = new ArrayList<Attribute>();
							for(int k = 0; k <tupleA.getAttributeList().size() ;k++){
								newAttributeList.add(tupleA.getAttributeList().get(k));
							}
							for(int l = 0; l <tupleB.getAttributeList().size() ;l++){
								newAttributeList.add(tupleB.getAttributeList().get(l));
							}
							return new Tuple(newAttributeList);
						}	
					}
				}
			}
			tupleB= rightChild.next();
		}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}