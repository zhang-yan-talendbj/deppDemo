package com.caribe.stone.word;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Hello world!
 * 
 */
public class App extends JFrame {

	private Image icon;// 托盘图标
	private TrayIcon trayIcon;
	private SystemTray systemTray;// 系统托盘
	private PopupMenu pop = new PopupMenu(); // 构造一个右键弹出式菜单
	private MenuItem exit = new MenuItem("Quite");
	private MenuItem show = new MenuItem("Show Window");

	public App() {

		// 托盘图标部分结束
		// icon初始化
		icon = Toolkit.getDefaultToolkit().getImage(
				this.getClass().getResource("icon.gif"));// 托盘图标显示的图片

		if (SystemTray.isSupported()) {
			systemTray = SystemTray.getSystemTray();// 获得系统托盘的实例
			trayIcon = new TrayIcon(icon, "鼠标放到托盘图标上的文字", pop);
			// wasw100
			pop.add(show);
			pop.add(exit);

			try {
				systemTray.add(trayIcon); // 设置托盘的图标
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
			addWindowListener(new WindowAdapter() {
				public void windowIconified(WindowEvent e) {
					dispose();// 窗口最小化时dispose该窗口
				}
			});

			trayIcon.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1
							&& e.getButton() != MouseEvent.BUTTON3) {// 左击击托盘窗口再现，如果是双击就是e.getClickCount()
																		// ==
																		// 2
						setVisible(true);
						setExtendedState(JFrame.NORMAL);// 设置此 frame 的状态。
					}
				}
			});

			show.addActionListener(new ActionListener() { // 点击"显示窗口"菜单后将窗口显示出来

				public void actionPerformed(ActionEvent e) {
					// systemTray.remove(trayIcon); // 从系统的托盘实例中移除托盘图标
					setVisible(true); // 显示窗口
					setExtendedState(JFrame.NORMAL);
				}
			});
			exit.addActionListener(new ActionListener() { // 点击“退出演示”菜单后推出程序

				public void actionPerformed(ActionEvent e) {
					// 这里可以写执行退出时执行的操作
					System.exit(0); // 退出程序
				}
			});
		}// 托盘图标部分结束

		final JTextField text = new JTextField();
		// text.setAction()
		text.setSize(100, 30);
		text.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String word = e.getActionCommand();
				text.setText("");
				WordThread wordThread = new WordThread(timeList, word);
				threadList.add(wordThread);
				wordThread.start();
			}
		});
		add(text);

		// 以下是swing程序
		setIconImage(icon);// 更改程序图标

		setTitle("Please input the word");
		setSize(200, 150);
		// 自动确定窗口位置
		setLocationByPlatform(true);
		// 点击关闭按钮能够自动退出
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		H2DB db = new H2DB();

		final Map map = new HashMap();
		db.execute(new StatementCallback() {

			@Override
			public Object execute(Statement stmt) {
				try {
					stmt.execute("select count(*),word from word group by word having(count(*)<5)");
					ResultSet rs = stmt.getResultSet();
					while (rs.next()) {
						map.put(rs.getString(2), rs.getInt(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				return null;
			}
		});

		Set keySet = map.keySet();
		for (Object key : keySet) {
			WordThread wordThread = new WordThread(timeList,
					String.valueOf(key));
			threadList.add(wordThread);
			wordThread.start();
		}

	}

	private static List<Long> timeList = new ArrayList<Long>(5);

	static {
		long e = 1000L * 60;
		timeList.add(e * 2);
		timeList.add(e * 5);
		timeList.add(e * 20);
		timeList.add(e * 60);
		timeList.add(e * 120);
	}
	private static List<Thread> threadList = new LinkedList<Thread>();

	public static void main(String[] args) {
		new App();
	}

	public static synchronized void writeWordToFile(Object result) {
		String str = (String) result;
		if (str != null && str.length() > 0) {
			File file = new File("d:/word.txt");
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				BufferedInputStream is = new BufferedInputStream(
						new FileInputStream(file));

				byte[] ary = new byte[is.available()];

				is.read(ary);
				BufferedOutputStream os = new BufferedOutputStream(
						new FileOutputStream(file));
				os.write(ary);

				os.write(str.getBytes());
				os.flush();
				os.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}

class WordThread extends Thread {
	private List<Long> list;
	private String word;

	public List<Long> getList() {
		return list;
	}

	public void setList(List<Long> list) {
		this.list = list;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	WordThread(List<Long> list, String word) {
		this.list = list;
		this.word = word;
	}

	@Override
	public void run() {
		try {
			H2DB db = new H2DB();
			Object obj = db.execute(new StatementCallback() {

				@Override
				public Object execute(Statement stmt) {
					int level = 0;
					try {
						stmt.execute("select count(*) from word where word='"
								+ word + "'");
						ResultSet rs = stmt.getResultSet();

						while (rs.next()) {
							level = rs.getInt(1);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return level;
				}
			});
			int insert = Integer.parseInt(String.valueOf(obj));
			if (insert >= list.size()) {
				return;
			}
			Thread.sleep(list.get(insert));
			final JFrame j = new JFrame("Please review [" + word + "]");
			JTextField text = new JTextField();
			text.setText(word);
			JButton btn1 = new JButton("remebered");
			btn1.addActionListener(new WordListener(j, this, true));
			JButton btn2 = new JButton("forgot");
			btn2.addActionListener(new WordListener(j, this, false));
			j.setSize(600, 100);
			JPanel panel = new JPanel();
			panel.setSize(600, 100);

			panel.add(text);
			panel.add(btn1);
			panel.add(btn2);
			j.add(panel);
			j.setVisible(true);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class WordListener implements ActionListener {
	private JFrame frame;
	private WordThread thread;
	private boolean flag;

	public WordListener(JFrame j, WordThread t, boolean b) {
		super();
		this.frame = j;
		this.thread = t;
		this.flag = b;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		frame.dispose();
		H2DB db = new H2DB();
		db.execute(new StatementCallback() {

			@Override
			public Object execute(Statement stmt) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				try {
					if (flag) {
						// set @cc=select count(*) from word;
						// insert into word(word , level , createdtime)
						// values('aaa',@cc,'2012-01-01')
						stmt.execute("set @wordNum=select count(*) from word where word='"
								+ thread.getWord()
								+ "';"
								+ "insert into word(word , level , createdtime) values('"
								+ thread.getWord()
								+ "',@wordNum,'"
								+ sdf.format(new Date()) + "')");
					} else {
						stmt.execute("delete from word where word='"
								+ thread.getWord() + "'");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		});

		thread = new WordThread(thread.getList(), thread.getWord());
		thread.start();

		Object result = db.execute(new StatementCallback() {

			@Override
			public Object execute(Statement stmt) {
				// TODO Auto-generated method stub
				StringBuffer sb = null;
				try {
					sb = new StringBuffer();
					stmt.execute("select distinct word from word where level=4 group by word order by word");
					ResultSet rs = stmt.getResultSet();
					while (rs.next()) {
						sb.append(rs.getString(1)).append("\r\n");
					}
					stmt.execute("update word set level=5 where level=4");
					stmt.execute("delete word where id in (select id where level=5)");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return sb.toString();
			}
		});

		App.writeWordToFile(result);
	}
}
