package ca.vanmulligen.json.schema;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Validator
{
	private Schema schema;

	public Validator(String rawJSONSchema){
		_setupSchema(rawJSONSchema, null);
	}

	public Validator(String rawJSONSchema, String resolutionScope){
		_setupSchema(rawJSONSchema, resolutionScope);
	}

	public String isValid(String testableJSON) throws Exception {
		JSONObject results = new JSONObject();
		results.put("error", "");

		try {
			this.schema.validate(new JSONObject(testableJSON));
		} catch (ValidationException e) {
			results.put("valid", false);
			results.put("error", e.toJSON());
			return results.toString();
		}
		results.put("valid", true);
		return results.toString();
	}

	private void _setupSchema(String rawJSONSchema, String resolutionScope) {
		JSONObject schemaJson = new JSONObject(new JSONTokener(rawJSONSchema));
		SchemaLoader loader;

		if(resolutionScope != null) {
			loader = SchemaLoader.builder()
			    .schemaJson(schemaJson)
			    .resolutionScope(resolutionScope)
			    .build();
		} else {
			loader = SchemaLoader.builder()
			    .schemaJson(schemaJson)
			    .build();
		}

		this.schema = loader.load().build();
	}
}
