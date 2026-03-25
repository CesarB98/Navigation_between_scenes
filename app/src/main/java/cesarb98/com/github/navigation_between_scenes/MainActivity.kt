package cesarb98.com.github.navigation_between_scenes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cesarb98.com.github.navigation_between_scenes.screens.LoginScreen
import cesarb98.com.github.navigation_between_scenes.screens.MenuScreen
import cesarb98.com.github.navigation_between_scenes.screens.PedidosScreen
import cesarb98.com.github.navigation_between_scenes.screens.PerfilScreen
import cesarb98.com.github.navigation_between_scenes.ui.theme.Navigation_between_cenesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigation_between_cenesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login",
                    ) {
                        composable(route = "login") {
                            LoginScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(route = "menu") {
                            MenuScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        //A rota de pedidos foi atualizada de estática para suportar argumentos opcionais via query string.
                        composable(
                            route = "pedidos?cliente={cliente}", // O '?' indica que o parâmetro 'cliente' é opcional.
                            arguments = listOf(navArgument("cliente") {
                                defaultValue = "Cliente Genérico" // Define um valor padrão caso nenhum dado seja enviado.
                            })
                        ) {
                            // Extração do argumento 'cliente' do NavBackStackEntry para repassar à tela de Pedidos.
                            PedidosScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController,
                                it.arguments?.getString("cliente")
                            )
                        }
                        //Com essa mudança, agora a rota espera um argumento para fazer a navegação.
                        //Deixa de ser um endereço fixo e passa a aceitar variaveis diretamento no URL
                        //"it.arguments" pega o valor que será utilizado na navegação, e define como usuário generido
                        //quando nao existir parâmetro.
                        composable(
                            // A rota de perfil foi expandida para aceitar múltiplos parâmetros obrigatórios: {nome} e {idade}.
                            route = "perfil/{nome}/{idade}",
                            // O parâmetro 'idade' agora é tratado como NavType.IntType,
                            // garantindo que o Compose faça o cast automático do valor da URL para um Inteiro.
                            arguments = listOf(
                                navArgument("nome") { type = NavType.StringType },
                                navArgument("idade") { type = NavType.IntType }
                            )
                        ) {
                            val nome: String? = it.arguments?.getString("nome", "Usuário Genérico")
                            // Recuperação do novo argumento 'idade'. O valor 0 é usado como fallback (padrão).
                            val idade: Int? = it.arguments?.getInt("idade", 0)
                            PerfilScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController,
                                nome!!,
                                // Repasse do parâmetro idade para a Screen.
                                idade!!
                            )
                        }
                    }
                }
            }
        }
    }
}
