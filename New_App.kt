package com.example.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                WebPageLauncher(this,intent)
            }
        }
    }
}

@Composable
fun WebPageLauncher(context: Context, intent: Intent) {
    val webUrl = "http://192.168.1.13:3000/"  // Change to your Node.js server URL

    var receivedMessage by remember { mutableStateOf("No Data Received") }

    // Check if intent contains data from the webpage
    intent.data?.let { uri ->
        receivedMessage = uri.getQueryParameter("message") ?: "No message"
        Log.d("ReceivedData", "Message: $receivedMessage")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { openCustomTab(context, webUrl) },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Open Web Page")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Received from Web:", style = MaterialTheme.typography.bodyLarge)
        SelectionContainer {
            Text(text = receivedMessage, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

fun openCustomTab(context: Context, url: String) {
//    val builder = CustomTabsIntent.Builder()
//    val customTabsIntent = builder.build()
//    customTabsIntent.launchUrl(context, Uri.parse(url))
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        component = ComponentName("com.android.chrome", "com.google.android.apps.chrome.Main")
    }

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.e("ChromeLauncher", "Chrome is not installed!", e)
    }
}
