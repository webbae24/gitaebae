

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

class Window04 extends JFrame {
//	��ġ�� �������(������Ʈ)�� ����ʵ�� ������ �� ���
	
//	�������� Component�� Frame�� ���� ��ġ�߾��µ� �̷��� ����ȿ���� ��������
//	Panel�� ���� Component�� ��ġ�� �� �ֵ��� ������ �� �ִ�(ContentPane)
	private JPanel mainPanel = new JPanel();
	
	private String name = "��û��";
	
	private JButton profile = new JButton("������");
	private JLabel user = new JLabel(name + "��", JLabel.CENTER);
	private JButton simulation = new JButton("�ùķ��̼�");
	private JButton userInformation = new JButton("��������");
	private JButton logout = new JButton("�α׾ƿ�");
	private JLabel title = new JLabel("< �Խ��� >");
	private Font font = new Font("", Font.PLAIN, 30);
	private int count = 0;
	private String[] header = new String[] { "��ȣ", "����", "�̸�", "����", "��ȸ��" };
	private Object[][] data = {
			{ 5, "����1", "ö��", "2018-03-26", count },
			{ 4, "����2", "����", "2018-03-25", count },
			{ 3, "����3", "¯��", "2018-03-24", count },
			{ 2, "����4", "¯��", "2018-03-23", count },
			{ 1, "����5", "�����", "2018-03-22", count }
	};
	private JTable table;
	//main�� �ϴ� �������� �����ڿ��� ����
	public Window04() {
		this.display();//ȭ�� ���� ���� ó��
		this.event();//�̺�Ʈ ���� ó��
		this.menu();//�޴� ���� ó��
		
		this.setTitle("GUI�׽�Ʈ");
		this.setSize(1000, 600);
//		��ġ�� �ü���� �����ϵ��� ����
		this.setLocationByPlatform(true);
//		��ܺκ��� ������ �ʵ��� ����
//		this.setUndecorated(true);
		this.setResizable(false);
		this.setVisible(true);
	}
	private void display() {
		//mainPanel�� �⺻ �гη� ����
		this.setContentPane(mainPanel);
		//��� ������Ʈ�� �߰��� mainPanel�� ����
		mainPanel.setLayout(null);
		//��ġ�� �������
		profile.setBounds(28, 18, 130, 128);
		user.setBounds(28,160,106,50);
		userInformation.setBounds(40, 220, 106, 50);
		logout.setBounds(40, 310, 106, 50);
		simulation.setBounds(40,400,106,50);
		title.setBounds(190, 18, 770, 40);
		//��Ʈ
		title.setFont(font);
		//������ 
		mainPanel.setBackground(Color.WHITE);
		profile.setBackground(Color.pink);
		//�����ֱ�
		mainPanel.add(profile);
		mainPanel.add(user);
		mainPanel.add(simulation);
		mainPanel.add(userInformation);
		mainPanel.add(logout);
		mainPanel.add(title);
		//S : ���̺�
		DefaultTableModel model = new DefaultTableModel(data, header) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		table = new JTable(model);
		JTableHeader header = table.getTableHeader();	//������ �ٲٱ����� �ڵ�
		header.setBackground(Color.yellow);
		JScrollPane pane = new JScrollPane(table);
		//��ũ�Ѽ���
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(pane);
		pane.setBounds(190, 68, 770, 469);
		//���̺� ����
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel columnModel = table.getColumnModel();
		for(int i=0; i<columnModel.getColumnCount(); i++) {
			columnModel.getColumn(i).setCellRenderer(renderer);
		}
		//���̺� width�� ����
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(34);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setPreferredWidth(88);
		//���̺� �̵��Ұ�
		table.getTableHeader().setReorderingAllowed(false);
		//���̺� ũ�� ���� �Ұ�
		table.getTableHeader().setResizingAllowed(false);
		//���콺�̺�Ʈ
		//F : ���̺�
	}
	private void event() {
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//���α׷� ����
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//â ����
//		this.setDefaultCloseOperation(HIDE_ON_CLOSE);//â ����
//		���� �͵��� �� ���� ��� Ŀ���� �̺�Ʈ ����
//		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//�⺻ �̺�Ʈ ����
		//���콺Ŭ�� �̺�Ʈ
		class MouseListener extends MouseAdapter {
		    @Override
		    public void mouseClicked(MouseEvent e) {
			    if (e.getButton() == 1) {
//			    	int row = table.getSelectedRow();
//			    	int column = table.getColumnCount();
//			    	for(int i = 0; i < column; i++) {
//			    		System.out.println(table.getValueAt(row, i));
//			    	}
//			    	System.out.println("------------------");
					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
					System.out.println(row + "��");
			    } //Ŭ��
	//		    if (e.getClickCount() == 2) { } // ����Ŭ��
	//		    if (e.getButton() == 3) { } // ������ Ŭ��
		    }
		}
		table.addMouseListener(new MouseListener());
	}
	private void menu() {}
}

public class Test04 {
	public static void main(String[] args) {
		//â�� ���̻� ���� ������ �ʰ� Ȯ���Ų Ŭ������ �ν��Ͻ��� ����
		Window04 window = new Window04();
	}
}
