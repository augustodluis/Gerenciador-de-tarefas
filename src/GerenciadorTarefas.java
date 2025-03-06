import java.util.ArrayList;
import java.util.List;

public class GerenciadorTarefas {
    private List<Tarefa> tarefas;

    public GerenciadorTarefas() {
        tarefas = new ArrayList<>();
    }

    // Adicionar uma nova tarefa
    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    // Editar uma tarefa existente
    public void editarTarefa(int indice, Tarefa tarefa) {
        if (indice >= 0 && indice < tarefas.size()) {
            tarefas.set(indice, tarefa);
        }
    }

    // Excluir uma tarefa
    public void excluirTarefa(int indice) {
        if (indice >= 0 && indice < tarefas.size()) {
            tarefas.remove(indice);
        }
    }

    // Listar todas as tarefas
    public List<Tarefa> listarTarefas() {
        return tarefas;
    }
}
