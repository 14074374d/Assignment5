package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next() {
		if (getAttribute == true) {
			try {
				String tupleLine = br.readLine();
				if (tupleLine == null) {
					return null;
				} else {
					BufferedReader br2 = null;
					br2 = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
					String attributeLine = br2.readLine();
					String dataTypeLine = br2.readLine();
					tuple = new Tuple(attributeLine, dataTypeLine, tupleLine);
					tuple.setAttributeName();
					tuple.setAttributeType();
					tuple.setAttributeValue();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (getAttribute == false){
			try {
				String attributeLine = br.readLine();
				String dataTypeLine = br.readLine();
				String tupleLine = br.readLine();
				tuple = new Tuple(attributeLine, dataTypeLine, tupleLine);
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				getAttribute = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tuple;
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}