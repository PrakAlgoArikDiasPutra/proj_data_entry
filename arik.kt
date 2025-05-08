package com.example.projek

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TransaksiScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransaksiScreen() {
    val context = LocalContext.current

    //untuk menyimpan teks yang diinput dan menyimpan lokasi file yg dipilih pengguna
    var inputData by remember { mutableStateOf("") }
    var fileUri by remember { mutableStateOf<Uri?>(null) }

    //file picker
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        fileUri = uri
        uri?.let {
            Toast.makeText(context, "File dipilih: ${it.path}", Toast.LENGTH_SHORT).show()
        }
    }

    //background berwarna hijau gelap
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6F765C))
            .padding(16.dp)
    ) {
        //judul
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = "TRANSAKSI",
                fontSize = 24.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        //roundshape background berwarna hijau gelap
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF333D2E))
        ) {
            //kolom input data
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Input Data", color = Color.White)
                OutlinedTextField(
                    value = inputData,
                    onValueChange = { inputData = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.DarkGray,
                        unfocusedContainerColor = Color.DarkGray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                //tombol upload
                Button(
                    onClick = {
                        launcher.launch("*/*") // Bisa disesuaikan ke "image/*" jika hanya gambar
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.Black
                    )
                ) {
                    Text("UPLOAD DOKUMEN")
                }

                Spacer(modifier = Modifier.height(16.dp))

                //tombol simpan
                Button(
                    onClick = {
                        Toast
                            .makeText(context, "Data disimpan: $inputData", Toast.LENGTH_SHORT)
                            .show()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = inputData.isNotEmpty() && fileUri != null,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Text("SAVE")
                }
            }
        }
    }
}


