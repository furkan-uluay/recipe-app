package com.furkanuluay.recipeservice.xmlloader.xmlentity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Head {

  private String title;

  private Categories categories;

  private String yield;
}
