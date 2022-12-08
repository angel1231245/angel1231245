package transporte;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListModel;
import javax.swing.GroupLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;

// Esta clase define una aplicación de interfaz gráfica de usuario para resolver problemas de transporte
public class principal extends JFrame
{
    // Modelos de lista para almacenar la demanda y la oferta
    DefaultListModel lm;
    DefaultListModel lm1;

    // Botones de la interfaz gráfica
    private JButton btnagregarde;
    private JButton btnagregarof;
    private JButton btncalcular;

    // Etiquetas de la interfaz gráfica
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;

    // Panel principal de la interfaz gráfica
    private JPanel jPanel1;

    // Paneles de desplazamiento para mostrar la demanda y la oferta
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;

    // Listas para mostrar la demanda y la oferta
    private JList lstdemanda;
    private JList lstoferta;
    
    // Campos de texto para ingresar la demanda y la oferta
    private JTextField txtdemanda;
    private JButton txtgenerar;
    private JTextField txtoferta;
    private JTable tbldatos;
    int o;
    int d;
    
    public principal() {
        this.lm = new DefaultListModel();
        this.lm1 = new DefaultListModel();
        this.initComponents();
    }
    
    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.txtdemanda = new JTextField();
        this.jLabel2 = new JLabel();
        this.txtoferta = new JTextField();
        this.jScrollPane2 = new JScrollPane();
        this.lstoferta = new JList();
        this.jScrollPane3 = new JScrollPane();
        this.lstdemanda = new JList();
        this.btnagregarde = new JButton();
        this.btnagregarof = new JButton();
        this.txtgenerar = new JButton();
        this.btncalcular = new JButton();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.setDefaultCloseOperation(3);
        this.setTitle("Modelo de transporte, costo minimo");
        this.jPanel1.setLayout(null);
        this.jLabel1.setText("Destinos: ");
        this.jPanel1.add(this.jLabel1);
        this.jLabel1.setBounds(330, 20, 140, 30);
        this.jPanel1.add(this.txtdemanda);
        this.txtdemanda.setBounds(480, 20, 130, 30);
        this.jLabel2.setText("Origen: ");
        this.jPanel1.add(this.jLabel2);
        this.jLabel2.setBounds(20, 20, 110, 30);
        this.txtoferta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                principal.this.txtofertaActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.txtoferta);
        this.txtoferta.setBounds(140, 20, 140, 30);
        this.jScrollPane2.setViewportView(this.lstoferta);
        this.jPanel1.add(this.jScrollPane2);
        this.jScrollPane2.setBounds(20, 100, 110, 150);
        this.jScrollPane3.setViewportView(this.lstdemanda);
        this.jPanel1.add(this.jScrollPane3);
        this.jScrollPane3.setBounds(330, 100, 110, 150);
        this.btnagregarde.setText("Agregar");
        this.btnagregarde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                principal.this.btnagregardeActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.btnagregarde);
        this.btnagregarde.setBounds(480, 60, 90, 28);
        this.btnagregarof.setText("Agregar");
        this.btnagregarof.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                principal.this.btnagregarofActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.btnagregarof);
        this.btnagregarof.setBounds(140, 60, 90, 28);
        this.txtgenerar.setText("Generar Tabla");
        this.txtgenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                principal.this.txtgenerarActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.txtgenerar);
        this.txtgenerar.setBounds(490, 210, 130, 40);
        this.btncalcular.setText("Calcular");
        this.btncalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                principal.this.btncalcularActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.btncalcular);
        this.btncalcular.setBounds(550, 290, 90, 40);
        this.jLabel3.setText("Resultado: ");
        this.jPanel1.add(this.jLabel3);
        this.jLabel3.setBounds(370, 530, 120, 16);
        this.jLabel4.setFont(new Font("Cantarell", 1, 18));
        this.jPanel1.add(this.jLabel4);
        this.jLabel4.setBounds(530, 530, 100, 30);
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -1, 645, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -1, 589, 32767));
        this.pack();
    }
    
    private void txtofertaActionPerformed(final ActionEvent evt) {
    }
    
    private void btnagregarofActionPerformed(final ActionEvent evt) {
        final String s = this.txtoferta.getText();
        this.lm.addElement(s);
        this.lstoferta.setModel(this.lm);
        this.txtoferta.setText("");
    }
    
    private void btnagregardeActionPerformed(final ActionEvent evt) {
        final String s = this.txtdemanda.getText();
        this.lm1.addElement(s);
        this.lstdemanda.setModel(this.lm1);
        this.txtdemanda.setText("");
    }
    
    private void txtgenerarActionPerformed(final ActionEvent evt) {
        this.o = this.lstoferta.getModel().getSize();
        this.d = this.lstdemanda.getModel().getSize();
        System.out.println(this.lstoferta.getModel().getSize() + " " + this.lstdemanda.getModel().getSize());
        this.tbldatos = new JTable();
        final JScrollPane jScrollPane1 = new JScrollPane();
        this.tbldatos.setModel(new DefaultTableModel(new String[this.lstoferta.getModel().getSize() + 1][], new String[this.lstdemanda.getModel().getSize() + 1]));
        jScrollPane1.setViewportView(this.tbldatos);
        this.jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(90, 300, 452, 200);
        final JTableHeader th = this.tbldatos.getTableHeader();
        final TableColumnModel tcm = th.getColumnModel();
        for (int i = 0; i < this.tbldatos.getColumnCount(); ++i) {
            final TableColumn tc = tcm.getColumn(i);
            if (i == this.tbldatos.getColumnCount() - 1) {
                tc.setHeaderValue("Oferta");
            }
            else {
                tc.setHeaderValue(this.lstdemanda.getModel().getElementAt(i));
            }
            th.repaint();
        }
        int lbl = 320;
        for (int j = 0; j <= this.lstoferta.getModel().getSize(); ++j) {
            if (j == this.lstoferta.getModel().getSize()) {
                final JLabel lblof1 = new JLabel();
                lblof1.setText("Demanda");
                this.jPanel1.add(lblof1);
                lblof1.setBounds(25, lbl, 80, 21);
                lbl += 15;
                break;
            }
            final JLabel lblof2 = new JLabel();
            lblof2.setText(this.lstoferta.getModel().getElementAt(j).toString());
            this.jPanel1.add(lblof2);
            lblof2.setBounds(25, lbl, 80, 21);
            lbl += 17;
        }
    }
    
    private void btncalcularActionPerformed(final ActionEvent evt) {
        final solucion sol = new solucion(this.o, this.d);
        for (int i = 0; i < this.tbldatos.getRowCount() - 1; ++i) {
            for (int j = 0; j < this.tbldatos.getColumnCount(); ++j) {
                if (j == this.tbldatos.getColumnCount() - 1) {
                    for (int k = -1; k < i; ++k) {
                        if (k == -1) {
                            ++k;
                        }
                        sol.setStock(Double.parseDouble(this.tbldatos.getModel().getValueAt(i, j).toString()), i);
                        System.out.println(i + "" + " Oferta: " + this.tbldatos.getModel().getValueAt(i, j));
                    }
                }
            }
            System.out.println("");
        }
        for (int i = 0; i < this.tbldatos.getRowCount(); ++i) {
            if (i == this.tbldatos.getRowCount() - 1) {
                for (int j = 0; j < this.tbldatos.getColumnCount() - 1; ++j) {
                    sol.setRequired(Double.parseDouble(this.tbldatos.getModel().getValueAt(i, j).toString()), j);
                    System.out.println("Demanda: " + this.tbldatos.getModel().getValueAt(i, j));
                }
            }
            System.out.println("");
        }
        for (int i = 0; i < this.tbldatos.getRowCount() - 1; ++i) {
            for (int j = 0; j < this.tbldatos.getColumnCount() - 1; ++j) {
                sol.setCost(Double.parseDouble(this.tbldatos.getModel().getValueAt(i, j).toString()), i, j);
            }
            System.out.println("");
        }
        sol.leastCostRule();
        this.jLabel4.setText(String.valueOf(sol.getSolution() + 12.0));
    }
    
    public static void main(final String[] args) {
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new principal().setVisible(true);
            }
        });
    }
}