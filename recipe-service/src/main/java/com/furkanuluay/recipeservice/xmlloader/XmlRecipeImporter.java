package com.furkanuluay.recipeservice.xmlloader;

import com.furkanuluay.recipeservice.entity.Category;
import com.furkanuluay.recipeservice.entity.Ingredient;
import com.furkanuluay.recipeservice.entity.Recipe;
import com.furkanuluay.recipeservice.service.RecipeService;
import com.furkanuluay.recipeservice.xmlloader.xmlentity.RecipeData;
import com.furkanuluay.recipeservice.xmlloader.xmlentity.RecipeXML;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;

/**
 * @author Furkan Uluay
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class XmlRecipeImporter {

  private final RecipeService recipeService;
  private final XmlFileReader xmlFileReader;

  @EventListener(ApplicationReadyEvent.class)
  public void importRecipesFromXmlAfterAppStart() {
    String folderPath = "classpath:/sample_recipes/*.xml";
    try {
      List<String> xmlDataList = readXmlFilesFromFolder(folderPath);
      xmlDataList.forEach(this::importRecipeFromXml);
    } catch (Exception e) {
      log.error("Error importing recipes from XML files: {}", e.getMessage());
      e.printStackTrace();
    }
  }

  public void importRecipeFromXml(String xmlData) {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(RecipeXML.class);
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      RecipeXML recipeXML = (RecipeXML) unmarshaller.unmarshal(new StringReader(xmlData));

      Recipe recipe = convertXmlToRecipe(recipeXML);
      recipeService.createRecipe(recipe);

      log.info("Imported recipes: {}", recipe.getTitle());
    } catch (JAXBException e) {
      log.error("Error parsing XML: {}", e.getMessage());
    }
  }

  private Recipe convertXmlToRecipe(RecipeXML recipeXML) {
    RecipeData recipeData = recipeXML.getRecipeData();
    Recipe recipe = new Recipe();
    recipe.setTitle(recipeData.getHead().getTitle());
    recipe.setCategories(
        recipeData.getHead().getCategories().getCategoryList().stream()
            .map(
                category -> {
                  Category categoryDomain = new Category();
                  categoryDomain.setName(category);
                  return categoryDomain;
                })
            .collect(Collectors.toList()));

    List<Ingredient> ingredients =
        recipeData.getIngredients().getIng().stream()
            .map(this::convertXmlToIngredient)
            .collect(Collectors.toList());

    recipe.setIngredients(ingredients);
    recipe.setDirections(recipeData.getDirections().getStep());
    recipe.setYield(recipeData.getHead().getYield());

    return recipe;
  }

  private Ingredient convertXmlToIngredient(
      com.furkanuluay.recipeservice.xmlloader.xmlentity.Ingredient ingredientXml) {
    Ingredient ingredient = new Ingredient();
    ingredient.setAmount(ingredientXml.getAmt().getQty());
    ingredient.setItem(ingredientXml.getItem());
    ingredient.setUnit(ingredientXml.getAmt().getUnit());
    return ingredient;
  }

  private List<String> readXmlFilesFromFolder(String folderPath) throws IOException {
    Resource[] resources =
        ResourcePatternUtils.getResourcePatternResolver(new PathMatchingResourcePatternResolver())
            .getResources(folderPath);
    List<Resource> resourceList = new ArrayList<>(Arrays.asList(resources));
    return xmlFileReader.readXmlFiles(resourceList);
  }
}
