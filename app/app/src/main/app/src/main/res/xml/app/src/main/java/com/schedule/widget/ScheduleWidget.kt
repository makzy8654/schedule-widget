package com.schedule.widget

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

data class Lesson(val num: String, val name: String, val room: String, val teacher: String)

class ScheduleWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val lessons = listOf(
            Lesson("II", "Основы философии", "каб. 304", "Глазунов"),
            Lesson("III", "Планирование МТО...", "каб. 304", "Торгашова"),
            Lesson("IV", "Монтаж гидравлических систем", "каб. 402", "Хазов"),
            Lesson("V", "Монтаж гидравлических систем", "каб. 402", "Хазов")
        )

        provideContent {
            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(ColorProvider(Color(0xFF1E1E1E)))
                    .padding(12.dp)
            ) {
                Text(
                    text = "24/пМПО-391 в (Четверг)",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold, color = ColorProvider(Color.White)),
                    modifier = GlanceModifier.padding(bottom = 8.dp)
                )
                LazyColumn(modifier = GlanceModifier.fillMaxSize()) {
                    items(lessons) { lesson ->
                        Column(
                            modifier = GlanceModifier
                                .fillMaxWidth()
                                .background(ColorProvider(Color(0xFF2C2C2C)))
                                .padding(8.dp)
                                .padding(bottom = 6.dp)
                        ) {
                            Row(modifier = GlanceModifier.fillMaxWidth()) {
                                Text(text = "${lesson.num} пара", style = TextStyle(fontSize = 12.sp, color = ColorProvider(Color(0xFF64B5F6))))
                                Spacer(modifier = GlanceModifier.defaultWeight())
                                Text(text = lesson.room, style = TextStyle(fontSize = 12.sp, color = ColorProvider(Color(0xFF81C784))))
                            }
                            Text(text = lesson.name, style = TextStyle(fontSize = 13.sp, color = ColorProvider(Color.White), fontWeight = FontWeight.Medium))
                            Text(text = lesson.teacher, style = TextStyle(fontSize = 11.sp, color = ColorProvider(Color.LightGray)))
                        }
                    }
                }
            }
        }
    }
}

class ScheduleReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = ScheduleWidget()
}