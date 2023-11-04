package com.example.todo.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.data.model.TodoEntity
import com.example.todo.ui.theme.Dark200
import com.example.todo.ui.theme.Green
import com.example.todo.ui.theme.TextColor
import com.example.todo.utils.Instance.addDate

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.TodoListItem(todoEntity: TodoEntity, onClick: () -> Unit, onDelete: () -> Unit) {
    val color by animateColorAsState(
        targetValue = if (todoEntity.done) Green else Red, label = "", animationSpec = tween(500)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateItemPlacement(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .clickable {
                onClick()
            }
            .padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                        .background(Dark200)
                        .padding(4.dp)
                ) {
                    Row {
                        AnimatedVisibility(
                            visible = !todoEntity.done,
                            enter = scaleIn() + fadeIn(),
                            exit = scaleOut() + fadeOut()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "",
                                tint = color
                            )
                        }
                        AnimatedVisibility(
                            visible = todoEntity.done,
                            enter = scaleIn() + fadeIn(),
                            exit = scaleOut() + fadeOut()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "",
                                tint = color
                            )
                        }
                    }
                }
                Column {
                    Text(
                        text = todoEntity.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = White
                    )
                    Text(text = todoEntity.subTitle, fontSize = 12.sp, color = TextColor)
                }
            }
            Box(modifier = Modifier
                .clickable { onDelete() }
                .size(28.dp)
                .clip(CircleShape)
                .background(Dark200)
                .padding(4.dp), contentAlignment = Alignment.Center) {
                Icon(
                    Icons.Default.Delete, contentDescription = null, tint = White
                )
            }
        }
        Text(
            text = todoEntity.addDate,
            fontSize = 10.sp,
            color = TextColor,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun test() {

}