package com.furkanuluay.recipeservice.xmlloader.xmlentity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Amount {

  private String qty;

  private String unit;
}
