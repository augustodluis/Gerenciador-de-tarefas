import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class JanelaPrincipal extends JFrame {
    private GerenciadorTarefas gerenciador;
    private DefaultListModel<String> modeloListaTarefas;
    private JList<String> listaTarefas;
    private JTextField campoTitulo, campoDataVencimento;
    private JTextArea campoDescricao;
    private JComboBox<String> comboPrioridade;
    private JPanel painelTarefas;
    
    // Cores para o tema da aplicação
    private final Color COR_PRINCIPAL = new Color(70, 130, 180); // Steel Blue
    private final Color COR_SECUNDARIA = new Color(135, 206, 250); // Light Sky Blue
    private final Color COR_DESTAQUE = new Color(30, 144, 255); // Dodger Blue
    private final Color COR_TEXTO = new Color(25, 25, 112); // Midnight Blue
    private final Color COR_BACKGROUND = new Color(240, 248, 255); // Alice Blue
    private final Color COR_BOTAO = new Color(65, 105, 225); // Royal Blue
    private final Color COR_BOTAO_TEXTO = Color.WHITE;

    public JanelaPrincipal() {
        gerenciador = new GerenciadorTarefas();
        modeloListaTarefas = new DefaultListModel<>();
        listaTarefas = new JList<>(modeloListaTarefas);
        
        // Configuração visual da lista de tarefas
        listaTarefas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaTarefas.setBackground(COR_BACKGROUND);
        listaTarefas.setForeground(COR_TEXTO);
        listaTarefas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listaTarefas.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Adicionar um MouseListener para quando uma tarefa for selecionada
        listaTarefas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    carregarDadosTarefa();
                }
            }
        });

        // Configuração da janela principal
        setTitle("✓ Gerenciador de Tarefas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COR_BACKGROUND);
        
        // Definir um ícone para a aplicação (se disponível)
        try {
            // Substitua pelo caminho do seu ícone
            // setIconImage(new ImageIcon("caminho/para/icone.png").getImage());
        } catch (Exception e) {
            System.out.println("Ícone não encontrado.");
        }
        
        // Painel principal com layout mais profissional
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBackground(COR_BACKGROUND);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Painel de título/cabeçalho
        JPanel painelCabecalho = criarPainelCabecalho();
        
        // Painel de formulário para entrada de dados
        JPanel painelFormulario = criarPainelFormulario();
        
        // Painel para a lista de tarefas
        painelTarefas = criarPainelListaTarefas();
        
        // Painel de botões
        JPanel painelBotoes = criarPainelBotoes();
        
        // Adicionar componentes ao painel principal
        painelPrincipal.add(painelCabecalho, BorderLayout.NORTH);
        
        // Painel central dividido entre formulário e lista de tarefas
        JSplitPane painelDividido = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT, 
            painelFormulario, 
            painelTarefas
        );
        painelDividido.setDividerLocation(350);
        painelDividido.setDividerSize(5);
        painelDividido.setBorder(null);
        painelDividido.setBackground(COR_BACKGROUND);
        
        painelPrincipal.add(painelDividido, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
        
        add(painelPrincipal);
    }
    
    private JPanel criarPainelCabecalho() {
        JPanel painelCabecalho = new JPanel(new BorderLayout());
        painelCabecalho.setBackground(COR_PRINCIPAL);
        painelCabecalho.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        JLabel labelTitulo = new JLabel("Gerenciador de Tarefas", JLabel.CENTER);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        labelTitulo.setForeground(Color.WHITE);
        
        JLabel labelSubtitulo = new JLabel("Organize suas tarefas com eficiência", JLabel.CENTER);
        labelSubtitulo.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        labelSubtitulo.setForeground(new Color(240, 248, 255));
        
        painelCabecalho.add(labelTitulo, BorderLayout.CENTER);
        painelCabecalho.add(labelSubtitulo, BorderLayout.SOUTH);
        
        return painelCabecalho;
    }
    
    private JPanel criarPainelFormulario() {
        JPanel painelFormulario = new JPanel(new BorderLayout(0, 10));
        painelFormulario.setBackground(COR_BACKGROUND);
        painelFormulario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COR_PRINCIPAL, 1, true),
                "Detalhes da Tarefa",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                COR_PRINCIPAL
            )
        ));
        
        JPanel painelCampos = new JPanel(new GridBagLayout());
        painelCampos.setBackground(COR_BACKGROUND);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Título
        JLabel labelTitulo = new JLabel("Título:");
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelTitulo.setForeground(COR_TEXTO);
        campoTitulo = new JTextField(20);
        estilizarCampoTexto(campoTitulo);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        painelCampos.add(labelTitulo, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.9;
        painelCampos.add(campoTitulo, gbc);
        
        // Data de Vencimento
        JLabel labelData = new JLabel("Data de Vencimento:");
        labelData.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelData.setForeground(COR_TEXTO);
        campoDataVencimento = new JTextField(10);
        estilizarCampoTexto(campoDataVencimento);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        painelCampos.add(labelData, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.9;
        painelCampos.add(campoDataVencimento, gbc);
        
        // Prioridade
        JLabel labelPrioridade = new JLabel("Prioridade:");
        labelPrioridade.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelPrioridade.setForeground(COR_TEXTO);
        
        String[] opcoesPrioridade = {"Alta", "Média", "Baixa"};
        comboPrioridade = new JComboBox<>(opcoesPrioridade);
        comboPrioridade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboPrioridade.setBackground(Color.WHITE);
        comboPrioridade.setForeground(COR_TEXTO);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.1;
        painelCampos.add(labelPrioridade, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.9;
        painelCampos.add(comboPrioridade, gbc);
        
        // Descrição
        JLabel labelDescricao = new JLabel("Descrição:");
        labelDescricao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelDescricao.setForeground(COR_TEXTO);
        
        campoDescricao = new JTextArea(8, 20);
        campoDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);
        
        JScrollPane scrollDescricao = new JScrollPane(campoDescricao);
        scrollDescricao.setBorder(BorderFactory.createLineBorder(COR_SECUNDARIA));
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        painelCampos.add(labelDescricao, gbc);
        
        gbc.gridy = 4;
        painelCampos.add(scrollDescricao, gbc);
        
        painelFormulario.add(painelCampos, BorderLayout.CENTER);
        
        return painelFormulario;
    }
    
    private JPanel criarPainelListaTarefas() {
        JPanel painelListaTarefas = new JPanel(new BorderLayout(0, 10));
        painelListaTarefas.setBackground(COR_BACKGROUND);
        painelListaTarefas.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COR_PRINCIPAL, 1, true),
                "Suas Tarefas",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                COR_PRINCIPAL
            )
        ));
        
        JScrollPane scrollLista = new JScrollPane(listaTarefas);
        scrollLista.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Adicionar um painel de pesquisa acima da lista
        JPanel painelPesquisa = new JPanel(new BorderLayout(5, 0));
        painelPesquisa.setBackground(COR_BACKGROUND);
        painelPesquisa.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        JTextField campoPesquisa = new JTextField();
        estilizarCampoTexto(campoPesquisa);
        campoPesquisa.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_SECUNDARIA),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        JLabel iconePesquisa = new JLabel("🔍");
        iconePesquisa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        painelPesquisa.add(iconePesquisa, BorderLayout.WEST);
        painelPesquisa.add(campoPesquisa, BorderLayout.CENTER);
        
        painelListaTarefas.add(painelPesquisa, BorderLayout.NORTH);
        painelListaTarefas.add(scrollLista, BorderLayout.CENTER);
        
        return painelListaTarefas;
    }
    
    private JPanel criarPainelBotoes() {
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        painelBotoes.setBackground(COR_BACKGROUND);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        JButton botaoAdicionar = criarBotaoEstilizado("Adicionar Tarefa", "✚");
        JButton botaoEditar = criarBotaoEstilizado("Editar Tarefa", "✎");
        JButton botaoExcluir = criarBotaoEstilizado("Excluir Tarefa", "✖");
        JButton botaoLimpar = criarBotaoEstilizado("Limpar Campos", "↺");
        
        // Adicionar eventos aos botões
        botaoAdicionar.addActionListener(e -> adicionarTarefa());
        botaoEditar.addActionListener(e -> editarTarefa());
        botaoExcluir.addActionListener(e -> excluirTarefa());
        botaoLimpar.addActionListener(e -> limparCamposEntrada());
        
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoExcluir);
        painelBotoes.add(botaoLimpar);
        
        return painelBotoes;
    }
    
    private JButton criarBotaoEstilizado(String texto, String icone) {
        JButton botao = new JButton(icone + " " + texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setForeground(COR_BOTAO_TEXTO);
        botao.setBackground(COR_BOTAO);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(180, 40));
        
        // Efeito hover
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(COR_DESTAQUE);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                botao.setBackground(COR_BOTAO);
            }
        });
        
        return botao;
    }
    
    private void estilizarCampoTexto(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_SECUNDARIA),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        campo.setCaretColor(COR_PRINCIPAL);
    }
    
    // Método para carregar os dados da tarefa selecionada nos campos
    private void carregarDadosTarefa() {
        int indice = listaTarefas.getSelectedIndex();
        if (indice >= 0) {
            Tarefa tarefa = gerenciador.listarTarefas().get(indice);
            campoTitulo.setText(tarefa.getTitulo());
            campoDescricao.setText(tarefa.getDescricao());
            campoDataVencimento.setText(tarefa.getDataVencimento());
            
            // Definir a prioridade no combobox
            String prioridade = tarefa.getPrioridade();
            if (prioridade != null) {
                for (int i = 0; i < comboPrioridade.getItemCount(); i++) {
                    if (comboPrioridade.getItemAt(i).equalsIgnoreCase(prioridade)) {
                        comboPrioridade.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }
    }
    
    // Método para adicionar uma tarefa
    private void adicionarTarefa() {
        try {
            String titulo = campoTitulo.getText();
            String descricao = campoDescricao.getText();
            String data = campoDataVencimento.getText();
            String prioridade = (String) comboPrioridade.getSelectedItem();

            if (titulo.isEmpty() || data.isEmpty()) {
                throw new IllegalArgumentException("Título e data são obrigatórios!");
            }

            Tarefa tarefa = new Tarefa(titulo, descricao, data, prioridade);
            gerenciador.adicionarTarefa(tarefa);
            atualizarListaTarefas();
            limparCamposEntrada();
            
            // Feedback visual de sucesso
            exibirMensagem("Tarefa adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            exibirMensagem("Erro ao adicionar tarefa: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para editar uma tarefa
    private void editarTarefa() {
        int indice = listaTarefas.getSelectedIndex();
        if (indice >= 0) {
            try {
                String titulo = campoTitulo.getText();
                String descricao = campoDescricao.getText();
                String data = campoDataVencimento.getText();
                String prioridade = (String) comboPrioridade.getSelectedItem();

                if (titulo.isEmpty() || data.isEmpty()) {
                    throw new IllegalArgumentException("Título e data são obrigatórios!");
                }

                Tarefa tarefa = new Tarefa(titulo, descricao, data, prioridade);
                gerenciador.editarTarefa(indice, tarefa);
                atualizarListaTarefas();
                limparCamposEntrada();
                
                // Feedback visual de sucesso
                exibirMensagem("Tarefa atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                exibirMensagem("Erro ao editar tarefa: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            exibirMensagem("Selecione uma tarefa para editar!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Método para excluir uma tarefa
    private void excluirTarefa() {
        int indice = listaTarefas.getSelectedIndex();
        if (indice >= 0) {
            int resposta = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja excluir esta tarefa?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (resposta == JOptionPane.YES_OPTION) {
                gerenciador.excluirTarefa(indice);
                atualizarListaTarefas();
                limparCamposEntrada();
                exibirMensagem("Tarefa excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            exibirMensagem("Selecione uma tarefa para excluir!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Atualiza a lista de tarefas na interface com cores diferentes por prioridade
    private void atualizarListaTarefas() {
        modeloListaTarefas.clear();
        for (Tarefa tarefa : gerenciador.listarTarefas()) {
            modeloListaTarefas.addElement(tarefa.toString());
        }
        
        // Adicionar renderizador personalizado para colorir conforme prioridade
        listaTarefas.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                
                Component c = super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                
                if (index >= 0 && index < gerenciador.listarTarefas().size()) {
                    Tarefa tarefa = gerenciador.listarTarefas().get(index);
                    String prioridade = tarefa.getPrioridade();
                    
                    if (!isSelected) {
                        // Definir cores de fundo com base na prioridade
                        if (prioridade != null) {
                            if (prioridade.equalsIgnoreCase("Alta")) {
                                c.setBackground(new Color(255, 232, 232));
                            } else if (prioridade.equalsIgnoreCase("Média")) {
                                c.setBackground(new Color(255, 252, 232));
                            } else if (prioridade.equalsIgnoreCase("Baixa")) {
                                c.setBackground(new Color(232, 255, 232));
                            }
                        }
                    }
                }
                
                return c;
            }
        });
    }

    // Limpa os campos de entrada
    private void limparCamposEntrada() {
        campoTitulo.setText("");
        campoDescricao.setText("");
        campoDataVencimento.setText("");
        comboPrioridade.setSelectedIndex(0);
        listaTarefas.clearSelection();
    }
    
    // Método para exibir mensagens com estilo personalizado
    private void exibirMensagem(String mensagem, String titulo, int tipo) {
        UIManager.put("OptionPane.background", COR_BACKGROUND);
        UIManager.put("Panel.background", COR_BACKGROUND);
        UIManager.put("OptionPane.messageForeground", COR_TEXTO);
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 14));
        
        JOptionPane.showMessageDialog(this, mensagem, titulo, tipo);
    }

    public static void main(String[] args) {
        try {
            // Definir o Look and Feel para o sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Personalizar componentes globais
            UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("TextArea.font", new Font("Segoe UI", Font.PLAIN, 14));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JanelaPrincipal().setVisible(true);
            }
        });
    }
}
