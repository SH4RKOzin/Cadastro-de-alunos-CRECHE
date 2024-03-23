package CRECHE;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;  
import java.sql.*;

@SuppressWarnings("serial")
public class Interface extends JFrame {
    private JTextField idField, nomeField, nomeDoEncarregadoField, contactoDoEncarregadoField, EnderecoField, recomendacoesField;
    private JButton btnAdicionar, btnAtualizar, btnApagar, btnProcurar;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    
    private static final  String serverName = "localhost";
    private static final String dbName = "crechedb";
   private static final String portNumber = "3306";
    private static final String URL = "jdbc:mysql://"+serverName+":"+portNumber+"/"+dbName;
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public Interface() {
        super("Gerenciador de Estudantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(830, 600);
     
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Nome Completo");
        modeloTabela.addColumn("Nome do encarregado");
        modeloTabela.addColumn("Contato do encarregado");
        modeloTabela.addColumn("Endereço");
        modeloTabela.addColumn("Recomendações");

        tabela = new JTable(modeloTabela);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(0, 327, 814, 234);
        getContentPane().setLayout(null);
        getContentPane().add(scrollPane);
        
        
                // Criação dos componentes
        		JLabel label = new JLabel("ID:");
        		label.setBounds(10, 42, 139, 30);
        		getContentPane().add(label);
                idField = new JTextField(5);
                idField.setBounds(168, 42, 249, 30);
                getContentPane().add(idField);
                idField.setToolTipText("Digite o ID do aluno");
                
                JLabel label_1 = new JLabel("Nome Completo:");
                label_1.setBounds(10, 83, 140, 30);
                getContentPane().add(label_1);
                nomeField = new JTextField(20);
                nomeField.setBounds(168, 83, 249, 30);
                getContentPane().add(nomeField);
                nomeField.setToolTipText("Digite o nome completo do aluno");
                
                JLabel label_2 = new JLabel("Endereço:");
                label_2.setBounds(10, 206, 84, 30);
                getContentPane().add(label_2);
                EnderecoField = new JTextField(20);
                EnderecoField.setBounds(168, 206, 249, 30);
                getContentPane().add(EnderecoField);
                EnderecoField.setToolTipText("digite o endereco do aluno");
                
                JLabel lblNomeDoEncarregado = new JLabel("Nome do encarregado:");
                lblNomeDoEncarregado.setBounds(10, 124, 139, 30);
                getContentPane().add(lblNomeDoEncarregado);
                nomeDoEncarregadoField = new JTextField(15);
                nomeDoEncarregadoField.setBounds(168, 124, 249, 30);
                getContentPane().add(nomeDoEncarregadoField);
                nomeDoEncarregadoField.setToolTipText("digite o nome do encarregado");
                
                JLabel lblContatoDoEncarregado = new JLabel("Contato do encarregado:");
                lblContatoDoEncarregado.setBounds(10, 165, 159, 30);
                getContentPane().add(lblContatoDoEncarregado);
                contactoDoEncarregadoField = new JTextField(15);
                contactoDoEncarregadoField.setBounds(168, 165, 249, 30);
                getContentPane().add(contactoDoEncarregadoField);
                contactoDoEncarregadoField.setToolTipText("digite o contacto do encarregado");
                
                JLabel label_5 = new JLabel("Recomendações:");
                label_5.setBounds(10, 247, 140, 30);
                getContentPane().add(label_5);
                recomendacoesField = new JTextField(30);
                recomendacoesField.setBounds(168, 247, 249, 30);
                getContentPane().add(recomendacoesField);
                
                JLabel lblListaDeAlunos = new JLabel("Lista de alunos");
                lblListaDeAlunos.setBounds(365, 302, 92, 14);
                getContentPane().add(lblListaDeAlunos);
                
                        btnAdicionar = new JButton("Adicionar");
                        btnAdicionar.setBounds(536, 69, 92, 23);
                        getContentPane().add(btnAdicionar);
                        btnAdicionar.setToolTipText("adicionar aluno");
                        btnAtualizar = new JButton("Atualizar");
                        btnAtualizar.setBounds(536, 111, 92, 23);
                        getContentPane().add(btnAtualizar);
                        btnAtualizar.setToolTipText("atualizar aluno");
                        btnApagar = new JButton("Apagar");
                        btnApagar.setBounds(536, 157, 92, 23);
                        getContentPane().add(btnApagar);
                        btnApagar.setToolTipText("apagar aluno");
                        btnProcurar = new JButton("Procurar");
                        btnProcurar.setBounds(536, 196, 92, 23);
                        getContentPane().add(btnProcurar);
                        btnProcurar.setToolTipText("procurar aluno");
                        
                        JLabel lblRegistroDeAlunos = new JLabel("Registro de alunos");
                        lblRegistroDeAlunos.setBounds(236, 11, 110, 20);
                        getContentPane().add(lblRegistroDeAlunos);
                        btnProcurar.addActionListener(new ActionListener() {
                         	public void actionPerformed(ActionEvent e) {
                                     	procurarAlunoPorID();
                         	}
                         });
                        
                                btnApagar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        apagarEstudante();
                                    }
                                });
                        
                                btnAtualizar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        atualizarEstudante();
                                    }
                                });
        
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarEstudante();
            }
        });

        carregarDados();
        setVisible(true);
    }

    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void adicionarEstudante() {
    	    String NomeCompleto = nomeField.getText();
    	    String NomeDoEncarregado = nomeDoEncarregadoField.getText();
    	    String ContactoDoEncarregado = contactoDoEncarregadoField.getText();
    	    String Endereco = EnderecoField.getText();
    	    String Recomendacoes = recomendacoesField.getText();

    	    try {
    	        Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

    	        String sql = "INSERT INTO alunos (NomeCompleto,  NomeDoEncarregado, ContactoDoEncarregado,Endereco, Recomendacoes) VALUES (?, ?, ?, ?, ?)";
    	        try (PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    	            statement.setString(1, NomeCompleto);
    	            statement.setString(2, NomeDoEncarregado);
    	            statement.setString(3, ContactoDoEncarregado);
    	             statement.setString(4, Endereco);
    	            statement.setString(5, Recomendacoes);
    	            int linhasAfetadas = statement.executeUpdate();

    	            if (linhasAfetadas > 0) {
    	                JOptionPane.showMessageDialog(this, "Aluno adicionado com sucesso!");

    	                ResultSet resultSet = statement.getGeneratedKeys();
    	                if (resultSet.next()) {
    	                    @SuppressWarnings("unused")
							int idGerado = resultSet.getInt(1);
    	                }
    	            } else {
    	                JOptionPane.showMessageDialog(this, "Falha ao adicionar o aluno.");
    	            }
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	        JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados.");
    	    }
    	    carregarDados();
    	}
    private void atualizarEstudante() {
    	 int id = Integer.parseInt(idField.getText());
    	    String NomeCompleto = nomeField.getText();
    	    String NomeDoEncarregado = nomeDoEncarregadoField.getText();
    	    String ContactoDoEncarregado = contactoDoEncarregadoField.getText();
    	    String Endereco = EnderecoField.getText();
    	    String Recomendacoes = recomendacoesField.getText();
    	    int linhaSelecionada = tabela.getSelectedRow();
    	    
    	    if (linhaSelecionada == -1) {
    	        JOptionPane.showMessageDialog(this, "Selecione um aluno na tabela para atualizar.");
    	        return;
    	    }
    	    int idSelecionado = (int) tabela.getValueAt(linhaSelecionada, 0);
    	    tabela.setValueAt(id, linhaSelecionada, 0);
    	    tabela.setValueAt(NomeCompleto, linhaSelecionada, 1);
    	    tabela.setValueAt(Endereco, linhaSelecionada, 2);
    	    tabela.setValueAt(NomeDoEncarregado, linhaSelecionada, 3);
    	    tabela.setValueAt(ContactoDoEncarregado, linhaSelecionada, 4);
    	    tabela.setValueAt(Recomendacoes, linhaSelecionada, 5);

    	    try {
    	        Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

    	        String sql = "UPDATE alunos SET NomeCompleto=?, Endereco=?, NomeDoEncarregado=?, ContactoDoEncarregado=?, Recomendacoes=? WHERE idEstudante=?";
    	        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
    	            statement.setString(1, NomeCompleto);
    	            statement.setString(3, NomeDoEncarregado);
    	            statement.setString(4, ContactoDoEncarregado);
    	             statement.setString(2, Endereco);
    	            statement.setString(5, Recomendacoes);
    	            statement.setInt(6, idSelecionado);

    	            int linhasAfetadas = statement.executeUpdate();

    	            if (linhasAfetadas > 0) {
    	                JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!");
    	            } else {
    	                JOptionPane.showMessageDialog(this, "Falha ao atualizar o aluno.");
    	            }
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	        JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados.");
    	    }
    }

    private void apagarEstudante() {
    	    int linhaSelecionada = tabela.getSelectedRow();

    	    if (linhaSelecionada == -1) {
    	        JOptionPane.showMessageDialog(this, "Selecione um Aluno na tabela para apagar.");
    	        return;
    	    }

    	    int idSelecionado = (int) tabela.getValueAt(linhaSelecionada, 0);

    	    try {
    	        Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
    	        

    	        String sql = "DELETE FROM alunos WHERE idEstudante=?";
    	        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
    	            statement.setInt(1, idSelecionado);

    	            int linhasAfetadas = statement.executeUpdate();

    	            if (linhasAfetadas > 0) {
    	                JOptionPane.showMessageDialog(this, "Aluno apagado com sucesso!");
    	            } else {
    	                JOptionPane.showMessageDialog(this, "Falha ao apagar o Aluno.");
    	            }
    	        }
    	    } catch (SQLException e) {
    	    	JOptionPane.showMessageDialog(null, "Erro de conexão à base de dados: "+e.getMessage(),
    	    			"Base de Dados",
    	    			JOptionPane.ERROR_MESSAGE); 
    	    			 }
    	    carregarDados();
    	}


    private void carregarDados() {
    	    modeloTabela.setRowCount(0);

    	    try {
    	        Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

    	        String sql = "SELECT idEstudante, NomeCompleto,  NomeDoEncarregado, ContactoDoEncarregado,Endereco, Recomendacoes FROM alunos";
    	        try (Statement statement = conexao.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
    	            while (resultSet.next()) {
    	                int id = resultSet.getInt("idEstudante");
    	                String NomeCompleto = resultSet.getString("NomeCompleto");
    	                String NomeDoEncarregado = resultSet.getString("NomeDoEncarregado");
    	                String ContactoDoEncarregado = resultSet.getString("ContactoDoEncarregado");
    	                String Endereco = resultSet.getString("Endereco");
    	                String Recomendacoes = resultSet.getString("Recomendacoes");

    	                modeloTabela.addRow(new Object[]{id, NomeCompleto,  NomeDoEncarregado, ContactoDoEncarregado,Endereco, Recomendacoes});
    	            }
    	        }
    	    } catch (SQLException e) {
    	    	JOptionPane.showMessageDialog(null, "Erro de conexão à base de dados: "+e.getMessage(),
    	    			"Base de Dados",
    	    			JOptionPane.ERROR_MESSAGE);  
    	    			 }
    	}

    private void procurarAlunoPorID() {
        String idEstudante = JOptionPane.showInputDialog(this, "Informe o ID do Aluno:");
        if (idEstudante != null && !idEstudante.isEmpty()) {
            try {
                int id = Integer.parseInt(idEstudante);
                buscarEExibirAlunoPorID(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID do aluno deve ser um número inteiro.");
            }
        }
    }

    private void buscarEExibirAlunoPorID(int id) {
        try {
        	Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            String query = "SELECT * FROM alunos WHERE idEstudante = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                exibirDetalhesAluno(resultSet);
            } else {
                JOptionPane.showMessageDialog(this, "Aluno não encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar aluno na base de dados.");
        }
    }

    private void exibirDetalhesAluno(ResultSet resultSet) throws SQLException {
        String nomeCompleto = resultSet.getString("NomeCompleto");
        String NomeDoEncarregado = resultSet.getString("NomeDoEncarregado");
        String ContactoDoEncarregado = resultSet.getString("ContactoDoEncarregado");
        String endereco = resultSet.getString("Endereco");
        String recomendacoes = resultSet.getString("Recomendacoes");

        String mensagem = "Detalhes do Aluno:\n" +
                "Nome Completo: " + nomeCompleto + "\n" +
                "Nome do encarregado: " + NomeDoEncarregado + "\n" +
                "Contacto do encarregado: " + ContactoDoEncarregado + "\n" +
                "Endereço: " + endereco + "\n" +
                "Recomendações: " + recomendacoes;

        JOptionPane.showMessageDialog(this, mensagem);
    }
  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interface();
            }
        });
    }
}
