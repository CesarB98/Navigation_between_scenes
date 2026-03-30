  O objetivo principal deste checkpoint foi evoluir uma estrutura de navegação estática para uma arquitetura dinâmica, capaz de transportar dados entre telas via argumentos, garantindo a tipagem correta e o tratamento de valores padrão (fallbacks).
 
  Explicação das Evoluções Implementadas
  
  A. Implementação de Parâmetros Opcionais (Rota de Pedidos)
A tela de Pedidos foi refatorada para aceitar argumentos de forma não obrigatória.

Evolução: Substituição da rota fixa "pedidos" pela rota com query string "pedidos?cliente={cliente}".

Diferencial Técnico: Utilização de defaultValue na configuração do navArgument. Isso garante que, se nenhum cliente for passado no navController.navigate, a tela exibirá automaticamente "Cliente Genérico", evitando falhas na interface.

  B. Múltiplos Argumentos Obrigatórios e Tipagem (Rota de Perfil)
Diferente da tela de pedidos, a tela de Perfil foi configurada para exigir dados específicos para sua construção.

Evolução: Implementação da rota por path: "perfil/{nome}/{idade}".

Diferencial Técnico: Uso de NavType.IntType para o argumento idade. Isso demonstra o uso de tipagem forte, onde o sistema de navegação converte automaticamente o valor da URL para um inteiro antes de entregá-lo à PerfilScreen.

  C. Refatoração de UI e Injeção de Parâmetros
As funções @Composable foram desacopladas para se tornarem reativas aos dados recebidos.

Mudança na PedidosScreen: Adição do parâmetro cliente: String? e uso de interpolação de string para exibição dinâmica.

Mudança na PerfilScreen: Adição dos parâmetros nome: String e idade: Int, permitindo que a tela seja reutilizada para qualquer usuário apenas alterando a chamada de navegação.

  D. Navegação Dinâmica no Menu
O gatilho de navegação foi atualizado para simular cenários reais de uso:

Exemplo de Chamada: navController.navigate("perfil/Fulano de Tal/27").

Isso demonstra como o aplicativo lida com espaços em strings e múltiplos valores em uma única instrução de navegação.
