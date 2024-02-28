package br.com.compulog.telas;

import br.com.compulog.dao.Conexao;
//import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class TelaUsuarios extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String btnConfirmaAlteracao;
    public TelaUsuarios() {
        initComponents();
        conexao = Conexao.conector();
        
        txtIdUsu.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                consultar();
            }
        });
        
        btnConfirmaUsu.setVisible(false);
        btnCancelaUsu.setVisible(false);
        
    }
    
    //Metodo de pesquisa ao banco de dados
    private void consultar(){
        String sql = "select * from usuarios where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtIdUsu.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                txtNomeUsu.setText(rs.getString(2));
                txtLogin.setText(rs.getString(3));
                txtSenhaUsu.setText(rs.getString(4));
                cboPerfil.setSelectedItem(rs.getString(5));
                
            }else{
                JOptionPane.showMessageDialog(null,"Usuario invalido");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        
        }finally {
            // Feche o PreparedStatement no bloco finally para garantir que seja fechado, independentemente do resultado
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    //Metodo para adicionar usuarios ao banco de dados
    private void adicionar() {
    String sql = "INSERT INTO usuarios (usuario, login, senha, perfil) VALUES ( ?, ?, ?,?)";
    
    try {
        pst = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        //pst.setString(1, txtIdUsu.getText());
        pst.setString(1, txtNomeUsu.getText());
        pst.setString(2, txtLogin.getText());  
        pst.setString(3, txtSenhaUsu.getText());
        pst.setString(4, (String) cboPerfil.getSelectedItem());
        JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Falha ao cadastrar, verifique os campos: " + e.getMessage());
    
    }finally {
        // Feche o PreparedStatement no bloco finally para garantir que seja fechado, independentemente do resultado
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    }

    
    //Metodo para alterar usuarios do banco de dados
    private void alterar(){
        String perfilSelecionado = (String) cboPerfil.getSelectedItem();
        String sql = "update usuarios set usuario=?,login=?,senha=?,perfil=? where iduser=?";
        try {
        pst = conexao.prepareStatement(sql);
        
        pst.setString(1, txtNomeUsu.getText());
        pst.setString(2, txtLogin.getText());
        pst.setString(3, txtSenhaUsu.getText());
        pst.setString(4, perfilSelecionado);
        pst.setString(5, txtIdUsu.getText());
        
            
        JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar" + e);
        }finally {
            // Feche o PreparedStatement no bloco finally para garantir que seja fechado, independentemente do resultado
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    private void habilitarCampos(){
        txtNomeUsu.setEnabled(true);
        txtSenhaUsu.setEnabled(true);
        txtLogin.setEnabled(true);
        cboPerfil.setEnabled(true);
    }
    private void desabilitarCampos(){
        txtNomeUsu.setEnabled(false);
        txtSenhaUsu.setEnabled(false);
        txtLogin.setEnabled(false);
        cboPerfil.setEnabled(false);
    }
    private void limparCampos(){
        txtIdUsu.setText(null);
        txtNomeUsu.setText(null);
        txtSenhaUsu.setText(null);
        txtLogin.setText(null);
        cboPerfil.setSelectedItem(" ");
    }
    private void cancelaAdicionar(){
        btnConfirmaUsu.setVisible(false);
        btnCancelaUsu.setVisible(false);
        desabilitarCampos();
    }
    
    
  
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNomeUsu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSenhaUsu = new javax.swing.JTextField();
        cboPerfil = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtIdUsu = new javax.swing.JTextField();
        btnConfirmaUsu = new javax.swing.JButton();
        btnCancelaUsu = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Tela Usuarios");

        jLabel1.setText("Id");

        jLabel2.setText("Nome");

        txtNomeUsu.setEnabled(false);

        jLabel3.setText("Login");

        txtLogin.setEnabled(false);

        jLabel4.setText("Senha");

        txtSenhaUsu.setEnabled(false);

        cboPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user", "" }));
        cboPerfil.setEnabled(false);

        jLabel5.setText("Perfil");

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/compulog/icones/adicionar.png"))); // NOI18N
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/compulog/icones/editar.png"))); // NOI18N
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/compulog/icones/remover.png"))); // NOI18N

        btnConfirmaUsu.setText("Confirma");
        btnConfirmaUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmaUsuActionPerformed(evt);
            }
        });

        btnCancelaUsu.setText("Cancela");
        btnCancelaUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelaUsuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtNomeUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtSenhaUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnConfirmaUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelaUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(btnAdicionar)
                        .addGap(114, 114, 114)
                        .addComponent(btnAlterar)
                        .addGap(119, 119, 119)
                        .addComponent(jButton4)))
                .addContainerGap(249, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtNomeUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmaUsu))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtSenhaUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btnCancelaUsu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionar)
                    .addComponent(btnAlterar)
                    .addComponent(jButton4))
                .addGap(147, 147, 147))
        );

        setBounds(0, 0, 1037, 585);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        btnConfirmaAlteracao = "confirmar inclusao";
        limparCampos();
        habilitarCampos();
        btnConfirmaUsu.setVisible(true);
        btnCancelaUsu.setVisible(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnConfirmaUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmaUsuActionPerformed
        if(btnConfirmaAlteracao.equals("confirmar alteração")){
            alterar();
        }else if (btnConfirmaAlteracao.equals("confirmar inclusao")){
            adicionar();
            
        }
        desabilitarCampos();
    }//GEN-LAST:event_btnConfirmaUsuActionPerformed

    private void btnCancelaUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelaUsuActionPerformed
       cancelaAdicionar();
    }//GEN-LAST:event_btnCancelaUsuActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        btnConfirmaAlteracao = "confirmar alteração";
        habilitarCampos();
        btnConfirmaUsu.setVisible(true);
        btnCancelaUsu.setVisible(true);
    }//GEN-LAST:event_btnAlterarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelaUsu;
    private javax.swing.JButton btnConfirmaUsu;
    private javax.swing.JComboBox<String> cboPerfil;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtIdUsu;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNomeUsu;
    private javax.swing.JTextField txtSenhaUsu;
    // End of variables declaration//GEN-END:variables
}
