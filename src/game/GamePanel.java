/*
 * 
 * 모든 게임은 이 패널 안에서 그려질 예정!
 * 아무리 게임의 장면이 다양하더라도 패널은 오직 1개만 사용된다!!
 * 
 * 모든 오브젝트는 결국 이 패널에 그려져야 하므로 이 패널의 paint메서드의 인수로 전달되는 
 * Graphics객체를 게임에 등장할 모든 오브젝트가 전달받아야 한다. 
 * 
*/
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	public static final int WIDTH=400;
	public static final int HEIGHT=300;
	public static final int SCALE=2;	
	Boolean flag = true; //게임작동여부를 결정하는 변수
	Thread thread; //게임 운영 쓰레드
	Player player;
	KeyBoard keyBoard;
	ObjectManager objectManager; //객체 명단 관리자.
	
	public GamePanel() {
		thread = new Thread(this); //this는 run()를 가지고 있는 주체.
		thread.start();
		
		init();
		
		//크기 지정
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
	}
	public void init(){
		//명단관리자 생성
		objectManager= new ObjectManager();
		
		//주인공등장시키기
		player = new Player(objectManager,ObjectId.Player,100, 200, 50, 50);
		objectManager.addObject(player);
		
		//적군등장
		Random r= new Random();
		
		for(int i =0; i<10;i++){
			int y = r.nextInt(HEIGHT*SCALE-50+1)+50;
			int x = r.nextInt((WIDTH*SCALE+500)-(WIDTH*SCALE+1));
			Enermy enermy = new Enermy(objectManager,ObjectId.Enermy,x, y, 30, 30);
			objectManager.addObject(enermy);
		}
		//패널과 키보드 리스너 연결
		addKeyListener(new KeyBoard(player));
		
	}
	
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		//player.render(g);
		for(int i=0; i<objectManager.list.size();i++){
			GameObject obj = objectManager.list.get(i);
			obj.render(g);
			
		}
	}
	
	public void run() {
		while(flag){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
	
			}
			//player.tick(); 이렇게 하지말고 오브젝트매니저를 활용해보자
			//오브젝트 매니져에 등록된 모든 ~~ 객체를 대상으로 tick()을 호출하자.
			for(int i=0; i<objectManager.list.size();i++){
				GameObject obj = objectManager.list.get(i);
				obj.tick();
				
			}
			
			repaint(); //paintComponent를 간접 호출...
			//총알의 tick, render호출
			//적군의 tick, render호출
			//아이템의 tick, render호출
		}
		
	}
}
