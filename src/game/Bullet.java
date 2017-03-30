package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Bullet extends GameObject{
						//is a
/*	int x;
	int y;
	int width;
	int height;
	int velX;
	int velY;*/
	
	public Bullet(ObjectManager objectManager,ObjectId id,int x, int y, int width, int height) {
		super(objectManager,id,x,y,width,height);
		velX=4;

	}
	
	public void tick(){
		x+=velX;
		rect.setBounds(x, y, width, height);
		
		//������ ���� �����ϸ�, �Ѵ� �ױ�..
		for(int i=0; i<objectManager.list.size();i++){
			GameObject obj = objectManager.list.get(i);
			if(obj.id==ObjectId.Enermy){
				if(obj.rect.intersects(rect)){
				//���װ� ������..
				objectManager.list.remove(obj); //���װ�
				objectManager.list.remove(this);//������  //�Ѿ��״°�
				}
			}
		}
	}
	
	public void render(Graphics g){
		g.setColor(Color.yellow);
		//g.fillOval(x, y, width, height);
		Graphics2D g2=(Graphics2D)g;
		g2.fillOval(x, y, width, height);
		
	}
}
