package com.dnegu.foodyape.data.model.response

import com.dnegu.foodyape.domain.model.Ingredient
import com.dnegu.foodyape.domain.model.Origin
import com.dnegu.foodyape.domain.model.Recipes

data class RecipesResponse(
    val status: String?,
    val totalResults: Int?,
    val articles: List<Recipes>?,
) {
    companion object {
        fun mock() = RecipesResponse(
            status = "ok",
            totalResults = 1000,
            articles = listOf(
                Recipes(
                    id = 1,
                    name = "Crock Pot Roast",
                    ingredients = listOf(
                        Ingredient(
                            quantity = "1",
                            name = "beef roast",
                            type= "Meat"
                        ),
                        Ingredient(
                            quantity = "1 package",
                            name = "brown gravy mix",
                            type= "Baking"
                        ),
                        Ingredient(
                            quantity = "1 package",
                            name = "dried Italian salad dressing mix",
                            type= "Condiments"
                        ),
                        Ingredient(
                            quantity = "1 package",
                            name = "dry ranch dressing mix",
                            type= "Condiments"
                        )
                    ),
                    steps = listOf(
                        "Place beef roast in crock pot.",
                        "Mix the dried mixes together in a bowl and sprinkle over the roast.",
                        "Pour the water around the roast.",
                        "Cook on low for 7-9 hours."
                    ),
                    timers = listOf(
                        0,
                        0,
                        0,
                        420
                    ),
                    imageURL = "http://img.sndimg.com/food/image/upload/w_266/v1/img/recipes/27/20/8/picVfzLZo.jpg",
                    originalURL = "http://www.food.com/recipe/to-die-for-crock-pot-roast-27208",
                    rating = "4.5/5.0",
                    origin = Origin(
                        country = "France",
                        latlng = "46.60138648008815, 2.6614330927475613"
                    ),
                    description = "Pot roast isn't a cut of meat itself. It's a savory, braised beef dish made by browning the meat before cooking it “low and slow” in a covered casserole dish or Dutch oven. In most recipes, you will brown the roast on the stovetop first, then transfer it to the oven or a slow cooker."
                ),
                Recipes(
                    id = 2,
                    name = "Roasted Asparagus",
                    ingredients = listOf(
                        Ingredient(
                            quantity = "1 lb",
                            name = "asparagus",
                            type= "Produce"
                        ),
                        Ingredient(
                            quantity = "1 1/2 tbsp",
                            name = "olive oil",
                            type= "Condiments"
                        ),
                        Ingredient(
                            quantity = "1/2 tsp",
                            name = "kosher salt",
                            type= "Baking"
                        )
                    ),
                    steps = listOf(
                        "Preheat oven to 425°F.",
                        "Cut off the woody bottom part of the asparagus spears and discard.",
                        "With a vegetable peeler, peel off the skin on the bottom 2-3 inches of the spears (this keeps the asparagus from being all.\",string.\", and if you eat asparagus you know what I mean by that).",
                        "Place asparagus on foil-lined baking sheet and drizzle with olive oil.",
                        "Sprinkle with salt.",
                        "With your hands, roll the asparagus around until they are evenly coated with oil and salt.",
                        "Roast for 10-15 minutes, depending on the thickness of your stalks and how tender you like them.",
                        "They should be tender when pierced with the tip of a knife.",
                        "The tips of the spears will get very brown but watch them to prevent burning.",
                        "They are great plain, but sometimes I serve them with a light vinaigrette if we need something acidic to balance out our meal."
                    ),
                    timers = listOf(
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        10,
                        0,
                        0,
                        0
                    ),
                    imageURL = "http://img.sndimg.com/food/image/upload/w_266/v1/img/recipes/50/84/7/picMcSyVd.jpg",
                    originalURL = "http://www.food.com/recipe/roasted-asparagus-50847",
                    rating = "4.1/5.0",
                    origin = Origin(
                        country = "Eastern Mediterranean",
                        latlng = "44.68410082969865, 13.238570016884545"
                    ),
                    description = "Roasted asparagus seasoned with garlic, lemon, and Parmesan cheese. This recipe is super quick to prep and roasting removes any bitterness. Try it next to lamb or grilled fish."
                )
            )
        )
    }
}