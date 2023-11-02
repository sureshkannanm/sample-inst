package com.aciworldwide.web.institution.model;

import java.util.Objects;

/**
 * CreateInstitutionRequest
 */


public class CreateInstitutionRequest   {
  private String name = null;

  public CreateInstitutionRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Institution name
   * @return name
   **/
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateInstitutionRequest createInstitutionRequest = (CreateInstitutionRequest) o;
    return Objects.equals(this.name, createInstitutionRequest.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateInstitutionRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
