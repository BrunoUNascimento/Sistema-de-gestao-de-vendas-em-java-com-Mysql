package br.com.compulog.telas;

import br.com.compulog.dao.Conexao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;




public class Produto extends javax.swing.JInternalFrame {

    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    //Contrutor
    public Produto() {
        initComponents();
        conexao = Conexao.conector();
        btnConfirma.setVisible(false);
        txtIdProduto.addActionListener(new ActionListener() {
            
            
            @Override
            public void actionPerformed(ActionEvent e) {
                consultar();
            }
        });
    
        btnConfirAlt.setVisible(false);
        btnCancelaA.setVisible(false);
        btnCancelaN.setVisible(false);
        
    }
    
    
    
    private void consultar(){
        String sql ="select * from produto where idproduto=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtIdProduto.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                //System.out.println("Deu certo");
                txtDescricao.setText(rs.getString(2));
                txtPreco.setText(rs.getString(3));
                txtTipo.setText(rs.getString(4));
                txtEstoque.setText(rs.getString(5));
                btnAlterar.setEnabled(true);
                btnExcluir.setEnabled(true);
                
            }else{
                JOptionPane.showMessageDialog(null,"Produto não encontrado");
                txtDescricao.setText(null);
                txtPreco.setText(null);
                txtTipo.setText(null);
                txtEstoque.setText(null);
                btnAlterar.setEnabled(false);
                btnExcluir.setEnabled(false);
                
            }
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    
    //Metodo para consultar produto no banco de dados
    private void adicionar(){
        String sql = "insert into produto(idproduto,nomeproduto,preco,tipo,estoque) value (?,?,?,?,?)";
        try {
        pst = conexao.prepareStatement(sql);
        pst.setString(1, txtIdProduto.getText());
        pst.setString(2, txtDescricao.getText());
        pst.setString(3, txtPreco.getText());
        pst.setString(4, txtTipo.getText());
        pst.setString(5, txtEstoque.getText());

        // Executar a atualização no banco de dados
        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Falha ao adicionar produto");
        }
        btnConfirma.setVisible(false);
        btnCancelaN.setVisible(false);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        btnConfirma.setVisible(false);
        btnCancelaN.setVisible(false);
    }
        
