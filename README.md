# 📋 Gerenciador de Tarefas

Um gerenciador de tarefas simples desenvolvido em Java com interface gráfica Swing, criado como aplicação acadêmica para demonstrar conceitos de programação orientada a objetos e desenvolvimento de interfaces.

## ✨ Funcionalidades

- **Adicionar tarefas** com título, descrição, data de vencimento e prioridade
- **Editar tarefas** existentes para atualizar qualquer informação
- **Excluir tarefas** que não são mais necessárias
- **Listar todas as tarefas** com visualização organizada
- **Interface gráfica intuitiva** desenvolvida com Java Swing

## 🔍 Prévia da Aplicação
![image](https://github.com/user-attachments/assets/2bba1da5-f196-4588-a0a5-a34b6968c1d7)


## 🚀 Como Usar

### Pré-requisitos

- Java 8 ou superior instalado
- IDE Java (Eclipse, IntelliJ IDEA, NetBeans) ou linha de comando para compilação

### Executando a Aplicação

1. Clone este repositório:
   git clone https://github.com/seu-usuario/gerenciador-tarefas.git
   cd gerenciador-tarefas
   ```

2. Compile e execute a aplicação:
   javac Main.java
   java Main
   ```

3. Utilizando a interface:
   - Para **adicionar uma tarefa**: Preencha os campos e clique em "Adicionar Tarefa"
   - Para **editar uma tarefa**: Selecione-a na lista, modifique os campos e clique em "Editar Tarefa"
   - Para **excluir uma tarefa**: Selecione-a na lista e clique em "Excluir Tarefa"
   - Para **limpar os campos**: Clique em "Limpar Campos"

## 📦 Estrutura do Projeto

### Classes Principais

| Classe | Responsabilidade |
|--------|-----------------|
| `GerenciadorTarefas` | Gerencia operações CRUD para tarefas |
| `JanelaPrincipal` | Implementa a interface gráfica Swing |
| `Main` | Ponto de entrada da aplicação |
| `Tarefa` | Modelo que representa uma tarefa |

## 🧪 Exemplo de Uso

```java
// Criando um gerenciador de tarefas
GerenciadorTarefas gerenciador = new GerenciadorTarefas();

// Adicionando tarefas
Tarefa tarefa1 = new Tarefa("Estudar Java", "2025-04-01", "Estudar conceitos de Java", "Alta");
gerenciador.adicionarTarefa(tarefa1);

Tarefa tarefa2 = new Tarefa("Comprar materiais", "2025-03-15", "Comprar materiais para o projeto", "Média");
gerenciador.adicionarTarefa(tarefa2);

// Listando todas as tarefas
System.out.println(gerenciador.listarTarefas());
```

## 🛠️ Tecnologias Utilizadas

- **Java 8+** - Linguagem de programação
- **Swing** - Biblioteca gráfica para interface do usuário
- **JList** - Componente para exibição de listas
- **JTextField** e **JTextArea** - Componentes para entrada de texto
- **JComboBox** - Componente para seleção de opções

## 🤝 Como Contribuir

1. Faça um fork do projeto
2. Crie uma nova branch (`git checkout -b feature/nova-funcionalidade`)
3. Faça commit das suas alterações (`git commit -m 'Adiciona nova funcionalidade'`)
4. Envie para a branch remota (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE] https://opensource.org/licenses/MIT para detalhes.

## ✍️ Autor

**Augusto Domingos Luís** - Desenvolvedor e responsável pelo projeto

⭐️ De: https://github.com/augustodluis
