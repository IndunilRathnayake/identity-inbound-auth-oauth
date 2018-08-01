package org.wso2.carbon.identity.oauth2.dcr.endpoint.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class CustomMetadataDTO  {
  
  
  
  private String name = null;
  
  
  private List<String> value = new ArrayList<String>();

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("value")
  public List<String> getValue() {
    return value;
  }
  public void setValue(List<String> value) {
    this.value = value;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomMetadataDTO {\n");
    
    sb.append("  name: ").append(name).append("\n");
    sb.append("  value: ").append(value).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
