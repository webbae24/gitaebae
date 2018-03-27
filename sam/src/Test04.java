

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
//	배치할 구성요소(컴포넌트)를 멤버필드로 구현한 뒤 사용
	
//	기존에는 Component를 Frame에 직접 배치했었는데 이러면 관리효율이 떨어진다
//	Panel을 만들어서 Component를 배치할 수 있도록 설정할 수 있다(ContentPane)
	private JPanel mainPanel = new JPanel();
	
	private String name = "멍청이";
	
	private JButton profile = new JButton("프로필");
	private JLabel user = new JLabel(name + "님", JLabel.CENTER);
	private JButton simulation = new JButton("시뮬레이션");
	private JButton userInformation = new JButton("정보수정");
	private JButton logout = new JButton("로그아웃");
	private JLabel title = new JLabel("< 게시판 >");
	private Font font = new Font("", Font.PLAIN, 30);
	private int count = 0;
	private String[] header = new String[] { "번호", "제목", "이름", "날자", "조회수" };
	private Object[][] data = {
			{ 5, "제목1", "철수", "2018-03-26", count },
			{ 4, "제목2", "영희", "2018-03-25", count },
			{ 3, "제목3", "짱구", "2018-03-24", count },
			{ 2, "제목4", "짱아", "2018-03-23", count },
			{ 1, "제목5", "배기태", "2018-03-22", count }
	};
	private JTable table;
	//main에 하던 설정들을 생성자에서 진행
	public Window04() {
		this.display();//화면 구성 관련 처리
		this.event();//이벤트 관련 처리
		this.menu();//메뉴 관련 처리
		
		this.setTitle("GUI테스트");
		this.setSize(1000, 600);
//		위치를 운영체제가 결정하도록 하자
		this.setLocationByPlatform(true);
//		상단부분이 나오지 않도록 설정
//		this.setUndecorated(true);
		this.setResizable(false);
		this.setVisible(true);
	}
	private void display() {
		//mainPanel을 기본 패널로 설정
		this.setContentPane(mainPanel);
		//모든 컴포넌트의 추가는 mainPanel에 수행
		mainPanel.setLayout(null);
		//위치와 사이즈설정
		profile.setBounds(28, 18, 130, 128);
		user.setBounds(28,160,106,50);
		userInformation.setBounds(40, 220, 106, 50);
		logout.setBounds(40, 310, 106, 50);
		simulation.setBounds(40,400,106,50);
		title.setBounds(190, 18, 770, 40);
		//폰트
		title.setFont(font);
		//색상설정 
		mainPanel.setBackground(Color.WHITE);
		profile.setBackground(Color.pink);
		//보여주기
		mainPanel.add(profile);
		mainPanel.add(user);
		mainPanel.add(simulation);
		mainPanel.add(userInformation);
		mainPanel.add(logout);
		mainPanel.add(title);
		//S : 테이블
		DefaultTableModel model = new DefaultTableModel(data, header) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		table = new JTable(model);
		JTableHeader header = table.getTableHeader();	//배경색을 바꾸기위한 코드
		header.setBackground(Color.yellow);
		JScrollPane pane = new JScrollPane(table);
		//스크롤설정
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(pane);
		pane.setBounds(190, 68, 770, 469);
		//테이블 정렬
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel columnModel = table.getColumnModel();
		for(int i=0; i<columnModel.getColumnCount(); i++) {
			columnModel.getColumn(i).setCellRenderer(renderer);
		}
		//테이블 width값 설정
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(34);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setPreferredWidth(88);
		//테이블 이동불가
		table.getTableHeader().setReorderingAllowed(false);
		//테이블 크기 조절 불가
		table.getTableHeader().setResizingAllowed(false);
		//마우스이벤트
		//F : 테이블
	}
	private void event() {
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//프로그램 종료
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//창 종료
//		this.setDefaultCloseOperation(HIDE_ON_CLOSE);//창 숨김
//		위의 것들이 다 싫을 경우 커스텀 이벤트 설정
//		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//기본 이벤트 방지
		//마우스클릭 이벤트
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
					System.out.println(row + "행");
			    } //클릭
	//		    if (e.getClickCount() == 2) { } // 더블클릭
	//		    if (e.getButton() == 3) { } // 오른쪽 클릭
		    }
		}
		table.addMouseListener(new MouseListener());
	}
	private void menu() {}
}

public class Test04 {
	public static void main(String[] args) {
		//창을 더이상 직접 만들지 않고 확장시킨 클래스의 인스턴스를 생성
		Window04 window = new Window04();
	}
}
