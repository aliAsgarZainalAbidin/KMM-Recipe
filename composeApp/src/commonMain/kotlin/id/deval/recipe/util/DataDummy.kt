package id.deval.recipe.util

import id.deval.recipe.domain.model.Recipe
import id.deval.recipe.domain.model.RecipeStep

object DataDummy {

    val dummyRecipes = listOf(
        Recipe(
            id = "1",
            name = "Spaghetti Bolognese",
            category = "Food",
            times = "30 mins",
            recipeOwner = "John Doe",
            idRecipeOwner = "101",
            likes = 125,
            description = "A classic Italian pasta dish with a rich meat sauce.",
            ingredients = listOf("Spaghetti", "Ground beef", "Tomato sauce", "Onion", "Garlic", "Olive oil", "Salt", "Pepper"),
            steps = listOf(
                RecipeStep("1", "1", "Boil spaghetti in salted water until al dente.", null),
                RecipeStep("2", "1", "Heat olive oil, cook onions and garlic until soft.", null),
                RecipeStep("3", "1", "Add ground beef and cook until browned.", null),
                RecipeStep("4", "1", "Add tomato sauce, salt, and pepper. Simmer for 20 minutes.", null),
                RecipeStep("5", "1", "Serve sauce over spaghetti and enjoy.", null)
            ),
            isLiked = true
        ),
        Recipe(
            id = "2",
            name = "Chicken Curry",
            category = "Food",
            times = "45 mins",
            recipeOwner = "Jane Smith",
            idRecipeOwner = "102",
            likes = 200,
            description = "A flavorful chicken curry with a spicy sauce.",
            ingredients = listOf("Chicken", "Coconut milk", "Curry powder", "Onion", "Garlic", "Ginger", "Salt", "Pepper"),
            steps = listOf(
                RecipeStep("1", "2", "Sauté onion, garlic, and ginger in oil until fragrant.", null),
                RecipeStep("2", "2", "Add chicken and cook until browned.", null),
                RecipeStep("3", "2", "Stir in curry powder, coconut milk, salt, and pepper.", null),
                RecipeStep("4", "2", "Simmer for 30 minutes until chicken is cooked through.", null)
            ),
            isLiked = false
        ),
        Recipe(
            id = "3",
            name = "Pancakes",
            category = "Drink",
            times = "20 mins",
            recipeOwner = "Emily Brown",
            idRecipeOwner = "103",
            likes = 89,
            description = "Fluffy pancakes perfect for breakfast.",
            ingredients = listOf("Flour", "Milk", "Egg", "Sugar", "Salt", "Butter"),
            steps = listOf(
                RecipeStep("1", "3", "Mix flour, sugar, and salt.", null),
                RecipeStep("2", "3", "Add milk and egg, and stir until smooth.", null),
                RecipeStep("3", "3", "Heat a pan with butter, pour batter, and cook until golden.", null),
                RecipeStep("4", "3", "Flip and cook the other side until done.", null)
            ),
            isLiked = true
        ),
        Recipe(
            id = "4",
            name = "Beef Tacos",
            category = "Drink",
            times = "25 mins",
            recipeOwner = "Carlos Diaz",
            idRecipeOwner = "104",
            likes = 140,
            description = "Crispy tacos with seasoned beef and fresh toppings.",
            ingredients = listOf("Taco shells", "Ground beef", "Cheese", "Lettuce", "Tomato", "Onion", "Sour cream"),
            steps = listOf(
                RecipeStep("1", "4", "Cook ground beef with seasoning.", null),
                RecipeStep("2", "4", "Fill taco shells with beef and toppings.", null),
                RecipeStep("3", "4", "Serve with sour cream.", null)
            ),
            isLiked = false
        ),
        Recipe(
            id = "5",
            name = "Caesar Salad",
            category = "Food",
            times = "15 mins",
            recipeOwner = "Anna Lee",
            idRecipeOwner = "105",
            likes = 75,
            description = "A classic Caesar salad with crispy croutons and creamy dressing.",
            ingredients = listOf("Lettuce", "Croutons", "Parmesan cheese", "Caesar dressing"),
            steps = listOf(
                RecipeStep("1", "5", "Chop lettuce and add to a bowl.", null),
                RecipeStep("2", "5", "Add croutons and Parmesan cheese.", null),
                RecipeStep("3", "5", "Toss with Caesar dressing and serve.", null)
            ),
            isLiked = true
        ),
        Recipe(
            id = "6",
            name = "Chocolate Cake",
            category = "Drink",
            times = "1 hr",
            recipeOwner = "Lucy Nguyen",
            idRecipeOwner = "106",
            likes = 300,
            description = "A rich and moist chocolate cake for dessert lovers.",
            ingredients = listOf("Flour", "Cocoa powder", "Sugar", "Eggs", "Butter", "Baking powder", "Milk"),
            steps = listOf(
                RecipeStep("1", "6", "Preheat oven to 350°F (175°C).", null),
                RecipeStep("2", "6", "Mix dry ingredients together.", null),
                RecipeStep("3", "6", "Add wet ingredients and stir until smooth.", null),
                RecipeStep("4", "6", "Pour batter into a greased pan and bake for 30-35 mins.", null)
            ),
            isLiked = true
        ),
        Recipe(
            id = "7",
            name = "Vegetable Stir Fry",
            category = "Drink",
            times = "20 mins",
            recipeOwner = "Tom Green",
            idRecipeOwner = "107",
            likes = 95,
            description = "A quick and healthy vegetable stir fry.",
            ingredients = listOf("Broccoli", "Carrot", "Bell pepper", "Soy sauce", "Garlic", "Ginger"),
            steps = listOf(
                RecipeStep("1", "7", "Heat oil in a wok, add garlic and ginger.", null),
                RecipeStep("2", "7", "Add vegetables and stir fry until tender.", null),
                RecipeStep("3", "7", "Add soy sauce and cook for another 2 minutes.", null)
            ),
            isLiked = false
        )
    )

}