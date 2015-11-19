package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		// Delete the lines below and add your code here
		if (child.from.equals(whereTablePredicate)==false){
			return child.next();
		}
		else{
			Tuple tuple = child.next();
			while (tuple != null){
				attributeList = child.getAttributeList();
				for (int i = 0; i < attributeList.size(); i++) {
					Attribute cur = attributeList.get(i);
					if (cur.getAttributeName().equals(whereAttributePredicate) && cur.getAttributeValue().equals(whereValuePredicate)) {
						return tuple;
					}
				}
				tuple = child.next();
			}
			return null;
		}
	}
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}