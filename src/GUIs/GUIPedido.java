package GUIs;

import DAOs.DAOPedido;
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
import GUIs.GUIListagemPedido;

public class GUIPedido extends JFrame {

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
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Date data1;
    private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel(new GridLayout(3, 2));
    private JPanel pnSul = new JPanel(new GridLayout(1, 1));
    private JLabel lbIdPedido = new JLabel("Id: ");
    private JTextField tfIdPedido = new JTextField(10);
    private JLabel lbDataPedido = new JLabel("Data do Pedido: ");
    private JTextField tfDataPedido = new JTextField(10);
    private JButton btEscolha1 = new JButton("Escolha: ");
    private JPanel pnDataPedido = new JPanel(new GridLayout(1, 2));
    private JPanel pnClienteRg = new JPanel(new GridLayout(1, 2));
    private JLabel lbClienteRg = new JLabel("Cliente: ");
    private JTextField tfClienteRg = new JTextField();
    private JButton btClienteRg = new JButton("Procurar");
    private JPanel pnFuncionarioId = new JPanel(new GridLayout(1, 2));
    private JLabel lbFuncionarioId = new JLabel("Funcionario: ");
    private JTextField tfFuncionarioId = new JTextField();
    private JButton btFuncionarioId = new JButton("Procurar");
    ScrollPane scroll = new ScrollPane();
    JTextArea jTextArea = new JTextArea();
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String qualAcao = "";//variavel para facilitar insert e update
    DAOPedido daoPedido = new DAOPedido();
    Pedido pedido;
    private CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();

