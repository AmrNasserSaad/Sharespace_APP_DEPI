package com.example.shareworkspace.presentation.main.confirmation

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shareworkspace.presentation.authentication.components.NumberOfPeopleTF
import com.example.shareworkspace.presentation.main.home.components.MainTopAppBar
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.Primary500
import com.example.shareworkspace.presentation.ui.theme.TextColor
import java.util.concurrent.TimeUnit

@Composable
fun RequestScreen(navController: NavController) {
    var fromTime by remember { mutableStateOf("Select Time") }
    var toTime by remember { mutableStateOf("Select Time") }
    var timeDifference by remember { mutableStateOf<String?>(null) }

    val onRequestClick: () -> Unit = {
        navController.navigate("Confirm_Screen")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 12.dp,
                bottom = 46.dp
            )
    ) {
        MainTopAppBar(title = "") {
            navController.popBackStack()
        }
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Malone Sharespace",
                fontWeight = FontWeight.Bold,
                fontFamily = Montserrat,
                fontSize = 24.sp,
                color = TextColor
            )
            Text(
                text = "Mansoura, jehan",
                fontWeight = FontWeight.Normal,
                fontFamily = Montserrat,
                fontSize = 16.sp,
                color = TextColor
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Number of people",
                fontWeight = FontWeight.Bold,
                fontFamily = Montserrat,
                fontSize = 16.sp,
                color = TextColor
            )
            NumberOfPeopleTF()
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "The appointment",
                fontWeight = FontWeight.Bold,
                fontFamily = Montserrat,
                fontSize = 16.sp,
                color = TextColor
            )
            DatePicker()
            Spacer(modifier = Modifier.height(16.dp))

        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Existing Text and other UI elements...

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "From:")
                    TimePicker(label = "From", onTimeSelected = {
                        fromTime = it
                        timeDifference = calculateTimeDifference(fromTime, toTime)
                    })
                }
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "To:")
                    TimePicker(label = "To", onTimeSelected = {
                        toTime = it
                        timeDifference = calculateTimeDifference(fromTime, toTime)
                    })
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            timeDifference?.let {
                Text(
                    text = "Total Hours : $it",
                    fontWeight = FontWeight.Bold,
                    fontFamily = Montserrat,
                    fontSize = 16.sp,
                    color = TextColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            DetailItem(label = "Money", value = "According to time ex: 60 LE ")
        }

        Spacer(modifier = Modifier.weight(0.85f))

        Button(
            onClick = onRequestClick,
            colors = ButtonDefaults.buttonColors(Primary500),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Request",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun DetailItem(label: String, value: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontFamily = Montserrat,
            fontSize = 16.sp,
            color = TextColor
        )
        Text(
            text = value,
            fontWeight = FontWeight.Normal,
            fontFamily = Montserrat,
            fontSize = 14.sp,
            color = TextColor
        )

    }
}


@Preview(showBackground = true)
@Composable
private fun RequestScreenPreview() {
    RequestScreen(rememberNavController())

}

@Composable
fun TimePicker(label: String, onTimeSelected: (String) -> Unit) {
    val context = LocalContext.current
    var timeText by remember { mutableStateOf("Select Time") }
    val calendar = Calendar.getInstance()

    // TimePickerDialog to show the time picker
    val timePickerDialog = TimePickerDialog(
        context,
        { _: TimePicker, hour: Int, minute: Int ->
            timeText = String.format("%02d:%02d", hour, minute)
            onTimeSelected(timeText)  // Call the callback to update the time
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        false // Set to true for 24-hour time, false for AM/PM
    )

    Row(
        modifier = Modifier
            .clickable { timePickerDialog.show() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label: $timeText",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
        )
        IconButton(onClick = { timePickerDialog.show() }) {
            Icon(Icons.Outlined.ArrowDropDown, contentDescription = "ArrowDropDown")
        }
    }
}


@Composable
fun DatePicker() {
    val context = LocalContext.current
    var dateText by remember { mutableStateOf("Select Date") }
    val calendar = Calendar.getInstance()

    // DatePickerDialog to show the date picker
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dateText =
                String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year) // Formatting the date
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { datePickerDialog.show() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Text that displays the selected date and triggers the DatePickerDialog
        Text(
            text = dateText,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
        )
        IconButton(onClick = { datePickerDialog.show() }) {
            Icon(Icons.Outlined.ArrowDropDown, contentDescription = "ArrowDropDown")
        }
    }
}



// Helper function to calculate the difference between two times
fun calculateTimeDifference(from: String, to: String): String? {
    // Check if time inputs are valid (i.e., not "Select Time")
    if (from == "Select Time" || to == "Select Time") return null

    // Split the time strings into hours and minutes
    val fromParts = from.split(":")
    val toParts = to.split(":")

    val fromHour = fromParts[0].toInt()
    val fromMinute = fromParts[1].toInt()

    val toHour = toParts[0].toInt()
    val toMinute = toParts[1].toInt()

    // Set the time in the calendar for comparison
    val fromCalendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, fromHour)
        set(Calendar.MINUTE, fromMinute)
    }

    val toCalendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, toHour)
        set(Calendar.MINUTE, toMinute)
    }

    // Check if the "to" time is not before or equal to "from" time
    if (toCalendar.timeInMillis <= fromCalendar.timeInMillis) {
        return "Invalid: End time must be after start time"
    }

    // Calculate the difference in milliseconds
    val diffInMillis = toCalendar.timeInMillis - fromCalendar.timeInMillis

    // Convert milliseconds to hours and minutes
    val hours = TimeUnit.MILLISECONDS.toHours(diffInMillis)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis) % 60

    // Ensure that the time difference is not zero or negative
    return if (hours > 0 || minutes > 0) {
        String.format("%02d hours, %02d minutes", hours, minutes)
    } else {
        "Invalid: Time difference cannot be zero or negative"
    }
}


