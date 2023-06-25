package com.furkanuluay.recipeservice.xmlloader.xmlentity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Categories {

  @XmlElement(name = "cat")
  private List<String> categoryList;
}