    btnConfirma.setEnabled(false);
    desativarAtributosProduto();
    
    
    
    }
    
    //Metodo para excluir produto do banco de dados
    public void excluir(){
        int excluir = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir este item?","Atenção",JOptionPane.YES_NO_OPTION);
        if(excluir == JOptionPane.YES_OPTION){
            
        
            String sql = "delete from produto where idproduto=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdProduto.getText());
                int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso");

                // Limpar os campos após a exclusão (ou realizar outras atualizações necessárias)
                txtIdProduto.setText(null);
                txtDescricao.setText(null);
                txtPreco.setText(null);
                txtTipo.setText(null);
                txtEstoque.setText(null);

                // Desativar os atributos do produto na tela
                desativarAtributosProduto();
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao excluir produto");
            }




            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + e.getMessage());
            }



        }
    }
    
    //METODO PARA LIMPAR OS CAMPOS DO PRODUTO E HABILITAR DIGITAÇÃO NOS CAMPOS
    private void ativarAtributosProduto(){
        txtIdProduto.setText(null);
        txtDescricao.setText(null);
        txtPreco.setText(null);
        txtTipo.setText(null);
        txtEstoque.setText(null);
        
        
        txtDescricao.setEnabled(true);
        txtPreco.setEnabled(true);
        txtTipo.setEnabled(true);
        txtEstoque.setEnabled(true);
    }
    
    //metodo para alterar produto no banco de dados
    private void alterar(){
        String sql = "update produto set nomeproduto=?,preco=?,tipo=?,estoque=? where idproduto=?";
        try {
        pst = conexao.prepareStatement(sql);
        
        pst.setString(1, txtDescricao.getText());
        pst.setString(2, txtPreco.getText());
        pst.setString(3, txtTipo.getText());
        pst.setString(4, txtEstoque.getText());
        pst.setString(5, txtIdProduto.getText());
        

        // Executar a atualização no banco de dados
        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Falha ao adicionar produto");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
        
        
    }
    
    
    
    
    
    //Metodo para voltar a desativar e limpar os atributos do produto na tela
    private void desativarAtributosProduto(){
        txtIdProduto.setText(null);
        txtDescricao.setText(null);
        txtPreco.setText(null);
        txtTipo.setText(null);
        txtEstoque.setText(null);
        
        
        txtDescricao.setEnabled(false);
        txtPreco.setEnabled(false);
        txtTipo.setEnabled(false);
        txtEstoque.setEnabled(false);
    }
    
    //Metodo para ativar campos de atributos do produto
    public void habilitarCampos(){
        txtDescricao.setEnabled(true);
        txtPreco.setEnabled(true);
        txtTipo.setEnabled(true);
        txtEstoque.setEnabled(true);
        
                
    }
    
    //metodo para desativar campos de atributos do produto
    public void desabilitarCampos(){
        txtDescricao.setEnabled(false);
        txtPreco.setEnabled(false);
        txtTipo.setEnabled(false);
        txtEstoque.setEnabled(false);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtIdProduto = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        txtPreco = new javax.swing.JTextField();
        txtEstoque = new javax.swing.JTextField();
        txtTipo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnConfirma = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnConfirAlt = new javax.swing.JButton();
        btnCancelaN = new javax.swing.JButton();
        btnCancelaA = new javax.swing.JButton();

        setBackground(null);
        setClosable(true);
        setIconifiable(true);

        jLabel1.setText("Codigo");

        jLabel2.setText("Estoque");

        jLabel3.setText("Descrição");

        jLabel6.setText("Preço");

        txtIdProduto.setForeground(new java.awt.Color(0, 0, 0));

        txtDescricao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDescricao.setForeground(new java.awt.Color(0, 0, 0));
        txtDescricao.setEnabled(false);

        txtPreco.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtPreco.setForeground(new java.awt.Color(0, 0, 0));
        txtPreco.setEnabled(false);

        txtEstoque.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtEstoque.setForeground(new java.awt.Color(0, 0, 0));
        txtEstoque.setEnabled(false);

        txtTipo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTipo.setForeground(new java.awt.Color(0, 0, 0));
        txtTipo.setEnabled(false);
        txtTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoActionPerformed(evt);
            }
        });

        jLabel4.setText("Tipo");

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnConfirma.setText("Confirmar");
        btnConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmaActionPerformed(evt);
            }
        });

        jLabel5.setText("FOTO");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/compulog/icones/Falha maior ainda.png"))); // NOI18N

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnConfirAlt.setText("Confirmar");
        btnConfirAlt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirAltActionPerformed(evt);
            }
        });

        btnCancelaN.setText("Cancela");
        btnCancelaN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelaNActionPerformed(evt);
            }
        });

        btnCancelaA.setText("Cancela");
        btnCancelaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelaAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTipo))
                                            .addComponent(txtEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(21, 21, 21)
                                        .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(btnConsultar)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(142, 142, 142))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnConfirma)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCancelaN))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnConfirAlt)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCancelaA)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(jLabel8)
                                .addGap(270, 270, 270))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(381, 381, 381))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConsultar))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNovo)
                            .addComponent(btnConfirma)
                            .addComponent(btnCancelaN)))
                    .addComponent(jLabel8))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btnAlterar)
                    .addComponent(btnConfirAlt)
                    .addComponent(btnCancelaA))
                .addGap(32, 32, 32)
                .addComponent(btnExcluir)
                .addGap(134, 134, 134))
        );

        setBounds(0, 0, 1037, 585);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        consultar();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        btnConfirma.setVisible(true);
        ativarAtributosProduto();
        btnCancelaN.setVisible(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmaActionPerformed
        adicionar();
        desativarAtributosProduto();
        
        
    }//GEN-LAST:event_btnConfirmaActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        habilitarCampos();
        btnConfirAlt.setVisible(true);
        btnCancelaA.setVisible(true);
        
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnConfirAltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirAltActionPerformed
        alterar();
        desabilitarCampos();
        btnConfirAlt.setVisible(false);
    }//GEN-LAST:event_btnConfirAltActionPerformed

    
    //Botão para cancelar a alteração do produto
    private void btnCancelaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelaAActionPerformed
        desabilitarCampos();
        btnConfirAlt.setVisible(false);
        btnCancelaA.setVisible(false);
    }//GEN-LAST:event_btnCancelaAActionPerformed

    private void btnCancelaNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelaNActionPerformed
       desabilitarCampos();
       btnConfirma.setVisible(false);
       btnCancelaN.setVisible(false);
    }//GEN-LAST:event_btnCancelaNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelaA;
    private javax.swing.JButton btnCancelaN;
    private javax.swing.JButton btnConfirAlt;
    private javax.swing.JButton btnConfirma;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtEstoque;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
