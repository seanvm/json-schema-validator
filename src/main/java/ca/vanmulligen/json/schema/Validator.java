package ca.vanmulligen.json.schema;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Validator
{
	private String rawJSONSchema;
	
	public Validator(String rawJSONSchema){
		this.rawJSONSchema = rawJSONSchema;
	}
	
	public String isValid(String testableJSON) throws Exception { 
		JSONObject results = new JSONObject();
		results.put("error", "");
		
		try {
			JSONObject schemaJSONObect = new JSONObject(new JSONTokener(rawJSONSchema));
			Schema schema = SchemaLoader.load(schemaJSONObect);
			schema.validate(new JSONObject(testableJSON));
		} catch (ValidationException e) {
			results.put("valid", false);
			results.put("error", e.toJSON());
			return results.toString();
		} 
		results.put("valid", true);
		return results.toString();
	}
}
