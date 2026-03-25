package cesarb98.com.github.navigation_between_scenes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MenuScreen(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF2C4EC7))
            .padding(32.dp)
    ) {
        Text(
            text = "MENU",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Button(
                //Agora a rota passa a enviar um dado real ("Fulano de Tal").
                //Assim, o NavHost identifica o parâmetro e entrega a tela de destino.
                onClick = { navController.navigate("perfil/Fulano de Tal/27") },
                // adição de envio de dados obrigatórios na ordem definida pela rota
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.size(width = 200.dp, height = 48.dp)
            ) {
                Text(
                    text = "Perfil",
                    fontSize = 20.sp,
                    color = Color.Blue
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                // A navegação foi atualizada para enviar o parâmetro opcional 'cliente'.
                // Agora, em vez de apenas chamar a rota "pedidos", passamos o valor "Cliente XPTO"
                // utilizando a sintaxe de query parameter (?cliente=valor).
                onClick = { navController.navigate("pedidos?cliente=Cliente XPTO") },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.size(width = 200.dp, height = 48.dp)
            ) {
                Text(
                    text = "Pedidos",
                    fontSize = 20.sp,
                    color = Color.Blue
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("login") },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.size(width = 200.dp, height = 48.dp)
            ) {
                Text(
                    text = "Sair",
                    fontSize = 20.sp,
                    color = Color.Blue
                )
            }
        }
    }
}