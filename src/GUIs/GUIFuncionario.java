package GUIs;

import DAOs.DAOFuncionario;
import Entidades.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Image;
import javax.swing.JTextField;
import tools.*;
import DAOs.*;

public class GUIFuncionario extends JFrame {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);
    private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel(new GridLayout(4, 2));
    private JPanel pnSul = new JPanel(new GridLayout(1, 1));
    private JLabel lbId = new JLabel("Id: ");
    private JTextField tfId = new JTextField(10);
    private JLabel lbNomeFuncionario = new JLabel("Nome: ");
    private JTextField tfNomeFuncionario = new JTextField(10);
    private JLabel lbEmail = new JLabel("Email: ");
    private JTextField tfEmail = new JTextField(10);
    private JLabel lbEndereco = new JLabel("Endereço: ");
    private JTextField tfEndereco = new JTextField(10);
    private JLabel lbTelefone = new JLabel("Telefone: ");
    private JTextField tfTelefone = new JTextField(10);
    ScrollPane scroll = new ScrollPane();
    JTextArea jTextArea = new JTextArea();
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String qualAcao = "";//variavel para facilitar insert e update
    DAOFuncionario daoFuncionario = new DAOFuncionario();
    Funcionario funcionario;
    private CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();

    public GUIFuncionario() {
        setSize(900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Funcionários");
        Container cp = getContentPane();
        cp = getContentPane();
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        cp.setLayout(new BorderLayout());
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        pnNorte.add(lbId);
        pnNorte.add(tfId);
        pnNorte.add(btnRetrieve);
        pnNorte.add(btnCreate);
        pnNorte.add(btnUpdate);
        pnNorte.add(btnDelete);
        pnNorte.add(btnSave);
        pnNorte.add(btnList);
        btnCancel.setVisible(false);
        btnDelete.setVisible(false);
        btnCreate.setVisible(false);
        btnSave.setVisible(false);
        btnUpdate.setVisible(false);
        pnCentro.add(lbNomeFuncionario);
        pnCentro.add(tfNomeFuncionario);
        pnCentro.add(lbEmail);
        pnCentro.add(tfEmail);
        pnCentro.add(lbEndereco);
        pnCentro.add(tfEndereco);
        pnCentro.add(lbTelefone);
        pnCentro.add(tfTelefone);
        pnSul.setBackground(Color.red);
        scroll.add(jTextArea);
        pnSul.add(scroll);
        tfNomeFuncionario.setEditable(false);
        tfEmail.setEditable(false);
        tfEndereco.setEditable(false);
        tfTelefone.setEditable(false);
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tfId.setBackground(Color.white);
                    jTextArea.setText("");
                    funcionario = new Funcionario();
                    int identificador = Integer.valueOf(tfId.getText());
                    funcionario.setId(identificador);
                    funcionario = daoFuncionario.obter(funcionario.getId());
                    if (funcionario == null) {
                        pnNorte.setBackground(Color.red);
                        tfNomeFuncionario.setText("");
                        tfEmail.setText("");
                        tfEndereco.setText("");
                        tfTelefone.setText("");
                        btnCreate.setVisible(true);
                    } else {
                        pnNorte.setBackground(Color.green);
                        tfNomeFuncionario.setText(funcionario.getNomeFuncionario());
                        tfEmail.setText(funcionario.getEmail());
                        tfEndereco.setText(funcionario.getEndereco());
                        tfTelefone.setText(funcionario.getTelefone());
                        btnUpdate.setVisible(true);
                        btnDelete.setVisible(true);
                        btnCreate.setVisible(false);
                    }
                    tfId.setEditable(false);
                    tfNomeFuncionario.setEditable(false);
                    tfEmail.setEditable(false);
                    tfEndereco.setEditable(false);
                    tfTelefone.setEditable(false);
                    tfId.selectAll();
                } catch (Exception erro) {
                    pnNorte.setBackground(Color.yellow);
                    tfId.requestFocus();
                    tfId.setBackground(Color.red);
                    jTextArea.setText("Erro... \n");
                    jTextArea.append(erro.getMessage());
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfId.setEditable(false);
                tfNomeFuncionario.requestFocus();
                btnCreate.setVisible(false);
                btnSave.setVisible(true);
                qualAcao = "incluir";
                funcionario = new Funcionario();
                tfNomeFuncionario.setEditable(true);
                tfEmail.setEditable(true);
                tfEndereco.setEditable(true);
                tfTelefone.setEditable(true);
                tfId.setEditable(false);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    funcionario = new Funcionario();
                    funcionario.setId(Integer.valueOf(tfId.getText()));
                    funcionario.setNomeFuncionario(tfNomeFuncionario.getText());
                    funcionario.setEmail(tfEmail.getText());
                    funcionario.setEndereco(tfEndereco.getText());
                    funcionario.setTelefone(tfTelefone.getText());
                    if (qualAcao.equals("incluir")) {
                        daoFuncionario.inserir(funcionario);
                    } else {
                        daoFuncionario.atualizar(funcionario);
                    }
                    tfId.setEditable(true);
                    tfId.requestFocus();
                    tfNomeFuncionario.setText("");
                    tfEmail.setText("");
                    tfEndereco.setText("");
                    tfTelefone.setText("");
                    btnSave.setVisible(false);
                    pnNorte.setBackground(Color.green);
                    tfNomeFuncionario.setEditable(false);
                    tfEmail.setEditable(false);
                    tfEndereco.setEditable(false);
                    tfTelefone.setEditable(false);
                } catch (Exception erro) {
                    jTextArea.append("Erro............");
                    tfId.setEditable(true);
                    pnNorte.setBackground(Color.red);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
                tfNomeFuncionario.requestFocus();
                btnSave.setVisible(true);
                qualAcao = "editar";
                tfNomeFuncionario.setEditable(true);
                tfEmail.setEditable(true);
                tfEndereco.setEditable(true);
                tfTelefone.setEditable(true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + funcionario.getId() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    daoFuncionario.remover(funcionario);
                    tfId.requestFocus();
                    tfNomeFuncionario.setText("");
                    tfEmail.setText("");
                    tfEndereco.setText("");
                    tfTelefone.setText("");
                    tfId.setEditable(true);
                    btnUpdate.setVisible(false);
                    btnDelete.setVisible(false);
                }
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIListagemFuncionario guiListagem = new GUIListagemFuncionario(daoFuncionario.list());
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        tfId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoFuncionario.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfId.setText(aux[0]);
                        btnRetrieve.doClick();

                    } else {
                        tfId.requestFocus();
                        tfId.selectAll();
                    }
                }
            }
        });
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setVisible(true);
    }

    public static void main(String[] args) {
        GUIFuncionario guiFuncionario = new GUIFuncionario();
    }
}
