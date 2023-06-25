package com.furkanuluay.recipeservice.xmlloader.xmlentity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "recipeml")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecipeXML {

  @XmlElement(name = "recipe")
  private RecipeData recipeData;
}
