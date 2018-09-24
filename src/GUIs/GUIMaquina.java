package GUIs;

import DAOs.DAOMaquina;
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
import GUIs.GUIListagemMaquina;

public class GUIMaquina extends JFrame {

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
    private JPanel pnCentro = new JPanel(new GridLayout(2, 2));
    private JPanel pnSul = new JPanel(new GridLayout(1, 1));
    private JLabel lbIdMaquina = new JLabel("Id: ");
    private JTextField tfIdMaquina = new JTextField(10);
    private JLabel lbNomeMaquina = new JLabel("Nome: ");
    private JTextField tfNomeMaquina = new JTextField(10);
    private JLabel lbValorHora = new JLabel("Valor por Hora: ");
    private JTextField tfValorHora = new JTextField(10);
    ScrollPane scroll = new ScrollPane();
    JTextArea jTextArea = new JTextArea();
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String qualAcao = "";//variavel para facilitar insert e update
    DAOMaquina daoMaquina = new DAOMaquina();
    Maquina maquina;
    private CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();

    public GUIMaquina() {
        setSize(900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Máquinas");
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
        pnNorte.add(lbIdMaquina);
        pnNorte.add(tfIdMaquina);
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
        pnCentro.add(lbNomeMaquina);
        pnCentro.add(tfNomeMaquina);
        pnCentro.add(lbValorHora);
        pnCentro.add(tfValorHora);
        pnSul.setBackground(Color.red);
        scroll.add(jTextArea);
        pnSul.add(scroll);
        tfNomeMaquina.setEditable(false);
        tfValorHora.setEditable(false);
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tfIdMaquina.setBackground(Color.white);
                    jTextArea.setText("");
                    maquina = new Maquina();
                    int identificador = Integer.valueOf(tfIdMaquina.getText());
                    maquina.setIdMaquina(identificador);
                    maquina = daoMaquina.obter(maquina.getIdMaquina());
                    if (maquina == null) {
                        pnNorte.setBackground(Color.red);
                        tfNomeMaquina.setText("");
                        tfValorHora.setText("");
                        btnCreate.setVisible(true);
                    } else {
                        pnNorte.setBackground(Color.green);
                        tfNomeMaquina.setText(maquina.getNomeMaquina());
                        tfValorHora.setText(String.valueOf(maquina.getValorHora()));
                        btnUpdate.setVisible(true);
                        btnDelete.setVisible(true);
                        btnCreate.setVisible(false);
                    }
                    tfIdMaquina.setEditable(false);
                    tfNomeMaquina.setEditable(false);
                    tfValorHora.setEditable(false);
                    tfIdMaquina.selectAll();
                } catch (Exception erro) {
                    pnNorte.setBackground(Color.yellow);
                    tfIdMaquina.requestFocus();
                    tfIdMaquina.setBackground(Color.red);
                    jTextArea.setText("Erro... \n");
                    jTextArea.append(erro.getMessage());
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdMaquina.setEditable(false);
                tfNomeMaquina.requestFocus();
                btnCreate.setVisible(false);
                btnSave.setVisible(true);
                qualAcao = "incluir";
                maquina = new Maquina();
                tfNomeMaquina.setEditable(true);
                tfValorHora.setEditable(true);
                tfIdMaquina.setEditable(false);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    maquina = new Maquina();
                    maquina.setIdMaquina(Integer.valueOf(tfIdMaquina.getText()));
                    maquina.setNomeMaquina(tfNomeMaquina.getText());
//                    data2 = sdf.parse(tfValorHora.getText());
//                    try {
//                        maquina.setValorHora(sdf.parse(tfValorHora.getText()));
//                    } catch (ParseException ex) {
//                        Logger.getLogger(GUIMaquina.class
//                                .getName()).log(Level.SEVERE, null, ex);
//                    }
                    if (qualAcao.equals("incluir")) {
                        daoMaquina.inserir(maquina);
                    } else {
                        daoMaquina.atualizar(maquina);
                    }
                    tfIdMaquina.setEditable(true);
                    tfIdMaquina.requestFocus();
                    tfNomeMaquina.setText("");
                    tfValorHora.setText("");
                    btnSave.setVisible(false);
                    pnNorte.setBackground(Color.green);
                    tfNomeMaquina.setEditable(false);
                    tfValorHora.setEditable(false);
                } catch (Exception erro) {
                    jTextArea.append("Erro............");
                    tfIdMaquina.setEditable(true);
                    pnNorte.setBackground(Color.red);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
                tfNomeMaquina.requestFocus();
                btnSave.setVisible(true);
                qualAcao = "editar";
                tfNomeMaquina.setEditable(true);
                tfValorHora.setEditable(true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + maquina.getIdMaquina() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    daoMaquina.remover(maquina);
                    tfIdMaquina.requestFocus();
                    tfNomeMaquina.setText("");
                    tfValorHora.setText("");
                    tfIdMaquina.setEditable(true);
                    btnUpdate.setVisible(false);
                    btnDelete.setVisible(false);
                }
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIListagemMaquina guiListagem = new GUIListagemMaquina(daoMaquina.list());
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        tfIdMaquina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoMaquina.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfIdMaquina.setText(aux[0]);
                        btnRetrieve.doClick();

                    } else {
                        tfIdMaquina.requestFocus();
                        tfIdMaquina.selectAll();
                    }
                }
            }
        });
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setVisible(true);
    }

    public static void main(String[] args) {
        GUIMaquina guiMaquina = new GUIMaquina();
    }
}
