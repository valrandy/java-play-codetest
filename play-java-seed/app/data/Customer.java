package data;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"name",
"duetime",
"jointime"
})
public class Customer {

@JsonProperty("id")
private Integer id;
@JsonProperty("name")
private String name;
@JsonProperty("duetime")
private String duetime;
@JsonProperty("jointime")
private String jointime;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("duetime")
public String getDuetime() {
return duetime;
}

@JsonProperty("duetime")
public void setDuetime(String duetime) {
this.duetime = duetime;
}

@JsonProperty("jointime")
public String getJointime() {
return jointime;
}

@JsonProperty("jointime")
public void setJointime(String jointime) {
this.jointime = jointime;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}