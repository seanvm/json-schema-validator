# JSON Schema Validator

### What is this?

This is a Java wrapper for the [org.everit.json.schema](https://github.com/everit-org/json-schema) JSON Schema validation library. This handles basic implementation and includes dependencies in an uber-jar. This was built for use in ColdFusion applications to simplify JSON Schema validation. In particular, the uber-jar is useful when adding to the classpath is not possible, or where implementation via JavaLoader is preferred.

### Usage

Example implementation and usage in ColdFusion cfscript:

```cfml
// CFC wrapper
component displayName="JSONSchemaValidator"{
  
  function init(required string schema, string resolutionScope="") {
    if ( Len( Trim( arguments.resolutionScope ) ) ) {
      variables.validator = createObject("java", "ca.vanmulligen.json.schema.Validator").init(arguments.schema, arguments.resolutionScope);
    } else {
      variables.validator = createObject("java", "ca.vanmulligen.json.schema.Validator").init(arguments.schema);
    }
  }

  public struct function isValid(required string schema){
    return DeserializeJson( validator.isValid( arguments.schema ) );
  }
}
```

```
// Usage
savecontent variable="schema" {
  include "your_schema.json";
}

results = new JSONSchemaValidator(schema).isValid(your_json_as_string);
```

This will return JSON:

```json
{
  "valid": "true/false",
  "error": "ValidationException output from org.everit.json.schema.Validate"
}
```

### Download

The jar file is available for download here: [https://github.com/seanvm/json-schema-validator/releases](https://github.com/seanvm/json-schema-validator/releases)
