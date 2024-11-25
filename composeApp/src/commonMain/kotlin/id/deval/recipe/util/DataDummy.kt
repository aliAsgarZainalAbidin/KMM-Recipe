package id.deval.recipe.util

import id.deval.recipe.domain.model.NotificationModel
import id.deval.recipe.domain.model.Recipe
import id.deval.recipe.domain.model.RecipeStep
import id.deval.recipe.domain.model.User

object DataDummy {

    val recipes = listOf(
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
                RecipeStep(1, "Boil spaghetti in salted water until al dente. Boil spaghetti in salted water until al dente. Boil spaghetti in salted water until al dente. Boil spaghetti in salted water until al dente. Boil spaghetti in salted water until al dente. Boil spaghetti in salted water until al dente. Boil spaghetti in salted water until al dente. ", null),
                RecipeStep(2, "Heat olive oil, cook onions and garlic until soft. Heat olive oil, cook onions and garlic until soft. Heat olive oil, cook onions and garlic until soft. Heat olive oil, cook onions and garlic until soft. Heat olive oil, cook onions and garlic until soft. ", null),
                RecipeStep(3, "Add ground beef and cook until browned. Add ground beef and cook until browned. Add ground beef and cook until browned. Add ground beef and cook until browned. Add ground beef and cook until browned. ", null),
                RecipeStep(4, "Add tomato sauce, salt, and pepper. Simmer for 20 minutes. Add tomato sauce, salt, and pepper. Simmer for 20 minutes. Add tomato sauce, salt, and pepper. Simmer for 20 minutes. Add tomato sauce, salt, and pepper. Simmer for 20 minutes. ", null),
                RecipeStep(5, "Serve sauce over spaghetti and enjoy. Serve sauce over spaghetti and enjoy. Serve sauce over spaghetti and enjoy. Serve sauce over spaghetti and enjoy. Serve sauce over spaghetti and enjoy. ", null)
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
                RecipeStep(1,  "Sauté onion, garlic, and ginger in oil until fragrant.", null),
                RecipeStep(2,  "Add chicken and cook until browned.", null),
                RecipeStep(3,  "Stir in curry powder, coconut milk, salt, and pepper.", null),
                RecipeStep(4,  "Simmer for 30 minutes until chicken is cooked through.", null)
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
                RecipeStep(1,  "Mix flour, sugar, and salt.", null),
                RecipeStep(2,  "Add milk and egg, and stir until smooth.", null),
                RecipeStep(3,  "Heat a pan with butter, pour batter, and cook until golden.", null),
                RecipeStep(4,  "Flip and cook the other side until done.", null)
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
                RecipeStep(1,  "Cook ground beef with seasoning.", null),
                RecipeStep(2,  "Fill taco shells with beef and toppings.", null),
                RecipeStep(3,  "Serve with sour cream.", null)
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
                RecipeStep(1,  "Chop lettuce and add to a bowl.", null),
                RecipeStep(2,  "Add croutons and Parmesan cheese.", null),
                RecipeStep(3,  "Toss with Caesar dressing and serve.", null)
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
                RecipeStep(1,  "Preheat oven to 350°F (175°C).", null),
                RecipeStep(2,  "Mix dry ingredients together.", null),
                RecipeStep(3,  "Add wet ingredients and stir until smooth.", null),
                RecipeStep(4,  "Pour batter into a greased pan and bake for 30-35 mins.", null)
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
                RecipeStep(1,  "Heat oil in a wok, add garlic and ginger.", null),
                RecipeStep(2,  "Add vegetables and stir fry until tender.", null),
                RecipeStep(3,  "Add soy sauce and cook for another 2 minutes.", null)
            ),
            isLiked = false
        )
    )

    val notifications = listOf(
        NotificationModel.Follow(
            id = 1,
            fromUser = arrayListOf(User(
                id = 101,
                username = "chef_master",
                totalRecipes = 25,
                following = 150,
                followers = 2000,
                liked = arrayListOf(),
                recipes = arrayListOf()
            )),
            message = "followed you",
            time = "2024-11-21T10:30:00",
            isFollowed = true
        ),
        NotificationModel.Liked(
            id = 2,
            fromUser = arrayListOf(User(
                id = 102,
                username = "baking_queen",
                totalRecipes = 40,
                following = 300,
                followers = 5000,
                liked = arrayListOf(),
                recipes = arrayListOf()
            )),
            message = "liked your recipe",
            time = "2024-11-21T11:00:00"
        ),
        NotificationModel.Follow(
            id = 3,
            fromUser = arrayListOf(User(
                id = 103,
                username = "foodie101",
                totalRecipes = 10,
                following = 120,
                followers = 800,
                liked = arrayListOf(),
                recipes = arrayListOf()
            )),
            message = "followed you",
            time = "2024-11-10T11:15:00",
            isFollowed = false
        ),
        NotificationModel.Liked(
            id = 4,
            fromUser = arrayListOf(User(
                id = 104,
                username = "grill_master",
                totalRecipes = 30,
                following = 200,
                followers = 3000,
                liked = arrayListOf(),
                recipes = arrayListOf()
            )),
            message = "liked your recipe",
            time = "2024-11-10T11:45:00"
        ),
        NotificationModel.Follow(
            id = 5,
            fromUser = arrayListOf(User(
                id = 105,
                username = "vegan_vibes",
                totalRecipes = 15,
                following = 100,
                followers = 1200,
                liked = arrayListOf(),
                recipes = arrayListOf()
            )),
            message = "followed you",
            time = "2024-11-10T12:00:00",
            isFollowed = true
        ),
        NotificationModel.Liked(
            id = 6,
            fromUser = arrayListOf(User(
                id = 106,
                username = "dessert_lover",
                totalRecipes = 50,
                following = 400,
                followers = 7000,
                liked = arrayListOf(),
                recipes = arrayListOf()
            )),
            message = "liked your recipe",
            time = "2024-11-19T12:30:00"
        ),
        NotificationModel.Follow(
            id = 7,
            fromUser = arrayListOf(User(
                id = 107,
                username = "spicy_kitchen",
                totalRecipes = 20,
                following = 180,
                followers = 2500,
                liked = arrayListOf(),
                recipes = arrayListOf()
            )),
            message = "followed you",
            time = "2024-11-19T13:00:00",
            isFollowed = false
        ),
        NotificationModel.Liked(
            id = 8,
            fromUser = arrayListOf(User(
                id = 108,
                username = "healthy_meals",
                totalRecipes = 35,
                following = 220,
                followers = 4000,
                liked = arrayListOf(),
                recipes = arrayListOf()
            )),
            message = "liked your recipe",
            time = "2024-11-19T13:30:00"
        ),
        NotificationModel.Follow(
            id = 9,
            fromUser = arrayListOf(User(
                id = 109,
                username = "quick_bites",
                totalRecipes = 12,
                following = 90,
                followers = 600,
                liked = arrayListOf(),
                recipes = arrayListOf()
            )),
            message = "followed you",
            time = "2024-11-19T14:00:00",
            isFollowed = true
        ),
        NotificationModel.Liked(
            id = 10,
            fromUser = arrayListOf(User(
                id = 110,
                username = "fusion_chef",
                totalRecipes = 45,
                following = 350,
                followers = 5500,
                liked = arrayListOf(),
                recipes = arrayListOf()
            )),
            message = "liked your recipe",
            time = "2024-11-19T14:30:00"
        )
    )

}