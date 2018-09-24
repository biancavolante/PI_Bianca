package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


import myUtil.CentroDoMonitorMaior;

public class GUIMenu extends JFrame {

    private Container cp;
    private Point p;
    private JPanel pnNorte = new JPanel();
    private JPanel pnCentro = new JPanel();
//    private JLabel lbTitulo = new JLabel("Departamento de Acadêmico de Biodiversidade e Conservação da Natureza!");
    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 17);
    private JLabel labelComImagemDeTamanhoDiferente = new JLabel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuCadastros = new JMenu("Cadastros");

    ImageIcon img = new ImageIcon("slogan.png");
    Image imgS = img.getImage();
    Image imgT = imgS.getScaledInstance(600, 500, java.awt.Image.SCALE_SMOOTH);
    JLabel imagem = new JLabel();
    JTextField abrir = new JTextField(15);
    JPanel painelImage = new JPanel();
//    private JLabel lbFrase = new JLabel("           ”Engenheiros ambientais são pessoas que cuidam de consertar os problemas do nosso planeta causado por outras pessoas. São como mães que arrumam a casa depois de seus filhos pequenos brincarem o dia todo. Nosso planeta precisa de mais atenção e esta não deve ser somente dos engenheiros ambientais.”          "
//            + "\n" + " - Carlos Drummond de Andrade.");
//------------------------ Itens do Menu ----------------------------
    JMenuItem cliente = new JMenuItem("Clientes");
    JMenuItem funcionario = new JMenuItem("Funcionários");
    JMenuItem maquina = new JMenuItem("Máquinas");
    JMenuItem pedido = new JMenuItem("Pedidos");

    public GUIMenu(Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setTitle("LP3-Bianca");

        imagem.setIcon(new ImageIcon(imgT));
        painelImage.add(imagem);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
//        pnNorte.add(lbTitulo);
//        pnNorte.add(lbFrase);
        pnNorte.add(painelImage);
//        lbTitulo.setFont(fonte);
        pnNorte.setBackground(Color.LIGHT_GRAY);
        pnCentro.add(labelComImagemDeTamanhoDiferente);
        pnCentro.setBackground(Color.BLACK);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        menuBar.add(menuCadastros);
        menuCadastros.add(cliente);
        menuCadastros.add(funcionario);
        menuCadastros.add(maquina);
        menuCadastros.add(pedido);


        cliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUICliente guiCliente = new GUICliente();
            }
        });
        funcionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIFuncionario guiFuncionario = new GUIFuncionario();
            }
        });
        maquina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIMaquina guiMaquina = new GUIMaquina();
            }
        });
        pedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIPedido guiPedido = new GUIPedido();
            }
        });

        p = new myUtil.CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        setVisible(true);
    } //fecha o contrutor

    public static void main(String[] args) {
        new GUIMenu(new Dimension(800, 600));
    }
}
