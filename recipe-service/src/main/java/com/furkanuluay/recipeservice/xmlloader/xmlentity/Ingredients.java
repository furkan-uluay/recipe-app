package com.furkanuluay.recipeservice.xmlloader.xmlentity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.util.List;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Ingredients {

  private List<Ingredient> ing;
}
