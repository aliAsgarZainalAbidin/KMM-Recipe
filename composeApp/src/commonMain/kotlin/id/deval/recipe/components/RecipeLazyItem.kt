package id.deval.recipe.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import id.deval.recipe.domain.model.Recipe
import kmm_recipe.composeapp.generated.resources.Onboarding
import kmm_recipe.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

object RecipeLazyItem {

    @Composable
    fun RecipeDefaultItem(
        modifier: Modifier,
        recipe: Recipe,
        onClick: () -> Unit,
        likeButton: @Composable BoxScope.() -> Unit,
    ) {
        Column(
            modifier = modifier
                .clickable { onClick() }
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(Res.drawable.Onboarding),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = recipe.recipeOwner,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp).fillMaxWidth()
                )
            }
            Box(
                modifier = Modifier.wrapContentSize()
            ) {
                Image(
                    painter = painterResource(Res.drawable.Onboarding),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                likeButton()
            }
            Text(
                text = recipe.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineMedium
            )
            Row {
                Text(
                    text = recipe.category,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = " • ${recipe.times}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}