    public GUIPedido() {
        setSize(900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CRUD - Pedido");
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
        pnNorte.add(lbIdPedido);
        pnNorte.add(tfIdPedido);
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
        pnCentro.add(lbDataPedido);
        pnCentro.add(pnDataPedido);
        pnDataPedido.add(btEscolha1);
        pnDataPedido.add(tfDataPedido);
        pnCentro.add(lbClienteRg);
        pnCentro.add(pnClienteRg);
        pnClienteRg.add(tfClienteRg);
        pnClienteRg.add(btClienteRg);
        pnCentro.add(lbFuncionarioId);
        pnCentro.add(pnFuncionarioId);
        pnFuncionarioId.add(tfFuncionarioId);
        pnFuncionarioId.add(btFuncionarioId);
        pnSul.setBackground(Color.red);
        scroll.add(jTextArea);
        pnSul.add(scroll);
        btEscolha1.setEnabled(false);
        tfDataPedido.setEditable(false);
        tfClienteRg.setEditable(false);
        btClienteRg.setEnabled(false);
        tfFuncionarioId.setEditable(false);
        btFuncionarioId.setEnabled(false);
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tfIdPedido.setBackground(Color.white);
                    jTextArea.setText("");
                    pedido = new Pedido();
                    int identificador = Integer.valueOf(tfIdPedido.getText());
                    pedido.setIdPedido(identificador);
                    pedido = daoPedido.obter(pedido.getIdPedido());
                    if (pedido == null) {
                        pnNorte.setBackground(Color.red);
                        tfDataPedido.setText("");
                        tfClienteRg.setText("");
                        tfFuncionarioId.setText("");
                        btnCreate.setVisible(true);
                    } else {
                        pnNorte.setBackground(Color.green);
                        tfDataPedido.setText(sdf.format(pedido.getDataPedido()));
                        String dao1 = String.valueOf(pedido.getClienteRg());
                        String[] aux1 = dao1.split("-");
                        tfClienteRg.setText(aux1[0]);
                        String dao2 = String.valueOf(pedido.getFuncionarioId());
                        String[] aux2 = dao2.split("-");
                        tfFuncionarioId.setText(aux2[0]);
                        btnUpdate.setVisible(true);
                        btnDelete.setVisible(true);
                        btnCreate.setVisible(false);
                    }
                    tfIdPedido.setEditable(false);
                    tfDataPedido.setEditable(false);
                    btClienteRg.setEnabled(false);
                    btFuncionarioId.setEnabled(false);
                    tfIdPedido.selectAll();
                } catch (Exception erro) {
                    pnNorte.setBackground(Color.yellow);
                    tfIdPedido.requestFocus();
                    tfIdPedido.setBackground(Color.red);
                    jTextArea.setText("Erro... \n");
                    jTextArea.append(erro.getMessage());
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdPedido.setEditable(false);
                tfDataPedido.requestFocus();
                btnCreate.setVisible(false);
                btnSave.setVisible(true);
                qualAcao = "incluir";
                pedido = new Pedido();
                btEscolha1.setEnabled(true);
                btClienteRg.setEnabled(true);
                btFuncionarioId.setEnabled(true);
                tfIdPedido.setEditable(false);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    pedido = new Pedido();
                    pedido.setIdPedido(Integer.valueOf(tfIdPedido.getText()));
                    sdf.setLenient(false);
                    data1 = sdf.parse(tfDataPedido.getText());
//                    try {
//                        pedido.setDataPedido(sdf.parse(tfDataPedido.getText()));
//                    } catch (ParseException ex) {
//                        Logger.getLogger(GUIPedido.class
//                                .getName()).log(Level.SEVERE, null, ex);
//                    }
                    String[] aux0 = tfClienteRg.getText().split("-");
                    DAOCliente daoCliente = new DAOCliente();
                    Cliente d0 = daoCliente.obter(Integer.valueOf(aux0[0]));
                    pedido.setClienteRg(d0);
                    String[] aux1 = tfFuncionarioId.getText().split("-");
                    DAOFuncionario daoFuncionario = new DAOFuncionario();
                    Funcionario d1 = daoFuncionario.obter(Integer.valueOf(aux1[0]));
                    pedido.setFuncionarioId(d1);
                    if (qualAcao.equals("incluir")) {
                        daoPedido.inserir(pedido);
                    } else {
                        daoPedido.atualizar(pedido);
                    }
                    tfIdPedido.setEditable(true);
                    tfIdPedido.requestFocus();
                    tfDataPedido.setText("");
                    tfClienteRg.setText("");
                    tfFuncionarioId.setText("");
                    btnSave.setVisible(false);
                    pnNorte.setBackground(Color.green);
                    btEscolha1.setEnabled(false);
                    btClienteRg.setEnabled(false);
                    btFuncionarioId.setEnabled(false);
                } catch (Exception erro) {
                    jTextArea.append("Erro............");
                    tfIdPedido.setEditable(true);
                    pnNorte.setBackground(Color.red);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
                tfDataPedido.requestFocus();
                btnSave.setVisible(true);
                qualAcao = "editar";
                btEscolha1.setEnabled(true);
                btClienteRg.setEnabled(true);
                btFuncionarioId.setEnabled(true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + pedido.getIdPedido() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    daoPedido.remover(pedido);
                    tfIdPedido.requestFocus();
                    tfDataPedido.setText("");
                    tfClienteRg.setText("");
                    tfFuncionarioId.setText("");
                    tfIdPedido.setEditable(true);
                    btnUpdate.setVisible(false);
                    btnDelete.setVisible(false);
                }
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIListagemPedido guiListagem = new GUIListagemPedido(daoPedido.list());
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        btEscolha1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    DateChooser dc1 = new DateChooser((JFrame) null, "Escolha uma data:");
                    data1 = dc1.select();
                    tfDataPedido.setText(sdf.format(data1));
                } catch (Exception ex) {
                    jTextArea.setText("Escolha uma data\n");
                }
            }
        });
        DAOCliente daoCliente = new DAOCliente();
        
        btClienteRg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoCliente.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfClienteRg.setText(aux[0]);
                    } else {
                        jTextArea.setText("Nenhum dado adicionado!");
                    }
                }
            }
        });
        DAOFuncionario daoFuncionario = new DAOFuncionario();
        btFuncionarioId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoFuncionario.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfFuncionarioId.setText(aux[0]);
                    } else {
                        jTextArea.setText("Nenhum dado adicionado!");
                    }
                }
            }
        });
//        tfIdPedido.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Pedido> listaAuxiliar = daoPedido.listInOrderId("id");
//                if (listaAuxiliar.size() > 0) {
//                    String selectedItem = new JanelaPesquisar(lista).getValorRetornado();
//                    if (!selectedItem.equals("")) {
//                        String[] aux = selectedItem.split("-");
//                        tfIdPedido.setText(aux[0]);
//                        btnRetrieve.doClick();
//
//                    } else {
//                        tfIdPedido.requestFocus();
//                        tfIdPedido.selectAll();
//                    }
//                }
//            }
//        });
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setVisible(true);
    }

    public static void main(String[] args) {
        GUIPedido guiPedido = new GUIPedido();
    }
}
