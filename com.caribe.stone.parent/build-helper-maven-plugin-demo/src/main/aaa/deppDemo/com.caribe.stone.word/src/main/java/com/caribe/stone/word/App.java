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
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends JFrame {
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	private Image icon;// 托盘图标
	private TrayIcon trayIcon;
	private SystemTray systemTray;// 系统托盘
	private PopupMenu pop = new PopupMenu(); // 构造一个右键弹出式菜单
	private MenuItem exit = new MenuItem("Quite");
	private MenuItem show = new MenuItem("Show Window");
	private WordLevelTime wordLevelTime;

	private WordService wordService;;

	public WordService getWordService() {
		return wordService;
	}

	public void setWordService(WordService wordService) {
		this.wordService = wordService;
	}

	private JTextField wordText = new JTextField();

	public WordLevelTime getWordLevelTime() {
		return wordLevelTime;
	}

	public App() {

		// 托盘图标部分结束
		// icon初始化
		icon = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("icon.gif"));// 托盘图标显示的图片

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
					if (e.getClickCount() == 1 && e.getButton() != MouseEvent.BUTTON3) {// 左击击托盘窗口再现，如果是双击就是e.getClickCount()
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

		wordText.setSize(100, 30);
		add(wordText);

		// 以下是swing程序
		setIconImage(icon);// 更改程序图标

		setTitle("Please input the word,then press Enter.");
		setSize(200, 150);
		// 自动确定窗口位置
		setLocationByPlatform(true);
		// 点击关闭按钮能够自动退出
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void setWordLevelTime(WordLevelTime timeList) {
		this.wordLevelTime = timeList;
	}

	private static List<Thread> threadList = new LinkedList<Thread>();

	public static void main(String[] args) {
		App app = new App();
		List<Long> testTimeList = getTestTimeList();
		WordLevelTime wordLevelTime = new WordLevelTime();
		wordLevelTime.setTimeList(testTimeList);
		app.setWordLevelTime(wordLevelTime);
		WordServiceImpl wordService = new WordServiceImpl();

		Properties properties = new Properties();
		String jdbcUrl = null;
		try {
			properties.load(H2DB.class.getResourceAsStream("db.properties"));
			jdbcUrl = properties.getProperty("jdbcUrl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		wordService.setJdbcUrl(jdbcUrl);
		app.setWordService(wordService);

		app.start();
	}

	private static List<Long> getTestTimeList() {
		List<Long> testTimeList = new LinkedList<Long>();
		long second = 1000L * 60;
		testTimeList.add(second * 2);
		testTimeList.add(second * 5);
		testTimeList.add(second * 20);
		testTimeList.add(second * 60);
		testTimeList.add(second * 120);
		return testTimeList;
	}

	public void start() {

		wordText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String word = e.getActionCommand();
				LOG.info("Add new word:" + word);

				wordText.setText("");
				Word words = new Word(word, 0);
				getWordService().insertWord(words);
				WordThread wordThread = new WordThread(words, getWordLevelTime(), getWordService());
				threadList.add(wordThread);
				wordThread.start();
			}
		});

		List<Word> list = wordService.getReviewWord();

		LOG.info(list.toString());

		for (Word word : list) {
			WordThread wordThread = new WordThread(word, getWordLevelTime(), getWordService());
			threadList.add(wordThread);
			wordThread.start();
		}

	}

	/**
	 * 输出已经完成单词
	 * 
	 * @param result
	 */
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
				BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));

				byte[] ary = new byte[is.available()];

				is.read(ary);
				BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
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
